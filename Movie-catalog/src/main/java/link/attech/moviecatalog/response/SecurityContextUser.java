package link.attech.moviecatalog.response;

import link.attech.moviecatalog.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class SecurityContextUser {
    private User user;
}
