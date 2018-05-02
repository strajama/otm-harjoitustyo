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
 *
 * @author strajama
 */
public class ScoreDao implements Dao<Score, Integer> {

    private Database database;

    public ScoreDao(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Score> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Score");
        ResultSet rs = stmt.executeQuery();

        ArrayList<Score> scores = new ArrayList<>();

        while (rs.next()) {
            String name = rs.getString("name");
            int points = rs.getInt("points");
            Score newScore = new Score(name, points);
            scores.add(newScore);
        }

        rs.close();
        stmt.close();
        connection.close();

        return scores;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Score WHERE key = ?");
        stmt.setObject(1, key);

        stmt.execute();
        stmt.close();
        connection.close();
    }

    @Override
    public Score saveOrUpdate(Score object) throws SQLException {
        Connection conn = database.getConnection();
        if (this.findIdByName(object.getName()) != null) {
            return null;
        }

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Score (name, points) VALUES(?, ?)");
        stmt.setString(1, object.getName().toLowerCase());
        stmt.setInt(2, object.getPoints());
        stmt.executeUpdate();

        stmt.close();
        conn.close();

        return object;
    }

    @Override
    public Integer findIdByName(String name) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Score WHERE name = ?");
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

    public ArrayList<Score> bestScores() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Score ORDER BY points DESC LIMIT 5");
        ResultSet rs = stmt.executeQuery();

        ArrayList<Score> scores = new ArrayList<>();

        while (rs.next()) {
            String name = rs.getString("name");
            int points = rs.getInt("points");
            Score newScore = new Score(name, points);
            scores.add(newScore);
        }

        rs.close();
        stmt.close();
        connection.close();

        return scores;
    }

}
