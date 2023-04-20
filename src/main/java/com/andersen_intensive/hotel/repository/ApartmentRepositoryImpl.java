package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.models.Utility;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApartmentRepositoryImpl implements ApartmentRepository {

    private final Map<Integer, Apartment> apartments = new HashMap<>();
    private final DataSource dataSource = DataSourceFactory.getDataSource();
    @Override
    public Apartment add(Apartment apartment) {
        String sql = "INSERT INTO apartments(apartmentId, price, apartmentType, ApartmentStatus) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, apartment.getApartmentId());
            statement.setBigDecimal(2, apartment.getApartmentPrice());
            statement.setString(3, String.valueOf(apartment.getApartmentType()));
            statement.setString(4, String.valueOf(apartment.getApartmentStatus()));
            statement.executeUpdate();
            return apartment;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Apartment getById(int number) {
        String sql = "SELECT * FROM apartments WHERE number = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,number);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int apartmentId = resultSet.getInt("id");
                    BigDecimal price = resultSet.getBigDecimal("price");
                    String apartmentType = resultSet.getString("apartmentType");
                    String apartmentStatus = resultSet.getString("apartmentStatus") ;
                    return new Apartment(apartmentId, price,apartmentType, apartmentStatus);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Apartment apartment) {
        apartments.put(apartment.getApartmentId(), apartment);
    }

    @Override
    public List<Apartment> getAll() {
        return new ArrayList<>(apartments.values());
    }

    @Override
    public void delete(Apartment apartment) {
        apartments.remove(apartment.getApartmentId());
    }
}
