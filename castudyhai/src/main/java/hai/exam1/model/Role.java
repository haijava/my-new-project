package hai.exam1.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    private String role;


    public Collection<Login> getLogins() {
        return logins;
    }

    public void setLogins(Collection<Login> logins) {
        this.logins = logins;
    }

    @ManyToMany(mappedBy = "roles")
    private Collection<Login> logins;
    public Role() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
