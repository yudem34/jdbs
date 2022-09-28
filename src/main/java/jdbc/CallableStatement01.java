package jdbc;

import java.lang.reflect.Type;
import java.sql.*;

public class CallableStatement01 {
    /*
    Java'da methodlar return type sahibi olsa da, void olsa da method olarak adlandırılır.
    SQL'de ise data return ediyorsa "function" denir. Return yapmıyorsa "procedure" diye adlandırılır.
    */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "Smgh1ygas.");
        Statement st = con.createStatement();
        //1. Örnek: İki parametre ile çalışıp bu parametreleri toplayarak return yapan bir fonksiyon oluşturun.

        //1.Adım: Fonksiyon kodunu yaz
        String sql1="Create or replace function toplamfy(x Numeric,y Numeric)\n" +
                "returns Numeric\n" +
                "language plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "begin\n" +
                "\n" +
                "return x+y;\n" +
                "\n" +
                "end\n" +
                "$$";

        //2.Adım: Fonksiyonu calistir.
        st.execute(sql1);

        //3.Adım: Fonksiyonu cagır.
        CallableStatement cst1=con.prepareCall("{? = call toplamfy(?,?)}");

        //4.Adım: Return icin registerOutParameter() methodunu, parametreler icin set() methodlarından uygun olanları kullan
        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2,15);
        cst1.setInt(3,25);

        //5.Adım: Calistirmak icin excute() methodu kullanılır
        cst1.execute();

        //6.Adım: Sonucu cagırmak icin return data tipine gore get methodlarından uygun olanı kullanıcaz
        System.out.println("toplamfy : " + cst1.getBigDecimal(1));

        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.
        //Hacim = (1/3)πr2h

        String sql2="create or REPLACE function konininHacmi(r NUMERIC,h NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "as \n" +
                "$$\n" +
                "BEGIN\n" +
                "RETURN (3.14*r*r*h)/3;\n" +
                "END\n" +
                "$$";
        st.execute(sql2);
        java.sql.CallableStatement cst2= con.prepareCall("{? = call konininHacmi(?,?)}");
        cst2.registerOutParameter(1, Types.NUMERIC);
        cst2.setInt(2,3);
        cst2.setInt(3,5);
        cst2.execute();
        System.out.println("KonininHacmi : " + cst2.getBigDecimal(1));


    }
}
