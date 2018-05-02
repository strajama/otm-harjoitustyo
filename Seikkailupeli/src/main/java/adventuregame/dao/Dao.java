package adventuregame.dao;

import java.sql.*;
import java.util.*;

/**
 * Dao-rajapinta varmistaa, että luokkien muokkaaminen ei häiritse tietokannan
 * käyttöä
 *
 * @author strajama
 * @param <T> - Olio, jonka tiedot luokka tallentaa tietokantaan
 * @param <K> - Avain, jolla tiedot tietokannassa yksilöidään
 */
public interface Dao<T, K> {

    List<T> findAll() throws SQLException;

    void delete(K key) throws SQLException;

    T saveOrUpdate(T object) throws SQLException;

    Integer findIdByName(String name) throws SQLException;

}
