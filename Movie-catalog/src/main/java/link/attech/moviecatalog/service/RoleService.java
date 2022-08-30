package link.attech.moviecatalog.service;

import link.attech.moviecatalog.entity.Role;
import link.attech.moviecatalog.request.UserToRole;
import link.attech.moviecatalog.response.BaseResponse;

public interface RoleService {
    BaseResponse addRole(Role role);
    BaseResponse addRoleToUser(UserToRole userToRole);
}
