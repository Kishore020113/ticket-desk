package ticket.com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ticket.com.example.demo.User;
import ticket.com.example.demo.dto.LoginRequest;
import ticket.com.example.demo.dto.RegisterRequest;
import ticket.com.example.demo.repository.UserRepository;
import ticket.com.example.demo.security.CustomerUserDetailsService;
import ticket.com.example.demo.security.JwtUtil;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController{
    private final CustomerUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest login){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(),login.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(login.getEmail());
        return jwtUtil.generateToken(userDetails);
    }

}