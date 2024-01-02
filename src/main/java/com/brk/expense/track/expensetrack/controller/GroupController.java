package com.brk.expense.track.expensetrack.controller;

import com.brk.expense.track.expensetrack.dataobject.GroupDO;
import com.brk.expense.track.expensetrack.model.Group;
import com.brk.expense.track.expensetrack.service.GroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
        @Autowired
        private GroupService groupService;

        @GetMapping("/{groupId}")
        public ResponseEntity<GroupDO> getGroupById(@PathVariable Long groupId) {
            GroupDO group = groupService.getGroupById(groupId);
            return new ResponseEntity<>(group, HttpStatus.OK);
        }

        @GetMapping
        public ResponseEntity<List<GroupDO>> getAllGroups() {
            List<GroupDO> groups = groupService.getAllGroups();
            return new ResponseEntity<>(groups, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<GroupDO> createGroup(@RequestBody GroupDO groupDO) {

            GroupDO savedGroup = groupService.saveGroup(groupDO);
            return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
        }

        @DeleteMapping("/{groupId}")
        public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId) {
            groupService.deleteGroup(groupId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
