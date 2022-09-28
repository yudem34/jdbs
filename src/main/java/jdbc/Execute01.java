package jdbc;

import java.sql.*;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.Adım Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2.Adım Database baglanma
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "Smgh1ygas.");

        //3.Adım Statement olustur
        Statement st = con.createStatement();

        //4.Adım Query Olusturma ve Calıstırma
        //1.Example: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
        String sql1 = "CREATE TABLE workers(worker_id VARCHAR(50), worker_name VARCHAR(50), worker_salary INT)";
        st.execute(sql1);
        //System.out.println(result);//False return yapar, çünkü data çağrılmadı.

        //2.Örnek: Table'a worker_address sütunu ekleyerel alter yapın.
        String sql2 = "ALTER table workers ADD worker_adress VARCHAR(80)";
        st.execute(sql2);

        //3.Ornek Drop Table Workers
        String sql3= "DROP TABLE workers";
        st.execute(sql3);

        //5.Adım Baglantı ve Statement'ı kapatma
        con.close();
        st.close();

    }
}
