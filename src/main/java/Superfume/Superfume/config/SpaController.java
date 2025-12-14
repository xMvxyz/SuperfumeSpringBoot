package Superfume.Superfume.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaController {
    
    @GetMapping(value = {"", "/", "/home", "/shop", "/about", "/contact", "/login", "/register", "/cart", "/admin", "/quiz", "/product/**"})
    public String forward() {
        return "forward:/index.html";
    }
}
