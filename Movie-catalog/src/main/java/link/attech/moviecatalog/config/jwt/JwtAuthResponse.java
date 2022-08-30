package link.attech.moviecatalog.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType;

    public JwtAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
