package dev.root101.spring_template.exception;

import dev.root101.clean.core.exceptions.ApiException;
import dev.root101.clean.core.exceptions.ValidationException;
import dev.root101.clean.core.rest.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    //------------------- Error logger UC -------------------\\

    private void logException(Exception exc, HttpStatus status) {
    }
    //---------------------- END ----------------------\\
    /**
     * <pre>
     * Flujo:
     * 1 - Obtengo el HttpStatus de la excepcion.
     * 2 - Hago el log de pepper(almaceno el error en la BD).
     * 3 - Creo y devuelvo el 'ResponseEntity' con body 'ApiResponse' y propiedades:
     *  3.1 - status: String.valueOf(status.value()),
     *  3.2 - msg: "Error validating entity",
     *  3.3 - data: validationExc.getMessages(). => Lista de todas las validaciones que se rompieron.
     * </pre>
     *
     * @param validationExc
     * @param request
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value
            = {ValidationException.class})
    protected ResponseEntity<ApiResponse> handleValidationException(
            ValidationException validationExc,
            WebRequest request
    ) throws Exception {
        //Step 1
        HttpStatus status = validationExc.getStatusCode();

        //Step 2
        logException(validationExc, status);

        //Step 3
        return new ResponseEntity(
                ApiResponse.build(
                        //Step 3.1
                        String.valueOf(status.value()),
                        //Step 3.2
                        "Error validating entity",
                        //Step 3.3
                        validationExc.getMessages()
                ),
                status
        );
    }

    /**
     * <pre>
     * Flujo:
     * 1 - Obtengo el HttpStatus de la excepcion.
     * 2 - Hago el log de pepper(almaceno el error en la BD).
     * 3 - Creo y devuelvo el 'ResponseEntity' con body 'ApiResponse' y propiedades:
     *  3.1 - status: String.valueOf(status.value()),
     *  3.2 - msg: apiExc.getMessage(),
     *  3.3 - data: null.
     * </pre>
     *
     * @param apiExc
     * @param request
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value
            = {ApiException.class})
    protected ResponseEntity<ApiResponse> handleApiException(
            ApiException apiExc,
            WebRequest request
    ) throws Exception {
        HttpStatus status = apiExc.status();

        logException(apiExc, status);

        return new ResponseEntity(
                ApiResponse.build(
                        String.valueOf(status.value()),
                        apiExc.getMessage(),
                        null
                ),
                status
        );
    }

    /**
     *
     * <pre>
     * Flujo:
     * 1 - Obtengo el HttpStatus de la excepcion.
     * 2 - Hago el log de pepper(almaceno el error en la BD).
     * 3 - Creo y devuelvo el 'ResponseEntity' con body 'ApiResponse' y propiedades:
     *  3.1 - status: String.valueOf(status.value()),
     *  3.2 - msg: HttpClientErrorExceptionProcessor.detailMessage(status, ex.getResponseBodyAsString()),
     *  3.3 - data: null.
     * </pre>
     *
     * @param ex
     * @param request
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value
            = {HttpClientErrorException.class})
    protected ResponseEntity<ApiResponse> handleHttpClientError(
            HttpClientErrorException ex,
            WebRequest request
    ) throws Exception {
        HttpStatus status = HttpStatus.valueOf(ex.getRawStatusCode());

        logException(ex, status);

        return new ResponseEntity(
                ApiResponse.build(
                        String.valueOf(status.value()),
                        ex.getResponseBodyAsString(),
                        //HttpClientErrorExceptionProcessor.detailMessage(status, ex.getResponseBodyAsString()),
                        null
                ),
                status
        );
    }

    /**
     *
     * <pre>
     * Flujo:
     * 1 - Compruebo si la excepcion esta anotada con 'ResponseStatus', lo que la convierte en una excepcion preparada para que la procese Spring.
     *  1.1 - Si esta anotada con 'ResponseStatus' hago el 'rethrow' para que la procese Spring.
     * Si no es una excepcion de Spting:
     * 2 - Como es 'Exception', no se ha procesado por mas nadie, el status se definio como INTERNAL_SERVER_ERROR(500)
     * 3 - Hago el log de pepper(almaceno el error en la BD).
     * 4 - Creo y devuelvo el 'ResponseEntity' con body 'ApiResponse' y propiedades:
     *  4.1 - status: String.valueOf(status.value()),
     *  4.2 - msg: ex.getMessage(),
     *  4.3 - data: null.
     * </pre>
     *
     * @param ex
     * @param request
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value
            = {Exception.class})
    protected ResponseEntity<ApiResponse> handleGeneric(
            Exception ex,
            WebRequest request
    ) throws Exception {
        //Step 1
        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
            //Step 1.1
            throw ex;
        } else {
            //Step 2
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

            //Step 3
            logException(ex, status);

            //Step 4
            return new ResponseEntity(
                    ApiResponse.build(
                            String.valueOf(status.value()),
                            ex.getMessage(),
                            null
                    ),
                    status
            );
        }
    }

    /**
     *
     * <pre>
     * Flujo:
     * 1 - Si es un Access Denied es un 403.
     * 2 - Hago el log de pepper(almaceno el error en la BD).
     * 3 - Creo y devuelvo el 'ResponseEntity' con body 'ApiResponse' y propiedades:
     *  3.1 - status: String.valueOf(status.value()),
     *  3.2 - msg: ex.getMessage()
     *  3.3 - data: null.
     * </pre>
     *
     * @param ex
     * @param request
     * @return
     * @throws Exception
     *
     * @ExceptionHandler(value = {AccessDeniedException.class}) protected
     * ResponseEntity<ApiResponse> handleAccessDenied( AccessDeniedException ex,
     * WebRequest request ) throws Exception { HttpStatus status =
     * HttpStatus.FORBIDDEN;
     *
     * pepperExceptionLogger.log(ex, status);
     *
     * return new ResponseEntity( ApiResponse.build(
     * String.valueOf(status.value()), ex.getMessage(), null ), status ); }
     */
}
