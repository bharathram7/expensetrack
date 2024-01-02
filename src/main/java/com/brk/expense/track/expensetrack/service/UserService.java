package com.brk.expense.track.expensetrack.service;

import com.brk.expense.track.expensetrack.dao.UserRepository;
import com.brk.expense.track.expensetrack.dataobject.UserDO;
import com.brk.expense.track.expensetrack.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDO getUserById(Long userId) {
        UserDO userDO = new UserDO();

        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        BeanUtils.copyProperties(user,userDO);
        return userDO;
    }

    public List<UserDO> getAllUsers() {
        List<UserDO> userList = new ArrayList<>();

        List<User> users = userRepository.findAll();

        users.stream().forEach(u -> { UserDO userDO = new UserDO(); BeanUtils.copyProperties(u,userDO); userList.add(userDO);});
        return userList;
    }

    public UserDO saveUser(UserDO userDO) {

        User user = new User();
        BeanUtils.copyProperties(userDO,user);

        user = userRepository.save(user);
        userDO = new UserDO();
        BeanUtils.copyProperties(user,userDO);

        return userDO;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
