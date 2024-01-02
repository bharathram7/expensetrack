package com.brk.expense.track.expensetrack.dao;

import com.brk.expense.track.expensetrack.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    // custom queries if needed
}
