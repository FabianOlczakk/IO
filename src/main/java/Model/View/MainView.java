package Model.View;

import Model.View.LoginView;
import Model.View.SalesModuleView;

import javax.swing.*;
import java.awt.*;

public class MainView {
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Cinema 3000 - Wybór modułu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton salesButton = new JButton("Moduł Sprzedaży");
        JButton managementButton = new JButton("Moduł Zarządzania");

        salesButton.addActionListener(e -> {
            frame.dispose(); // Zamknięcie głównego ekranu
            new LoginView(user -> {
                SalesModuleView.createAndShowGUI(user.getLogin()); // Przekazanie nazwy użytkownika
            });
        });

        managementButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Moduł Zarządzania (do zaimplementowania).");
        });

        panel.add(salesButton);
        panel.add(managementButton);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainView::createAndShowGUI);
    }
}
