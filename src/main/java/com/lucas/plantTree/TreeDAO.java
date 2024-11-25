package com.lucas.plantTree;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TreeDAO {
    public void insertTree(Tree tree) {
        String sql = "INSERT INTO tree (tree_name, id_planter, plantation_city, amount_planted) VALUES(?, ?, ?, ?)";
        String updatePlanterSql = "UPDATE planter SET amountPlantedTree = amountPlantedTree + ? WHERE id = ?";


        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            PreparedStatement updateStmt = conn.prepareStatement(updatePlanterSql)){

            stmt.setString(1, tree.getTreeName());
            stmt.setInt(2, tree.getIdPlanter());
            stmt.setString(3, tree.getPlantationCity());
            stmt.setInt(4, tree.getAmountPlanted());

            stmt.executeUpdate();
            System.out.println("Insert Tree Successfully");

            updateStmt.setInt(1, tree.getAmountPlanted());
            updateStmt.setInt(2, tree.getIdPlanter());
            updateStmt.executeUpdate();


        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tree> getAllTrees() {
        List<Tree> trees = new ArrayList<>();
        String sql = "SELECT * FROM tree";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while(rs.next()) {
                Tree tree = new Tree();
                tree.setId(rs.getInt("id"));
                tree.setTreeName(rs.getString("tree_name"));
                tree.setIdPlanter(rs.getInt("id_planter"));
                tree.setPlantationCity(rs.getString("plantation_city"));
                tree.setAmountPlanted(rs.getInt("amount_planted"));

                trees.add(tree);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trees;
    }

    public void updateTree(Tree tree) {
        String sql = "UPDATE tree SET tree_name = ?, id_planter = ?, plantation_city = ?, amount_planted = ? WHERE id = ?";
        String updatePlanterSql = "UPDATE planter SET amountPlantedTree = amountPlantedTree + ? WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            PreparedStatement updateStmt = conn.prepareStatement(updatePlanterSql)){

            stmt.setString(1, tree.getTreeName());
            stmt.setInt(2, tree.getIdPlanter());
            stmt.setString(3, tree.getPlantationCity());
            stmt.setInt(4, tree.getAmountPlanted());;
            stmt.setInt(5, tree.getId());

            stmt.executeUpdate();
            System.out.println("Update Tree Successfully");

            updateStmt.setInt(1, tree.getAmountPlanted());
            updateStmt.setInt(2, tree.getIdPlanter());
            updateStmt.executeUpdate();


        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTree(int treeId) {
        String getTreeSql = "SELECT amount_planted, id_planter FROM tree WHERE id = ?";
        String updatePlanterSql = "UPDATE planter SET amountPlantedTree = amountPlantedTree - ? WHERE id = ?";
        String deleteTreeSql = "DELETE FROM tree WHERE id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement getTreeStmt = conn.prepareStatement(getTreeSql);
             PreparedStatement updateStmt = conn.prepareStatement(updatePlanterSql);
             PreparedStatement deleteStmt = conn.prepareStatement(deleteTreeSql)) {

            getTreeStmt.setInt(1, treeId);
            ResultSet rs = getTreeStmt.executeQuery();

            if (rs.next()) {
                int amountPlanted = rs.getInt("amount_planted");
                int planterId = rs.getInt("id_planter");

                updateStmt.setInt(1, amountPlanted);
                updateStmt.setInt(2, planterId);
                updateStmt.executeUpdate();

                deleteStmt.setInt(1, treeId);
                deleteStmt.executeUpdate();

                System.out.println("Delete Tree Successfully");
            } else {
                System.out.println("Tree not found for deletion");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
