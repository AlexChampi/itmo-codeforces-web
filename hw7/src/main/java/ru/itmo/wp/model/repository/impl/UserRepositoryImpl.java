package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.repository.UserRepository;

import java.util.List;

@SuppressWarnings("SqlNoDataSourceInspection")
public class UserRepositoryImpl extends AbstractBasicRepository<User> implements UserRepository {

    public UserRepositoryImpl() {
        super("User", User::new);
    }

    @Override
    public User find(long id) {
        return findByParams("SELECT * FROM User WHERE id=?", id);
    }


    @Override
    public User findByLogin(String login) {
        return findByParams("SELECT * FROM User WHERE login=?", login);
    }

    @Override
    public User findByLoginAndPasswordSha(String login, String passwordSha) {
        return findByParams("SELECT * FROM User WHERE login=? AND passwordSha=?", login, passwordSha);
    }

    @Override
    public User findByEmailAndPasswordSha(String email, String passwordSha) {
        return findByParams("SELECT * FROM User WHERE email=? AND passwordSha=?", email, passwordSha);
    }

    @Override
    public List<User> findAll() {
        return findAllWithParams("SELECT * FROM User ORDER BY id DESC");
    }


    @Override
    public void save(User user, String passwordSha) {
        save(user, "INSERT INTO `User` (`login`, `email`, `passwordSha`, `admin`, `creationTime`) VALUES (?, ?, ?, FALSE, NOW())",
                user.getLogin(), user.getEmail(), passwordSha);
    }

    @Override
    public Long findCount() {
        return findCount("SELECT COUNT(*) row_count FROM  `User`");
    }

    @Override
    public void setAdmin(long userId, boolean admin) {
        update("UPDATE `User` SET admin=? WHERE id=?", admin, userId);
    }

    @Override
    public User findByEmail(String email) {
        return findByEmail(email);
    }
}
