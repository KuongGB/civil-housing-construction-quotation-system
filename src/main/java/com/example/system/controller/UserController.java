package com.example.system.controller;


import com.example.system.dto.userdto.UserDto;
import com.example.system.model.user.Role;
import com.example.system.repository.user.UserRepository;
import com.example.system.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> getUsers(){
        List<UserDto> userDtos = userService.getUserList();
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile(@RequestParam String email){
        UserDto profile = userService.getProfile(email);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile/update")
    public ResponseEntity<UserDto> updateProfile(@RequestBody UserDto dto, @RequestParam String email){
        UserDto profile = userService.updateProfile(dto, email);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/update-role")
    public ResponseEntity<UserDto> updateRole(@RequestParam Long userId, @RequestParam String role){
        UserDto update = userService.updateRole(userId, role);
        return ResponseEntity.ok(update);
    }

    @GetMapping("/userlogin")
    public ResponseEntity<UserDto> getUserLogin(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getUserLoginFromJWT(request));
    }
}
