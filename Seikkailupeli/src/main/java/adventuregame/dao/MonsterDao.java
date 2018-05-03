package adventuregame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import adventuregame.domain.Monster;

/**
 * MonsterDao-luokkaa käytetään yhteydenpitoon tietokannan kanssa ja se
 * toteuttaa Dao-rajapinnan
 *
 * @author strajama
 */
public class MonsterDao implements Dao<Monster, Integer> {

    private Database database;

    /**
     * Metodi luo uuden MonsterDao-olion. Jos tietokannassa ei ole ennestään
     * Monster-taulussa tietoa, niin metodi lisää sinne pelin
     * perustoiminnallisuuden mahdollistavat tiedot.
     *
     * @param database -tietokanta, jota MonsterDao käyttää
     */
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

    /**
     * Metodi etsii kaikki tietokannassa olevat Monster-taulun tiedot
     *
     * @return lista, joka sisältää Monster-olioita
     * @throws SQLException - jos jotain menee pieleen
     */
    @Override
    public ArrayList<Monster> findAll() throws SQLException {
        ArrayList<Monster> monsters;
        try (Connection connection = database.getConnection();
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Monster");
                ResultSet rs = stmt.executeQuery()) {
            monsters = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                Monster newMonster = new Monster(name, description);
                monsters.add(newMonster);
            }
        }

        return monsters;
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
                PreparedStatement stmt = connection.prepareStatement("DELETE FROM Monster WHERE id = ?")) {
            stmt.setObject(1, key);
            stmt.execute();
        }
    }

    /**
     * Metodi tallentaa uuden tiedon tietokantaan, mutta ei päivitä vanhaa
     *
     * @param object - Monster-olio
     * @return - Monster tai null
     * @throws SQLException - jos jotain menee pieleen
     */
    @Override
    public Monster saveOrUpdate(Monster object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            if (this.findIdByName(object.getName()) != null) {
                return null;
            }

            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Monster (name, description) VALUES(?, ?)")) {
                stmt.setString(1, object.getName().toLowerCase());
                stmt.setString(2, object.getSlogan());
                stmt.executeUpdate();
            }
        }

        return object;
    }

    /**
     * Metodi etsii Monster-taulusta tiedon nimihaulla
     *
     * @param name - nimi, jolla haetaan
     * @return id-numero, joka yksilöi tiedon
     * @throws SQLException - jos jotain menee pieleen
     */
    @Override
    public Integer findIdByName(String name) throws SQLException {
        Integer id;
        try (Connection connection = database.getConnection();
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Monster WHERE name = ?")) {
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
        list.add("INSERT INTO Monster (name, description) VALUES ('gazebo', 'Et voi paeta. Sinun on taisteltava.')");
        return list;
    }
}
