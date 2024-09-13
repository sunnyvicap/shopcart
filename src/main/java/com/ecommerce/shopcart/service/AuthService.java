package com.ecommerce.shopcart.service;


import com.ecommerce.shopcart.dto.SignInDTO;
import com.ecommerce.shopcart.dto.SignUpDTO;
import com.ecommerce.shopcart.dto.UserDTO;
import com.ecommerce.shopcart.exceptions.BadRequestException;
import com.ecommerce.shopcart.exceptions.ResourceNotFoundException;
import com.ecommerce.shopcart.model.Role;
import com.ecommerce.shopcart.model.User;
import com.ecommerce.shopcart.repository.RoleRepository;
import com.ecommerce.shopcart.repository.UserRepository;
import com.ecommerce.shopcart.security.UserDetailsImpl;
import com.ecommerce.shopcart.security.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ModelMapper mapper;

    public ResponseEntity<UserDTO> signInUser(SignInDTO signIn) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        } catch (AuthenticationException e) {
            log.debug(e.getMessage());
            throw e;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        UserDTO userDTO = mapper.map(userDetails, UserDTO.class);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(userDTO);
    }

    public UserDTO signUpUser(SignUpDTO signUp) {
        if (userRepository.existsByUserName(signUp.getEmail())) {
            throw new BadRequestException("Username is already exists.", "", signUp.getEmail());
        }

        if (userRepository.existsByEmail(signUp.getEmail())) {
            throw new BadRequestException("Email is already exists.", "", signUp.getEmail());
        }
        // Create new user's account
        User user = new User();
        user.setUserName(signUp.getEmail());
        user.setEmail(signUp.getEmail());
        user.setPassword(encoder.encode(signUp.getPassword()));

        Iterable<Long> roleIds = signUp.getRoles();
        List<Role> roleList = roleRepository.findAllById(roleIds);
        if (roleList.isEmpty()) {
            throw new ResourceNotFoundException("Roles not found", "ids are invalid");
        }
        user.setRoles(roleList);
        user = userRepository.save(user);
        return mapper.map(user, UserDTO.class);
    }

    public UserDTO getUserDetails(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return mapper.map(userDetails, UserDTO.class);
    }

    public ResponseEntity<String> signOutUser() {
        ResponseCookie cookie = jwtUtils.cleanCookie();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("User signed out successfully.");
    }
}
