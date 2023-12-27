package michaln.expensetracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_details")
public class AccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @NotNull
    private boolean locked;
    @NotNull
    private boolean enabled;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @OneToOne(mappedBy = "details")
    @JsonIgnore
    private User user;
}
