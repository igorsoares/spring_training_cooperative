package br.com.solinftec.treinamento.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestErrorHandler extends DefaultErrorAttributes {
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, Object> processValidationError(MethodArgumentNotValidException ex) throws Exception {

        ObjectError objectError = ex.getBindingResult().getAllErrors().stream().findFirst()
                .orElseThrow(() -> new Exception("UNKNOWN_ERROR"));
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("message", objectError.getDefaultMessage() + "_");
        return response;
    }

    @ResponseBody
    @ExceptionHandler(TreinamentoDefaultException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String, Object> processTreinamentoDefault(TreinamentoDefaultException ex) {
        Map<String, Object> response = new HashMap<>();
        if (ex.getMessage().contains("NOT_FOUND"))
            response.put("status", HttpStatus.NOT_FOUND);
        else
            response.put("status", HttpStatus.BAD_REQUEST);
        response.put("message", ex.getMessage());
        return response;
    }
}
