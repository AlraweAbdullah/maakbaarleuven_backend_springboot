package be.groep14.domain.service;

import be.groep14.domain.exception.DomainException;
import be.groep14.domain.exception.ServiceException;
import be.groep14.domain.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        return userRepository.findById(id).orElseThrow(() -> new ServiceException("get", "Gebruiker met id {" + id + "} is niet gevonden."));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ServiceException("get", "Gebruiker met e-mail {" + email + "} is niet gevonden."));
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
            user.setBirthdate(dto.getBirthdate());
            user.setTelephone(dto.getTelephone() == null ? 0 : Integer.parseInt(dto.getTelephone()));
            user.setPersons(dto.getPersons() == null ? 0 : Integer.parseInt(dto.getPersons()));
            user.setStreet(dto.getStreet());
            user.setHouseNr(dto.getHouseNr() == null ? "Geen" : dto.getHouseNr());
            user.setDevices(dto.getDevices());
            return userRepository.save(user);

        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("add", "Gebruiker met e-mail :  {" + user.getEmail() + "} bestaat al.");
        }

    }

    public Device addDeviceToUser(long userId, Device device) {
        try {
            User user = findById(userId);
            user.addDevice(device);
            userRepository.save(user);
            return device;
        } catch (DomainException exception) {
            throw new ServiceException("add", exception.getMessage());
        }
    }


    public List<Device> getDevices(long userId) {
        User user = findById(userId);
        return user.getDevices();
    }


    public Device removeDeviceFromUser(long userId, Device device) {
        User user = findById(userId);
        List<Device> userDevices = getDevices(userId);

        if (!userDevices.contains(device)) {
            throw new ServiceException("delete", "device.{" + device.getSerial() + "}.is.not.in.user.{" + user.getEmail() + "} devices");
        }
        user.removeDevice(device);

        userRepository.save(user);

        return device;
    }


    @PostConstruct
    public void seedUser() {
        String userEmail = "user@user.com";
        String userPassword = "12345";
        if (userRepository.findByEmail(userEmail).isEmpty()) {
            User user = new User();
            user.setEmail(userEmail);
            user.setPassword(passwordEncoder.encode(userPassword));
            user.setStreet("123 Main St");
            user.setName("John");
            user.setLastname("Doe");
            user.setHouseNr("A1");
            user.setBirthdate(LocalDate.parse("1990-05-15"));
            user.setPersons(1);
            user.setTelephone(123456789);
            userRepository.save(user);
        }
    }

}
