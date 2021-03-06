/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventuregame.dao;

import adventuregame.domain.Score;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ScoreDao-luokkaa käytetään yhteydenpitoon tietokannan kanssa ja se toteuttaa
 * Dao-rajapinnan
 *
 * @author strajama
 */
public class ScoreDao implements Dao<Score, Integer> {

    private Database database;

    /**
     * Metodi luo uuden ScoreDao-olion.
     *
     * @param database -tietokanta, jota ScoreDao käyttää
     */
    public ScoreDao(Database database) {
        this.database = database;
    }

    /**
     * Metodi etsii kaikki tietokannassa olevat Score-taulun tiedot
     *
     * @return lista, joka sisältää Score-olioita
     * @throws SQLException - jos jotain menee pieleen
     */
    @Override
    public ArrayList<Score> findAll() throws SQLException {
        ArrayList<Score> scores;
        try (Connection connection = database.getConnection();
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Score");
                ResultSet rs = stmt.executeQuery()) {
            scores = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                int points = rs.getInt("points");
                Score newScore = new Score(name, points);
                scores.add(newScore);
            }
        }

        return scores;
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
                PreparedStatement stmt = connection.prepareStatement("DELETE FROM Score WHERE id = ?")) {
            stmt.setObject(1, key);

            stmt.execute();
        }
    }

    /**
     * Metodi tallentaa uuden tiedon tietokantaan ja päivittää vanhaa
     *
     * @param object - Helper-olio
     * @return - Helper tai null
     * @throws SQLException - jos jotain menee pieleen
     */
    @Override
    public Score saveOrUpdate(Score object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            if (this.findIdByName(object.getName()) != null) {
                try (PreparedStatement stmt = conn.prepareStatement("UPDATE Score SET points=? WHERE name=?")) {
                    stmt.setInt(1, object.getPoints());
                    stmt.setString(2, object.getName().toLowerCase());
                    stmt.executeUpdate();
                }
                return null;
            }

            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Score (name, points) VALUES(?, ?)")) {
                stmt.setString(1, object.getName().toLowerCase());
                stmt.setInt(2, object.getPoints());
                stmt.executeUpdate();
            }
        }

        return object;
    }

    /**
     * Metodi etsii Score-taulusta tiedon nimihaulla
     *
     * @param name - nimi, jolla haetaan
     * @return id-numero, joka yksilöi tiedon
     * @throws SQLException - jos jotain menee pieleen
     */
    @Override
    public Integer findIdByName(String name) throws SQLException {
        Integer id;
        try (Connection connection = database.getConnection();
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Score WHERE name = ?")) {
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
     * Metodi hakee viisi korkeinta pistetulosta laskevassa järjestyksessä
     *
     * @return - lista Score-olioita
     * @throws SQLException - jos jotain menee pieleen
     */
    public ArrayList<Score> bestScores() throws SQLException {
        ArrayList<Score> scores;
        try (Connection connection = database.getConnection();
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Score ORDER BY points DESC LIMIT 5");
                ResultSet rs = stmt.executeQuery()) {
            scores = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                int points = rs.getInt("points");
                Score newScore = new Score(name, points);
                scores.add(newScore);
            }
        }

        return scores;
    }

}
