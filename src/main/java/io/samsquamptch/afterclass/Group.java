package io.samsquamptch.afterclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    private String name;

    @Setter
    private String passCode;

    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private final List<User> users = new ArrayList<>();

    public Group(String name, String passCode) {
        this.name = name;
        this.passCode = passCode;
    }

    public void addUser(User user) {
        this.users.add(user);
        user.setGroup(this);}

    public void removeUser(User user) {
        this.users.remove(user);
        user.setGroup(null);}
}
