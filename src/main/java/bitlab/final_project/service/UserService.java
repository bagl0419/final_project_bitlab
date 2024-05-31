package bitlab.final_project.service;
import java.util.Collections;
import java.util.Optional;

import bitlab.final_project.entity.Role;
import bitlab.final_project.entity.User;
import bitlab.final_project.repository.RoleRepository;
import bitlab.final_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        } else {
            return (User) authentication.getPrincipal();
        }
    }

    public String create(User user, String rePassword) {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent()) {
            return "Username is busy!";
        }

        if (!user.getPassword().equals(rePassword)) {
            return "Passwords are not the same!";
        }

        final String encodedPassword = passwordEncoder.encode(rePassword);
        user.setPassword(encodedPassword);

        Role userRole = roleRepository.findRoleUser();
        user.setRoles(Collections.singletonList(userRole));

        userRepository.save(user);
        return "Account created successfully!";
    }

    public String changePassword(String currentPassword, String newPassword, String reNewPassword) { // qwe, qwerty, qwerty
        boolean correctPassword = passwordEncoder.matches(currentPassword, getCurrentUser().getPassword());
        if (!correctPassword) { // проверка текущего пароля
            return "Incorrect current password!";
        }

        if (!newPassword.equals(reNewPassword)) { // сравнение новых паролей
            return "Passwords are not the same!";
        }

        getCurrentUser().setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(getCurrentUser());

        return "Password successfully changed!";
    }
}