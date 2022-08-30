package link.attech.moviecatalog.service;

import link.attech.moviecatalog.config.jwt.JwtAuthResponse;
import link.attech.moviecatalog.entity.Role;
import link.attech.moviecatalog.entity.User;
import link.attech.moviecatalog.request.LoginRequest;
import link.attech.moviecatalog.request.UserLogin;
import link.attech.moviecatalog.request.UserRequest;
import link.attech.moviecatalog.request.UserToRole;
import link.attech.moviecatalog.response.BaseResponse;
import link.attech.moviecatalog.response.UserResponse;

import java.util.List;

public interface UserService {
    BaseResponse<UserResponse> userRegistration(UserRequest userRequest);
    BaseResponse<JwtAuthResponse> userLogin(UserLogin userlogin);
    BaseResponse<List<User>> getUsers();
}
