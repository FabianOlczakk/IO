package Model.View;

import Model.Controller.UzytkownikController;
import Model.Uzytkownik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class LoginView {
    public LoginView(Consumer<Uzytkownik> onLoginSuccess) {
        JFrame frame = new JFrame("Logowanie");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel userLabel = new JLabel("Login:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Hasło:");
        JPasswordField passField = new JPasswordField();
        JButton loginButton = new JButton("Zaloguj");

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(new JLabel());
        panel.add(loginButton);

        loginButton.addActionListener((ActionEvent e) -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            Uzytkownik user = UzytkownikController.zaloguj(username, password);
            if (user != null) {
                frame.dispose(); // Zamknij okno logowania
                onLoginSuccess.accept(user); // Przekaż zalogowanego użytkownika do dalszej logiki
            } else {
                JOptionPane.showMessageDialog(frame, "Niepoprawne dane logowania", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
