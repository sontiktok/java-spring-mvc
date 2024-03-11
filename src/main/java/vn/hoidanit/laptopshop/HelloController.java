package vn.hoidanit.laptopshop;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {
    @GetMapping("/")
    public String index() {
        return "Hello World - Nguyen The Son";
    }

    @GetMapping("/user")
    public String userPage() {
        return "Only user access page";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "Only admin";
    }
}
