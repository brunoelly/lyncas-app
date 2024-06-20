package com.app.controller;

import com.app.domain.AccountsPayable;
import com.app.domain.MultipartFile;
import com.app.domain.Status;
import com.app.service.AccountsPayableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        AccountsPayable savedpayableAccount = payableAccountService.save(payableAccount);
        return ResponseEntity.ok(savedpayableAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountsPayable> update(@PathVariable Long id, @RequestBody AccountsPayable payableAccount) {
        Optional<AccountsPayable> existingpayableAccount = payableAccountService.findById(id);
        if (existingpayableAccount.isPresent()) {
            payableAccount.setId(id);
            AccountsPayable updatedpayableAccount = payableAccountService.save(payableAccount);
            return ResponseEntity.ok(updatedpayableAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<AccountsPayable> updatestatus(@PathVariable Long id, @RequestParam Status status) {
        Optional<AccountsPayable> existingpayableAccount = payableAccountService.findById(id);
        if (existingpayableAccount.isPresent()) {
            AccountsPayable payableAccount = existingpayableAccount.get();
            payableAccount.setStatus(status);
            AccountsPayable updatedpayableAccount = payableAccountService.save(payableAccount);
            return ResponseEntity.ok(updatedpayableAccount);
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

    @GetMapping("/total-pago")
    public ResponseEntity<BigDecimal> getTotalPago(@RequestParam LocalDate inicio, @RequestParam LocalDate fim) {
        BigDecimal totalPago = payableAccountService.getTotalPagoPorPeriodo(inicio, fim);
        return ResponseEntity.ok(totalPago);
    }

    @PostMapping("/importar")
    public ResponseEntity<Void> importarCSV(@RequestParam("file") MultipartFile file) {
        // Implementar lógica de importação de CSV
        return ResponseEntity.ok().build();
    }
}
