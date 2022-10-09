package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.UserPrincipal;
import com.example.model.Token;
import com.example.model.User;
import com.example.service.TokenService;
import com.example.service.UserService;
import com.example.utils.JwtUtil;


@RestController
public class AuthController {
	@Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    
    @Autowired
    private TokenService tokenService;
	
	@PostMapping("/register")
	public User register(@RequestBody User user) throws Exception{
		
		System.out.print("register");
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return userService.createUser(user);
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
		
		System.out.println(user.toString());
		
        UserPrincipal userPrincipal =
                userService.findByUsername(user.getUsername());

        if (null == user || !new BCryptPasswordEncoder()
                .matches(user.getPassword(), userPrincipal.getPassword())) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Account or password is not valid!");
        }

        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));

        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setCreatedBy(userPrincipal.getUserId());
        tokenService.createToken(token);

        return ResponseEntity.ok(token.getToken());
    }


    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('USER_READ')")
    public ResponseEntity hello() throws Exception{
    	
        return ResponseEntity.ok("hello");
    }
}
