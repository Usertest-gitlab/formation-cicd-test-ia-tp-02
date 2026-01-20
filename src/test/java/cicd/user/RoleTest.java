package cicd.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.devops.cicd.user.Role;
import com.devops.cicd.user.User;

public class RoleTest {
   
    @Test
    void cannotAccessAdminAreaTest() {
        User user = new User("john@doe.com", "Abcdef1!", Role.USER);
        assertFalse(user.canAccessAdminArea());
    }

    @Test
    void canAccessAdminAreaTest() {
        User user = new User("john@doe.com", "Abcdef1!", Role.ADMIN);
        assertTrue(user.canAccessAdminArea());
    }
}
