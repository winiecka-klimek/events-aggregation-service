package Javaldz26.event_aggregation_service.services;

import Javaldz26.event_aggregation_service.dao.RoleRepository;
import Javaldz26.event_aggregation_service.dao.UserRepository;
import Javaldz26.event_aggregation_service.dtos.RegisteredUserDto;
import Javaldz26.event_aggregation_service.dtos.request.NewUserForm;
import Javaldz26.event_aggregation_service.entities.Role;
import Javaldz26.event_aggregation_service.entities.User;
import Javaldz26.event_aggregation_service.exceptions.UserWithSuchEmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public RegisterService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public RegisteredUserDto registerUser(NewUserForm newUserForm) {

        final boolean existsByEmail = userRepository.existsByEmail(newUserForm.getEmail());

        if(existsByEmail) {
           throw new UserWithSuchEmailExistsException(newUserForm.getEmail());
        }

        final String roleName = "ROLE_REGULAR_USER";
        final Role role = roleRepository
                .findRoleByRoleName(roleName)
                .orElseGet(() -> roleRepository.save(new Role(roleName)));

        final User user = new User();
        user.setEmail(newUserForm.getEmail());
        user.setNickname(newUserForm.getNickname());
        user.setPassword(passwordEncoder.encode(newUserForm.getPassword()));
        user.addRole(role);

        userRepository.save(user);

        return null;
    }
}
