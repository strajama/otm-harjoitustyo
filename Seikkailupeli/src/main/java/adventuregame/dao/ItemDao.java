package seikkailupeli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import seikkailupeli.domain.Item;

public class ItemDao implements Dao<Item, Integer> {

    private Database database;

    public ItemDao(Database database) {
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
    public ArrayList<Item> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Item");
        ResultSet rs = stmt.executeQuery();

        ArrayList<Item> items = new ArrayList<>();

        while (rs.next()) {
            String name = rs.getString("name");
            String description = rs.getString("description");
            Item newItem = new Item(name, description);
            items.add(newItem);
        }

        rs.close();
        stmt.close();
        connection.close();

        return items;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Item WHERE key = ?");
        stmt.setObject(1, key);

        stmt.execute();
        stmt.close();
        connection.close();
    }

    @Override
    public Item saveOrUpdate(Item object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            if (this.findIdByName(object.getName()) != null) {
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Item (name, description) VALUES(?, ?)");
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
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Item WHERE name = ?");
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
        list.add("INSERT INTO Item (name, description) VALUES ('palantiri', 'kauaksi näkevä kivi')");
        list.add("INSERT INTO Item (name, description) VALUES ('google', 'hakukone, jolla voi löytää maailman')");
        list.add("INSERT INTO Item (name, description) VALUES ('tv', 'loputon uusien ideoiden lähde')");
        list.add("INSERT INTO Item (name, description) VALUES ('kommunikaattori', 'kone, jolla saa yhteyden ystäviin')");
        list.add("INSERT INTO Item (name, description) VALUES ('kartta', 'esine, jota käytetään, kun ei voi kysyä tietä')");
        list.add("INSERT INTO Item (name, description) VALUES ('lokikirja', 'tapahtumien tallennuspaikka')");
        return list;
    }
}
