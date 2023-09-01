package SpringBoot.webFormSecurity.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "roles",schema = "eStore")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;
    @Column(name = "role_name")
    private String name;

    public RolesEntity(String name)
    {
        this.name = name;
    }

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<UsersEntity> users;

}
