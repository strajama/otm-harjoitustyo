package adventuregame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import adventuregame.domain.Area;

/**
 * AreaDao-luokkaa käytetään yhteydenpitoon tietokannan kanssa ja se toteuttaa
 * Dao-rajapinnan
 *
 * @author strajama
 */
public class AreaDao implements Dao<Area, Integer> {

    private Database database;

    /**
     * Metodi luo uuden AreaDao-olion. Jos tietokannassa ei ole ennestään
     * Area-taulussa tietoa, niin metodi lisää sinne pelin
     * perustoiminnallisuuden mahdollistavat tiedot.
     *
     * @param database - tietokanta, jota AreaDao käyttää
     */
    public AreaDao(Database database) {
        this.database = database;
        ArrayList<String> list = sqlites();
        try (Connection conn = database.getConnection()) {
            Statement test = conn.createStatement();
            ResultSet rs = test.executeQuery("SELECT * FROM Area");
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
     * Metodi etsii kaikki tietokannassa olevat Area-taulun tiedot
     *
     * @return palauttaa listan Area-olioita
     * @throws SQLException - jos tietokantayhteyden kanssa on häiriöitä
     */
    @Override
    public ArrayList<Area> findAll() throws SQLException {
        ArrayList<Area> areas;
        try (Connection connection = database.getConnection();
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Area");
                ResultSet rs = stmt.executeQuery()) {
            areas = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                Area newArea = new Area(name, description);
                areas.add(newArea);
            }
        }

        return areas;
    }

    /**
     * Metodi poistaa Area-taulusta tiedon
     *
     * @param key - id-avain
     * @throws SQLException - jos tietokantayhteyden kanssa on häiriöitä
     */
    @Override
    public void delete(Integer key) throws SQLException {
        try (Connection connection = database.getConnection();
                PreparedStatement stmt = connection.prepareStatement("DELETE FROM Area WHERE id = ?")) {
            stmt.setObject(1, key);

            stmt.execute();
        }
    }

    /**
     * Metodi tallentaa uuden tiedon tietokantaan, mutta ei päivitä vanhaa
     *
     * @param object - lisättävä tieto
     * @return - lisätty Area tai null
     * @throws SQLException - jos tietokantayhteyden kanssa on häiriöitä
     */
    @Override
    public Area saveOrUpdate(Area object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            if (this.findIdByName(object.getName()) != null) {
                return null;
            }

            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Area (name, description) VALUES(?, ?)")) {
                stmt.setString(1, object.getName().toLowerCase());
                stmt.setString(2, object.getDescription());
                stmt.executeUpdate();
            }
        }
        return object;
    }

    /**
     * Metodi etsii Area-taulusta nimellä oikeaa tietoa ja palauttaa id-numeron
     *
     * @param name - haettavan tiedon nimi
     * @return - id-numero
     * @throws SQLException - jos tietokantayhteyden kanssa on häiriöitä
     */
    @Override
    public Integer findIdByName(String name) throws SQLException {
        Integer id;
        try (Connection connection = database.getConnection();
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Area WHERE name = ?")) {
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
        list.add("INSERT INTO Area (name, description) VALUES ('suo', 'Tunnet suopursun voimakkaan tuoksun sieraimissasi. Sinua yskittää.')");
        list.add("INSERT INTO Area (name, description) VALUES ('metsä', 'Seisot tiheäkasvuisessa paikassa, jossa et näe metsää puilta.')");
        list.add("INSERT INTO Area (name, description) VALUES ('aukio', 'Olet pienellä aukiolla. Melkein näkymättömät, pienet polut vievät eri suuntiin.')");
        list.add("INSERT INTO Area (name, description) VALUES ('lehto', 'Auringonsäteet valaisevat lehtien läpi lehdossa ja saa sinulle lämpimän olon.')");
        list.add("INSERT INTO Area (name, description) VALUES ('niitty', 'Kukkaniityllä kasvaa orvokki, lehdokki, vuokko ja moni muu.')");
        list.add("INSERT INTO Area (name, description) VALUES ('pusikko', 'Olet pusikossa. On vaikea nähdä minne mennä.')");
        list.add("INSERT INTO Area (name, description) VALUES ('puro', 'Edessäsi on kylmävetinen puro, jonka vesi juoksee iloisesti pulisten.')");
        list.add("INSERT INTO Area (name, description) VALUES ('lähde', 'Näet kirkasvetisen lähteen. Kumarrut juomaan siitä huikan.')");
        list.add("INSERT INTO Area (name, description) VALUES ('kumpu', 'Seisot kummulla, joka muistuttaa hautakeroja. Saat kylmiä väreitä.')");
        list.add("INSERT INTO Area (name, description) VALUES ('vuori', 'Vuorelta on komeat näkymät yli koko maan.')");
        list.add("INSERT INTO Area (name, description) VALUES ('laakso', 'Laakso on kaunis ja laakea. Täällä sinun on hyvä olla.')");
        list.add("INSERT INTO Area (name, description) VALUES ('luola', 'Tulet pimeään luolaan, jota ei voi ylittää eikä alittaa.')");
        list.add("INSERT INTO Area (name, description) VALUES ('kallio', 'Olet kalliolla kukkalalla ja sinun tekisi mieli rakentaa maja.')");
//        list.add("INSERT INTO Area (name, description) VALUES ('koti', 'Oma koti kullan kallis.')");
        return list;
    }

}
