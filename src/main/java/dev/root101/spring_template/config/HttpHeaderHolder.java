package dev.root101.spring_template.config;

import org.springframework.http.HttpHeaders;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Clase para obtener en runtime un header especifico de la peticion que me
 * hacen a la api.
 */
@Service
public class HttpHeaderHolder {

    /**
     * Obtiene el header de 'header' de la peticion actual.
     *
     * @param header
     * @return
     */
    public String get(String header) {
        if (RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes servletRequest) {
            return servletRequest.getRequest().getHeader(header);
        }
        return "";
    }

    /**
     * Obtiene el header de AUTHORIZATION de la peticion actual.
     *
     * @return
     */
    public String getAuthorization() {
        return get(AUTHORIZATION);
    }

    public String getCookies() {
        return get(HttpHeaders.COOKIE);
    }
}
