package GUI;

import DatabaseOperations.DBConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class DynamicTableSportEvent {
    private static JTable table;
    private static DefaultTableModel tableModel;
    private static JPanel panel;
    private static Connection connection;

    public DynamicTableSportEvent(Connection connection) throws SQLException {
        DynamicTableSportEvent.connection = connection;
        panel = new JPanel(new BorderLayout());
        loadTableData();
    }

    static void loadTableData() throws SQLException {

        connection = DBConnection.Verbindung();

        Vector<Vector<Object>> data = new Vector<>();
        Vector<String> columnNames = new Vector<>();

        try {
            String query = "SELECT m.firstName || ' ' || m.lastName AS TeilnehmerName, s.name AS Eventtyp, se.resultValue AS Ergebnis " +
                    "FROM T_member m " +
                    "JOIN T_eventMember em ON m.memberId = em.memberId " +
                    "JOIN T_sportEvent se ON em.eventMemberId = se.eventMemberId " +
                    "JOIN T_sport s ON se.sportId = s.sportId";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Fehler beim Laden der Daten: " + e.getMessage());
        }

        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void refreshTable() throws SQLException {
        panel.removeAll();
        loadTableData();
        panel.revalidate();
        panel.repaint();
    }
}
