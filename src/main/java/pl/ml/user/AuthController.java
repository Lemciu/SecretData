package pl.ml.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
public class AuthController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private UserRoleService userRoleService;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder, UserRoleService userRoleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/logowanie")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/pierwsze-logowanie")
    public String loginFormFromRegister(Model model) {
        model.addAttribute("firstLogin", true);
        return "loginForm";
    }

    @GetMapping("/rejestracja")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "registerForm";
    }

    @PostMapping("/rejestracja")
    public String register(User user) {
        String username = user.getUsername();
        String rawPassword = user.getPassword();
        String email = user.getEmail();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        userService.registerUser(username, rawPassword, email, firstName, lastName);
        return "redirect:/pierwsze-logowanie";
    }

    @PostMapping("/logowanie")
    public String login() {
        return "redirect:/";
    }

    @GetMapping("/mojeKonto")
    public String myAccount(Principal principal, Model model) {
        Optional<User> optionalUser = userService.findByUsername(principal.getName());
        User user = optionalUser.orElseThrow();
        model.addAttribute("currentUser", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", Role.values());
        return "edit";
    }

    @PostMapping("/save")
    public String editAccount(@RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String password,
                              Principal principal) {
        Optional<User> optionalUser = userService.findByUsername(principal.getName());
        User user = optionalUser.orElseThrow();
        userService.updateUser(user, firstName, lastName, email, password);

        return "redirect:/mojeKonto";
    }

    @PostMapping("/giveRole")
    public String giveRole(Role userRole, String username) {
        System.out.println(userRole.getName());
        Optional<User> optionalUser = userService.findByUsername(username);
        User user = optionalUser.orElseThrow();
        userService.changeRole(userRole, username, user);
        userService.save(user);
        return "redirect:/mojeKonto";
    }

}
