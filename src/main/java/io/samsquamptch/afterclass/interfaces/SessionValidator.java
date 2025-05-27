package io.samsquamptch.afterclass.interfaces;

import io.samsquamptch.afterclass.exception.UnauthorisedException;

public interface SessionValidator {
    default void validateSession(Long groupId, Long userId) {
            if (groupId == null || userId == null) {
                throw new UnauthorisedException("Invalid session");
            }
    }
}
