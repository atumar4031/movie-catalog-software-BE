package link.attech.moviecatalog.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponse {
    private String email;
    private String password;
    private String c_password;
}
