package michaln.expensetracker.controller;

import jakarta.validation.Valid;
import michaln.expensetracker.model.User;
import michaln.expensetracker.model.dto.UserInputDto;
import michaln.expensetracker.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public HttpEntity<List<User>> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public HttpEntity<User> getUser(@PathVariable(name = "id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/register")
    public HttpEntity<?> addUser(@RequestBody @Valid UserInputDto userDto) {
        return userService.addUser(userDto);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateUser(@RequestBody @Valid UserInputDto userDto,
                                    @PathVariable(name = "id") Long id) {
        return userService.updateUser(userDto, id);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        return userService.deleteUser(id);
    }
}
