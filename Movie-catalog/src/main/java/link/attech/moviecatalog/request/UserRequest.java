package link.attech.moviecatalog.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRequest {
    private String email;
    private String password;
    private String c_password;
}
