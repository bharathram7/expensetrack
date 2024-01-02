package com.brk.expense.track.expensetrack.dao;

import com.brk.expense.track.expensetrack.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // custom queries if needed
    List<Expense> findByGroupId(Long groupId);
}
