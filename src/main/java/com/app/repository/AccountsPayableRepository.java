package com.app.repository;

import com.app.domain.AccountsPayable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsPayableRepository extends JpaRepository<AccountsPayable, Long> {
}
