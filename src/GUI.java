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

        // Panel-Konfiguration mit Layout
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245)); // Dezentes Grau für den Hintergrund
        GridBagConstraints gbc = new GridBagConstraints();

        // Label-Einstellungen
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        label.setForeground(new Color(50, 50, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(label, gbc);

        // Button "Event-Manager"
        buttonEventManager.setPreferredSize(new Dimension(150, 40));
        buttonEventManager.setFocusPainted(false);
        buttonEventManager.setBackground(new Color(100, 150, 255));
        buttonEventManager.setForeground(Color.WHITE);
        buttonEventManager.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(buttonEventManager, gbc);

        // Button "Verein"
        buttonVerein.setPreferredSize(new Dimension(150, 40));
        buttonVerein.setFocusPainted(false);
        buttonVerein.setBackground(new Color(100, 200, 150));
        buttonVerein.setForeground(Color.WHITE);
        buttonVerein.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(buttonVerein, gbc);

        // ActionListener für Buttons
        buttonEventManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Event-Manager ausgewählt!",
                        "Auswahl", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        buttonVerein.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Verein ausgewählt!",
                        "Auswahl", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Panel zum Frame hinzufügen
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}
