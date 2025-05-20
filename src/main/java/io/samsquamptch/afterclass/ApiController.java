package io.samsquamptch.afterclass;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/group/create")
    public String createGroup() {
        return null;
    }

    @GetMapping("/group/update")
    public void updateGroup() {}


    @GetMapping("/user/create")
    public String createUser() {
        return null;
    }

    @GetMapping("/user/delete")
    public void deleteUser() {}

    @GetMapping("/user/update")
    public void updateUser() {}

    @GetMapping("/class/create")
    public String createClass() {
        return null;
    }

    @GetMapping("/class/delete")
    public void deleteClass() {}

    @GetMapping("/class/update")
    public void updateClass() {}
}