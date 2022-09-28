package jdbc;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        //DBWork objesini olustur
        DBWork db = new DBWork();
        Connection con=db.connect_to_db("techproed","postgres","Smgh1ygas.");

        //Yeni table oluşturma methodunu çağır.
        db.createTable(con,"employees");


    }
}
