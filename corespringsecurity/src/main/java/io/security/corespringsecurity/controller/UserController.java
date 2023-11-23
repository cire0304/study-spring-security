package io.security.corespringsecurity.controller;

import io.security.corespringsecurity.domain.Account;
import io.security.corespringsecurity.domain.AccountDto;
import io.security.corespringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/mypage")
    public String myPage() {
        return "mypage";
    }

    @GetMapping("/users")
    public String createUser() {
        return "su";
    }

    @PostMapping("/users")
    public String createUser(@RequestBody AccountDto accountDto) {
        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountDto, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        userService.createUser(account);
        return "sussess";
    }
}
