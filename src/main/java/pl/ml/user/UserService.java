package pl.ml.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserRoleService userRoleService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
    }

    public void registerUser(String username, String rawPassword, String email, String firstName, String lastName) {
        User userToAdd = new User();
        userToAdd.setUsername(username);
        String encryptedPassword = passwordEncoder.encode(rawPassword);
        userToAdd.setPassword(encryptedPassword);
        userToAdd.setFirstName(firstName);
        userToAdd.setLastName(lastName);
        userToAdd.setEmail(email);
        userRepository.save(userToAdd);
    }

    public Optional<User> findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void updateUser(User user, String firstName, String lastName, String email, String password) {
        if (firstName != null) {
            user.setFirstName(firstName);
            save(user);
        }
        if (lastName != null) {
            user.setLastName(lastName);
            save(user);
        }
        if (email != null) {
            user.setEmail(email);
            save(user);
        }
        if (password != null) {
            String encryptedPassword = passwordEncoder.encode(password);
            user.setPassword(encryptedPassword);
            save(user);
        }
    }

    public void changeRole(Role userRole, String username, User user) {
        if (userRole.getName().equals(Role.ROLE_ADMIN.getName())) {
            userRoleService.save(new UserRole(user, Role.ROLE_ADMIN));
        } else {
            userRoleService.delete(userRoleService.findByRoleAndUsername(Role.ROLE_ADMIN, username));
        }
    }
}
