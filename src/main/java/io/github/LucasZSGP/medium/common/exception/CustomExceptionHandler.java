/* (C)2023 */
package io.github.LucasZSGP.medium.common.exception;

import java.util.List;
import org.openapitools.model.GenericErrorModel;
import org.openapitools.model.GenericErrorModelErrors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<GenericErrorModel> handler(UserException exception) {
        var model =
                GenericErrorModel.builder()
                        .errors(new GenericErrorModelErrors(List.of(exception.getMessage())))
                        .build();
        return ResponseEntity.unprocessableEntity().body(model);
    }
}
