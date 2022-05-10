package pl.ml;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.ml.user.*;

import java.security.Principal;

@Controller
public class HomeController {
    private UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("loggedIn", true);
            model.addAttribute("username", principal.getName());
        } else {
            model.addAttribute("loggedIn", false);
        }
        return "home";
    }

    @GetMapping("/tajne")
    public String secure(Model model) {
        model.addAttribute("loggedIn", true);
        return "secure";
    }
}