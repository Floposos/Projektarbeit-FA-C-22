package GUI;

import javax.swing.*;
import java.awt.*;

public class GUI {

    JFrame frame = new JFrame("Auswahlfenster");
    JPanel panel = new JPanel();

    public GUI() {
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

        loginButton.addActionListener(e -> showActionSelectionPanel());

        // Zurück-Button
        JButton backButton = new JButton("←");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        backButton.setMargin(new Insets(2,5,2,5));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);

        // Pfeil-Button unten links
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);
        backButton.addActionListener(e -> createRoleSelectionPanel());

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
        JButton backButton = new JButton("←");

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
        backButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        backButton.setMargin(new Insets(2,5,2,5));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);

        buttonNewEvent.addActionListener(e -> showNewEventPanel());
        buttonManageEvent.addActionListener(e -> showManageEventPanel());
        buttonAddManager.addActionListener(e -> showAddManagerPanel());
        buttonManageSports.addActionListener(e -> showManageSportsPanel());
        backButton.addActionListener(e -> createRoleSelectionPanel());

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

        // Zurück-Button
        JButton backButton = new JButton("←");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        backButton.setMargin(new Insets(2,5,2,5));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);

        // Pfeil-Button unten links
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);

        String eventName = nameField.getText();
        saveButton.addActionListener(e -> showNewSportEventPanel(eventName));
//            JOptionPane.showMessageDialog(frame, "Event erfolgreich gespeichert!");
//            showActionSelectionPanel(););
        backButton.addActionListener(e -> showActionSelectionPanel());



        panel.revalidate();
        panel.repaint();
    }

    private void showNewSportEventPanel(String eventName){
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();



        JLabel EventNameLabel = new JLabel("Test");
        EventNameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        panel.add(EventNameLabel, gbc);

        JLabel eventLabel = new JLabel("Sportart:");
        JComboBox<String> eventComboBox = new JComboBox<>(new String[]{"Schwimmen 25m", "Laufen", "Schwimmen 50m"}); // TODO aus DB aus T_sportart
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(eventLabel, gbc);
        gbc.gridx = 1;
        panel.add(eventComboBox, gbc);

        JLabel dateLabel = new JLabel("Datum:");
        JFormattedTextField dateField = new JFormattedTextField("TT/MM/JJJJ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(dateLabel, gbc);
        gbc.gridx = 1;
        panel.add(dateField, gbc);

//        JComboBox<String> participantComboBox = new JComboBox<>(new String[]{"Teilnehmer 1", "Teilnehmer 2"});
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        panel.add(participantLabel, gbc);
//        gbc.gridx = 1;
//        panel.add(participantComboBox, gbc);

        JLabel timeLabel = new JLabel("Gemessene Zeit:");
        JTextField timeField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(timeLabel, gbc);
        gbc.gridx = 1;
        panel.add(timeField, gbc);

        JButton finishEventButton = new JButton("Event abschließen");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(finishEventButton, gbc);

        // Zurück-Button
        JButton backButton = new JButton("←");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        backButton.setMargin(new Insets(2,5,2,5));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);

        // Pfeil-Button unten links
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);

        finishEventButton.addActionListener(e -> showActionSelectionPanel());
        backButton.addActionListener(e -> showNewEventPanel());

        panel.revalidate();
        panel.repaint();
    }
//

//        EventNameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        gbc.insets = new Insets(10, 10, 20, 10);
//        panel.add(EventNameLabel, gbc);
//
//        JLabel dateLabel = new JLabel("Datum:");
//        JFormattedTextField dateField = new JFormattedTextField("TT/MM/JJJJ");
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        panel.add(dateLabel, gbc);
//        gbc.gridx = 1;
//        panel.add(dateField, gbc);
//
//        JLabel sportLabel = new JLabel("Sportart:");
//        JComboBox<String> sportComboBox = new JComboBox<>(new String[]{"Schwimmen", "Sprint", "Weitsprung"});
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        panel.add(sportLabel, gbc);
//        gbc.gridx = 1;
//        panel.add(sportComboBox, gbc);
//    }

    // Panel für Event verwalten
    private void showManageEventPanel() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel manageEventLabel = new JLabel("Event Verwalten");
        manageEventLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        panel.add(manageEventLabel, gbc);

        JLabel eventLabel = new JLabel("Event:");
        JComboBox<String> eventComboBox = new JComboBox<>(new String[]{"Event 1", "Event 2", "Event 3"});
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(eventLabel, gbc);
        gbc.gridx = 1;
        panel.add(eventComboBox, gbc);

        JLabel participantLabel = new JLabel("Teilnehmer:");
        JComboBox<String> participantComboBox = new JComboBox<>(new String[]{"Teilnehmer 1", "Teilnehmer 2"});
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(participantLabel, gbc);
        gbc.gridx = 1;
        panel.add(participantComboBox, gbc);

        JLabel timeLabel = new JLabel("Gemessene Zeit:");
        JTextField timeField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(timeLabel, gbc);
        gbc.gridx = 1;
        panel.add(timeField, gbc);

        JButton saveParticipantButton = new JButton("Teilnehmerdaten speichern");
        JButton finishEventButton = new JButton("Event abschließen");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(saveParticipantButton, gbc);
        gbc.gridy = 5;
        panel.add(finishEventButton, gbc);

        saveParticipantButton.addActionListener(e -> {
            timeField.setText("");
            participantComboBox.setSelectedIndex(0);
            JOptionPane.showMessageDialog(frame, "Teilnehmerdaten erfolgreich gespeichert!");
        });

        finishEventButton.addActionListener(e -> showActionSelectionPanel());

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
        panel.add(VorNameLabel, gbc);
        gbc.gridx = 1;
        panel.add(VorNameField, gbc);

        JLabel NachNameLabel = new JLabel("Nachname:");
        JTextField NachNameField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(NachNameLabel, gbc);
        gbc.gridx = 1;
        panel.add(NachNameField, gbc);

        JLabel nameLabel = new JLabel("Benutzername:");
        JTextField nameField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

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

        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Manager erfolgreich gespeichert!");
            showActionSelectionPanel();
        });

        panel.revalidate();
        panel.repaint();
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

        panel.revalidate();
        panel.repaint();
    }

    private void showNewSportsTypePanel() {
        //TODO
    }

    private void showManageSportsTypePanel() {
        //TODO
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
