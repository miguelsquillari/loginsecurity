package com.security.msq.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/auth")
public class AuthController {

    public AuthController() {}


    @Autowired
    private AuthService authService;

    @PostMapping("/authrequest")
    public ResponseEntity<AuthResponse> auth
            (@RequestBody RegisterRequest registerRequest){
        System.out.println("************ paso con controller");
        return ResponseEntity.ok(
                authService.register(registerRequest));
    }

}
