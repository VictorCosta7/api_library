package br.com.victorcosta.libraryapi.exeptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentTypeMismatchException exception) {
        ErrorMessageDTO errorMessage = new ErrorMessageDTO(exception.getName(), exception.getErrorCode());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Object> handlerException(AppException exception) {
        ErrorMessageDTO errorMessage = new ErrorMessageDTO(exception.getMessage(), exception.getClass().getSimpleName());

        return new ResponseEntity<>(errorMessage, exception.getHttpStatus());
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleUserFoundException(UserFoundException e) {
        ErrorMessageDTO dto = new ErrorMessageDTO(
                e.getMessage(),e.getClass().getSimpleName()
        );

        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleUserFoundException(UserNotFoundException e) {
        ErrorMessageDTO dto = new ErrorMessageDTO(
                e.getMessage(),e.getClass().getSimpleName()
        );

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleBookFoundException(BookFoundException e) {
        ErrorMessageDTO dto = new ErrorMessageDTO(
                e.getMessage(), e.getClass().getSimpleName()
        );

        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidIsbnException.class)
    public ResponseEntity<ErrorMessageDTO> handleInvalidIsbnException(InvalidIsbnException e) {
        ErrorMessageDTO dto = new ErrorMessageDTO(
                e.getMessage(), e.getClass().getSimpleName()
        );

        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }
}