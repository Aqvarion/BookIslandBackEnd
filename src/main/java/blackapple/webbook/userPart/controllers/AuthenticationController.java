package blackapple.webbook.userPart.controllers;

import blackapple.webbook.service.UserDetailsImpl;
import blackapple.webbook.userPart.model.ERole;
import blackapple.webbook.userPart.model.Role;
import blackapple.webbook.userPart.model.User;
import blackapple.webbook.userPart.payload.JwtResponse;
import blackapple.webbook.userPart.payload.LoginRequest;
import blackapple.webbook.userPart.payload.MessageResponse;
import blackapple.webbook.userPart.payload.SignUpRequest;
import blackapple.webbook.userPart.repositories.RoleRepository;
import blackapple.webbook.userPart.repositories.UserRepository;
import blackapple.webbook.userPart.security.jwt.JwtUtils;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping(value = "/api/auth")
public class AuthenticationController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signUp")
    public ResponseEntity registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        System.out.println("Происходит регистрация");

        // проверка на уникальность имени пользователя или почты
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        // создание нового пользователя
        User user = new User(
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            passwordEncoder.encode(signUpRequest.getPassword())
        );

        // добавление ролей
        // получение ролей из запроса
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        // добавление в переменную roles ролей из запроса
        if(strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found!"));
            roles.add(userRole);
        } else {
            strRoles.forEach( role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signIn")
    public ResponseEntity authenticateUser (@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("Выполняется аутентификация");
        // изучить подробней

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        System.out.println("Jwt: " + jwt);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        JwtResponse userJwt = new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);

        return ResponseEntity.ok(userJwt);
    }
}
