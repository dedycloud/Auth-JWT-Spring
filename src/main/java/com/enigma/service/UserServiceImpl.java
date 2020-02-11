package com.enigma.service;

import com.enigma.entities.User;
import com.enigma.exception.BadRequest;
import com.enigma.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ObjectMapper objectMapper;

    private void checkSaveUser(User user) throws BadRequest {
        if(StringUtils.isEmpty(user.getUsername())) throw new BadRequest("Data tidak ada");
        else if (StringUtils.isEmpty(user.getPassword())) throw new BadRequest("data tidak ada");
    }
    @Override
    public User saveUser(User user) {
        checkSaveUser(user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);

    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User getUserById(String idUser) {
        return null;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(String idUser) {

    }

    @Override
    public Page<User> getAllUserByRole(String role, Pageable pageable) {
        Page<User> user = userRepository.findAllByRoleLikeOrderByFullName(role, pageable);
        return user;
    }

    @Override
    public void deleteAllUser() {

    }

    @Override
    public User saveUserDoctorWithImage(MultipartFile multipartFile, String user) throws JsonProcessingException {
        return null;
    }
}
