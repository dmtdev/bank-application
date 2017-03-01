package ua.spalah.bank.dao.impl;

import ua.spalah.bank.commands.BankServerCommander;
import ua.spalah.bank.dao.CityDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by root on 15.02.2017.
 */
public class CityDaoImpl implements CityDao {
    @Override
    public int checkCityByName(String cityName) {
        Connection connection = BankServerCommander.connection;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select city_id from cities where trim(LOWER(city_name))=?");
            String city = cityName.toLowerCase().trim();
            preparedStatement.setString(1, city);
            ResultSet resultSet = preparedStatement.executeQuery();
            int city_id = 0;
            while (resultSet.next()) {
                city_id = resultSet.getInt("city_id");
            }
            if (city_id == 0) {
                preparedStatement = connection.prepareStatement("insert into cities values(null,?)");
                preparedStatement.setString(1, cityName.trim());
                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();
                while (resultSet.next()) {
                    city_id = resultSet.getInt(1);
                }
            }
            return city_id;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
