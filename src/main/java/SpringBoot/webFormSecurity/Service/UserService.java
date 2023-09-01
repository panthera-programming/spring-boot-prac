package SpringBoot.webFormSecurity.Service;

import SpringBoot.webFormSecurity.Entities.RolesEntity;
import SpringBoot.webFormSecurity.Entities.UsersEntity;
import SpringBoot.webFormSecurity.Repositories.RolesEntityRepository;
import SpringBoot.webFormSecurity.Repositories.UsersEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolesEntityRepository rolesRepository;
    @Autowired
    UsersEntityRepository repository;
    public void saveUser(UsersEntity user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }
    public UsersEntity findUserById(int id)
    {
        return (repository.findById(id));
    }
    public UsersEntity findUserByEmail(String email)
    {
        return (repository.findByEmail(email));
    }
    public UsersEntity findByName(String name)
    {
        return (repository.findByName(name));
    }
    public RolesEntity findRole(String name)
    {
        return (rolesRepository.findByName(name));
    }
    public List<UsersEntity> findAll()
    {
        return (repository.findAll());
    }
    public void deleteUser(int id)
    {
        repository.deleteById(id);
    }
}
