package io.samsquamptch.afterclass.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupRequest {
    private String name;
    private String passCode;
}
