//package com.andersen_intensive.hotel.repository;
//
//import com.andersen_intensive.hotel.models.Utility;
//
//import javax.sql.DataSource;
//import java.math.BigDecimal;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class UtilityRepositoryImpl implements UtilityRepository {
//
//    private final Map<Integer, Utility> services = new HashMap<>();
//    private final DataSource dataSource = DataSourceFactory.getDataSource();
//
//    @Override
//    public Utility addUtility(Utility utility) {
//        String sql = "INSERT INTO utilities(id, name, price) VALUES (?, ?, ?)";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, utility.getId());
//            statement.setString(2, utility.getName());
//            statement.setBigDecimal(3, utility.getPrice());
//            statement.executeUpdate();
//            return utility;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public Utility getUtilityById(int id) {
//        String sql = "SELECT * FROM utilities WHERE id = ?";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, id);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    int utilityId = resultSet.getInt("id");
//                    String name = resultSet.getString("name");
//                    BigDecimal price = resultSet.getBigDecimal("price");
//                    return new Utility(utilityId, name, price);
//                } else {
//                    return null;
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public Utility updateUtility(Utility utility) {
//        services.put(utility.getId(), utility);
//        return utility;
//    }
//
//    @Override
//    public List<Utility> getAllUtility() {
//        List<Utility> utilities = new ArrayList<>();
//        String sql = "SELECT * FROM utilities";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql);
//             ResultSet resultSet = statement.executeQuery()) {
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                BigDecimal price = resultSet.getBigDecimal("price");
//                Utility utility = new Utility(id, name, price);
//                utilities.add(utility);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return utilities;
//    }
//
//    @Override
//    public Utility getByName(String name) {
//        for (Map.Entry<Integer, Utility> entry : services.entrySet()) {
//            if (entry.getValue().getName().equals(name)) {
//                return entry.getValue();
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteUtility(Utility utility) {
//        services.remove(utility.getId());
//    }
//}
