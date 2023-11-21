package io.security.basicsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/")
    public String index() {
        return "heelo";
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/denied")
    public String denied() {
        return "loginPage";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }
    @GetMapping("/admin/pay")
    public String adminPay() {
        return "loginPage";
    }
    @GetMapping("/admin/**")
    public String admin() {
        return "loginPage";
    }


}
