/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventuregame.dao;

import java.sql.SQLException;

/**
 *
 * @author strajama
 */
public class DaoService {

    private AreaDao areaDao;
    private ItemDao itemDao;
    private HelperDao helperDao;
    private MonsterDao monsterDao;
    private ScoreDao scoreDao;
    private Database database;

    public DaoService(Database database) throws SQLException {
        this.database = database;
        this.areaDao = new AreaDao(database);
        this.itemDao = new ItemDao(database);
        this.helperDao = new HelperDao(database);
        this.monsterDao = new MonsterDao(database);
        this.scoreDao = new ScoreDao(database);
    }

    public AreaDao getAreaDao() {
        return areaDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public HelperDao getHelperDao() {
        return helperDao;
    }

    public MonsterDao getMonsterDao() {
        return monsterDao;
    }

    public ScoreDao getScoreDao() {
        return scoreDao;
    }

}
