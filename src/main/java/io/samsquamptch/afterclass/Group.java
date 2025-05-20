package io.samsquamptch.afterclass;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Group {
    @Setter
    private String name;
    @Setter
    private String identifier;
    private List<User> users = new ArrayList<User>();

    public Group(String name, String identifier) {
        this.name = name;
        this.identifier = identifier;
    }

    public void addUser(User user) {this.users.add(user);}

    public void removeUser(User user) {this.users.remove(user);}
}
