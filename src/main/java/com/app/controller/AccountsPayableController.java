package com.app.controller;

import com.app.domain.AccountsPayable;
import com.app.domain.MultipartFile;
import com.app.domain.Status;
import com.app.service.AccountsPayableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/payableAccounts")
public class AccountsPayableController {

    @Autowired
    private AccountsPayableService payableAccountService;

    @PostMapping
    public ResponseEntity<AccountsPayable> create(@RequestBody AccountsPayable payableAccount) {
        AccountsPayable savedPayableAccount = payableAccountService.save(payableAccount);
        return ResponseEntity.ok(savedPayableAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountsPayable> update(@PathVariable Long id, @RequestBody AccountsPayable payableAccount) {
        Optional<AccountsPayable> existingPayableAccount = payableAccountService.findById(id);
        if (existingPayableAccount.isPresent()) {
            payableAccount.setId(id);
            AccountsPayable updatedPayableAccount = payableAccountService.save(payableAccount);
            return ResponseEntity.ok(updatedPayableAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<AccountsPayable> updateStatus(@PathVariable Long id, @RequestParam Status status) {
        Optional<AccountsPayable> existingPayableAccount = payableAccountService.findById(id);
        if (existingPayableAccount.isPresent()) {
            AccountsPayable payableAccount = existingPayableAccount.get();
            payableAccount.setStatus(status);
            AccountsPayable updatedPayableAccount = payableAccountService.save(payableAccount);
            return ResponseEntity.ok(updatedPayableAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<AccountsPayable>> getAll(Pageable pageable) {
        Page<AccountsPayable> payableAccounts = payableAccountService.findAll(pageable);
        return ResponseEntity.ok(payableAccounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountsPayable> getById(@PathVariable Long id) {
        Optional<AccountsPayable> payableAccount = payableAccountService.findById(id);
        return payableAccount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/totalAmount")
    public ResponseEntity<BigDecimal> getTotalPago(@RequestParam LocalDate startDate, @RequestParam LocalDate fim) {
        BigDecimal paidAmount = payableAccountService.getTotalPagoPorPeriodo(startDate, fim);
        return ResponseEntity.ok(paidAmount);
    }

    @PostMapping("/import")
    public ResponseEntity<Void> importCSV(@RequestParam("file") MultipartFile file) {
        return null;
    }
}