package adventuregame.dao;

import adventuregame.domain.Adventure;
import adventuregame.domain.Area;
import adventuregame.domain.Helper;
import adventuregame.domain.Item;
import adventuregame.domain.Monster;
import adventuregame.domain.Score;
import adventuregame.ui.Language;
import adventuregame.ui.SeikkailuFXMain;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Luokka toimii tietokantataulujen ja käyttöliittymän välissä
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

    /**
     * Metodi luo uuden DataService-olion, jolla on AreaDao, ItemDao, HelperDao,
     * MonsterDao ja ScoreDao
     *
     * @param database - tietokanta parametrina
     * @throws SQLException - jos jotain menee pieleen
     */
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

    /**
     * Metodi tallentaa uuden tuloksen
     *
     * @param s - käyttöliittymä
     * @param a - seikkailu, jota suoritetaan
     */
    public void saveScore(SeikkailuFXMain s, Adventure a) {
        if (!s.getPlayerName().getText().isEmpty()) {
            try {
                scoreDao.saveOrUpdate(new Score(s.getPlayerName().getText(), a.getPoints()));
            } catch (SQLException ex) {
                Logger.getLogger(DaoService.class.getName()).log(Level.SEVERE, null, ex);
            }
            s.getPlayerName().clear();
            updateScore(s);
        }
    }

    /**
     * Metodi päivittää pistetaulua
     *
     * @param s - käyttöliittymä
     */
    private void updateScore(SeikkailuFXMain s) {
        ArrayList<Score> scoreList = new ArrayList<>();
        try {
            scoreList = scoreDao.bestScores();
        } catch (SQLException ex) {
            Logger.getLogger(DaoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Score> data = FXCollections.observableArrayList(scoreList);
        s.getScoreTable().setItems(data);
    }

    /**
     * Metodin avulla näkyy peliin vaikuttavat tietokantataulut
     *
     * @param s - käyttöliittymä
     */
    public void allDaos(SeikkailuFXMain s) throws SQLException {
        if (s.getTable().equals("Area")) {
            s.getCbAll().setItems(FXCollections.observableArrayList(areaDao.findAll()));
        } else if (s.getTable().equals("Item")) {
            s.getCbAll().setItems(FXCollections.observableArrayList(itemDao.findAll()));
        } else if (s.getTable().equals("Helper")) {
            s.getCbAll().setItems(FXCollections.observableArrayList(helperDao.findAll()));
        } else if (s.getTable().equals("Monster")) {
            s.getCbAll().setItems(FXCollections.observableArrayList(monsterDao.findAll()));
        }
    }

    /**
     * Metodi tallentaa oikeaan tietokantatauluun uuden tiedon
     *
     * @param s - käyttöliittymä
     * @param f - kieli-olio
     */
    public void submit(SeikkailuFXMain s, Language f) throws SQLException {
        if (checkSubmit(s, f)) {
            if (s.getTable().equals("Area")) {
                areaDao.saveOrUpdate(new Area(s.getNameTextField().getText(), s.getDescriptionTextField().getText()));
                allDaos(s);
            } else if (s.getTable().equals("Item")) {
                itemDao.saveOrUpdate(new Item(s.getNameTextField().getText(), s.getDescriptionTextField().getText()));
                allDaos(s);
            } else if (s.getTable().equals("Helper")) {
                helperDao.saveOrUpdate(new Helper(s.getNameTextField().getText(), s.getDescriptionTextField().getText()));
                allDaos(s);
            } else if (s.getTable().equals("Monster")) {
                monsterDao.saveOrUpdate(new Monster(s.getNameTextField().getText(), s.getDescriptionTextField().getText()));
                allDaos(s);
            }
            s.getCreateMessageLabel().setText(f.getNewTable());
            s.getNameTextField().clear();
            s.getDescriptionTextField().clear();
        }
    }

    /**
     * Metodi, joka tarkastaa submit-metodia varten onko tekstikentissä tekstiä
     * ja muut valinnat kunnossa
     *
     * @param s - käyttöliittymä
     * @param f - kieli
     * @return false tai true, jos kaikki tarvittava tieto olemassa
     */
    private boolean checkSubmit(SeikkailuFXMain s, Language f) {
        if (s.getTable().isEmpty()) {
            s.getCreateMessageLabel().setText(f.getTableIsEmpty());
            return false;
        } else if (s.getNameTextField().getText().isEmpty() && s.getDescriptionTextField().getText().isEmpty()) {
            s.getCreateMessageLabel().setText(f.getNameAndDescription());
            return false;
        } else if (s.getNameTextField().getText().isEmpty()) {
            s.getCreateMessageLabel().setText(f.getNewName());
            return false;
        } else if (s.getDescriptionTextField().getText().isEmpty()) {
            s.getCreateMessageLabel().setText(f.getDescriptionTextField());
            return false;
        }
        return true;
    }

    /**
     * Metodi poistaa tietokantataulusta tiedon
     *
     * @param s - käyttöliittymä
     * @param f - kieli
     * @throws SQLException
     */
    public void delete(SeikkailuFXMain s, Language f) throws SQLException {
        if (checkDelete(s, f)) {
            if (s.getTable().equals("Area")) {
                Integer key = areaDao.findIdByName(s.getNameTextField().getText());
                areaDao.delete(key);
            } else if (s.getTable().equals("Item")) {
                Integer key = itemDao.findIdByName(s.getNameTextField().getText());
                itemDao.delete(key);
            } else if (s.getTable().equals("Helper")) {
                Integer key = helperDao.findIdByName(s.getNameTextField().getText());
                helperDao.delete(key);
            } else if (s.getTable().equals("Monster")) {
                Integer key = monsterDao.findIdByName(s.getNameTextField().getText());
                monsterDao.delete(key);
            }
            s.getCreateMessageLabel().setText("Taulu poistettu.");
            s.getNameTextField().clear();
            s.getDescriptionTextField().clear();
        }
    }

    /**
     * Metodi, joka tarkastaa delete-metodia varten onko tekstikentässä tekstiä
     * ja muut valinnat kunnossa
     *
     * @param s - käyttöliittymä
     * @param f - kieli
     * @return false tai true, jos kaikki on kunnossa
     */
    private boolean checkDelete(SeikkailuFXMain s, Language f) {
        if (s.getTable().isEmpty()) {
            s.getCreateMessageLabel().setText(f.getTableIsEmpty());
            return false;
        } else if (s.getNameTextField().getText().isEmpty()) {
            s.getCreateMessageLabel().setText(f.getNewName());
            return false;
        }
        return true;
    }
}
