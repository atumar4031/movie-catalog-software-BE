package link.attech.ratingservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {
    private String email;
    private String password;
    private String c_password;
}
