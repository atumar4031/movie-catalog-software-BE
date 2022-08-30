package link.attech.moviecatalog.controller;

import link.attech.moviecatalog.entity.Role;
import link.attech.moviecatalog.request.UserToRole;
import link.attech.moviecatalog.response.BaseResponse;
import link.attech.moviecatalog.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/addRole")
    public BaseResponse<?> addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @PostMapping("/roleToUser")
    public BaseResponse<?> addRoleToUser(@RequestBody UserToRole userToRole){
        return roleService.addRoleToUser(userToRole);
    }
}
