package com.cloud.music_cloud.Controllers;

import com.cloud.music_cloud.Auth.JwtUtil;
import com.cloud.music_cloud.DAOS.UserDAO;
import com.cloud.music_cloud.DTO.LoginDTO;
import com.cloud.music_cloud.DTO.RegisterDTO;
import com.cloud.music_cloud.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;  // ✅ Fix here

    @Autowired
    private UserDAO userDAO;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.username, loginDTO.password));
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.username);
        return jwtUtil.generateToken(userDetails.getUsername());
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO dto) {
        if (userDAO.existsByUsername(dto.getUsername())) {
            return ResponseEntity.badRequest().body("Username is taken");
        }

        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        Users user = new Users(0, dto.getUsername(), hashedPassword, dto.getEmail());
        userDAO.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
}
