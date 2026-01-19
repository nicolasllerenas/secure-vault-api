package com.example.securevaultapi.service;

import com.example.securevaultapi.model.Account;
import com.example.securevaultapi.model.AuditLog;
import com.example.securevaultapi.repository.AccountRepository;
import com.example.securevaultapi.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final AccountRepository accountRepository;
    private final AuditLogRepository auditLogRepository;

    @Transactional // Garantiza Atomicidad (ACID)
    public void transfer(Long fromId, Long toId, BigDecimal amount) {
        // 1. Validaciones
        Account from = accountRepository.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada"));
        Account to = accountRepository.findById(toId)
                .orElseThrow(() -> new RuntimeException("Cuenta destino no encontrada"));

        if (from.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Fondos insuficientes");
        }

        // 2. Movimiento de dinero
        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        // 3. Persistencia
        accountRepository.save(from);
        accountRepository.save(to);

        // 4. Auditoría de Seguridad (Trazabilidad)
        AuditLog log = new AuditLog();
        log.setAction("TRANSFER");
        log.setFromAccount(from.getAccountNumber());
        log.setToAccount(to.getAccountNumber());
        log.setAmount(amount);
        log.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(log);
    }
}