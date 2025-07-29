package co.simplon.jamixbusiness.security;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import co.simplon.jamixbusiness.accounts.Account;
import co.simplon.jamixbusiness.commons.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_roles")
public class Role extends AbstractEntity {
    @Column(name = "role_name", unique = true, nullable = false)
    private String roleName;

    @Column(name = "default_role", nullable = false)
    private boolean defaultRole;

    @OneToMany(mappedBy = "role")
    private Set<Account> accounts = new HashSet<>();

    public String getRoleName() {
	return roleName;
    }

    public boolean isDefaultRole() {
	return defaultRole;
    }

    protected Role() {
	// ORM
    }

    @Override
    public String toString() {
	return "Role [roleName=" + roleName + ", defaultRole=" + defaultRole + ", accounts=" + accounts + "]";
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	return obj instanceof Role other && roleName.equals(other.roleName);
    }

    @Override
    public int hashCode() {
	return Objects.hash(roleName, defaultRole);
    }

    public Role(String roleName, boolean defaultRole) {
	Objects.requireNonNull(roleName);
	this.roleName = roleName;
	this.defaultRole = defaultRole;
    }

}
