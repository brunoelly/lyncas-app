package com.app.service;

import com.app.domain.AccountsPayable;
import com.app.repository.AccountsPayableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class AccountsPayableService {

    @Autowired
    private AccountsPayableRepository payableAccountRepository;

    public AccountsPayable save(AccountsPayable payableAccount) {
        return payableAccountRepository.save(payableAccount);
    }

    public Optional<AccountsPayable> findById(Long id) {
        return payableAccountRepository.findById(id);
    }

    public Page<AccountsPayable> findAll(Pageable pageable) {
        return payableAccountRepository.findAll(pageable);
    }

    public void deleteById(Long id) {
        payableAccountRepository.deleteById(id);
    }

    public BigDecimal getTotalPagoPorPeriodo(LocalDate inicio, LocalDate fim) {
        // Implementar lógica de cálculo do total pago por período
        return BigDecimal.ZERO;
    }
}
