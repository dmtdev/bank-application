package ua.spalah.bank.dao.impl;

import ua.spalah.bank.commands.BankServerCommander;
import ua.spalah.bank.dao.ClientDao;
import ua.spalah.bank.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//import static ua.spalah.bank.commands.BankServerCommander.connection;

/**
 * Created by root on 14.02.2017.
 */
public class ClientDaoImpl implements ClientDao {

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

    @Override
    public Client save(Client client) {
        Connection connection = BankServerCommander.connection;
        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("Select city_id from cities where trim(LOWER(city_name))=?");
//            String city = client.getCity().toLowerCase().trim();
//            preparedStatement.setString(1, city);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            int city_id = 0;
//            while (resultSet.next()) {
//                city_id = resultSet.getInt("city_id");
//            }
//            if (city_id == 0) {
//                preparedStatement = connection.prepareStatement("insert into cities values(null,?)");
//                preparedStatement.setString(1, client.getCity().trim());
//                preparedStatement.executeUpdate();
//                resultSet = preparedStatement.getGeneratedKeys();
//                while (resultSet.next()) {
//                    city_id = resultSet.getInt(1);
//                }
//            }
            int city_id = checkCityByName(client.getCity());
            Client c = findByName(client.getClientName());
            //System.out.println(c.toString());
//            PreparedStatement preparedStatement = connection.prepareStatement("select count(client_id) c from clients where trim(lower(name))=?");
//            preparedStatement.setString(1, client.getClientName().toLowerCase().trim());
//            ResultSet resultSet = preparedStatement.executeQuery();
//            boolean check = true;
//            while (resultSet.next()) {
//                if (resultSet.getLong("c") != 0) {
//                    check = false;
//                }
//            }
            if (c == null) {
                System.out.println("new client");
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT into clients values(null,?,?,?,?,?,null)");
                preparedStatement.setString(1, client.getClientName());
                preparedStatement.setString(2, client.getEmail());
                preparedStatement.setString(3, client.getTel());
                preparedStatement.setLong(4, city_id);
                preparedStatement.setString(5, client.getSex().toString());
                preparedStatement.executeUpdate();
                BankServerCommander.currentClient = client;
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Client update(Client client) {
        return null; // TODO: 14.02.2017 add UpdateClientCommander
    }

    @Override
    public Client saveOrUpdate(Client client) {
        return null;
        //а надо ли....
    }

    @Override
    public boolean delete(long clientId) {
        PreparedStatement preparedStatement = null;
        Connection connection = BankServerCommander.connection;
        try {
            preparedStatement = connection.prepareStatement("delete from clients where client_id=?");
            preparedStatement.setLong(1,clientId);
            boolean result = preparedStatement.execute();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Client find(long id) {
        PreparedStatement preparedStatement = null;
        Connection connection = BankServerCommander.connection;
        try {
            preparedStatement = connection.prepareStatement("select client_id as clientId, name as clientName, gender as sex, email as email, phone as tel, city_name as city from clients, cities where client_id = ? and cities.city_id=clients.city_id");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client client = BankServerCommander.mapModel(resultSet, new Client().getClass());
                return client;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        PreparedStatement preparedStatement = null;
        Connection connection = BankServerCommander.connection;
        try {
            preparedStatement = connection.prepareStatement("select client_id as clientId, name as clientName, gender as sex, email as email, phone as tel, city_name as city from clients, cities where cities.city_id=clients.city_id");
            List<Client> clientList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client client = BankServerCommander.mapModel(resultSet, new Client().getClass());
                clientList.add(client);
                return clientList;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client findByName(String name) {
        PreparedStatement preparedStatement = null;
        Connection connection = BankServerCommander.connection;
        try {
            preparedStatement = connection.prepareStatement("select client_id as clientId, name as clientName, gender as sex, email as email, phone as tel, city_name as city from clients, cities where trim(lower(clients.name)) = ? and cities.city_id=clients.city_id");
            preparedStatement.setString(1, name.toLowerCase().trim());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client client = BankServerCommander.mapModel(resultSet, new Client().getClass());
                return client;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
