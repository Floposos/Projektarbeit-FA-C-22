package GUI;

import DatabaseOperations.DBConnection;
import Logic.AdministratorManager;
import Logic.*;
import Model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUI {

    //test
    private AdministratorManager admin;
    private ClubManager clubManager;
    private EventManager event;
    public int adminID;
    private SportManager sportsType;
    private JComboBox<String> clubDropdown;
    private JPasswordField clubPasswordField;
    private JButton clubLoginButton;
    private int loggedInClubId;


    JFrame frame = new JFrame("Auswahlfenster");
    JPanel panel = new JPanel();
    private List<JComboBox<String>> sportDropdowns;
    private List<JFormattedTextField> dateFields;

    public GUI() {
        this.admin = new AdministratorManager();
        this.event = new EventManager();
        this.sportsType = new SportManager();
        this.clubManager = new ClubManager();
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
        pnlRoleSelection();

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Erstellt das Auswahl-Panel (Event-Manager / Verein)
    private void pnlRoleSelection() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
//        System.out.println("Verfügbare Vereine: " + clubManager.getAllClubs().size());
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
        buttonEventManager.addActionListener(e -> pnlLogin());
        buttonVerein.addActionListener(e -> {
            System.out.println("Verein Button gedrückt");
            showClubLoginPanel(); // Hier soll das Login-Panel erscheinen
        });
        panel.revalidate();
        panel.repaint();
    }

    // Login-Panel
    private void pnlLogin() {
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
        //TODO Fehleranzeige, warum zeigt es dieMessage nicht an
        loginButton.addActionListener(e -> {
            try {
                adminID = Integer.parseInt(userField.getText());
                String password = new String(passField.getPassword());
                if (!admin.checkAuthorization(adminID, password)) {
                    JOptionPane.showMessageDialog(panel, "Das Passwort oder der Nutzername stimmen nicht!", "Fehler", JOptionPane.ERROR_MESSAGE);
                } else {
                    pnlActionSelection();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Der Benutzername muss eine Zahl sein!", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });

        //TEST

        // Pfeil-Button unten links
        JButton backButton = btnBackButton(this::pnlRoleSelection);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);
        panel.revalidate();
        panel.repaint();

    }

    private void showClubLoginPanel() {
        panel.removeAll(); // Panel zurücksetzen
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel clubLabel = new JLabel("Verein auswählen:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(clubLabel, gbc);

        clubDropdown = new JComboBox<>();
        for (Club club : clubManager.getAllClubs()) {
            clubDropdown.addItem(club.getName());
        }
        gbc.gridx = 1;
        panel.add(clubDropdown, gbc);

        JLabel passwordLabel = new JLabel("Passwort:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        clubPasswordField = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(clubPasswordField, gbc);

        clubLoginButton = new JButton("Anmelden");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(clubLoginButton, gbc);

        clubLoginButton.addActionListener(e -> {
            String selectedClub = (String) clubDropdown.getSelectedItem();
            String password = new String(clubPasswordField.getPassword());

            if (clubManager.checkAuthorization(selectedClub, password)) {
                loggedInClubId = clubManager.getClubIdByName(selectedClub);
                JOptionPane.showMessageDialog(null, "Erfolgreich bei " + selectedClub + " angemeldet!");
                showClubManagementPanel();
            } else {
                JOptionPane.showMessageDialog(null, "Fehlgeschlagene Anmeldung. Überprüfen Sie das Passwort.", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.revalidate();
        panel.repaint();
    }


    // Erstellt die Haupt-Auswahlseite
    private void pnlActionSelection() {
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
        JButton backButton = btnBackButton(this::pnlRoleSelection);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);

        buttonNewEvent.addActionListener(e -> pnlNewEvent());
        buttonManageEvent.addActionListener(e -> {
            try {
                pnlManageEvent();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonAddManager.addActionListener(e -> pnlAddManager());
        buttonManageSports.addActionListener(e -> pnlManageSports());

        panel.revalidate();
        panel.repaint();
    }

    // Panel für Neues Event
    private void pnlNewEvent() {
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
        JButton backButton = btnBackButton(this::pnlActionSelection);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);

        saveButton.addActionListener(e -> {
            String eventName = nameField.getText();
            int adminID = 1000;

            if (eventName.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Bitte einen Event-Namen eingeben!", "Fehler", JOptionPane.ERROR_MESSAGE);
            } else {

                //  event.addEvent(adminID,eventName);

                pnlNewSportEvent(eventName);
                //pnlNewSportEvent(eventName); // Event-Name an die nächste Methode übergeben
            }
        });

        panel.revalidate();
        panel.repaint();
    }

    private void pnlNewSportEvent(String eventName) {
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
        saveButton.addActionListener(e -> pnlActionSelection());

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
        JComboBox<String> sportComboBox = new JComboBox<>();
        //TODO aktuell
        for (Sport sporttype : sportsType.getAllSports()) {  // Instanzmethode richtig aufrufen
            sportComboBox.addItem(sporttype.getName());
        }

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

    private void showClubManagementPanel() {
        panel.removeAll();  // Panel zurücksetzen
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Titel der Vereinsverwaltungsseite
        JLabel clubLabel = new JLabel("Vereinsverwaltung");
        clubLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 100, 10);
        panel.add(clubLabel, gbc);

        // Mitglieder verwalten Button
        JButton manageMembersButton = new JButton("Mitglieder verwalten");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(manageMembersButton, gbc);

        // Eventanmeldung Button
        JButton eventRegistrationButton = new JButton("Eventanmeldung");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(eventRegistrationButton, gbc);

        // ActionListener für den Mitglieder verwalten Button
        manageMembersButton.addActionListener(e -> {
            // Code für Mitglieder verwalten
            showManageMembersPanel();
        });

        // ActionListener für den Eventanmeldung Button
        eventRegistrationButton.addActionListener(e -> {
            // Code für Eventanmeldung
            showEventRegistrationPanel();
        });

        // Panel neu validieren und repainten
        panel.revalidate();
        panel.repaint();
    }

    private void showManageMembersPanel() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel manageLabel = new JLabel("Mitglieder verwalten");
        manageLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 50, 10);
        panel.add(manageLabel, gbc);

        JButton addMemberButton = new JButton("Mitglied hinzufügen");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(addMemberButton, gbc);

        JButton editMemberButton = new JButton("Mitglied bearbeiten");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(editMemberButton, gbc);

        JButton deleteMemberButton = new JButton("Mitglied löschen");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(deleteMemberButton, gbc);

        addMemberButton.addActionListener(e -> showAddMembersPanel());
        editMemberButton.addActionListener(e -> showEditMembersPanel());
        deleteMemberButton.addActionListener(e -> showDeleteMembersPanel());

        JButton backButton = btnBackButton(this::showClubManagementPanel);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);

        panel.add(backButton, gbc);
        panel.revalidate();
        panel.repaint();
    }


    private void showEventRegistrationPanel() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel eventLabel = new JLabel("Eventanmeldung");
        eventLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 50, 10);
        panel.add(eventLabel, gbc);

        JButton registerEventButton = new JButton("Mitglied für Event anmelden");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(registerEventButton, gbc);

        JButton unregisterEventButton = new JButton("Mitglied von Event abmelden");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(unregisterEventButton, gbc);

        registerEventButton.addActionListener(e -> showRegisterEventPanel());
        unregisterEventButton.addActionListener(e -> showUnregisterEventPanel());

        JButton backButton = btnBackButton(this::showClubManagementPanel);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);

        panel.revalidate();
        panel.repaint();
    }
    private void showAddMembersPanel() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel addMemberLabel = new JLabel("Mitglied hinzufügen");
        addMemberLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 50, 10);
        panel.add(addMemberLabel, gbc);

        JTextField firstNameField = new JTextField(20);
        firstNameField.setPreferredSize(new Dimension(200, 30));

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Vorname:"), gbc);
        gbc.gridx = 3;
        panel.add(firstNameField, gbc);

        JTextField lastNameField = new JTextField(20);
        lastNameField.setPreferredSize(new Dimension(200, 30));

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Nachname:"), gbc);
        gbc.gridx = 3;
        panel.add(lastNameField, gbc);

        JTextField birthDateField = new JTextField(10);
        birthDateField.setPreferredSize(new Dimension(200, 30));

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Geburtsdatum (YYYY-MM-DD):"), gbc);
        gbc.gridx = 3;
        panel.add(birthDateField, gbc);

        JButton addButton = new JButton("Mitglied hinzufügen");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(addButton, gbc);

        addButton.addActionListener(e -> {
            try {
                int clubId = 1; // Hier wäre der Club ID, wenn benötigt
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                LocalDate birthDate = LocalDate.parse(birthDateField.getText());

                MemberManager memberManager = new MemberManager();
                memberManager.addMember(clubId, firstName, lastName, birthDate);
                JOptionPane.showMessageDialog(panel, "Mitglied erfolgreich hinzugefügt.");
                showManageMembersPanel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Fehler: " + ex.getMessage());
            }
        });

        JButton backButton = btnBackButton(this::showManageMembersPanel);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(backButton, gbc);

        panel.revalidate();
        panel.repaint();
    }

    private void showEditMembersPanel() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel editMemberLabel = new JLabel("Mitglied bearbeiten");
        editMemberLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 50, 10);
        panel.add(editMemberLabel, gbc);

        // Dropdown mit allen Mitgliedern des Clubs
        JLabel selectMemberLabel = new JLabel("Mitglied auswählen:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(selectMemberLabel, gbc);

        JComboBox<Member> memberDropdown = new JComboBox<>();
        List<Member> members = getMembersForClub(loggedInClubId);  // ClubId des eingeloggenen Clubs wird verwendet
        for (Member member : members) {
            memberDropdown.addItem(member);
        }

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(memberDropdown, gbc);

        // Vorname Textfeld (kann geändert werden)
        JLabel firstNameLabel = new JLabel("Vorname:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(firstNameLabel, gbc);

        JTextField firstNameField = new JTextField(20);
        firstNameField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 3;
        gbc.gridy = 2;
        panel.add(firstNameField, gbc);

        // Nachname Textfeld (kann geändert werden)
        JLabel lastNameLabel = new JLabel("Nachname:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lastNameLabel, gbc);

        JTextField lastNameField = new JTextField(20);
        lastNameField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 3;
        gbc.gridy = 3;
        panel.add(lastNameField, gbc);

        // Geburtsdatum Textfeld (kann geändert werden)
        JLabel birthDateLabel = new JLabel("Geburtsdatum (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(birthDateLabel, gbc);

        JTextField birthDateField = new JTextField(20);
        birthDateField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 3;
        gbc.gridy = 4;
        panel.add(birthDateField, gbc);

        // Ausgewähltes Mitglied aus Dropdown laden
        memberDropdown.addActionListener(e -> {
            Member selectedMember = (Member) memberDropdown.getSelectedItem();
            if (selectedMember != null) {
                firstNameField.setText(selectedMember.getFirstName());
                lastNameField.setText(selectedMember.getLastName());
                birthDateField.setText(selectedMember.getBirthDate().toString());
            }
        });

        // Update-Button für die Änderungen
        JButton updateButton = new JButton("Mitglied bearbeiten");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(updateButton, gbc);

        updateButton.addActionListener(e -> {
            try {
                Member selectedMember = (Member) memberDropdown.getSelectedItem();
                if (selectedMember == null) {
                    JOptionPane.showMessageDialog(panel, "Kein Mitglied ausgewählt.");
                    return;
                }

                String firstName = firstNameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                String birthDateString = birthDateField.getText().trim();
                LocalDate birthDate = LocalDate.parse(birthDateString);

                MemberManager memberManager = new MemberManager();

                // Nur das geänderte Attribut aktualisieren
                if (!firstName.isEmpty()) {
                    selectedMember.setFirstName(firstName);
                }
                if (!lastName.isEmpty()) {
                    selectedMember.setLastName(lastName);
                }
                if (!birthDateString.isEmpty()) {
                    selectedMember.setBirthDate(birthDate);
                }

                memberManager.updateMember(selectedMember.getMemberId(), selectedMember.getClubId(),
                        selectedMember.getFirstName(), selectedMember.getLastName(), selectedMember.getBirthDate());

                JOptionPane.showMessageDialog(panel, "Mitglied erfolgreich bearbeitet.");
                showManageMembersPanel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Fehler: " + ex.getMessage());
            }
        });

        JButton backButton = btnBackButton(this::showManageMembersPanel);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(backButton, gbc);

        panel.revalidate();
        panel.repaint();
    }

    private void showDeleteMembersPanel() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel deleteMemberLabel = new JLabel("Mitglied löschen");
        deleteMemberLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 50, 10);
        panel.add(deleteMemberLabel, gbc);

        JTextField memberIdField = new JTextField(20);
        memberIdField.setPreferredSize(new Dimension(200, 30)); // Anpassung der Größe

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Mitglieds-ID:"), gbc);
        gbc.gridx = 3;
        panel.add(memberIdField, gbc);

        JButton deleteButton = new JButton("Mitglied löschen");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(deleteButton, gbc);

        deleteButton.addActionListener(e -> {
            try {
                int memberId = Integer.parseInt(memberIdField.getText());

                MemberManager memberManager = new MemberManager();
                memberManager.deleteMember(memberId);
                JOptionPane.showMessageDialog(panel, "Mitglied erfolgreich gelöscht.");
                showManageMembersPanel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Fehler: " + ex.getMessage());
            }
        });

        JButton backButton = btnBackButton(this::showManageMembersPanel);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(backButton, gbc);

        panel.revalidate();
        panel.repaint();
    }

    private List<Member> getMembersForClub(int clubId) {
        MemberManager memberManager = new MemberManager();
        List<Member> allMembers = memberManager.getAllMembers();

        System.out.println("Anzahl aller Mitglieder: " + allMembers.size()); // Ausgabe der Gesamtzahl

        List<Member> filteredMembers = allMembers.stream()
                .filter(member -> {
                    System.out.println("Prüfe Mitglied: " + member.getFirstName() + ", ClubId: " + member.getClubId() + " == " + clubId);
                    return member.getClubId() == clubId; // Filtert nach Club-ID
                })
                .collect(Collectors.toList());

        System.out.println("Gefilterte Mitglieder: " + filteredMembers.size()); // Ausgabe der gefilterten Mitglieder
        return filteredMembers;
    }


    public void showRegisterEventPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel lblMemberId = new JLabel("Mitglieds-ID:");
        JTextField txtMemberId = new JTextField();

        JLabel lblEventId = new JLabel("Event-ID:");
        JTextField txtEventId = new JTextField();

        JLabel lblSportEventId = new JLabel("SportEvent-ID:");
        JTextField txtSportEventId = new JTextField();

        JButton btnRegister = new JButton("Anmelden");

        panel.add(lblMemberId);
        panel.add(txtMemberId);
        panel.add(lblEventId);
        panel.add(txtEventId);
        panel.add(lblSportEventId);
        panel.add(txtSportEventId);
        panel.add(btnRegister);

        JFrame frame = new JFrame("Event Anmeldung");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        btnRegister.addActionListener(e -> {
            try {
                int memberId = Integer.parseInt(txtMemberId.getText());
                int eventId = Integer.parseInt(txtEventId.getText());
                int sportEventId = Integer.parseInt(txtSportEventId.getText());

                EventMemberManager eventMemberManager = new EventMemberManager();
                eventMemberManager.registerMemberForEvent(memberId, eventId, sportEventId);

                JOptionPane.showMessageDialog(frame, "Erfolgreich für das Event angemeldet!");
                frame.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Bitte gültige Zahlen eingeben!");
            }
        });
    }

    public void showUnregisterEventPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel lblMemberId = new JLabel("Mitglieds-ID:");
        JTextField txtMemberId = new JTextField();

        JLabel lblEventId = new JLabel("Event-ID:");
        JTextField txtEventId = new JTextField();

        JButton btnUnregister = new JButton("Abmelden");

        panel.add(lblMemberId);
        panel.add(txtMemberId);
        panel.add(lblEventId);
        panel.add(txtEventId);
        panel.add(btnUnregister);

        JFrame frame = new JFrame("Event Abmeldung");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        btnUnregister.addActionListener(e -> {
            try {
                int memberId = Integer.parseInt(txtMemberId.getText());
                int eventId = Integer.parseInt(txtEventId.getText());

                EventMemberManager eventMemberManager = new EventMemberManager();
                eventMemberManager.unregisterMemberFromEvent(memberId, eventId);

                JOptionPane.showMessageDialog(frame, "Erfolgreich vom Event abgemeldet!");
                frame.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Bitte gültige Zahlen eingeben!");
            }
        });
    }




    // Panel für Event verwalten
    private void pnlManageEvent() throws SQLException {
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

        //Tabelle?

        Connection connection = DBConnection.Verbindung();
        DynamicTableSportEvent dynamicTableSportEvent = new DynamicTableSportEvent(connection);
        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.add(dynamicTableSportEvent.getPanel(), BorderLayout.CENTER);

        GridBagConstraints gbcTable = new GridBagConstraints();
        gbcTable.gridx = 0;
        gbcTable.gridy = 4;
        gbcTable.gridwidth = 2;
        gbcTable.fill = GridBagConstraints.BOTH;
        gbcTable.weightx = 1.0;
        gbc.weighty = 1.0;
        gbcTable.insets = new Insets(10, 10, 10, 10);
        panel.add(tableContainer, gbcTable);

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

        finishEventButton.addActionListener(e -> pnlActionSelection());

        panel.revalidate();
        panel.repaint();

        // Pfeil-Button unten links
        JButton backButton = btnBackButton(this::pnlActionSelection);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);

        panel.revalidate();
        panel.repaint();
    }


    private void pnlAddManager() {
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
                pnlActionSelection();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            }
            // Weiterleitung zur nächsten Ansicht
            pnlActionSelection();
        });

        // Pfeil-Button unten links
        JButton backButton = btnBackButton(this::pnlActionSelection);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);

        panel.revalidate();
        panel.repaint();
        //Test
    }

    private void pnlManageSports() {
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

        buttonNewSportType.addActionListener(e -> pnlNewSportsType());
        buttonManageSportsType.addActionListener(e -> pnlManageSportsType());

        // Pfeil-Button unten links
        JButton backButton = btnBackButton(this::pnlActionSelection);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);

        panel.revalidate();
        panel.repaint();
    }

    private void pnlNewSportsType() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel newSportsTypeLabel = new JLabel("Neue Sportart anlegen");
        newSportsTypeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        panel.add(newSportsTypeLabel, gbc);
        panel.revalidate();
        panel.repaint();

        JLabel lbl_sportsType = new JLabel("Neue Sportart: ");
        JTextField sportsTypeNameField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lbl_sportsType, gbc);
        gbc.gridx = 1;
        panel.add(sportsTypeNameField, gbc);

        JLabel lbl_resultType = new JLabel("Ergebnisart: ");
        JTextField resultTypeField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lbl_resultType, gbc);
        gbc.gridx = 1;
        panel.add(resultTypeField, gbc);

        JButton saveButton = new JButton("Anlegen");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(saveButton, gbc);

        saveButton.addActionListener(e -> {
            String sportsTypeName = sportsTypeNameField.getText();
            String resultType = resultTypeField.getText();
            sportsType.addSport(sportsTypeName, resultType);
            pnlActionSelection();
        });

        // Pfeil-Button unten links
        JButton backButton = btnBackButton(this::pnlManageSports);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backButton, gbc);

    }

    //TODO
    private void pnlManageSportsType() {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel newSportsTypeLabel = new JLabel("Sportart bearbeiten");
        newSportsTypeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        panel.add(newSportsTypeLabel, gbc);
        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }

    private JButton btnBackButton(Runnable action) {
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
