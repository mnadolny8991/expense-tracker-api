package michaln.expensetracker.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityUserDetailsService securityUserDetailsService;
    private final PasswordEncoder encoder;

    public SecurityConfig(SecurityUserDetailsService securityUserDetailsService,
                          PasswordEncoder encoder) {
        this.securityUserDetailsService = securityUserDetailsService;
        this.encoder = encoder;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(securityUserDetailsService);
        provider.setPasswordEncoder(encoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(c -> c.disable())
                .anonymous(Customizer.withDefaults())
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/users/register").permitAll()
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .requestMatchers("/categories/**").hasRole("ADMIN")
                        .requestMatchers(toH2Console()).hasRole("ADMIN"))
                .authenticationProvider(daoAuthenticationProvider())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
