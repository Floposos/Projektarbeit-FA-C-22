package GUI;
import Logic.AdministratorManager;
import Logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUI {

    private AdministratorManager admin;
    private EventManager event;
    public int adminID;

    JFrame frame = new JFrame("Auswahlfenster");
    JPanel panel = new JPanel();
    private List<JComboBox<String>> sportDropdowns; // List to store sport dropdowns
    private List<JFormattedTextField> dateFields;

    public GUI() {
        this.admin = new AdministratorManager();
        this.event = new EventManager();
        // Nimbus-Look-and-Feel aktivieren
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Nimbus Look-and-Feel konnte nicht geladen werden.");
        }

        // Grundlegende JFrame-Einstellungen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Startpanel erstellen
        createRoleSelectionPanel();

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Erstellt das Auswahl-Panel (Event-Manager / Verein)
    private void createRoleSelectionPanel() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel selectRoleLabel = new JLabel("Wählen Sie aus, was auf Sie zutrifft:");
        selectRoleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(selectRoleLabel, gbc);

        JButton buttonEventManager = new JButton("Event-Manager");
        JButton buttonVerein = new JButton("Verein");

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(buttonEventManager, gbc);

        gbc.gridx = 1;
        panel.add(buttonVerein, gbc);

        // ActionListener für die Buttons
        buttonEventManager.addActionListener(e -> createLoginPanel());
        buttonVerein.addActionListener(e -> showVereinPanel()); // Platzhalter für Verein

        panel.revalidate();
        panel.repaint();
    }

    // Login-Panel
    private void createLoginPanel() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel loginLabel = new JLabel("Anmeldung");
        loginLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        panel.add(loginLabel, gbc);

        JLabel userLabel = new JLabel("Benutzername:");
        JTextField userField = new JTextField(15);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(userLabel, gbc);
        gbc.gridx = 1;
        panel.add(userField, gbc);

        JLabel passLabel = new JLabel("Passwort:");
        JPasswordField passField = new JPasswordField(15);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passLabel, gbc);
        gbc.gridx = 1;
        panel.add(passField, gbc);

        JButton loginButton = new JButton("Einloggen");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(loginButton, gbc);

        //LOGIN fertig, nur zum weiterarbeiten Auskommentiert
//        loginButton.addActionListener(e -> {
//            adminID = Integer.parseInt(userField.getText());
//            String password = new String(passField.getPassword());
//            if (!admin.checkAuthorization(adminID, password)) {
//                JOptionPane.showMessageDialog(panel, "Das Passwort oder der Nutzername stimmen nicht!", "Fehler", JOptionPane.ERROR_MESSAGE);
//            } else {
                showActionSelectionPanel();
//            }
//        });


        // Pfeil-Button unten links
        JButton backButton = createBackButton(this::createRoleSelectionPanel);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);
        panel.revalidate();
        panel.repaint();

    }

    // Platzhalter für die Verein-Seite
    private void showVereinPanel() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());

        JLabel vereinLabel = new JLabel("Verein-Seite in Arbeit...");
        vereinLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(vereinLabel);

        panel.revalidate();
        panel.repaint();


    }

    // Erstellt die Haupt-Auswahlseite
    private void showActionSelectionPanel() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel actionLabel = new JLabel("Was möchten Sie tun?");
        actionLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(actionLabel, gbc);

        JButton buttonNewEvent = new JButton("Neues Event anlegen");
        JButton buttonManageEvent = new JButton("Event Verwalten");
        JButton buttonAddManager = new JButton("Neuen Event-Manager hinzufügen");
        JButton buttonManageSports = new JButton("Sportarten Verwalten");

        //NewEvent Button
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(buttonNewEvent, gbc);
        gbc.gridx = 1;
        panel.add(buttonManageEvent, gbc);

        //AddManager Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(buttonAddManager, gbc);
        gbc.gridx = 1;
        panel.add(buttonManageSports, gbc);

        //back-button
        JButton backButton = createBackButton(this::createRoleSelectionPanel);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);

        buttonNewEvent.addActionListener(e -> showNewEventPanel());
        buttonManageEvent.addActionListener(e -> showManageEventPanel());
        buttonAddManager.addActionListener(e -> showAddManagerPanel());
        buttonManageSports.addActionListener(e -> showManageSportsPanel());

        panel.revalidate();
        panel.repaint();
    }

    // Panel für Neues Event
    private void showNewEventPanel() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel newEventLabel = new JLabel("Neues Event");
        newEventLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        panel.add(newEventLabel, gbc);

        JLabel nameLabel = new JLabel("Event-Name:");
        JTextField nameField = new JTextField(15);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        JButton saveButton = new JButton("Speichern");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(saveButton, gbc);

        // Pfeil-Button unten links
        JButton backButton = createBackButton(this::showActionSelectionPanel);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);

        // **Hier wird der Event-Name erst beim Klick abgerufen**
        saveButton.addActionListener(e -> {
            String eventName = nameField.getText(); // Text aus dem Textfeld abrufen
            int adminID = 1000; //zum Testen

            if (eventName.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Bitte einen Event-Namen eingeben!", "Fehler", JOptionPane.ERROR_MESSAGE);
            } else {

              //  event.addEvent(adminID,eventName);

                showNewSportEventPanel(eventName);
                //showNewSportEventPanel(eventName); // Event-Name an die nächste Methode übergeben
            }
        });

        panel.revalidate();
        panel.repaint();
    }

    private void showNewSportEventPanel(String eventName) {
        panel.removeAll(); // Clear the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Überschrift
        JLabel headerLabel = new JLabel(eventName);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headerLabel, gbc);

        // Sportart hinzufügen Button
        JButton addSportButton = new JButton("Sportart hinzufügen");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 5, 5, 5);
        panel.add(addSportButton, gbc);

        // Event Speichern Button
        JButton saveButton = new JButton("Event Speichern");
        gbc.gridx = 4;
        gbc.gridy = 1; // Startpunkt (wird bei neuen Rows nach unten verschoben)
        gbc.insets = new Insets(20, 5, 5, 5);
        panel.add(saveButton, gbc);

        // ActionListener für den Hinzufügen-Button
        addSportButton.addActionListener(e -> addSportRow());
        // ActionListener für den Event Speichern Button
        saveButton.addActionListener(e -> showActionSelectionPanel());

        panel.revalidate();
        panel.repaint();
    }

    private void addSportRow() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        if (sportDropdowns == null || dateFields == null) {
            sportDropdowns = new ArrayList<>();
            dateFields = new ArrayList<>();
        }

        // Neue Zeile für Dropdown und Kalender
        int row = sportDropdowns.size() + 2; // Startposition nach Überschrift und Button

        // Dropdown für Sportarten
        JComboBox<String> sportComboBox = new JComboBox<>(new String[]{"Schwimmen 25m", "Laufen", "Schwimmen 50m"});
        sportDropdowns.add(sportComboBox); // Speichern in der Liste
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        panel.add(sportComboBox, gbc);

        // Kalenderobjekt für Datum
        JFormattedTextField dateField = new JFormattedTextField("TT/MM/JJJJ");
        dateFields.add(dateField); // Speichern in der Liste
        gbc.gridx = 1;
        panel.add(dateField, gbc);

        // Event Speichern Button nach unten verschieben
        JButton saveButton = (JButton) panel.getComponent(panel.getComponentCount() - 1);
        gbc.gridx = 0;
        gbc.gridy = row + 1;
        gbc.gridwidth = 2;
        panel.remove(saveButton); // Entferne den alten Button
        panel.add(saveButton, gbc); // Füge ihn darunter wieder hinzu

        panel.revalidate();
        panel.repaint();
    }


    // Panel für Event verwalten
    private void showManageEventPanel() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        int anzahlTeilnehmer = 5; //TODO aus db ziehen, je nach Event und Sportart

        JLabel manageEventLabel = new JLabel("Event Verwalten");
        manageEventLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        panel.add(manageEventLabel, gbc);

//
//        List<Event> events = event.getAllEvents();
//        JComboBox<Event> eventComboBox = new JComboBox<>(events.toArray(new Event[0]));
//
//// Optional: Überschreibe die toString-Methode in der Event-Klasse
//// oder füge einen CustomRenderer hinzu:
//        eventComboBox.setRenderer(new DefaultListCellRenderer() {
//            @Override
//            public Component getListCellRendererComponent(JList<?> list, Object value,
//                                                          int index, boolean isSelected, boolean cellHasFocus) {
//                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//                if (value instanceof Event) {
//                    Event event = (Event) value;
//                    setText(event.getName()); // oder welches Attribut du anzeigen möchtest
//                }
//                return this;
//            }
//        });


        JLabel eventLabel = new JLabel("Event:");
        JComboBox<String> eventComboBox = new JComboBox<>(new String[]{"Event 1", "Event 2", "Event 3"});
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(eventLabel, gbc);
        gbc.gridx = 1;
        panel.add(eventComboBox, gbc);

        JLabel sportLabel = new JLabel("Sportart:");
        JComboBox<String> sportComboBox = new JComboBox<>(new String[]{"Schwimmen 25m", "Teilnehmer 2"}); //TODO, nur die Sportarten zur auswahl anzeigen, die diesem Event zugewiesen wurden
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(sportLabel, gbc);
        gbc.gridx = 1;
        panel.add(sportComboBox, gbc);

        // Tabelle für Teilnehmer und ihre Ergebnisse
        JLabel participantsLabel = new JLabel("Teilnehmer Ergebnisse");
        participantsLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(participantsLabel, gbc);

        // TODO Teilnehmer und Ergebniseingabe einrichten, siee Quelle: https://www.java-forum.org/thema/swing-eingabefelder-in-tabelle.27003/

        // Buttons für Abschluss
        JButton finishSportTypeButton = new JButton("Sportart abschließen");
        JButton finishEventButton = new JButton("Event abschließen");
        gbc.gridx = 0;
        gbc.gridy = 4 + anzahlTeilnehmer; // Buttons unterhalb der Tabelle
        gbc.gridwidth = 2;
        panel.add(finishSportTypeButton, gbc);
        gbc.gridy = 5 + anzahlTeilnehmer;
        panel.add(finishEventButton, gbc);

        finishSportTypeButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Sportart erfolgreich abgeschlossen und gespeichert!");
        });

        finishEventButton.addActionListener(e -> showActionSelectionPanel());

        panel.revalidate();
        panel.repaint();

        // Pfeil-Button unten links
        JButton backButton = createBackButton(this::showActionSelectionPanel);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);

        panel.revalidate();
        panel.repaint();
    }


    private void showAddManagerPanel() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel newEventLabel = new JLabel("Neuer Event-Manager");
        newEventLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        panel.add(newEventLabel, gbc);

        JLabel VorNameLabel = new JLabel("Vorname:");
        JTextField VorNameField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(VorNameLabel, gbc);
        gbc.gridx = 1;
        //gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(VorNameField, gbc);

        JLabel NachNameLabel = new JLabel("Nachname:");
        JTextField NachNameField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(NachNameLabel, gbc);
        gbc.gridx = 1;
        panel.add(NachNameField, gbc);

        JLabel passLabel = new JLabel("Passwort:");
        JPasswordField passField = new JPasswordField(15);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(passLabel, gbc);
        gbc.gridx = 1;
        panel.add(passField, gbc);

        JButton saveButton = new JButton("Anlegen");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(saveButton, gbc);

        // Event-Handler für den Button "Anlegen"
        saveButton.addActionListener(e -> {
            // Daten aus den Textfeldern abrufen und in Variablen speichern
            String firstname = VorNameField.getText();
            String lastname = NachNameField.getText();
            String password = new String(passField.getPassword());  // Passwörter abrufen

            // Debug-Ausgabe oder weitere Verarbeitung
            System.out.println("Vorname: " + firstname);
            System.out.println("Nachname: " + lastname);
            System.out.println("Passwort: " + password);

            // Fehlerbehandlung und Datenübergabe an AdministratorManager
            try {
                admin.addAdmin(firstname, lastname, password);
                JOptionPane.showMessageDialog(frame, "Manager " + firstname + " erfolgreich gespeichert!");
                showActionSelectionPanel();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            }
            // Weiterleitung zur nächsten Ansicht
            showActionSelectionPanel();
        });

        // Pfeil-Button unten links
        JButton backButton = createBackButton(this::showActionSelectionPanel);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);

        panel.revalidate();
        panel.repaint();
        //Test
    }

    private void  showManageSportsPanel() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel actionLabel = new JLabel("Was möchten Sie tun?");
        actionLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(actionLabel, gbc);

        JButton buttonNewSportType = new JButton("Neue Sportart anlegen");
        JButton buttonManageSportsType = new JButton("Sportart Verwalten");

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(buttonNewSportType, gbc);
        gbc.gridx = 1;
        panel.add(buttonManageSportsType, gbc);

        buttonNewSportType.addActionListener(e -> showNewSportsTypePanel());
        buttonManageSportsType.addActionListener(e -> showManageSportsTypePanel());

        // Pfeil-Button unten links
        JButton backButton = createBackButton(this::showActionSelectionPanel);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);

        panel.revalidate();
        panel.repaint();
    }

    private void showNewSportsTypePanel() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

//        JLabel newSportsTypeLabel = new JLabel("Neuer Event-Manager");
//        newEventLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        gbc.insets = new Insets(10, 10, 20, 10);
//        panel.add(newEventLabel, gbc);
//        panel.revalidate();
//        panel.repaint();
    }

    private void showManageSportsTypePanel() {
        //TODO
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }

    private JButton createBackButton(Runnable action) {
        JButton backButton = new JButton("←");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        backButton.setMargin(new Insets(2, 5, 2, 5));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(e -> action.run());
        return backButton;
    }

}
