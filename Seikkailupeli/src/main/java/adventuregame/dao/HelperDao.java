package adventuregame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import adventuregame.domain.Helper;

/**
 * HelperDao-luokkaa käytetään yhteydenpitoon tietokannan kanssa ja se toteuttaa
 * Dao-rajapinnan
 *
 * @author strajama
 */
public class HelperDao implements Dao<Helper, Integer> {

    private Database database;

    /**
     * Metodi luo uuden HelperDao-olion. Jos tietokannassa ei ole ennestään
     * Helper-taulussa tietoa, niin metodi lisää sinne pelin
     * perustoiminnallisuuden mahdollistavat tiedot.
     *
     * @param database -tietokanta, jota HelperDao käyttää
     */
    public HelperDao(Database database) {
        this.database = database;
        ArrayList<String> list = sqlites();
        try (Connection conn = database.getConnection()) {
            Statement test = conn.createStatement();
            ResultSet rs = test.executeQuery("SELECT * FROM Helper");
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
     * Metodi etsii kaikki tietokannassa olevat Helper-taulun tiedot
     *
     * @return lista, joka sisältää Helper-olioita
     * @throws SQLException - jos jotain menee pieleen
     */
    @Override
    public ArrayList<Helper> findAll() throws SQLException {
        ArrayList<Helper> helpers;
        try (Connection connection = database.getConnection();
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Helper");
                ResultSet rs = stmt.executeQuery()) {
            helpers = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                Helper newHelper = new Helper(name, description);
                helpers.add(newHelper);
            }
        }

        return helpers;
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
                PreparedStatement stmt = connection.prepareStatement("DELETE FROM Helper WHERE id = ?")) {
            stmt.setObject(1, key);

            stmt.execute();
        }
    }

    /**
     * Metodi tallentaa uuden tiedon tietokantaan, mutta ei päivitä vanhaa
     *
     * @param object - Helper-olio
     * @return - Helper tai null
     * @throws SQLException - jos jotain menee pieleen
     */
    @Override
    public Helper saveOrUpdate(Helper object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            if (this.findIdByName(object.getName()) != null) {
                conn.close();
                return null;
            }

            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Helper (name, description) VALUES(?, ?)")) {
                stmt.setString(1, object.getName().toLowerCase());
                stmt.setString(2, object.getDescription());
                stmt.executeUpdate();
            }
        }

        return object;
    }

    /**
     * Metodi etsii Helper-taulusta tiedon nimihaulla
     *
     * @param name - nimi, jolla haetaan
     * @return id-numero, joka yksilöi tiedon
     * @throws SQLException - jos jotain menee pieleen
     */
    @Override
    public Integer findIdByName(String name) throws SQLException {
        Integer id;
        try (Connection connection = database.getConnection();
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Helper WHERE name = ?")) {
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
        list.add("INSERT INTO Helper (name, description) VALUES ('gandalf', 'viisas velho Keski-Maasta')");
        list.add("INSERT INTO Helper (name, description) VALUES ('luke cage', 'supervahva ja vahingoittumaton sankari')");
        list.add("INSERT INTO Helper (name, description) VALUES ('michonne', 'taitava katanan käyttäjä')");
        list.add("INSERT INTO Helper (name, description) VALUES ('gizmo', 'söpöläinen, jota ei pidä ruokkia keskiyön jälkeen')");
        list.add("INSERT INTO Helper (name, description) VALUES ('hermione', 'jästisyntyinen taikaministeri')");
        list.add("INSERT INTO Helper (name, description) VALUES ('R2-D2', 'droidi, jota et ollut etsimässä')");
        return list;
    }

}
