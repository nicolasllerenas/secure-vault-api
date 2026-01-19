package com.example.securevaultapi.controller;

import com.example.securevaultapi.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<?> transfer(@RequestBody Map<String, Object> request) {
        Long fromId = Long.valueOf(request.get("fromId").toString());
        Long toId = Long.valueOf(request.get("toId").toString());
        BigDecimal amount = new BigDecimal(request.get("amount").toString());

        transferService.transfer(fromId, toId, amount);
        return ResponseEntity.ok(Map.of("message", "Transferencia exitosa"));
    }
}