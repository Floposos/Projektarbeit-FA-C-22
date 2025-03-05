package DatabaseOperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static final String PATH = "C:\\Users\\Flo\\OneDrive - OSZ IMT\\LF12\\PROJEKT ARBEIT\\untitled\\ProjektDB_v4.db";
    public static final String ConnectionURL = "jdbc:sqlite:" + PATH;


    public static Connection Verbindung() throws SQLException {

        Connection connection = null;
        connection = DriverManager.getConnection(ConnectionURL);
        return connection;
    }


}

