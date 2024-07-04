import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UserManagementTest {
    private UserManagement userManagement;

    @BeforeEach
    public void setUp() {
        userManagement = new UserManagement();
    }

    @Test
    public void testAddUser() {
        userManagement.addUser("1", "Rahul Sharma", "rahul.sharma@example.com", "password123", "Admin");
        User user = userManagement.getUserById("1");
        assertNotNull(user);
        assertEquals("Rahul Sharma", user.name);
        assertEquals("rahul.sharma@example.com", user.email);
        assertEquals("password123", user.password);
        assertEquals("Admin", user.role);
    }

    @Test
    public void testDeleteUser() {
        userManagement.addUser("1", "Rahul Sharma", "rahul.sharma@example.com", "password123", "Admin");
        userManagement.deleteUser("1");
        User user = userManagement.getUserById("1");
        assertNull(user);
    }

    @Test
    public void testUpdateUser() {
        userManagement.addUser("1", "Rahul Sharma", "rahul.sharma@example.com", "password123", "Admin");
        userManagement.updateUser("1", "Rahul Sharma", "rahul.sharma@example.com", "newpassword456", "Member");
        User user = userManagement.getUserById("1");
        assertNotNull(user);
        assertEquals("newpassword456", user.password);
        assertEquals("Member", user.role);
    }

    @Test
    public void testGetUserById() {
        userManagement.addUser("1", "Rahul Sharma", "rahul.sharma@example.com", "password123", "Admin");
        User user = userManagement.getUserById("1");
        assertNotNull(user);
        assertEquals("Rahul Sharma", user.name);
    }

    @Test
    public void testGetAllUsers() {
        userManagement.addUser("1", "Rahul Sharma", "rahul.sharma@example.com", "password123", "Admin");
        userManagement.addUser("2", "Aditi Verma", "aditi.verma@example.com", "password456", "Member");
        List<User> users = userManagement.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    public void testSearchUsersByName() {
        userManagement.addUser("1", "Rahul Sharma", "rahul.sharma@example.com", "password123", "Admin");
        userManagement.addUser("2", "Aditi Verma", "aditi.verma@example.com", "password456", "Member");
        List<User> users = userManagement.searchUsersByName("Rahul");
        assertEquals(1, users.size());
        assertEquals("Rahul Sharma", users.get(0).name);
    }
}
