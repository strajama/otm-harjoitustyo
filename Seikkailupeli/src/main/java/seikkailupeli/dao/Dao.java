
package seikkailupeli.dao;

import java.sql.*;
import java.util.*;

public interface Dao<T, K> {

    List<T> findAll() throws SQLException;

    void delete(K key) throws SQLException;
    
    T saveOrUpdate(T object) throws SQLException;
    
     Integer findIdByName(String name) throws SQLException;
    
}