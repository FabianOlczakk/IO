package Model.View;

import Model.Repository.MenuRepository;
import Model.Repository.RepertuarRepository;
import Model.Service.TransakcjaService;
import Model.Produkt;
import Model.Model.Seans;
import Model.View.MainView;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SalesModuleView {
    private static TransakcjaService transakcjaService = new TransakcjaService(); // Zarządzanie transakcjami
    private static JLabel totalPriceLabel = new JLabel("Suma: 0.00 PLN", SwingConstants.CENTER); // Wyświetlanie sumy transakcji
    private static DefaultListModel<String> transactionListModel = new DefaultListModel<>(); // Lista transakcji

    public static void createAndShowGUI(String currentUser) {
        JFrame frame = new JFrame("Cinema 3000 - Moduł Sprzedaży");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Pasek górny
        JPanel topPanel = createTopPanel(currentUser, frame);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Zakładki
        JTabbedPane tabbedPane = new JTabbedPane();

        // Zakładka "Seanse"
        JPanel seancePanel = createSeancePanel();
        tabbedPane.addTab("Seanse", seancePanel);

        // Zakładka "Produkty Barowe"
        JPanel menuPanel = createMenuPanel();
        tabbedPane.addTab("Produkty Barowe", menuPanel);

        // Panel transakcji
        JPanel transactionPanel = createTransactionPanel();
        mainPanel.add(transactionPanel, BorderLayout.EAST);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createTopPanel(String currentUser, JFrame frame) {
        JPanel topPanel = new JPanel(new BorderLayout());

        // Panel z użytkownikiem i przyciskiem wyloguj
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel userLabel = new JLabel("Zalogowany użytkownik: " + currentUser);
        JButton logoutButton = new JButton("Wyloguj");
        logoutButton.addActionListener(e -> {
            frame.dispose();
            MainView.createAndShowGUI();
        });
        userPanel.add(userLabel);
        userPanel.add(logoutButton);

        // Panel z datą i godziną
        JPanel dateTimePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel dateTimeLabel = new JLabel();
        updateDateTime(dateTimeLabel);
        Timer timer = new Timer(1000, e -> updateDateTime(dateTimeLabel));
        timer.start();
        dateTimePanel.add(dateTimeLabel);

        topPanel.add(userPanel, BorderLayout.WEST);
        topPanel.add(dateTimePanel, BorderLayout.EAST);

        return topPanel;
    }

    private static JPanel createSeancePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Lista seansów
        DefaultListModel<String> seanceListModel = new DefaultListModel<>();
        JList<String> seanceList = new JList<>(seanceListModel);
        JScrollPane scrollPane = new JScrollPane(seanceList);

        // Wybór daty
        JPanel datePanel = new JPanel(new FlowLayout());
        JLabel dateLabel = new JLabel("Wybierz datę:");
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);

        updateSeanceList(((JSpinner.DateEditor) dateSpinner.getEditor()).getFormat().format(dateSpinner.getValue()), seanceListModel);

        dateSpinner.addChangeListener(e -> {
            String selectedDate = ((JSpinner.DateEditor) dateSpinner.getEditor()).getFormat().format(dateSpinner.getValue());
            updateSeanceList(selectedDate, seanceListModel);
        });

        seanceList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedSeance = seanceList.getSelectedValue();
                if (selectedSeance != null) {
                    openSeatSelectionDialog(selectedSeance);
                }
            }
        });

        datePanel.add(dateLabel);
        datePanel.add(dateSpinner);

        panel.add(datePanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        DefaultListModel<String> menuListModel = new DefaultListModel<>();
        JList<String> menuList = new JList<>(menuListModel);
        JScrollPane scrollPane = new JScrollPane(menuList);

        JButton addToTransactionButton = new JButton("Dodaj do transakcji");
        addToTransactionButton.addActionListener(e -> {
            String selectedProduct = menuList.getSelectedValue();
            if (selectedProduct != null) {
                try {
                    int quantity = Integer.parseInt(JOptionPane.showInputDialog("Podaj ilość:"));
                    addProductToTransaction(selectedProduct, quantity);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Nieprawidłowa ilość.");
                }
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(addToTransactionButton, BorderLayout.SOUTH);

        // Wczytaj listę produktów
        updateMenuList(menuListModel);

        return panel;
    }

    private static JPanel createTransactionPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Aktualna Transakcja");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        JList<String> transactionList = new JList<>(transactionListModel);
        JScrollPane scrollPane = new JScrollPane(transactionList);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
        JButton clearButton = new JButton("Wyczyść");
        JButton finalizeButton = new JButton("Finalizuj");

        clearButton.addActionListener(e -> {
            transakcjaService.wyczyscTransakcje();
            transactionListModel.clear();
            updateTotalPrice();
        });

        finalizeButton.addActionListener(e -> {
            transakcjaService.finalizujTransakcje();
            transactionListModel.clear();
            updateTotalPrice();
            JOptionPane.showMessageDialog(null, "Transakcja zakończona.");
        });

        buttonsPanel.add(clearButton);
        buttonsPanel.add(finalizeButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.SOUTH);
        panel.add(totalPriceLabel, BorderLayout.SOUTH);

        return panel;
    }

    private static void updateDateTime(JLabel dateTimeLabel) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateTimeLabel.setText("Data i godzina: " + formatter.format(new Date()));
    }

    private static void updateSeanceList(String date, DefaultListModel<String> listModel) {
        listModel.clear();
        List<Seans> seances = RepertuarRepository.findByDate(date);
        for (Seans seans : seances) {
            listModel.addElement(formatSeance(seans));
        }
    }

    private static void updateMenuList(DefaultListModel<String> listModel) {
        listModel.clear();
        List<Produkt> products = MenuRepository.findAll();
        for (Produkt product : products) {
            listModel.addElement(formatProduct(product));
        }
    }

    private static void addProductToTransaction(String productDescription, int quantity) {
        // Znajdź produkt na podstawie opisu
        List<Produkt> produkty = MenuRepository.findAll(); // Pobieramy listę produktów z repozytorium
        Produkt selectedProduct = null;

        for (Produkt produkt : produkty) {
            if (productDescription.contains(produkt.getNazwa())) {
                selectedProduct = produkt;
                break;
            }
        }

        if (selectedProduct != null) {
            // Dodajemy artykuł do transakcji
            transakcjaService.dodajArtykulDoTransakcji(new Model.Artykul(selectedProduct, quantity));
            transactionListModel.addElement(selectedProduct.getNazwa() + " x" + quantity + " - " + (selectedProduct.getCenaNormalna() * quantity) + " PLN");
            updateTotalPrice();
        } else {
            JOptionPane.showMessageDialog(null, "Nie znaleziono produktu: " + productDescription, "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }


    private static void updateTotalPrice() {
        totalPriceLabel.setText("Suma: " + transakcjaService.getAktualnaTransakcja().getKwotaTransakcji() + " PLN");
    }

    private static String formatSeance(Seans seans) {
        return String.format("Film: %s | Data: %s | Godzina: %s | Cena: %.2f PLN",
                seans.getFilm().getTytul(),
                seans.getData(),
                seans.getGodzina(),
                seans.getCenaNormalna());
    }

    private static String formatProduct(Produkt product) {
        return String.format("%s | Typ: %s | Cena: %.2f PLN",
                product.getNazwa(),
                product.getRodzaj(),
                product.getCenaNormalna());
    }

    private static void openSeatSelectionDialog(String seance) {
        JOptionPane.showMessageDialog(null, "Okno wyboru miejsc (do zaimplementowania)");
    }
}
