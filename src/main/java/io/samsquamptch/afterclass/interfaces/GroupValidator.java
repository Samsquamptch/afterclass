package io.samsquamptch.afterclass.interfaces;

import io.samsquamptch.afterclass.exception.UnauthorisedException;

public interface GroupValidator {
    default void validateGroup(Long groupId) {
        if (groupId == null) {
            throw new UnauthorisedException("Invalid session");
        }
    }
}
