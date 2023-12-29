package michaln.expensetracker.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInputDto {
    @NotBlank(message = "Login: login cannot be blank")
    private String login;
    @NotBlank(message = "Email: email cannot be blank")
    @Email(message = "Email: invalid format")
    private String email;
    @NotBlank(message = "Password: password cannot be blank")
    private String password;
    private Long userId;
}
