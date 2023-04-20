package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Client;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientRepositoryImpl implements ClientRepository {

    private final Map<Integer, Client> clients = new HashMap<>();

    private final DataSource dataSource = DataSourceFactory.getDataSource();

    @Override
    public Client addClient(Client client) {
        String sql = "INSERT INTO clients(firstName, LastName, phoneNumber, personalID) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getPhoneNumber());
            statement.setInt(4, client.getPersonalID());
            statement.executeUpdate();
            return client;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client getClientById(int personalID) {
        String sql = "SELECT * FROM clients WHERE personalID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(4, personalID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    personalID = resultSet.getInt("personalID");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    return new Client(firstName, lastName, phoneNumber, personalID);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client getClientByPhoneNumber(String phoneNumber) {
        String sql = "SELECT * FROM clients WHERE phoneNumber = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(3, phoneNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int personalID = resultSet.getInt("personalID");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    phoneNumber = resultSet.getString("phoneNumber");
                    return new Client(firstName, lastName, phoneNumber, personalID);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String phoneNumber = resultSet.getString("phoneNumber");
                int personalID = resultSet.getInt("personalID");
                Client client = new Client(firstName, lastName, phoneNumber, personalID);
                clients.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }

    @Override
    public void updateClient(Client client) {
        clients.put(client.getPersonalID(), client);
    }

    @Override
    public void deleteClient(int id) {
        clients.remove(id);
    }
}