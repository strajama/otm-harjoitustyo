package seikkailupeli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import seikkailupeli.domain.Area;
import seikkailupeli.domain.Item;

public class AreaDao implements Dao<Area, Integer> {

    private Database database;

    public AreaDao(Database database) {
        this.database = database;
    }

    @Override
    public List<Area> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Area");
        ResultSet rs = stmt.executeQuery();

        List<Area> areas = new ArrayList<>();

        while (rs.next()) {
            String name = rs.getString("name");
            String description = rs.getString("description");
            Area newArea = new Area(name, description);
            areas.add(newArea);
        }

        rs.close();
        stmt.close();
        connection.close();

        return areas;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Area WHERE key = ?");
        stmt.setObject(1, key);

        stmt.execute();
        stmt.close();
        connection.close();
    }

    @Override
    public Area saveOrUpdate(Area object) throws SQLException {
        Connection conn = database.getConnection();

        if (this.findIdByName(object.getName()) != null) {
            return null;
        }

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Area (name, description) VALUES(?, ?)");
        stmt.setString(1, object.getName());
        stmt.setString(2, object.getDescription());
        stmt.executeUpdate();

        stmt.close();
        conn.close();

        return object;
    }

    @Override
    public Integer findIdByName(String name) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Area WHERE name = ?");
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
