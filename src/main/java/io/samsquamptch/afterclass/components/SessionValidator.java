package io.samsquamptch.afterclass.components;

import io.samsquamptch.afterclass.exception.UnauthorisedException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionValidator {
    public Long validateSessionAttribute(String attribute, HttpSession session) {
        Long idLong = (Long) session.getAttribute(attribute);
        if (idLong == null) {
            throw new UnauthorisedException("Invalid session: missing " + attribute + " ID");
        }
        return idLong;
    }
}
