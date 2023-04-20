package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.models.Utility;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository {

    private final DataSource dataSource = DataSourceFactory.getDataSource();
    private final UtilityRepositoryImpl utilityRepository;

    public ReservationRepositoryImpl(UtilityRepositoryImpl utilityRepository) {
        this.utilityRepository = utilityRepository;
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        String query = "INSERT INTO reservations(id, client_id, apartment_id, check_in) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservation.getId());
            statement.setInt(2, reservation.getClientID());
            statement.setInt(3, reservation.getApartmentID());
            statement.setDate(4, Date.valueOf(reservation.getCheckIn()));
            statement.executeUpdate();
            return reservation;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Reservation getReservationById(int id) {
        String firstQuery = "SELECT * FROM reservations WHERE id = ?";
        String secondQuery = "SELECT * FROM reservation_utilities WHERE reservation_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement firstStatement = connection.prepareStatement(firstQuery);
             PreparedStatement secondStatement = connection.prepareStatement(firstQuery)) {
            firstStatement.setInt(1, id);
            secondStatement.setInt(1, id);
            try (ResultSet fistResultSet = firstStatement.executeQuery();
                 ResultSet secondResultSet = firstStatement.executeQuery()) {
                if (fistResultSet.next()) {
                    int reservationId = fistResultSet.getInt("id");
                    int clientId = fistResultSet.getInt("client_id");
                    int apartmentId = fistResultSet.getInt("apartment_id");
                    LocalDate checkIn = fistResultSet.getDate("check_in").toLocalDate();
                    Reservation reservation = new Reservation(reservationId, clientId, apartmentId, checkIn);
                    reservation.setCheckOut(fistResultSet.getDate("check_out").toLocalDate());
                    List<Utility> utilities = new ArrayList<>();
                    while (secondResultSet.next()) {
                        int utilityId = secondResultSet.getInt("utility_id");
                        Utility utility = utilityRepository.getUtilityById(utilityId);
                        utilities.add(utility);
                    }
                    reservation.setUtilities(utilities);
                    return reservation;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reservation> getAllReservationsList() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int reservationId = resultSet.getInt("id");
                int clientId = resultSet.getInt("client_id");
                int apartmentId = resultSet.getInt("apartment_id");
                LocalDate checkIn = resultSet.getDate("check_in").toLocalDate();
                Reservation reservation = new Reservation(reservationId, clientId, apartmentId, checkIn);
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservations;
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        String firstQuery = "UPDATE reservations " +
                "SET client_id = ?, apartment_id = ?, check_in = ?, check_out = ? " +
                "WHERE id = ?";
        String secondQuery = "INSERT INTO reservation_utilities (reservation_id, utility_id) VALUES (?, ?)" +
                "ON CONFLICT (reservation_id, utility_id) DO NOTHING";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement firstStatement = connection.prepareStatement(firstQuery);
             PreparedStatement secondStatement = connection.prepareStatement(secondQuery)) {
            firstStatement.setInt(1, reservation.getClientID());
            firstStatement.setInt(2, reservation.getApartmentID());
            firstStatement.setDate(3, Date.valueOf(reservation.getCheckIn()));
            firstStatement.setDate(4, Date.valueOf(reservation.getCheckOut()));
            firstStatement.setInt(5, reservation.getId());
            firstStatement.executeUpdate();
            List<Utility> utilities = reservation.getUtilities();
            for (Utility utility : utilities) {
                secondStatement.setInt(1, reservation.getId());
                secondStatement.setInt(2, utility.getId());
                secondStatement.executeUpdate();
            }
            return reservation;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteReservation(int id) {
        String firstQuery = "DELETE * FROM reservations WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement firstStatement = connection.prepareStatement(firstQuery)) {
            firstStatement.setInt(1, id);
            firstStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Reservation findByClientId(int clientId) {
        String firstQuery = "SELECT * FROM reservations WHERE clientid = ?";
        String secondQuery = "SELECT * FROM reservation_utilities WHERE reservation_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement firstStatement = connection.prepareStatement(firstQuery)) {
            firstStatement.setInt(1, clientId);
            try (ResultSet resultSet = firstStatement.executeQuery();
                 PreparedStatement secondStatement = connection.prepareStatement(secondQuery)) {
                if (resultSet.next()) {
                    int reservationId = resultSet.getInt("id");
                    int apartmentId = resultSet.getInt("apartment_id");
                    LocalDate checkIn = resultSet.getDate("check_in").toLocalDate();
                    LocalDate checkOut = resultSet.getDate("check_out").toLocalDate();
                    secondStatement.setInt(1, reservationId);
                    Reservation reservation = new Reservation(reservationId, clientId, apartmentId, checkIn);
                    reservation.setCheckOut(checkOut);
                    List<Utility> utilities = new ArrayList<>();
                    try (ResultSet secondResultSet = secondStatement.executeQuery()) {
                        while (secondResultSet.next()) {
                            int utilityId = secondResultSet.getInt("utility_id");
                            Utility utility = utilityRepository.getUtilityById(utilityId);
                            utilities.add(utility);
                        }
                    }
                    reservation.setUtilities(utilities);
                    return reservation;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
