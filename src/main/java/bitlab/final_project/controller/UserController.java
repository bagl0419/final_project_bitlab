package bitlab.final_project.controller;

import bitlab.final_project.dto.UserCreate;
import bitlab.final_project.dto.UserUpdate;
import bitlab.final_project.dto.UserView;
import bitlab.final_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/current-user")
    public ResponseEntity<UserView> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @GetMapping("{id}")
    public UserView getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserView> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserView createUser(@RequestBody UserCreate userCreate) {
        return userService.createUser(userCreate);
    }

    @PutMapping
    public UserView updateUser(@RequestBody UserUpdate userUpdate) {
        return userService.updateUser(userUpdate);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
