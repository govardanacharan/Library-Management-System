import java.util.ArrayList;
import java.util.List;

public class UserManagement {
    private List<User> users = new ArrayList<>();

    public void addUser(String userId, String name, String email, String password, String role) {
        users.add(new User(userId, name, email, password, role));
        System.out.println("User added successfully.");
    }

    public void deleteUser(String userId) {
        users.removeIf(user -> user.userId.equals(userId));
        System.out.println("User deleted successfully.");
    }

    public void updateUser(String userId, String name, String email, String password, String role) {
        for (User user : users) {
            if (user.userId.equals(userId)) {
                user.name = name;
                user.email = email;
                user.password = password;
                user.role = role;
                System.out.println("User updated successfully.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    public User getUserById(String userId) {
        for (User user : users) {
            if (user.userId.equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public List<User> searchUsersByName(String name) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (user.name.toLowerCase().contains(name.toLowerCase())) {
                result.add(user);
            }
        }
        return result;
    }
}
