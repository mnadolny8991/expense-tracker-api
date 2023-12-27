package michaln.expensetracker.config.command_runner;

import michaln.expensetracker.model.AccountDetails;
import michaln.expensetracker.model.Role;
import michaln.expensetracker.model.User;
import michaln.expensetracker.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminAdder implements CommandLineRunner {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public AdminAdder(PasswordEncoder encoder,
                      UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User admin = new User();
        admin.setEmail("admin.admin@gmail.com");
        admin.setLogin("admin");
        admin.setPassword(encoder.encode("admin"));
        AccountDetails adminDetails = new AccountDetails();
        adminDetails.setEnabled(true);
        adminDetails.setLocked(false);
        adminDetails.setRole(Role.ROLE_ADMIN);
        admin.setDetails(adminDetails);
        userRepository.save(admin);
    }
}
