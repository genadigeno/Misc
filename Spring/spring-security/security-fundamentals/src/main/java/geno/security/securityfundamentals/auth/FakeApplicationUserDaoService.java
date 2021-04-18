package geno.security.securityfundamentals.auth;

import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.function.Predicate;
import java.util.Optional;
import java.util.List;

import static geno.security.securityfundamentals.security.UserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream().filter(new Predicate<ApplicationUser>() {
            @Override
            public boolean test(ApplicationUser applicationUser) {
                return applicationUser.getUsername().equals(username);
            }
        }).findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
            new ApplicationUser(STUDENT.getGrantedAuthorities(), passwordEncoder.encode("123"),"geno"),
            new ApplicationUser(ADMIN.getGrantedAuthorities(),   passwordEncoder.encode("123"),"lynda"),
            new ApplicationUser(ADMINTRAINEE.getGrantedAuthorities(), passwordEncoder.encode("123"),"john")
        );
        return applicationUsers;
    }
}
