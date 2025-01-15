import DatabaseOperations.DBConnection;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try {
            DBConnection.Verbindung();
            System.out.println("Verbunden");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}