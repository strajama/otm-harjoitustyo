/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventuregame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import adventuregame.domain.Helper;
import adventuregame.domain.Item;

public class HelperDao implements Dao<Helper, Integer> {

    private Database database;

    public HelperDao(Database database) {
        this.database = database;
        ArrayList<String> list = sqlites();
        try (Connection conn = database.getConnection()) {
            Statement test = conn.createStatement();
            ResultSet rs = test.executeQuery("SELECT * FROM Monster");
            if (!rs.next()) {
                Statement st = conn.createStatement();
                for (String d : list) {
                    st.executeUpdate(d);
                }
            }
        } catch (Throwable t) {
            System.out.println("Error >> " + t.getMessage());
        }
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
        Connection conn = database.getConnection();
        if (this.findIdByName(object.getName()) != null) {
            return null;
        }

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Helper (name, description) VALUES(?, ?)");
        stmt.setString(1, object.getName().toLowerCase());
        stmt.setString(2, object.getDescription());
        stmt.executeUpdate();

        stmt.close();
        conn.close();

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

    private ArrayList<String> sqlites() {
        ArrayList<String> list = new ArrayList<>();
        list.add("INSERT INTO Helper (name, description) VALUES ('gandalf', 'viisas velho Keski-Maasta')");
        list.add("INSERT INTO Helper (name, description) VALUES ('luke cage', 'supervahva ja vahingoittumaton sankari')");
        list.add("INSERT INTO Helper (name, description) VALUES ('michonne', 'taitava katanan käyttäjä')");
        list.add("INSERT INTO Helper (name, description) VALUES ('gizmo', 'söpöläinen, jota ei pidä ruokkia keskiyön jälkeen')");
        list.add("INSERT INTO Helper (name, description) VALUES ('hermione', 'jästisyntyinen taikaministeri')");
        list.add("INSERT INTO Helper (name, description) VALUES ('R2-D2', 'droidi, jota et ollut etsimässä')");
        return list;
    }

}
