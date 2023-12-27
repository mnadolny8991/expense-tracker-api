package michaln.expensetracker.service;

import michaln.expensetracker.mapper.UserDtoMapper;
import michaln.expensetracker.model.User;
import michaln.expensetracker.model.dto.UserInputDto;
import michaln.expensetracker.repository.UserRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public UserService(UserRepository userRepository,
                       UserDtoMapper userDtoMapper,
                       PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }

    public HttpEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public HttpEntity<User> getUser(Long id) {
        var userOpt = userRepository.findById(id);
        return userOpt
                .<HttpEntity<User>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public HttpEntity<?> addUser(UserInputDto userDto) {
        User user = userDtoMapper.fromDto(userDto);
        userRepository.save(user);
        return ResponseEntity.created(URI.create("/" + user.getId())).build();
    }

    @Transactional
    public HttpEntity<?> updateUser(UserInputDto userDto, Long id) {
        var userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOpt.get();
        userDtoMapper.updateFromDto(user, userDto);
        return ResponseEntity.ok(user);
    }

    public HttpEntity<?> deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
