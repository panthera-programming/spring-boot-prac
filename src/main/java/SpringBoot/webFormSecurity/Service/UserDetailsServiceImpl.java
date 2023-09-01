package SpringBoot.webFormSecurity.Service;

import SpringBoot.webFormSecurity.Entities.RolesEntity;
import SpringBoot.webFormSecurity.Entities.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Configuration
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserService service;
    @Override
    public UserDetails loadUserByUsername(String email)
    {
        UsersEntity user = service.findUserByEmail(email);
        RolesEntity role = user.getRole();
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role.getName()));
        System.out.println("\n\n\t\t***************** Returning User with username ("+user.getName()+")*************\n\n");
        return (new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                authorities
        ));
    }
}
