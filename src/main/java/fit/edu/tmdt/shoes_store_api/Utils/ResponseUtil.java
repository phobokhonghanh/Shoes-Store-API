package fit.edu.tmdt.shoes_store_api.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    public static <T> ResponseEntity<T> getResponse(T object, HttpStatus status) {
        if (object == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        else
            return ResponseEntity.status(status).body(object);
    }
}
