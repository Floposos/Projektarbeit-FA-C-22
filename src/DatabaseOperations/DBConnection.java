package DatabaseOperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

   public static final String DB_Name = "ProjektDB_2";
   public static final String PATH = "C://Users//Flo//OneDrive - OSZ IMT//LF12//PROJEKT ARBEIT//DB/" + DB_Name;
   public static final String ConnectionURL = "jdbc:sqlite:" + PATH;

   //private Connection connection = null;


   public static Connection Verbindung() throws SQLException {

       Connection connection = null;
       connection = DriverManager.getConnection(ConnectionURL);
       return connection;
   }
//    public boolean checkDBConnectionWV() throws SQLException {
//        connection = Verbindung();
//        if (connection != null) {
//            System.out.println("klappt");
//            return true;
//        } else {
//            return false;
//        }
//    }

}

