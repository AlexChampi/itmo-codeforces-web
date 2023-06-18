package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.UserRegisterForm;
import ru.itmo.wp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByLoginAndPassword(String login, String password) {
        return login == null || password == null ? null : userRepository.findByLoginAndPassword(login, password);
    }

    public User findById(Long id) {
        return id == null ? null : userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAllByOrderByIdDesc();
    }

    @Transactional
    public User register(UserRegisterForm userRegisterForm) {
        User user = new User();
        user.setLogin(userRegisterForm.getLogin());
        user.setName(userRegisterForm.getName());
        user.setAdmin(false);
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), user.getLogin(), userRegisterForm.getPassword());
        return user;
    }


    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
