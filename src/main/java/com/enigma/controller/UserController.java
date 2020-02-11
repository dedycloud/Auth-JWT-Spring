package com.enigma.controller;

import com.enigma.entities.User;
import com.enigma.response.CustomResponse;
import com.enigma.security.Login;
import com.enigma.security.MyUserDetailService;
import com.enigma.security.UserPrincipal;
import com.enigma.service.UserService;
import com.enigma.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jdk.nashorn.internal.parser.Token;
import jdk.nashorn.internal.parser.TokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@PermitAll
@RestController
public class UserController {
private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetailService myUserDetailsService;


    @CrossOrigin
    @GetMapping("/users")
    @RolesAllowed({"admin", "customer"})
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @CrossOrigin
    @GetMapping("/user/{idUser}")
    public User getUserById(@PathVariable String idUser){
        return userService.getUserById(idUser);
    }
    @CrossOrigin
    @PostMapping("/user")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CustomResponse saveUser(@RequestBody User user){
        try {
         User users =  userService.saveUser(user);
         return  new  CustomResponse<>("201","create success",users);
     } catch (Exception e){
         LOGGER.error(e.getMessage());
         return new CustomResponse<>("500",e.getMessage());
     }
}
    @CrossOrigin
    @DeleteMapping("/user/{idUser}")
    public void deleteUserById(@PathVariable String idUser){
        userService.deleteUserById(idUser);
    }

    @CrossOrigin
    @RolesAllowed({"admin", "customer"})
    @GetMapping("/users/{role}")
    public Page<User> getAllUserByRole(@PathVariable String role, @RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return userService.getAllUserByRole(role, pageable);
    }
    @CrossOrigin
    @PostMapping("/user-doctor")
    public User saveUserDoctorWithImage(@RequestPart MultipartFile picture, @RequestPart String user) throws JsonProcessingException {
        return userService.saveUserDoctorWithImage(picture, user);
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserPrincipal userDetails = myUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
      Login login = new Login(jwt, userDetails.getUser().getRole(),userDetails.getUser().getIdUser(), userDetails.getUser().getFullName());
       // Login login = new Login(jwt);
//        final UserDetails userDetails = myUserDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());
//        final String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(login);

    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
