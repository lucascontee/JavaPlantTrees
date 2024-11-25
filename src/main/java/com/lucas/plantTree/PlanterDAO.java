package com.lucas.plantTree;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlanterDAO {
    public boolean emailExists(String email) {
        String sql = "SELECT 1 FROM planter WHERE email = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Retorna true se encontrar o e-mail
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insertPlanter(Planter planter) {
        String sql = "INSERT INTO planter (planter_name, last_name, age, country, amountPlantedTree, email, planter_password) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, planter.getPlanterName());
            stmt.setString(2, planter.getLastName());
            stmt.setInt(3, planter.getAge());
            stmt.setString(4, planter.getCountry());
            stmt.setInt(5, planter.getAmountPlantedTree());
            stmt.setString(6, planter.getEmail());
            stmt.setString(7, planter.getPassword());

            stmt.executeUpdate();
            System.out.println("Planter inserted successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Planter> getAllPlanters() {
        List<Planter> planters = new ArrayList<>();
        String sql = "SELECT * FROM planter";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Planter planter = new Planter();
                planter.setId(rs.getInt("id"));
                planter.setPlanterName(rs.getString("planter_name"));
                planter.setLastName(rs.getString("last_name"));
                planter.setAge(rs.getInt("age"));
                planter.setCountry(rs.getString("country"));
                planter.setAmountPlantedTree(rs.getInt("amountPlantedTree"));
                planter.setEmail(rs.getString("email"));
                planter.setPassword(rs.getString("planter_password"));

                planters.add(planter);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planters;
    }

    public void updatePlanter(Planter planter) {
        String sql = "UPDATE planter SET planter_name = ?, last_name = ?, age = ?, country = ?, amountPlantedTree = ?," +
                " email = ?, planter_password = ? WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, planter.getPlanterName());
            stmt.setString(2, planter.getLastName());
            stmt.setInt(3, planter.getAge());
            stmt.setString(4, planter.getCountry());
            stmt.setInt(5, planter.getAmountPlantedTree());
            stmt.setString(6, planter.getEmail());
            stmt.setString(7, planter.getPassword());
            stmt.setInt(8, planter.getId());

            stmt.executeUpdate();
            System.out.println("Planter successfully updated");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePlanter(int id) {
        String sql = "DELETE FROM planter WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Planter successfully deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

