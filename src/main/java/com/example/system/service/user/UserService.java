package com.example.system.service.user;

import com.example.system.dto.userdto.UserDto;
import com.example.system.model.user.Role;
import com.example.system.model.user.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService {
    List<UserDto> getUserList();
    UserDto getProfile(String email);
    UserDto updateProfile(UserDto dto, String email);
    UserDto updateRole(Long id, String role);
    UserDto getUserLoginFromJWT(HttpServletRequest request);
}
