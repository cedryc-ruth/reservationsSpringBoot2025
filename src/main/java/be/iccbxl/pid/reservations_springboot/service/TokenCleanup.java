package be.iccbxl.pid.reservations_springboot.service;

import be.iccbxl.pid.reservations_springboot.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import jakarta.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class TokenCleanup {
    private final PasswordResetTokenRepository tokenRepo;

    @Scheduled(cron = "0 0 * * * *") // toutes les heures
    @Transactional
    public void removeExpiredTokens() {
        tokenRepo.findAll().stream()
            .filter(token -> token.getExpiryDate().isBefore(java.time.LocalDateTime.now()))
            .forEach(token -> tokenRepo.delete(token));
    }
}