package link.attech.moviecatalog.service.impl;

import link.attech.moviecatalog.config.jwt.JwtAuthResponse;
import link.attech.moviecatalog.config.jwt.JwtTokenProvider;
import link.attech.moviecatalog.entity.Role;
import link.attech.moviecatalog.entity.User;
import link.attech.moviecatalog.repository.RoleRepository;
import link.attech.moviecatalog.repository.UserRepository;
import link.attech.moviecatalog.request.UserLogin;
import link.attech.moviecatalog.request.UserRequest;
import link.attech.moviecatalog.request.UserToRole;
import link.attech.moviecatalog.response.BaseResponse;
import link.attech.moviecatalog.response.UserResponse;
import link.attech.moviecatalog.service.RoleService;
import link.attech.moviecatalog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, RoleService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public BaseResponse<UserResponse> userRegistration(UserRequest userRequest) {
        Optional<User> userOptional = userRepository.findByEmail(userRequest.getEmail());
        if (!userRequest.getPassword().equals(userRequest.getC_password())){
            return new BaseResponse<>(HttpStatus.BAD_REQUEST, "Password not matched", null);
        }
        if (userOptional.isPresent()){
            return new BaseResponse<>(HttpStatus.BAD_REQUEST, "email has been taken", null);
        }

        // Build user entity
        User user = User.builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();

        // Save user entity
        userRepository.save(user);

        // Build uer response
        UserResponse userResponse = UserResponse.builder()
                .email(userRequest.getEmail())
                .build();

        return new BaseResponse<>(HttpStatus.CREATED, "Registration success", userResponse);
    }
    @Override
    public BaseResponse<JwtAuthResponse> userLogin(UserLogin userlogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                userlogin.getEmail(), userlogin.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtTokenProvider.generateToken(authentication);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse(jwtToken);

        return new BaseResponse<>(HttpStatus.OK, "user logged in successfully", jwtAuthResponse);
    }
    @Override
    public BaseResponse<Role> addRole(Role role) {
        return new BaseResponse<>(HttpStatus.CREATED, "Role is added", roleRepository.save(role));
    }
    @Override
    public BaseResponse addRoleToUser(UserToRole userToRole) {
        String email = userToRole.getUserEmail();
        String role = userToRole.getRoleName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        Optional<Role> optionalRole = roleRepository.findByName(role);
        if(optionalUser.isEmpty()){
            return new BaseResponse(HttpStatus.BAD_REQUEST, "User not found", email);
        }
        if(optionalRole.isEmpty()){
            return new BaseResponse(HttpStatus.BAD_REQUEST, "Role not found", role);
        }
        User user = optionalUser.get();
        Role role1 = optionalRole.get();

        user.getRoles().add(role1);
        log.info("user: {}", user);
        userRepository.save(user);
        return new BaseResponse(HttpStatus.OK, "Role has been to user", null);
    }
    @Override
    public BaseResponse<List<User>> getUsers() {
        List<User> users = userRepository.findAll();
        return new BaseResponse<>(HttpStatus.OK, "users found", users);
    }

}
