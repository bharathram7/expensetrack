package com.brk.expense.track.expensetrack.service;

import com.brk.expense.track.expensetrack.dao.GroupRepository;
import com.brk.expense.track.expensetrack.dataobject.GroupDO;
import com.brk.expense.track.expensetrack.dataobject.UserDO;
import com.brk.expense.track.expensetrack.model.Group;
import com.brk.expense.track.expensetrack.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public GroupDO getGroupById(Long groupId) {
        GroupDO groupDO =null;
        Optional<Group> group = groupRepository.findById(groupId);
        if(group.isPresent()) {
            groupDO = new GroupDO();
            BeanUtils.copyProperties(group.get(), groupDO);
            Set<UserDO> userlist = new HashSet<>();
            for (User u : group.get().getMembers()){
                UserDO userDO = new UserDO();
                BeanUtils.copyProperties(u, userDO);
                userlist.add(userDO);
            }
            groupDO.setMembers(userlist);
        }
        return groupDO;
    }

    public List<GroupDO> getAllGroups() {
        List<GroupDO> groupDOList =null;
        List<Group> groupList =  groupRepository.findAll();
        if(!groupList.isEmpty()) {
            groupDOList = groupList.stream().map(e -> {
                GroupDO groupDO = new GroupDO();
                BeanUtils.copyProperties(e, groupDO);
                Set<UserDO> userlist = new HashSet<>();
                for (User u : e.getMembers()){
                    UserDO userDO = new UserDO();
                    BeanUtils.copyProperties(u, userDO);
                    userlist.add(userDO);
                }
                groupDO.setMembers(userlist);
                return groupDO;}).collect(Collectors.toList());

        }
        return groupDOList;
    }

    public GroupDO saveGroup(GroupDO groupDO) {
        Group group = new Group();
        BeanUtils.copyProperties(groupDO,group);
        Set<User> users = new HashSet<>();
        for (UserDO u : groupDO.getMembers()){
            User user = new User();
            BeanUtils.copyProperties(u, user);
            users.add(user);
        }
        group.setMembers(users);
        Group groupResult = groupRepository.save(group);
        groupDO = new GroupDO();
        BeanUtils.copyProperties(groupResult,groupDO);
        Set<UserDO> userlist = new HashSet<>();
        for (User u : groupResult.getMembers()){
            UserDO userDO = new UserDO();
            BeanUtils.copyProperties(u, userDO);
            userlist.add(userDO);
        }
        groupDO.setMembers(userlist);
        return groupDO;
    }

    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }
}
