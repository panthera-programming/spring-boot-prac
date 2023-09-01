package SpringBoot.webFormSecurity.Controllers;

import SpringBoot.webFormSecurity.Entities.UsersEntity;
import SpringBoot.webFormSecurity.Repositories.RolesEntityRepository;
import SpringBoot.webFormSecurity.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    UserService service;
    @Autowired
    RolesEntityRepository rolesRepository;

    @GetMapping("/login")
    public String renderLoginPage(Model mod)
    {
        UsersEntity user = new UsersEntity();
        mod.addAttribute("user", user);
        return ("index");
    }
    @GetMapping("/admin")
    //@PreAuthorize("hasRole('Admin')")
    public String rootMapping(Model mod)
    {
        UsersEntity user = new UsersEntity();
        mod.addAttribute("userReg", user);
        List<String> roles = Arrays.asList("User","Admin");
        mod.addAttribute("rolesList",roles);
        List<UsersEntity> usersList = service.findAll();
        mod.addAttribute("usersList", usersList);
        return ("adminHome");
    }
    @GetMapping("/admin/edit/{id}")
    public String renderEditForm(@PathVariable("id") int id, Model mod)
    {
        UsersEntity user = service.findUserById(id);
        mod.addAttribute("userEdit", user);
        List<String> roles = Arrays.asList("User","Admin");
        mod.addAttribute("rolesList",roles);
        List<UsersEntity> usersList = service.findAll();
        mod.addAttribute("usersList", usersList);
        return ("adminHome");
    }
    @PostMapping("/admin/edit/save/{id}")
    public String saveEdittedForm(@ModelAttribute UsersEntity userEdit, @PathVariable int id)
    {
        userEdit.setId(id);
        userEdit.setRole(service.findRole(userEdit.getRoleName()));
        service.saveUser(userEdit);
        return ("redirect:/admin");
    }
    @PostMapping("/admin/register")
    public String saveRegistrationForm(@ModelAttribute UsersEntity userReg)
    {
        if (service.findUserByEmail(userReg.getEmail()) == null)
        {
            userReg.setRole(rolesRepository.findByName(userReg.getRoleName()));
            service.saveUser(userReg);
            return ("redirect:/admin");
        }

        return ("redirect:/admin?exists");
    }
    @GetMapping("/admin/delete/{id}")
    public String deleteRegisteredUser(@PathVariable("id") int id)
    {
        service.deleteUser(id);
        return ("redirect:/admin");
    }
    @GetMapping("/welcome")
    public String processLogin(Principal loggedInUser, Authentication auth, Model mod)
    {
        System.out.println("\n\n\t##########\t"+loggedInUser.getName()+"\t############\n\n");
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        boolean isAdmin = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("Admin"));
        System.out.println("\n\n\t##########\t"+isAdmin+"\t############\n\n");
        if (isAdmin)
        {
            return ("redirect:/admin");
        }

        return ("userHome");
    }
    @PostMapping("/save")
    public String saveSelfRegisteredUser(@ModelAttribute UsersEntity user)
    {
        if (service.findUserByEmail(user.getEmail()) == null)
        {
            user.setRole(rolesRepository.findByName("User"));
            service.saveUser(user);
            return ("redirect:/login");
        }

        return ("redirect:/login?exists");
    }
}
