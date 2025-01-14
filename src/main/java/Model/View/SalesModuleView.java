package Model.View;

import Model.Bilet;
import Model.Repository.MenuRepository;
import Model.Repository.RepertuarRepository;
import Model.SalaKinowa;
import Model.Service.TransakcjaService;
import Model.Produkt;
import Model.Model.Seans;
import org.jdesktop.swingx.JXDatePicker;

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

        // Lista seansów (tym razem bez nasłuchiwania listSelection – zamiast tego będzie przycisk)
        DefaultListModel<Seans> seanceListModel = new DefaultListModel<>();
        JList<Seans> seanceList = new JList<>(seanceListModel);
        JScrollPane scrollPane = new JScrollPane(seanceList);

        // NOWY DatePicker zamiast JSpinner
        JPanel datePanel = new JPanel(new FlowLayout());
        JLabel dateLabel = new JLabel("Wybierz datę:");
        JXDatePicker datePicker = new JXDatePicker();

        datePicker.addActionListener(e -> {
            if (datePicker.getDate() != null) {
                // Pobieramy datę z JXDatePicker
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String selectedDate = sdf.format(datePicker.getDate());
                // Aktualizujemy listę seansów
                updateSeanceList(selectedDate, seanceListModel);
            }
        });

        datePanel.add(dateLabel);
        datePanel.add(datePicker);

        // PRZYCISK do wyświetlenia okna miejsc
        JButton pickSeanceButton = new JButton("Wybierz seans");
        pickSeanceButton.addActionListener(e -> {
            Seans selectedSeans = seanceList.getSelectedValue();
            if (selectedSeans == null) {
                JOptionPane.showMessageDialog(panel, "Nie wybrano żadnego seansu z listy!",
                        "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }
            openSeatSelectionDialog(selectedSeans);
        });

        // Panel dolny z przyciskiem
        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.add(pickSeanceButton);

        panel.add(datePanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

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

        // Lista transakcji (pozycje) w formie tekstowej
        JList<String> transactionList = new JList<>(transactionListModel);
        JScrollPane scrollPane = new JScrollPane(transactionList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // --- Główne przyciski ---
        JButton clearButton = new JButton("Wyczyść");
        JButton finalizeButton = new JButton("Finalizuj");
        JButton znizkaButton = new JButton("Dodaj zniżkę");

        // Obsługa przycisku "Wyczyść"
        clearButton.addActionListener(e -> {
            transakcjaService.wyczyscTransakcje();
            transactionListModel.clear();
            updateTotalPrice();
        });

        // Obsługa przycisku "Dodaj zniżkę"
        znizkaButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(
                    null,
                    "Podaj procentową wartość zniżki (np. 10 = 10%)",
                    "Zniżka",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (input == null || input.trim().isEmpty()) {
                // Użytkownik anulował lub nic nie wpisał
                return;
            }

            try {
                double discountPercent = Double.parseDouble(input.trim());
                if (discountPercent < 0 || discountPercent > 100) {
                    JOptionPane.showMessageDialog(null,
                            "Wartość zniżki musi być w zakresie 0–100.",
                            "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                float discountValue = (float) (transakcjaService.getAktualnaTransakcja().getKwotaTransakcji() * (discountPercent / 100.0));
                transakcjaService.dodajZnizke(discountValue);

                // Odswież kwotę
                updateTotalPrice();

                JOptionPane.showMessageDialog(null,
                        "Zniżka " + discountPercent + "% została zastosowana.",
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Nieprawidłowa wartość zniżki!",
                        "Błąd",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Obsługa przycisku "Finalizuj"
        finalizeButton.addActionListener(e -> {
            // 1) Pobierz listę biletów z aktualnej transakcji
            List<Bilet> bilety = transakcjaService.getAktualnaTransakcja().getBilety();
            // 2) Dla każdego biletu oznacz miejsca w tablicy seansu
            for (Bilet b : bilety) {
                Seans s = b.getSeans();
                if (s != null && s.getSalaKinowa() != null) {
                    int[][] mtx = s.getSalaKinowa().getMiejsca();
                    int row = rowFromSeatName(b.getMiejsca());
                    int col = colFromSeatName(b.getMiejsca());
                    if (row >= 0 && col >= 0) {
                        mtx[row][col] = 1;
                    }
                }
            }

            // 3) Zapisz cały repertuar do pliku (blokujemy te miejsca na stałe)
            RepertuarRepository.save();

            // 4) Czyścimy transakcję i listę
            transakcjaService.finalizujTransakcje();
            transactionListModel.clear();
            updateTotalPrice();

            JOptionPane.showMessageDialog(null,
                    "Transakcja zakończona. Miejsca zablokowane w repertuarze.",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // Panel na przyciski
        // UWAGA: GridLayout(1, 3) – bo mamy 3 przyciski
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 3));
        buttonsPanel.add(clearButton);
        buttonsPanel.add(znizkaButton);
        buttonsPanel.add(finalizeButton);

        // Panel na dole, który zawiera i przyciski, i sumę
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonsPanel, BorderLayout.CENTER);
        bottomPanel.add(totalPriceLabel, BorderLayout.SOUTH);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    private static void updateDateTime(JLabel dateTimeLabel) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateTimeLabel.setText("Data i godzina: " + formatter.format(new Date()));
    }

    private static void updateSeanceList(String date, DefaultListModel<Seans> listModel) {
        listModel.clear();
        List<Seans> seances = RepertuarRepository.findByDate(date);
        for (Seans seans : seances) {
            listModel.addElement(seans);
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

    private static void openSeatSelectionDialog(Seans seans) {
        // Tworzymy okno dialogowe (modalne)
        JDialog seatDialog = new JDialog((Frame) null, "Wybór miejsc - " + seans.getFilm().getTytul(), true);
        seatDialog.setSize(600, 500);
        seatDialog.setLocationRelativeTo(null);
        seatDialog.setLayout(new BorderLayout());

        // =========== 1. PANEL Z INFORMACJAMI O FILMIE (NORTH) ============
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel titleLabel = new JLabel("Tytuł: " + seans.getFilm().getTytul());
        JLabel castLabel = new JLabel("Obsada: " + seans.getFilm().getObsada());
        JLabel lengthLabel = new JLabel("Czas trwania: " + seans.getFilm().getCzasTrwania() + " min");
        JLabel descLabel = new JLabel("Opis: " + seans.getFilm().getOpis());
        JLabel dateLabel = new JLabel("Data seansu: " + seans.getData());
        JLabel hourLabel = new JLabel("Godzina seansu: " + seans.getGodzina());
        JLabel typeLabel = new JLabel("Rodzaj: " + seans.getRodzaj()
                + " | Cena: " + seans.getCenaNormalna() + " PLN");

        infoPanel.add(titleLabel);
        infoPanel.add(castLabel);
        infoPanel.add(lengthLabel);
        infoPanel.add(descLabel);
        infoPanel.add(dateLabel);
        infoPanel.add(hourLabel);
        infoPanel.add(typeLabel);

        seatDialog.add(infoPanel, BorderLayout.NORTH);

        // =========== 2. SPRAWDZAMY SALĘ I TABLICĘ MIEJSC =============
        SalaKinowa sala = seans.getSalaKinowa();
        if (sala == null) {
            JOptionPane.showMessageDialog(null, "Brak informacji o sali dla tego seansu!");
            return;
        }
        int[][] miejsca = sala.getMiejsca();
        if (miejsca == null) {
            JOptionPane.showMessageDialog(null, "Brak rozkładu miejsc dla tej sali!");
            return;
        }
        int rows = miejsca.length;
        int cols = miejsca[0].length; // zakładamy prostokątną macierz

        // =========== 3. PANEL ŚRODKOWY (CENTER) ze "screenPanel" + "seatsPanel" ============

        // Główny panel w części CENTER, w którym będzie panel "EKRAN" (NORTH)
        // i panel z fotelami (CENTER)
        JPanel centerPanel = new JPanel(new BorderLayout());

        // 3a. Panel z rysowanym prostokątem i napisem "EKRAN"
        // Można to zrobić jako anonimowa klasa potomna JPanel z nadpisaniem paintComponent().
        JPanel screenPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Rysujemy prostokąt symbolizujący ekran
                int rectHeight = 30;
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(10, 10, getWidth() - 20, rectHeight);
                // Napis "EKRAN"
                g.setColor(Color.BLACK);
                FontMetrics fm = g.getFontMetrics();
                String screenText = "EKRAN";
                int textWidth = fm.stringWidth(screenText);
                g.drawString(screenText, (getWidth() - textWidth)/2, 10 + (rectHeight/2 + fm.getAscent()/2));
            }
        };
        screenPanel.setPreferredSize(new Dimension(0, 50));
        screenPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        centerPanel.add(screenPanel, BorderLayout.NORTH);

        // 3b. Panel z przyciskami miejsc (GridLayout)
        JPanel seatsPanel = new JPanel(new GridLayout(rows, cols, 5, 5));
        java.util.List<JToggleButton> seatButtons = new java.util.ArrayList<>();
        java.util.List<Bilet> nowoWybraneBilety = new java.util.ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                JToggleButton seatBtn = new JToggleButton("R" + (r+1) + "M" + (c+1));
                seatBtn.setOpaque(true);
                seatBtn.setContentAreaFilled(true);

                if (miejsca[r][c] == 1) {
                    // Miejsce zajęte
                    seatBtn.setEnabled(false);
                    seatBtn.setBackground(Color.RED);
                } else {
                    // Miejsce wolne
                    seatBtn.setBackground(Color.GREEN);

                    seatBtn.addItemListener(e -> {
                        if (seatBtn.isSelected()) {
                            seatBtn.setBackground(Color.YELLOW);
                            // Dodajemy bilet do listy tymczasowych biletów
                            Bilet tmpBilet = new Bilet();
                            tmpBilet.setSeans(seans);
                            tmpBilet.setMiejsca(seatBtn.getText());
                            tmpBilet.setTyp("normalny");
                            tmpBilet.setCenaBiletu(seans.getCenaNormalna());

                            nowoWybraneBilety.add(tmpBilet);
                        } else {
                            seatBtn.setBackground(Color.GREEN);
                            // Usuwamy bilet, jeśli user odkliknął
                            nowoWybraneBilety.removeIf(b -> b.getMiejsca().equals(seatBtn.getText()));
                        }
                    });
                }
                seatsPanel.add(seatBtn);
                seatButtons.add(seatBtn);
            }
        }

        centerPanel.add(seatsPanel, BorderLayout.CENTER);

        // Teraz dodajemy centerPanel do seatDialog
        seatDialog.add(centerPanel, BorderLayout.CENTER);

        // =========== 4. PRZYCISK "DODAJ DO TRANSAKCJI" (SOUTH) ============
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton confirmButton = new JButton("Dodaj do transakcji");
        bottomPanel.add(confirmButton);

        confirmButton.addActionListener(e -> {
            if (nowoWybraneBilety.isEmpty()) {
                JOptionPane.showMessageDialog(seatDialog, "Nie wybrano żadnych wolnych miejsc!");
                return;
            }

            // Dodajemy WSZYSTKIE bilety z nowoWybraneBilety do transakcji
            for (Bilet b : nowoWybraneBilety) {
                transakcjaService.dodajBiletDoTransakcji(b);
                transactionListModel.addElement(
                        String.format("Bilet: %s, %s, Cena: %.2f PLN",
                                b.getSeans().getFilm().getTytul(),
                                b.getMiejsca(),
                                b.getCenaBiletu())
                );
            }
            updateTotalPrice();

            // Zamykamy okno (miejsce w pliku oznaczamy dopiero przy finalizacji)
            seatDialog.dispose();
        });

        seatDialog.add(bottomPanel, BorderLayout.SOUTH);

        seatDialog.setVisible(true);
    }

    private static int rowFromSeatName(String seatName) {
        // seatName np. "R1M3"
        // Szybka wersja:
        int rIndex = seatName.indexOf("R");
        int mIndex = seatName.indexOf("M");
        if (rIndex >= 0 && mIndex > rIndex) {
            String rowStr = seatName.substring(rIndex+1, mIndex);
            try {
                return Integer.parseInt(rowStr) - 1;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    private static int colFromSeatName(String seatName) {
        int mIndex = seatName.indexOf("M");
        if (mIndex >= 0) {
            String colStr = seatName.substring(mIndex+1);
            try {
                return Integer.parseInt(colStr) - 1;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

}
