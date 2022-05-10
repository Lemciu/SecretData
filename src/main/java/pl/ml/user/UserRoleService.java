package pl.ml.user;

import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    private UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    public void delete(UserRole userRole) {
        userRoleRepository.delete(userRole);
    }

    public UserRole findByRoleAndUsername(Role role, String username) {
        return userRoleRepository.findByRoleAndUser_Username(role, username);
    }
}
