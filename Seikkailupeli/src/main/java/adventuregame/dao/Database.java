package adventuregame.dao;

import java.sql.*;
import java.util.ArrayList;

/**
 * Luokka Database luo sovelluksen tarvitsemat tietokantataulut, jos niitä ei
 * ole ja ottaa tietokantaan yhteyden
 *
 * @author strajama
 */
public class Database {

    private String databaseAddress;

    /**
     * Metodi luo uuden Database-olion
     *
     * @param databaseAddress - osoite, josta tietokanta löytyy
     * @throws ClassNotFoundException - jos tulee jokin poikkeus
     */
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    /**
     * Metodi avaa tietokannan
     */
    public void init() {
        ArrayList<String> createTables = sqlitesTables();
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();
            for (String d : createTables) {
                st.executeUpdate(d);
            }
        } catch (Throwable t) {
        }
    }

    /**
     * Metodi palauttaa listan, joka luo tarvittavat tietokantataulut, jos ne
     * puuttuvat
     *
     * @return lista SQL-käskyjä
     */
    private ArrayList<String> sqlitesTables() {
        ArrayList<String> list = new ArrayList<>();
        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        list.add("CREATE TABLE IF NOT EXISTS Area (id integer PRIMARY KEY, name varchar(20), description varchar(200))");
        list.add("CREATE TABLE IF NOT EXISTS Item (id integer PRIMARY KEY, name varchar(20), description varchar (200))");
        list.add("CREATE TABLE IF NOT EXISTS Helper (id integer PRIMARY KEY, name varchar(20), description varchar(200))");
        list.add("CREATE TABLE IF NOT EXISTS Monster (id integer PRIMARY KEY, name varchar(20), description varchar (200))");
        list.add("CREATE TABLE IF NOT EXISTS Score (id integer PRIMARY KEY, name varchar(10), points integer)");

        return list;
    }

}
/*
    private ArrayList<String> sqlitesInsertAreas() {
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

    private ArrayList<String> sqlitesInsertRest() {
        ArrayList<String> list = new ArrayList<>();
        list.add("INSERT INTO Item (name, description) VALUES ('palantiri', 'kauaksi näkevä kivi')");
        list.add("INSERT INTO Item (name, description) VALUES ('google', 'hakukone, jolla voi löytää maailman')");
        list.add("INSERT INTO Item (name, description) VALUES ('tv', 'loputon uusien ideoiden lähde')");
        list.add("INSERT INTO Item (name, description) VALUES ('kommunikaattori', 'kone, jolla saa yhteyden ystäviin')");
        list.add("INSERT INTO Item (name, description) VALUES ('kartta', 'esine, jota käytetään, kun ei voi kysyä tietä')");
        list.add("INSERT INTO Item (name, description) VALUES ('lokikirja', 'tapahtumien tallennuspaikka')");
        list.add("INSERT INTO Helper (name, description) VALUES ('gandalf', 'viisas velho Keski-Maasta')");
        list.add("INSERT INTO Helper (name, description) VALUES ('luke cage', 'supervahva ja vahingoittumaton sankari')");
        list.add("INSERT INTO Helper (name, description) VALUES ('michonne', 'taitava katanan käyttäjä')");
        list.add("INSERT INTO Helper (name, description) VALUES ('gizmo', 'söpöläinen, jota ei pidä ruokkia keskiyön jälkeen')");
        list.add("INSERT INTO Helper (name, description) VALUES ('hermione', 'jästisyntyinen taikaministeri')");
        list.add("INSERT INTO Helper (name, description) VALUES ('R2-D2', 'droidi, jota et ollut etsimässä')");
        list.add("INSERT INTO Monster (name, description) VALUES ('gazebo', 'Et voi paeta. Sinun on taisteltava.')");
        return list;
    }*/
