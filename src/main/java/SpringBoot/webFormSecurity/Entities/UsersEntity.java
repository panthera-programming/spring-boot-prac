package SpringBoot.webFormSecurity.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", schema = "eStore")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(length = 100, unique = true)
    private String name;
    @Column(length = 50, unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Transient
    private String roleName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_role_id", referencedColumnName = "role_id", nullable = false)
    private RolesEntity role;
}
