package server.Spring.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import server.Spring.model.User;
import server.Spring.repository.UserRepository;
import server.Spring.service.UserService;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Collection<User> listAllUsers(int limit) {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
