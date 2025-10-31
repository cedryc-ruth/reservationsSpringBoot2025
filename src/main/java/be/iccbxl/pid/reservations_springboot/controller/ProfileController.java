package be.iccbxl.pid.reservations_springboot.controller;


import be.iccbxl.pid.reservations_springboot.dto.UserProfileDto;
import be.iccbxl.pid.reservations_springboot.model.Language;
import be.iccbxl.pid.reservations_springboot.model.User;
import be.iccbxl.pid.reservations_springboot.repository.UserRepository;
import be.iccbxl.pid.reservations_springboot.service.UserService;
import jakarta.validation.Valid;

import java.util.Arrays;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    private final UserRepository userRepository;
    private final UserService userService;

    public ProfileController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    // Afficher la page de profil
    @GetMapping("/profile")
    public String showProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();

        User user = userRepository.findByLogin(login);

        if(user==null) {
            throw new RuntimeException("Utilisateur introuvable");
        }

        // Conversion User → UserProfileDto
        UserProfileDto dto = new UserProfileDto();
        dto.setId(user.getId());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setLangue(user.getLangue());
        dto.setLogin(user.getLogin());
        dto.setRole(user.getRole().getValue());
        
        //Conversion du code linguistique en nom de la langue
        Language userLanguage = Arrays.stream(Language.values())
            .filter(lang -> lang.toString().equals(user.getLangue().toUpperCase()))
            .findFirst().get();

        model.addAttribute("user_language", userLanguage!=null ? userLanguage.getDescription() : "Inconnue");
        model.addAttribute("user", dto);
        return "authentication/profile";
    }

    // Enregistrer les modifications
    @PostMapping(value = "/profile", params = {"edit"})
    public String updateProfile(
            @Valid @ModelAttribute("user") UserProfileDto dto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Erreurs de validation !");
            return "authentication/profile";
        }

        // Appel du service pour gérer mot de passe, etc.
        userService.updateUserFromDto(dto);
        model.addAttribute("successMessage", "Profil mis à jour avec succès !");
        return "redirect:profile";
    }
}