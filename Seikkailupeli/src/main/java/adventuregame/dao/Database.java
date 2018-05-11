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