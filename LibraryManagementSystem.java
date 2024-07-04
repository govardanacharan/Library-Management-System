import java.awt.*;
import javax.swing.*;

public class LibraryManagementSystem extends JFrame {
    private UserManagement userManagement = new UserManagement();
    private BookManagement bookManagement = new BookManagement();

    public LibraryManagementSystem() {
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(34, 40, 49));
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        JMenu userMenu = new JMenu("User Management");
        JMenuItem addUserMenuItem = new JMenuItem("Add User");
        addUserMenuItem.addActionListener(e -> handleUserManagement());
        userMenu.add(addUserMenuItem);
        menuBar.add(userMenu);

        JMenu bookMenu = new JMenu("Book Management");
        JMenuItem addBookMenuItem = new JMenuItem("Add Book");
        addBookMenuItem.addActionListener(e -> handleBookManagement());
        bookMenu.add(addBookMenuItem);
        menuBar.add(bookMenu);

        JLabel titleLabel = new JLabel("Library Management System", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        contentPanel.setBackground(new Color(34, 40, 49));

        JButton userButton = createStyledButton("User Management", new Color(0, 173, 181), "user_icon.png");
        userButton.addActionListener(e -> handleUserManagement());

        JButton bookButton = createStyledButton("Book Management", new Color(255, 193, 7), "book_icon.png");
        bookButton.addActionListener(e -> handleBookManagement());

        JButton exitButton = createStyledButton("Exit", new Color(237, 85, 59), "exit_icon.png");
        exitButton.addActionListener(e -> System.exit(0));

        contentPanel.add(userButton);
        contentPanel.add(bookButton);
        contentPanel.add(exitButton);

        add(contentPanel, BorderLayout.CENTER);
    }

    private void handleUserManagement() {
        JFrame userFrame = new JFrame("User Management");
        userFrame.setSize(500, 400);
        userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userFrame.getContentPane().setBackground(new Color(34, 40, 49));
        userFrame.setLayout(new GridLayout(10, 2, 10, 10));

        JLabel userIdLabel = createStyledLabel("User ID:");
        JTextField userIdField = new JTextField();
        JLabel nameLabel = createStyledLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel emailLabel = createStyledLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = createStyledLabel("Password:");
        JTextField passwordField = new JTextField();
        JLabel roleLabel = createStyledLabel("Role:");
        JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Admin", "Member"});

        JButton addButton = createStyledButton("Add User", new Color(0, 173, 181), "add_icon.png");
        addButton.addActionListener(e -> {
            String userId = userIdField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String role = (String) roleComboBox.getSelectedItem();
            userManagement.addUser(userId, name, email, password, role);
            JOptionPane.showMessageDialog(userFrame, "User added successfully!");
        });

        JButton deleteButton = createStyledButton("Delete User", new Color(237, 85, 59), "delete_icon.png");
        deleteButton.addActionListener(e -> {
            String userId = userIdField.getText();
            userManagement.deleteUser(userId);
            JOptionPane.showMessageDialog(userFrame, "User deleted successfully!");
        });

        JButton updateButton = createStyledButton("Update User", new Color(0, 173, 181), "update_icon.png");
        updateButton.addActionListener(e -> {
            String userId = userIdField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String role = (String) roleComboBox.getSelectedItem();
            userManagement.updateUser(userId, name, email, password, role);
            JOptionPane.showMessageDialog(userFrame, "User updated successfully!");
        });

        JButton getButton = createStyledButton("Get User by ID", new Color(57, 62, 70), "search_icon.png");
        getButton.addActionListener(e -> {
            String userId = userIdField.getText();
            User user = userManagement.getUserById(userId);
            if (user != null) {
                JOptionPane.showMessageDialog(userFrame, "User ID: " + user.userId + "\nName: " + user.name + "\nEmail: " + user.email + "\nRole: " + user.role);
            } else {
                JOptionPane.showMessageDialog(userFrame, "User not found.");
            }
        });

        JButton listButton = createStyledButton("List All Users", new Color(238, 238, 238), "list_icon.png");
        listButton.addActionListener(e -> {
            StringBuilder usersList = new StringBuilder();
            for (User user : userManagement.getAllUsers()) {
                usersList.append("User ID: ").append(user.userId)
                        .append("\nName: ").append(user.name)
                        .append("\nEmail: ").append(user.email)
                        .append("\nRole: ").append(user.role).append("\n\n");
            }
            JOptionPane.showMessageDialog(userFrame, usersList.toString());
        });

        JButton searchButton = createStyledButton("Search Users by Name", new Color(0, 173, 181), "search_icon.png");
        searchButton.addActionListener(e -> {
            String name = nameField.getText();
            StringBuilder usersList = new StringBuilder();
            for (User user : userManagement.searchUsersByName(name)) {
                usersList.append("User ID: ").append(user.userId)
                        .append("\nName: ").append(user.name)
                        .append("\nEmail: ").append(user.email)
                        .append("\nRole: ").append(user.role).append("\n\n");
            }
            JOptionPane.showMessageDialog(userFrame, usersList.toString());
        });

        userFrame.add(userIdLabel);
        userFrame.add(userIdField);
        userFrame.add(nameLabel);
        userFrame.add(nameField);
        userFrame.add(emailLabel);
        userFrame.add(emailField);
        userFrame.add(passwordLabel);
        userFrame.add(passwordField);
        userFrame.add(roleLabel);
        userFrame.add(roleComboBox);
        userFrame.add(addButton);
        userFrame.add(deleteButton);
        userFrame.add(updateButton);
        userFrame.add(getButton);
        userFrame.add(listButton);
        userFrame.add(searchButton);

        userFrame.setVisible(true);
    }

    private void handleBookManagement() {
        JFrame bookFrame = new JFrame("Book Management");
        bookFrame.setSize(500, 400);
        bookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookFrame.getContentPane().setBackground(new Color(34, 40, 49));
        bookFrame.setLayout(new GridLayout(10, 2, 10, 10));

        JLabel bookIdLabel = createStyledLabel("Book ID:");
        JTextField bookIdField = new JTextField();
        JLabel titleLabel = createStyledLabel("Title:");
        JTextField titleField = new JTextField();
        JLabel authorLabel = createStyledLabel("Author:");
        JTextField authorField = new JTextField();
        JLabel genreLabel = createStyledLabel("Genre:");
        JTextField genreField = new JTextField();
        JLabel availabilityLabel = createStyledLabel("Availability:");
        JCheckBox availabilityCheckBox = new JCheckBox("Available");
        availabilityCheckBox.setForeground(Color.WHITE);
        availabilityCheckBox.setBackground(new Color(34, 40, 49));

        JButton addButton = createStyledButton("Add Book", new Color(0, 173, 181), "add_icon.png");
        addButton.addActionListener(e -> {
            String bookId = bookIdField.getText();
            String title = titleField.getText();
            String author = authorField.getText();
            String genre = genreField.getText();
            boolean isAvailable = availabilityCheckBox.isSelected();
            bookManagement.addBook(bookId, title, author, genre, isAvailable);
            JOptionPane.showMessageDialog(bookFrame, "Book added successfully!");
        });

        JButton deleteButton = createStyledButton("Delete Book", new Color(237, 85, 59), "delete_icon.png");
        deleteButton.addActionListener(e -> {
            String bookId = bookIdField.getText();
            bookManagement.deleteBook(bookId);
            JOptionPane.showMessageDialog(bookFrame, "Book deleted successfully!");
        });

        JButton updateButton = createStyledButton("Update Book", new Color(0, 173, 181), "update_icon.png");
        updateButton.addActionListener(e -> {
            String bookId = bookIdField.getText();
            String title = titleField.getText();
            String author = authorField.getText();
            String genre = genreField.getText();
            boolean isAvailable = availabilityCheckBox.isSelected();
            bookManagement.updateBook(bookId, title, author, genre, isAvailable);
            JOptionPane.showMessageDialog(bookFrame, "Book updated successfully!");
        });

        JButton getButton = createStyledButton("Get Book by ID", new Color(57, 62, 70), "search_icon.png");
        getButton.addActionListener(e -> {
            String bookId = bookIdField.getText();
            Book book = bookManagement.getBookById(bookId);
            if (book != null) {
                JOptionPane.showMessageDialog(bookFrame, "Book ID: " + book.bookId + "\nTitle: " + book.title + "\nAuthor: " + book.author + "\nGenre: " + book.genre + "\nAvailability: " + (book.isAvailable ? "Available" : "Checked out"));
            } else {
                JOptionPane.showMessageDialog(bookFrame, "Book not found.");
            }
        });

        JButton listButton = createStyledButton("List All Books", new Color(238, 238, 238), "list_icon.png");
        listButton.addActionListener(e -> {
            StringBuilder booksList = new StringBuilder();
            for (Book book : bookManagement.getAllBooks()) {
                booksList.append("Book ID: ").append(book.bookId)
                        .append("\nTitle: ").append(book.title)
                        .append("\nAuthor: ").append(book.author)
                        .append("\nGenre: ").append(book.genre)
                        .append("\nAvailability: ").append(book.isAvailable ? "Available" : "Checked out").append("\n\n");
            }
            JOptionPane.showMessageDialog(bookFrame, booksList.toString());
        });

        JButton searchButton = createStyledButton("Search Books by Title", new Color(0, 173, 181), "search_icon.png");
        searchButton.addActionListener(e -> {
            String title = titleField.getText();
            StringBuilder booksList = new StringBuilder();
            for (Book book : bookManagement.searchBooksByTitle(title)) {
                booksList.append("Book ID: ").append(book.bookId)
                        .append("\nTitle: ").append(book.title)
                        .append("\nAuthor: ").append(book.author)
                        .append("\nGenre: ").append(book.genre)
                        .append("\nAvailability: ").append(book.isAvailable ? "Available" : "Checked out").append("\n\n");
            }
            JOptionPane.showMessageDialog(bookFrame, booksList.toString());
        });

        bookFrame.add(bookIdLabel);
        bookFrame.add(bookIdField);
        bookFrame.add(titleLabel);
        bookFrame.add(titleField);
        bookFrame.add(authorLabel);
        bookFrame.add(authorField);
        bookFrame.add(genreLabel);
        bookFrame.add(genreField);
        bookFrame.add(availabilityLabel);
        bookFrame.add(availabilityCheckBox);
        bookFrame.add(addButton);
        bookFrame.add(deleteButton);
        bookFrame.add(updateButton);
        bookFrame.add(getButton);
        bookFrame.add(listButton);
        bookFrame.add(searchButton);

        bookFrame.setVisible(true);
    }

    private JButton createStyledButton(String text, Color bgColor, String iconPath) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        java.net.URL imgURL = getClass().getResource(iconPath);
        if (imgURL != null) {
            button.setIcon(new ImageIcon(imgURL));
        }
        return button;
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementSystem().setVisible(true));
    }
}
