package michaln.expensetracker.mapper;

import michaln.expensetracker.model.AccountDetails;
import michaln.expensetracker.model.Role;
import michaln.expensetracker.model.User;
import michaln.expensetracker.model.dto.UserInputDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDtoMapper {

    private final PasswordEncoder encoder;

    public UserDtoMapper(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    private AccountDetails getDefaultDetails(Role role) {
        AccountDetails details = new AccountDetails();
        details.setRole(role);
        details.setEnabled(true);
        details.setLocked(false);
        return details;
    }

    public User fromDto(UserInputDto userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setDetails(getDefaultDetails(Role.ROLE_USER));
        return user;
    }

    public void updateFromDto(User toUpdate, UserInputDto userDto) {
        toUpdate.setLogin(userDto.getLogin());
        toUpdate.setEmail(userDto.getEmail());
        toUpdate.setPassword(userDto.getPassword());
        toUpdate.setDetails(getDefaultDetails(Role.ROLE_USER));
    }
}
