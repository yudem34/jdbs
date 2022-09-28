package jdbsPractise;

import java.sql.*;
import java.sql.DriverManager;

public class Query02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "Smgh1ygas.");
        Statement st=con.createStatement();

        //SORU : Ogrenciler tablosundaki erkek ogrencileri listeleyiniz
        ResultSet veri=st.executeQuery("select * from ogrenciler where cinsiyet='E'");

        while (veri.next()){
            System.out.printf("%-6d %-15.15s %-8s %-8s\n", veri.getInt(1), veri.getString(2), veri.getString(3), veri.getString(4));
        }
    }
}
