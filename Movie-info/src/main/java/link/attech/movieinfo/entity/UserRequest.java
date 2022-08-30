package link.attech.movieinfo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {
    private String email;
    private String password;
    private String c_password;
}
