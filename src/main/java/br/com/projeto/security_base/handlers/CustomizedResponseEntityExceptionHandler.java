package br.com.projeto.security_base.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestController
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> allHandlerException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                new ExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> allHandlerMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                            WebRequest request) {
        return new ResponseEntity<Object>(new ErrorResponse(LocalDateTime.now(), ex.getBindingResult().getAllErrors()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> allHandlerBadRequestException(BadRequestException ex, WebRequest request) {
        return new ResponseEntity<Object>(
                new ExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjetoNotFoundException.class)
    public ResponseEntity<Object> handlerFuncionarioNotFoundException(ObjetoNotFoundException ex, WebRequest request) {
        return new ResponseEntity<Object>(
                new ExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
                HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(ErrorGeneratingTokenException.class)
    public ResponseEntity<Object> allHandlerErrorGeneratingTokenException(ErrorGeneratingTokenException ex, WebRequest request) {
        return new ResponseEntity<Object>(
                new ExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
