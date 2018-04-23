/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seikkailupeli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import seikkailupeli.domain.Helper;
import seikkailupeli.domain.Item;

/**
 *
 * @author strajama
 */
public class HelperDao implements Dao<Helper, Integer> {

    private Database database;

    public HelperDao(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Helper> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Helper");
        ResultSet rs = stmt.executeQuery();

        ArrayList<Helper> helpers = new ArrayList<>();

        while (rs.next()) {
            String name = rs.getString("name");
            String description = rs.getString("description");
            Helper newHelper = new Helper(name, description);
            helpers.add(newHelper);
        }

        rs.close();
        stmt.close();
        connection.close();

        return helpers;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Helper WHERE key = ?");
        stmt.setObject(1, key);

        stmt.execute();
        stmt.close();
        connection.close();
    }

    @Override
    public Helper saveOrUpdate(Helper object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            if (this.findIdByName(object.getName()) != null) {
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Helper (name, description) VALUES(?, ?)");
            stmt.setString(1, object.getName().toLowerCase());
            stmt.setString(2, object.getDescription());
            stmt.executeUpdate();

            stmt.close();
        }

        return object;
    }

    @Override
    public Integer findIdByName(String name) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Helper WHERE name = ?");
        stmt.setObject(1, name);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        rs.close();
        stmt.close();
        connection.close();

        return id;
    }

}
