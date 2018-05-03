package adventuregame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import adventuregame.domain.Item;

/**
 * ItemDao-luokkaa käytetään yhteydenpitoon tietokannan kanssa ja se toteuttaa
 * Dao-rajapinnan
 *
 * @author strajama
 */
public class ItemDao implements Dao<Item, Integer> {

    private Database database;

    /**
     * Metodi luo uuden ItemDao-olion. Jos tietokannassa ei ole ennestään
     * Item-taulussa tietoa, niin metodi lisää sinne pelin
     * perustoiminnallisuuden mahdollistavat tiedot.
     *
     * @param database -tietokanta, jota ItemDao käyttää
     */
    public ItemDao(Database database) throws SQLException {
        this.database = database;
        ArrayList<String> list = sqlites();
        try (Connection conn = database.getConnection();
                Statement test = conn.createStatement();
                ResultSet rs = test.executeQuery("SELECT * FROM Item")) {
            if (!rs.next()) {
                Statement st = conn.createStatement();
                for (String d : list) {
                    st.executeUpdate(d);
                }
            }
        } catch (Throwable t) {
        }

    }

    /**
     * Metodi etsii kaikki tietokannassa olevat Item-taulun tiedot
     *
     * @return lista, joka sisältää Item-olioita
     * @throws SQLException - jos jotain menee pieleen
     */
    @Override
    public ArrayList<Item> findAll() throws SQLException {
        ArrayList<Item> items;
        try (Connection connection = database.getConnection();
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Item");
                ResultSet rs = stmt.executeQuery()) {
            items = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                Item newItem = new Item(name, description);
                items.add(newItem);
            }
        }

        return items;
    }

    /**
     * Metodi poistaa tietokannasta tiedon
     *
     * @param key - tiedon yksilöivä id-numero
     * @throws SQLException - jos jotain menee pieleen
     */
    @Override
    public void delete(Integer key) throws SQLException {
        try (Connection connection = database.getConnection();
                PreparedStatement stmt = connection.prepareStatement("DELETE FROM Item WHERE id = ?")) {
            stmt.setObject(1, key);

            stmt.execute();
        }
    }

    /**
     * Metodi tallentaa uuden tiedon tietokantaan, mutta ei päivitä vanhaa
     *
     * @param object - Item-olio
     * @return - Item tai null
     * @throws SQLException - jos jotain menee pieleen
     */
    @Override
    public Item saveOrUpdate(Item object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            if (this.findIdByName(object.getName()) != null) {
                return null;
            }

            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Item (name, description) VALUES(?, ?)")) {
                stmt.setString(1, object.getName().toLowerCase());
                stmt.setString(2, object.getDescription());
                stmt.executeUpdate();
            }
        }

        return object;
    }

    /**
     * Metodi etsii Item-taulusta tiedon nimihaulla
     *
     * @param name - nimi, jolla haetaan
     * @return id-numero, joka yksilöi tiedon
     * @throws SQLException - jos jotain menee pieleen
     */
    @Override
    public Integer findIdByName(String name) throws SQLException {
        Integer id;
        try (Connection connection = database.getConnection();
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Item WHERE name = ?")) {
            stmt.setObject(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                boolean hasOne = rs.next();
                if (!hasOne) {
                    return null;
                }
                id = rs.getInt("id");
            }
        }

        return id;
    }

    /**
     * Metodi palauttaa listana SQL-käskyt, joissa on pelin toiminnan varmistava
     * perussisältö
     *
     * @return lista SQL-lauseita
     */
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
