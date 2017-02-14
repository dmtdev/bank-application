package ua.spalah.bank.tmp;

import java.sql.*;

/**
 * Created by root on 07.02.2017.
 */

public class TestConnection {
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection("jdbc:h2:~/test","sa","")) {
            Statement st = null;
            st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("select * from cities");

            while (resultSet.next()) {

            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));

            }
            conn.close();
        }

    }
}
