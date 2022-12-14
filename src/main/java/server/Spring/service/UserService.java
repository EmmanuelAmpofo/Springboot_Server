package server.Spring.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import server.Spring.model.User;
import server.Spring.repository.UserRepository;


import java.util.Collection;
@Service

public interface UserService extends UserDetailsService {


    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User createUser (User user);
    Collection<User> listAllUsers (int limit);
    User getUser(Long id);
    User updateUser(User user);
    Boolean deleteUser(Long id);
}
