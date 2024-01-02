package com.brk.expense.track.expensetrack.dataobject;

import com.brk.expense.track.expensetrack.model.Group;
import com.brk.expense.track.expensetrack.model.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

public class ExpenseDO {

    private Long id;
    private String description;
    private BigDecimal amount;

    private UserDO payer;

    private GroupDO group;

    private Set<UserDO> participants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UserDO getPayer() {
        return payer;
    }

    public void setPayer(UserDO payer) {
        this.payer = payer;
    }

    public Set<UserDO> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<UserDO> participants) {
        this.participants = participants;
    }

    public GroupDO getGroup() {
        return group;
    }

    public void setGroup(GroupDO group) {
        this.group = group;
    }
}
