package be.groep14.domain.service;

import be.groep14.domain.exception.ServiceException;
import be.groep14.domain.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin create(AdminDto dto) {
        return saveAdmin(new Admin(), dto);
    }

    public Admin update(Long id, AdminDto dto) {
        return saveAdmin(findById(id), dto);
    }

    public Admin findById(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new ServiceException("get", "Admin met id {" + id + "} is niet gevonden."));
    }

    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email).orElseThrow(() -> new ServiceException("get", "Admin met e-mail {" + email + "} is niet gevonden."));
    }

    public Admin delete(long id) {
        Admin admin = findById(id);
        adminRepository.delete(admin);
        return admin;
    }


    public Admin login(String email, String password) {
        Admin admin = findByEmail(email);
        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new ServiceException("login", "Ongeldige inloggegevens.");
        }

        return admin;
    }

    public Admin changePassword(String email, String password) {
        Admin admin = findByEmail(email);
        AdminDto dto = new AdminDto();
        dto.setPassword(password);
        dto.setEmail(admin.getEmail());
        update(admin.getId(), dto);
        return admin;
    }

    private Admin saveAdmin(Admin admin, AdminDto dto) {
        try {
            admin.setEmail(dto.getEmail());
            admin.setPassword(passwordEncoder.encode(dto.getPassword()));
            return adminRepository.save(admin);

        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("add", "Admin met e-mail :  {" + admin.getEmail() + "} bestaat al.");
        }

    }


    @PostConstruct
    public void seedAdmin() {
        String adminEmail = "admin@maakbaar.com";
        String adminPassword = "admin@maakbar.com";
        if (adminRepository.findByEmail(adminEmail).isEmpty()) {
            Admin admin = new Admin();
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            adminRepository.save(admin);
        }
    }

}
