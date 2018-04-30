package seikkailupeli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import seikkailupeli.domain.Monster;

public class MonsterDao implements Dao<Monster, Integer> {

    private Database database;

    public MonsterDao(Database database) {
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

        }
    }

    @Override
    public ArrayList<Monster> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Monster");
        ResultSet rs = stmt.executeQuery();

        ArrayList<Monster> monsters = new ArrayList<>();

        while (rs.next()) {
            String name = rs.getString("name");
            String description = rs.getString("description");
            Monster newMonster = new Monster(name, description);
            monsters.add(newMonster);
        }

        rs.close();
        stmt.close();
        connection.close();

        return monsters;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Monster WHERE key = ?");
        stmt.setObject(1, key);

        stmt.execute();
        stmt.close();
        connection.close();
    }

    @Override
    public Monster saveOrUpdate(Monster object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            if (this.findIdByName(object.getName()) != null) {
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Monster (name, description) VALUES(?, ?)");
            stmt.setString(1, object.getName().toLowerCase());
            stmt.setString(2, object.getSlogan());
            stmt.executeUpdate();

            stmt.close();
        }

        return object;
    }

    @Override
    public Integer findIdByName(String name) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Monster WHERE name = ?");
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
        list.add("INSERT INTO Monster (name, description) VALUES ('gazebo', 'Et voi paeta. Sinun on taisteltava.')");
        return list;
    }
}
