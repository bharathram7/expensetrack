package com.brk.expense.track.expensetrack.dao;

import com.brk.expense.track.expensetrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // custom queries if needed
}
