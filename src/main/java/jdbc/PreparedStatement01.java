package jdbc;

import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "Smgh1ygas.");
        Statement st = con.createStatement();

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        //1. Adım Prepared statement query'sini olustur.
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";

        //2.Adım Preparedstatement objesini olustur
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3.Adım set....() methodlari ile ? yerine deger girilebilecek
        pst1.setInt(1, 9999);
        pst1.setString(2, "IBM");

        //4.Adım Execute query
        int updateRowSayisi = pst1.executeUpdate();
        System.out.println(updateRowSayisi + " " + "satir guncellendi");

        String sql2 = "select * from companies";
        ResultSet result1 = st.executeQuery(sql2);
        while (result1.next()) {
            System.out.println(result1.getInt(1) + "---" + result1.getString(2) + "---" + result1.getInt(3));
        }

        //3.Adım set....() methodlari ile ? yerine deger girilebilecek
        pst1.setInt(1, 15000);
        pst1.setString(2, "GOOGLE");

        //4.Adım Execute query
        int updateRowSayisi2 = pst1.executeUpdate();
        System.out.println(updateRowSayisi + " " + "satir guncellendi");

        String sql3 = "select * from companies";
        ResultSet result2 = st.executeQuery(sql3);
        while (result2.next()) {
            System.out.println(result2.getInt(1) + "---" + result2.getString(2) + "---" + result2.getInt(3));
        }
        ////2.Ornek "Select * from <table name>" query sini prepared statment ile kullanın
        //String sql4 = "SELECT * FROM ?";
        //PreparedStatement pst2 = con.prepareStatement(sql4);

        //ResultSet result4 = pst2.executeQuery();
        //while (result4.next()) {
        //    System.out.println(result4.getInt(1) + "---" + result4.getString(2) + "---" + result4.getInt(3));

        //}
        System.out.println();
        read_data(con, "countries");
    }

    public static void read_data(Connection con, String tableName) {

        try {
            String query = String.format("Select * from %s", tableName); // Format methodu dinamik string olusturur. %s yerine tableName yazılan yazı gelir
            Statement statement = con.createStatement();
            ResultSet result2 = statement.executeQuery(query);
            while (result2.next()) {
                System.out.println(result2.getString(1) + "---" + result2.getString(2) + "---" + result2.getInt(3));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
