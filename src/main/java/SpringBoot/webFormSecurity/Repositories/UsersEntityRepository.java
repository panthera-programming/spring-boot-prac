package SpringBoot.webFormSecurity.Repositories;

import SpringBoot.webFormSecurity.Entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UsersEntityRepository extends JpaRepository<UsersEntity, Serializable> {
    UsersEntity findByEmail(String email);
    UsersEntity findByName(String name);
    UsersEntity findById(int id);
}
