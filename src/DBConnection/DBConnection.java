package DBConnection;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

   String url = "jdbc:sqlite:C://Users//Flo//OneDrive - OSZ IMT//LF12//PROJEKT ARBEIT//DB";

   Connection connection = DriverManager.getConnection(url);


    public DBConnection() throws SQLException {
    }
}
