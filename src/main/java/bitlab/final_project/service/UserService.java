package bitlab.final_project.service;

import bitlab.final_project.dto.UserCreate;
import bitlab.final_project.dto.UserUpdate;
import bitlab.final_project.dto.UserView;
import bitlab.final_project.entity.Role;
import bitlab.final_project.entity.User;
import bitlab.final_project.mapper.UserMapper;
import bitlab.final_project.repository.RoleRepository;
import bitlab.final_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserView getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        } else {
            return (UserView) authentication.getPrincipal();
        }
    }

    public UserView getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return UserMapper.INSTANCE.toView(user);
    }

    public List<UserView> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.toView(users);
    }

    public UserView createUser(UserCreate userCreate) {

        logger.info("Начало создания пользователя: {}", userCreate.getUsername());

        Optional<User> userOptional = userRepository.findByUsername(userCreate.getUsername());
        if (userOptional.isPresent()) {
            logger.warn("Пользователь с таким username уже существует: {}", userCreate.getUsername());
            throw new RuntimeException("Пользователь с таким username уже существует!");
        }

        if (!userCreate.getPassword().equals(userCreate.getRePassword())) {
            logger.error("Введенные пароли не совпадают для пользователя: {}", userCreate.getUsername());
            throw new RuntimeException("Введеные пароли не совпадают!");
        }

        final String encodedPassword = passwordEncoder.encode(userCreate.getPassword());
        userCreate.setPassword(encodedPassword);

        Role userRole = roleRepository.findRoleUser();
        userCreate.setRoles(Collections.singletonList(userRole));

        User user = UserMapper.INSTANCE.toEntity(userCreate);
        User savedUser = userRepository.save(user);
        logger.info("Пользователь успешно создан: {}", userCreate.getUsername());
        return UserMapper.INSTANCE.toView(savedUser);
    }

    public UserView updateUser(UserUpdate userUpdate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User currentUser = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        String currentPassword = currentUser.getPassword();
        boolean correctPassword = passwordEncoder.matches(userUpdate.getPassword(), currentPassword);
        if (!correctPassword) {
            throw new RuntimeException("Введен неверный текущий пароль!");
        }

        if (!userUpdate.getNewPassword().equals(userUpdate.getReNewPassword())) {
            throw new RuntimeException("Введеные пароли не совпадают!");
        }

        currentUser.setPassword(passwordEncoder.encode(userUpdate.getNewPassword()));

        userRepository.save(currentUser);
        logger.info("Пароль успешно изменен для пользователя: {}", userUpdate.getUsername());
        return UserMapper.INSTANCE.toView(currentUser);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}