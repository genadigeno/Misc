package geno.security.securityfundamentals.security;

import lombok.Getter;
import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static geno.security.securityfundamentals.security.UserPermission.*;

@Getter
public enum UserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));

    private final Set<UserPermission> permissions;

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {

        Set<SimpleGrantedAuthority> grantedAuthorities = getPermissions().stream().map(
            new Function<UserPermission, SimpleGrantedAuthority>() {
                @Override
                public SimpleGrantedAuthority apply(UserPermission userPermission) {
                    return new SimpleGrantedAuthority(userPermission.getPermission());
                }
            }
        ).collect(Collectors.toSet());

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return grantedAuthorities;
    }

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }
}
