package io.github.minhaz1217.onlineadvising.models;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "Role")
public class Role {
    @Id
    private String id;
    @Indexed(unique = true, direction = IndexDirection.ASCENDING)
    private String role;

    public Role(){}
    public Role(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
