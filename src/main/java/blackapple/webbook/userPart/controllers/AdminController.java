package blackapple.webbook.userPart.controllers;

import blackapple.webbook.userPart.models.User;
import blackapple.webbook.userPart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping(value = "/api/user")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
