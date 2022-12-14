package link.attech.moviecatalog.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseResponse<T> {
    private HttpStatus status;
    private String message;
    private T response;
}
