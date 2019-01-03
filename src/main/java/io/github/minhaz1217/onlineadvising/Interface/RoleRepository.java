package io.github.minhaz1217.onlineadvising.Interface;

import io.github.minhaz1217.onlineadvising.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findRoleByRole(String role);
}
