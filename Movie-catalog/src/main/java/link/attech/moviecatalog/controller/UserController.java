package link.attech.moviecatalog.controller;

import link.attech.moviecatalog.config.jwt.JwtAuthResponse;
import link.attech.moviecatalog.config.jwt.JwtTokenProvider;
import link.attech.moviecatalog.entity.User;
import link.attech.moviecatalog.request.UserLogin;
import link.attech.moviecatalog.request.UserRequest;
import link.attech.moviecatalog.response.BaseResponse;
import link.attech.moviecatalog.response.UserResponse;
import link.attech.moviecatalog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcom atumar";
    }

    @PostMapping("/signup")
    public BaseResponse<UserResponse> userRegistration(@RequestBody UserRequest userRequest){
        return userService.userRegistration(userRequest);
    }

    @PostMapping("/signin")
    public BaseResponse<JwtAuthResponse> userLogin(@RequestBody UserLogin userLogin){
        return userService.userLogin(userLogin);
    }

    @GetMapping("/users")
    public BaseResponse<List<User>> getUsers(){
        return userService.getUsers();
    }

}
