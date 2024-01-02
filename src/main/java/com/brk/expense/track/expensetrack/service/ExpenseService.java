package com.brk.expense.track.expensetrack.service;

import com.brk.expense.track.expensetrack.dao.ExpenseRepository;
import com.brk.expense.track.expensetrack.dataobject.ExpenseDO;
import com.brk.expense.track.expensetrack.dataobject.GroupDO;
import com.brk.expense.track.expensetrack.dataobject.UserDO;
import com.brk.expense.track.expensetrack.model.Expense;
import com.brk.expense.track.expensetrack.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public ExpenseDO getExpenseById(Long expenseId) {
        ExpenseDO expenseDO = null;
        Optional<Expense> expense =  expenseRepository.findById(expenseId);
        if(expense.isPresent()) {
            expenseDO= getExpenseDO(expense.get());
        }
        return expenseDO;
    }

    public List<ExpenseDO> getExpenseByGroupId(Long groupId) {
        List<ExpenseDO> expenseDOList= null;
        List<Expense> expenses =  expenseRepository.findByGroupId(groupId);
        if(expenses !=null && !expenses.isEmpty()) {
            expenseDOList= expenses.stream().map(e -> {
                return getExpenseDO(e);
            }).collect(Collectors.toList());
        }
        return expenseDOList;
    }

    public List<ExpenseDO> getAllExpenses() {
        List<ExpenseDO> expenseDOList= null;
        List<Expense> expenses =  expenseRepository.findAll();
        if(expenses !=null && !expenses.isEmpty()) {
            expenseDOList= expenses.stream().map(e -> {
                return getExpenseDO(e);
            }).collect(Collectors.toList());
        }
        return expenseDOList;
    }

    public ExpenseDO saveExpense(ExpenseDO expenseDO) {
        Expense expense= new Expense();
        BeanUtils.copyProperties(expenseDO,expense);

        Expense expens = expenseRepository.save(expense);
        return getExpenseDO(expens);
    }

    private static ExpenseDO getExpenseDO(Expense expense) {
        ExpenseDO expenseDO;
        expenseDO = new ExpenseDO();
        BeanUtils.copyProperties(expense,expenseDO);
        UserDO payerDO = new UserDO();
        BeanUtils.copyProperties(expense.getGroup(), payerDO);
        expenseDO.setPayer(payerDO);
        GroupDO groupDO = new GroupDO();
        BeanUtils.copyProperties(expense.getGroup(), groupDO);
        Set<UserDO> memberlist = new HashSet<>();
        for (User u : expense.getGroup().getMembers()){
            UserDO userDO = new UserDO();
            BeanUtils.copyProperties(u, userDO);
            memberlist.add(userDO);
        }
        groupDO.setMembers(memberlist);
        expenseDO.setGroup(groupDO);
        Set<UserDO> userlist = new HashSet<>();
        for (User u : expense.getParticipants()){
            UserDO userDO = new UserDO();
            BeanUtils.copyProperties(u, userDO);
            userlist.add(userDO);
        }
        expenseDO.setParticipants(userlist);
        return expenseDO;
    }

    public void deleteExpense(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }
}
