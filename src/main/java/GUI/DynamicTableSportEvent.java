package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class DynamicTableSportEvent {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel panel;
    private Connection connection;
    private JButton addColumnButton;
    private JButton saveButton;

    public DynamicTableSportEvent(Connection connection) {
        this.connection = connection;
        panel = new JPanel(new BorderLayout());
        initUI();
    }

    private void initUI() {
        // Erstelle ein TableModel mit initialen Spalten:
        // Spalte 0: ID (wird später verborgen), Spalte 1: Teilnehmer, Spalte 2: Ergebnis 1
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");           // Versteckte Spalte für interne IDs
        tableModel.addColumn("Teilnehmer");
        tableModel.addColumn("Ergebnis 1");

        loadParticipants();

        // JTable erstellen und die ID-Spalte in der Ansicht entfernen
        table = new JTable(tableModel);
        table.removeColumn(table.getColumnModel().getColumn(0)); // Verberge die ID

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel für Buttons (Spalte hinzufügen und Speichern)
        JPanel buttonPanel = new JPanel();
        addColumnButton = new JButton("Spalte hinzufügen");
        saveButton = new JButton("Speichern");

        addColumnButton.addActionListener(e -> addResultColumn());
        saveButton.addActionListener(e -> saveResults());

        buttonPanel.add(addColumnButton);
        buttonPanel.add(saveButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadParticipants() {
        try {
            // Beispiel-Abfrage: Lädt eventMemberId und den zusammengesetzten Namen des Teilnehmers
            String query = "SELECT em.eventMemberId, m.firstName || ' ' || m.lastName AS Teilnehmer " +
                    "FROM T_eventMember em " +
                    "JOIN T_member m ON em.memberId = m.memberId";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("eventMemberId");
                String name = rs.getString("Teilnehmer");
                // Füge eine Zeile hinzu: ID, Teilnehmer, initial leerer Wert für "Ergebnis 1"
                Vector<Object> row = new Vector<>();
                row.add(id);
                row.add(name);
                row.add(""); // Ergebnis 1 zunächst leer
                tableModel.addRow(row);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Fehler beim Laden der Teilnehmer: " + e.getMessage());
        }
    }

    private void addResultColumn() {
        // Bestimme, wie viele Ergebnis-Spalten bereits vorhanden sind
        int resultColumns = tableModel.getColumnCount() - 2; // Spalten ab Index 2
        String newColumnName = "Ergebnis " + (resultColumns + 1);
        tableModel.addColumn(newColumnName);

        // Setze alle Zellen der neuen Spalte initial auf leer
        int rowCount = tableModel.getRowCount();
        int newColIndex = tableModel.getColumnCount() - 1;
        for (int i = 0; i < rowCount; i++) {
            tableModel.setValueAt("", i, newColIndex);
        }
    }

    private void saveResults() {
        int rowCount = tableModel.getRowCount();
        int colCount = tableModel.getColumnCount();
        try {
            // Update-Statement: Speichert alle Ergebniswerte (z. B. als kommagetrennte Zeichenkette) in resultValue
            String updateSQL = "UPDATE T_sportEvent SET resultValue = ? WHERE eventMemberId = ?";
            PreparedStatement pstmt = connection.prepareStatement(updateSQL);
            for (int i = 0; i < rowCount; i++) {
                // Hole die eventMemberId aus der versteckten Spalte (Index 0 im Model)
                int eventMemberId = (Integer) tableModel.getValueAt(i, 0);
                StringBuilder resultValue = new StringBuilder();
                // Ergebniszellen befinden sich ab Spalte 2
                for (int j = 2; j < colCount; j++) {
                    Object value = tableModel.getValueAt(i, j);
                    if (value != null) {
                        resultValue.append(value.toString());
                    }
                    if (j < colCount - 1) {
                        resultValue.append(", ");
                    }
                }
                pstmt.setString(1, resultValue.toString());
                pstmt.setInt(2, eventMemberId);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            pstmt.close();
            JOptionPane.showMessageDialog(null, "Ergebnisse erfolgreich gespeichert!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Fehler beim Speichern der Ergebnisse: " + e.getMessage());
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
