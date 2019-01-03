package io.github.minhaz1217.onlineadvising.models;


import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Document(collection="User")
public class User {
    @Id
    @GeneratedValue
    private String id;

    @NotNull
    @Size(min=5, max=30)
    private String username;
    @NotNull
    @Size(min=8, max=40)
    private String password;
    private Set<Role> role;

    public User(@NotNull @Size(min = 5, max = 30) String username, @NotNull @Size(min = 8, max = 40) String password, Set<Role> role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    private User(){
    }
}
