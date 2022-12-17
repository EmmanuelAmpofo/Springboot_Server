package server.Spring.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import server.Spring.model.User;
import server.Spring.repository.UserRepository;
import server.Spring.security.email.EmailValidator;
import server.Spring.service.UserService;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final EmailValidator emailValidator;
    private final String USER_NOT_FOUND_MSG = "This user cannot be found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImplementation(UserRepository userRepository, EmailValidator emailValidator, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.emailValidator = emailValidator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }


    @Override
    public User createUser(User user) {
        boolean isValidEmail = emailValidator.test(user.getEmail());
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if(!isValidEmail){
            throw  new IllegalStateException("Email is invalid");
        }

        if(userExists){
            throw new IllegalStateException("Email is already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

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
