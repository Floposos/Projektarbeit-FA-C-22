package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Wrapper;

public class DBConnection {

   public static final String DB_Name = "ProjektDB_2";
   public static final String PATH = "C://Users//Flo//OneDrive - OSZ IMT//LF12//PROJEKT ARBEIT//DB/" + DB_Name;
   public static final String ConnectionURL = "jdbc:sqlite:" + PATH;

    public  Connection() throws SQLException {

        Connection connection = null;
        connection = DriverManager.getConnection(ConnectionURL);
        return connection;
        
    }
}
