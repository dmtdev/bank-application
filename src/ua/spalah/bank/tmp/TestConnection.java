package ua.spalah.bank.tmp;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        try(Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","")) {
            Statement st = null;
            st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("select * from clients ");

            while (resultSet.next()) {

                Array row = resultSet.getArray(2);
//            System.out.println(resultSet.getString(1));
//            System.out.println(resultSet.getString(2));
//            System.out.println(resultSet.getString(3));
//            System.out.println(resultSet.getString(4));
//            System.out.println(resultSet.getString(5));
            }
        }
    }
}
