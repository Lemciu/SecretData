package pl.ml.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByRoleAndUser_Username(Role role, String username);
}
