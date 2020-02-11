package com.enigma.service;

import com.enigma.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User updateUser(User user);

    User getUserById(String idUser);

    User getUserByUsernameAndPassword(String username, String password);

    List<User> getAllUser();

    void deleteUserById(String idUser);

    Page<User> getAllUserByRole(String role, Pageable pageable);

    void deleteAllUser();

    User saveUserDoctorWithImage(MultipartFile multipartFile, String user) throws JsonProcessingException;
}
