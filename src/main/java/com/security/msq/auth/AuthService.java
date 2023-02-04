package com.security.msq.auth;

import com.security.msq.config.JwtTokenUtil;
import com.security.msq.users.UserRepository;
import com.security.msq.users.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired private UserRepository userRepository;
    private JwtTokenUtil jwtTokenUtil;

    private AuthenticationManager authenticationManager;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtTokenUtil jwtTokenUtil){
        this.passwordEncoder = passwordEncoder;
        this.userRepository =userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     *
     * @param request
     * @return
     */
    public AuthResponse register(RegisterRequest request){
        Usuario user  = new Usuario();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        userRepository.save(user);
        AuthResponse ar = new AuthResponse();
        ar.setToken(jwtTokenUtil.generateToken(user));
        System.out.println("token " + ar.getToken());
        return ar;
    }

    public AuthResponse authenticate(AuthRequest authRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        Usuario usuario = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow();
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtTokenUtil.generateToken(usuario));
        return authResponse;
    }

}
