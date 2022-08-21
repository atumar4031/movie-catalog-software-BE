package link.attech.ratingservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResponse<T> {

    private HttpStatus status;
    private String message;
    private T response;

}
