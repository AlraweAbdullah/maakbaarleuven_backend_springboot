package be.groep14.domain.service;

import be.groep14.domain.exception.ServiceException;
import be.groep14.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(UserDto dto) {
        return saveUser(new User(), dto);
    }

    public User update(Long id, UserDto dto) {
        return saveUser(findById(id), dto);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ServiceException("get", "User met id {" + id + "} is niet gevonden."));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ServiceException("get", "User met e-mail {" + email + "} is niet gevonden."));
    }

    public User delete(long id) {
        User user = findById(id);
        userRepository.delete(user);
        return user;
    }


    public User login(String email, String password) {
        User user = findByEmail(email);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ServiceException("login", "Ongeldige inloggegevens.");
        }

        return user;
    }

    public User changePassword(String email, String password) {
        User user = findByEmail(email);
        UserDto dto = new UserDto();
        dto.setPassword(password);
        dto.setEmail(user.getEmail());
        dto.setLastname(user.getLastname());
        dto.setName(user.getName());


        update(user.getId(), dto);

        return user;
    }




    private User saveUser(User user, UserDto dto) {
        try {
            user.setEmail(dto.getEmail());
            // Hash the password before saving
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setName(dto.getName());
            user.setLastname(dto.getLastname());

            return userRepository.save(user);

        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("add", "Gebruiker met mail :  {" + user.getEmail() + "} bestaat al.");
        }

    }
}
