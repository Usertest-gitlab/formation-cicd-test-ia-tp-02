package cicd.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.devops.cicd.user.Role;
import com.devops.cicd.user.User;
import com.devops.cicd.user.UserService;

@Tag("unit")
class UserServiceTest {

    @Test
    void registerWorkTest() {
        UserService service = new UserService();

        User user = service.register("john@doe.com", "Abcdef1!", Role.USER);

        assertNotNull(user);
        assertTrue(user instanceof User);
    }

    @Test
    void registerReturnEmailExceptionTest() {
        UserService service = new UserService();
        
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> service.register("@doe.com", "Abcdef1!", Role.USER)
        );

        assertEquals("email must be valid", ex.getMessage());
    }
}
