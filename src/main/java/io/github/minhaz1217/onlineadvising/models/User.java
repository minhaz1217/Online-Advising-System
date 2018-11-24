package io.github.minhaz1217.onlineadvising.models;


import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    private String[] role;
    private User(){
    }
    public User(String username, String password, String... role) {
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

    public String[] getRole() {
        return role;
    }

    public void setRole(String... role) {
        this.role = role;
    }
}
