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

    /**
     * Metodi etsii kaikki tietokannassa olevat rajapinnan toteuttavan luokan
     * taulun tiedot
     *
     * @return lista, joka sisältää olioita
     * @throws SQLException - jos jotain menee pieleen
     */
    ArrayList<T> findAll() throws SQLException;

    /**
     * Metodi poistaa tietokannasta tiedon
     *
     * @param key - tiedon yksilöivä id-numero
     * @throws SQLException - jos jotain menee pieleen
     */
    void delete(K key) throws SQLException;

    /**
     * Metodi tallentaa ja päivittää tietokantaa
     *
     * @param object - olio, jonka tietokantataulun tietoja muutetaan
     * @return - olio tai null
     * @throws SQLException - jos jotain menee pieleen
     */
    T saveOrUpdate(T object) throws SQLException;

    /**
     * Metodi etsii Helper-taulusta tiedon nimihaulla
     *
     * @param name - nimi, jolla haetaan
     * @return id-numero, joka yksilöi tiedon
     * @throws SQLException - jos jotain menee pieleen
     */
    Integer findIdByName(String name) throws SQLException;

}
