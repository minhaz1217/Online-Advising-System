package io.github.minhaz1217.onlineadvising.Interface;

import io.github.minhaz1217.onlineadvising.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findRoleByRole(String role);
}
