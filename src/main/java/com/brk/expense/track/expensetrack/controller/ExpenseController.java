package com.brk.expense.track.expensetrack.controller;

import com.brk.expense.track.expensetrack.dataobject.ExpenseDO;
import com.brk.expense.track.expensetrack.model.Expense;
import com.brk.expense.track.expensetrack.service.ExpenseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseDO> getExpenseById(@PathVariable Long expenseId) {
        ExpenseDO expense = expenseService.getExpenseById(expenseId);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<ExpenseDO>> getExpenseByGroupId(@PathVariable Long groupId) {
        List<ExpenseDO> expense = expenseService.getExpenseByGroupId(groupId);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDO>> getAllExpenses() {
        List<ExpenseDO> expenses = expenseService.getAllExpenses();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExpenseDO> createExpense(@RequestBody ExpenseDO expenseDO) {

        ExpenseDO savedExpense = expenseService.saveExpense(expenseDO);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long expenseId) {
        expenseService.deleteExpense(expenseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
