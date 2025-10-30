package be.iccbxl.pid.reservations_springboot.controller;


import be.iccbxl.pid.reservations_springboot.dto.UserRegistrationDto;
import be.iccbxl.pid.reservations_springboot.service.UserService;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "authentication/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserRegistrationDto dto, 
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Erreurs de validation !");
            return "authentication/register";
        }

        // Vérification de doublons
        if (!userService.isLoginAndEmailAvailable(dto.getLogin(), dto.getEmail())) {
            model.addAttribute("errorMessage", "Email ou login déjà utilisé !");
            return "authentication/register";
        }

        userService.registerFromDto(dto);
        model.addAttribute("successMessage", "Inscription réussie !");
        return "redirect:login";
    }
}