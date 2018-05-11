package adventuregame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import adventuregame.dao.AreaDao;
import adventuregame.dao.DaoService;
import adventuregame.dao.HelperDao;
import adventuregame.dao.ItemDao;
import adventuregame.dao.MonsterDao;

/**
 * World-luokka ylläpitää tietoa mitä on missäkin
 *
 * @author strajama
 */
public class World {

    private ArrayList<Area> areas;
    private ArrayList<Item> items;
    private ArrayList<Helper> helpers;
    private ArrayList<Monster> monsters;
    private Monster monster;
    private Random random;
    private Area home;

    /**
     * Metodi luo uuden maailman ja sijoittaa alueet, esineet, apurit ja hirviön
     * ja sijoittaa kaiken paikoilleen
     *
     * @param daoService - tietokannoissa olevia tietoja käsittelevä olio
     * @throws Exception - tietokannan käyttämisestä voi tulla virheilmoitus
     */
    public World(DaoService daoService) throws Exception {
        this.random = new Random();
        this.items = new ArrayList();
        this.areas = new ArrayList();
        this.helpers = new ArrayList();
        this.monsters = new ArrayList();
        this.home = new Area("koti", "Oma koti kullan kallis");
        this.createAreas(daoService.getAreaDao());
        this.createItems(daoService.getItemDao());
        this.createHelpers(daoService.getHelperDao());
        this.createMonsters(daoService.getMonsterDao());
    }

    public ArrayList<Area> getAreas() {
        Collections.shuffle(areas);
        return areas;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Helper> getHelpers() {
        return helpers;
    }

    public Monster getMonster() {
        return monster;
    }

    public Area getHome() {
        return home;
    }

    /**
     * Metodi hakee tietokannassa olevat alueet ja arpoo niille naapurit valmiin
     * pohjan mukaisesti
     *
     * @param areaDao - tietokannassa olevat alueet
     * @throws Exception
     */
    private void createAreas(AreaDao areaDao) throws Exception {
        this.areas = areaDao.findAll();
        Collections.shuffle(areas);
        home.putNeighbors(areas.get(0), areas.get(1), areas.get(2), null);
        areas.get(0).putNeighbors(areas.get(3), areas.get(4), areas.get(5), home);
        areas.get(1).putNeighbors(areas.get(4), null, home, null);
        areas.get(2).putNeighbors(areas.get(5), home, areas.get(6), null);
        areas.get(3).putNeighbors(areas.get(7), areas.get(8), areas.get(9), areas.get(0));
        areas.get(4).putNeighbors(areas.get(8), null, areas.get(0), areas.get(1));
        areas.get(5).putNeighbors(areas.get(9), areas.get(0), areas.get(10), areas.get(2));
        areas.get(6).putNeighbors(areas.get(10), areas.get(2), null, null);
        areas.get(7).putNeighbors(null, null, areas.get(11), areas.get(3));
        areas.get(8).putNeighbors(null, null, areas.get(3), areas.get(4));
        areas.get(9).putNeighbors(areas.get(11), areas.get(3), areas.get(12), areas.get(5));
        areas.get(10).putNeighbors(areas.get(12), areas.get(5), null, areas.get(6));
        areas.get(11).putNeighbors(null, areas.get(7), null, areas.get(9));
        areas.get(12).putNeighbors(null, areas.get(9), null, areas.get(10));
    }

    /**
     * Metodi hakee tietokannassa olevat esineet ja arpoo niille sijainnin
     * alueella, jossa ei vielä ole muuta Finding-oliota ja joka ei ole koti
     *
     * @param itemDao - tietokannassa olevat esineet
     * @throws Exception
     */
    private void createItems(ItemDao itemDao) throws Exception {
        this.items = itemDao.findAll();
        Collections.shuffle(items);
        for (int i = 0; i < Math.min(items.size(), areas.size() / 2); i++) {
            Area place = findRandomPlace();
            while (!place.getFindings().isEmpty()) {
                place = findRandomPlace();
            }
            place.putFinding(items.get(i));
        }
    }

    /**
     * Metodi hakee tietokannassa olevat apurit ja arpoo niille sijainnin
     * alueella, jossa ei vielä ole muuta Finding-oliota ja joka ei ole koti
     * sekä antaa apurille tavaran, jota hän toivoo
     *
     * @param helperDao - tietokannassa olevat apurit
     * @throws Exception
     */
    private void createHelpers(HelperDao helperDao) throws Exception {
        this.helpers = helperDao.findAll();
        Collections.shuffle(helpers);
        for (int i = 0; i < Math.min(helpers.size(), areas.size() / 2); i++) {
            Area place = findRandomPlace();
            while (!place.getFindings().isEmpty()) {
                place = findRandomPlace();
            }
            place.putFinding(helpers.get(i));
            helpers.get(i).setItem(items.get(i));
        }
    }

    /**
     * Metodi hakee tietokannassa olevat hirviöt, arpoo niistä yhden ja
     * sijoittaa sen arvotulle alueelle, joka ei ole koti
     *
     * @param monsterDao - tietokannassa olevat hirviöt
     * @throws Exception
     */
    private void createMonsters(MonsterDao monsterDao) throws Exception {
        this.monsters = monsterDao.findAll();
        Collections.shuffle(monsters);
        this.monster = monsters.get(0);
        Area area = findRandomPlace();
        monster.setArea(area);
    }

    /**
     * Metodi palauttaa listalta arvotun alueen
     *
     * @return - Area
     */
    private Area findRandomPlace() {
        int r = random.nextInt(areas.size());

        return areas.get(r);
    }

}
