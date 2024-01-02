package com.brk.expense.track.expensetrack.controller;

import com.brk.expense.track.expensetrack.dataobject.UserDO;
import com.brk.expense.track.expensetrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDO> getUserById(@PathVariable Long userId) {
        UserDO user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDO> getUserByEmail(@PathVariable String email) {
        List<UserDO> users = userService.getAllUsers();
        List<UserDO> userList = users.stream().filter(u-> u.getEmail().equalsIgnoreCase(email)).collect(Collectors.toList());
        ResponseEntity user  = userList.isEmpty() ? new ResponseEntity<>( HttpStatus.NO_CONTENT) : new ResponseEntity<>( userList.get(0), HttpStatus.OK);
        return user;
    }
}
