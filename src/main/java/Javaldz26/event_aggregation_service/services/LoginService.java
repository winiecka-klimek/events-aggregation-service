package Javaldz26.event_aggregation_service.services;

import Javaldz26.event_aggregation_service.dao.UserRepository;
import Javaldz26.event_aggregation_service.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Slf4j
@SessionScope
@Service
public class LoginService implements InitializingBean {

    private final UserRepository userRepository;

    private UUID uuid;

    private boolean logged;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.uuid = UUID.randomUUID();
        this.logged = false;
    }

    public void loginUser(String email, String password) {
        log.info("LoginService loginUser: {} / {}", email, uuid.toString());

        final User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserDoesntExistException(email));

        if(!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException("invalid password");
        }

        this.userSessionDto = new UserSessionDto(user.getEmail());
        this.logged = true;
    }

    public boolean isLogged() {
        return logged;
    }

    public void logout() {
        this.logged = false;
        this.userSessionDto = null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("LoginService afterPropertiesSet: {}", uuid.toString());
    }
}
