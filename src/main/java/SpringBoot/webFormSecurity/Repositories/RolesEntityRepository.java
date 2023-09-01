package SpringBoot.webFormSecurity.Repositories;

import SpringBoot.webFormSecurity.Entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface RolesEntityRepository extends JpaRepository<RolesEntity, Serializable> {
    RolesEntity findByName(String name);
}
