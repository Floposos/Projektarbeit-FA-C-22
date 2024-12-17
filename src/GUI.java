import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    JFrame frame = new JFrame("Auswahlfenster");
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Wählen Sie aus, was auf Sie zutrifft");
    JButton buttonEventManager = new JButton("Event-Manager");
    JButton buttonVerein = new JButton("Verein");

    public GUI() {
        // Nimbus-Look-and-Feel aktivieren
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Nimbus Look-and-Feel konnte nicht geladen werden.");
        }

        // Grundlegende JFrame-Einstellungen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Startpanel erstellen
        createMainPanel();

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Erstellt das Hauptmenü-Panel
    private void createMainPanel() {
        panel.removeAll(); // Löscht vorherige Inhalte
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();

        // Label "Wählen Sie aus, was auf Sie zutrifft"
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        label.setForeground(new Color(50, 50, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(label, gbc);

        // Button "Event-Manager"
        buttonEventManager.setPreferredSize(new Dimension(150, 40));
        buttonEventManager.setBackground(new Color(100, 150, 255));
        buttonEventManager.setForeground(Color.WHITE);
        buttonEventManager.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(buttonEventManager, gbc);

        // Button "Verein"
        buttonVerein.setPreferredSize(new Dimension(150, 40));
        buttonVerein.setBackground(new Color(100, 200, 150));
        buttonVerein.setForeground(Color.WHITE);
        buttonVerein.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(buttonVerein, gbc);

        // ActionListener für "Event-Manager"
        buttonEventManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginPanel();
            }
        });

        // Panel aktualisieren
        panel.revalidate();
        panel.repaint();
    }

    // Erstellt das Login-Panel für "Event-Manager"
    private void showLoginPanel() {
        panel.removeAll(); // Löscht vorherige Inhalte
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();

        // Label "Anmeldung"
        JLabel loginLabel = new JLabel("Anmeldung");
        loginLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        loginLabel.setForeground(new Color(50, 50, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        panel.add(loginLabel, gbc);

        // Label und Eingabefeld "Benutzername"
        JLabel userLabel = new JLabel("Benutzername:");
        JTextField userField = new JTextField(15);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(userLabel, gbc);
        gbc.gridx = 1;
        panel.add(userField, gbc);

        // Label und Eingabefeld "Passwort"
        JLabel passLabel = new JLabel("Passwort:");
        JPasswordField passField = new JPasswordField(15);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passLabel, gbc);
        gbc.gridx = 1;
        panel.add(passField, gbc);

        // Button "Speichern"
        JButton saveButton = new JButton("Speichern");
        saveButton.setBackground(new Color(100, 150, 255));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(saveButton, gbc);

        // ActionListener für den Speichern-Button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                JOptionPane.showMessageDialog(frame,
                        "Benutzername: " + username + "\nPasswort: " + password,
                        "Gespeichert", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Panel aktualisieren
        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}
