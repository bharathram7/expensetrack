package com.brk.expense.track.expensetrack.dataobject;

import com.brk.expense.track.expensetrack.model.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

public class GroupDO {

    private Long id;
    private String name;
    private Set<UserDO> members;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserDO> getMembers() {
        return members;
    }

    public void setMembers(Set<UserDO> members) {
        this.members = members;
    }
}
