package adventuregame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import adventuregame.dao.AreaDao;
import adventuregame.dao.HelperDao;
import adventuregame.dao.ItemDao;
import adventuregame.dao.MonsterDao;
import java.sql.SQLException;

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
    private Player player;
    private Random random;
    private Area home;

    /**
     * Metodi luo uuden maailman, sille pelaajan ja sijoittaa alueet, esineet,
     * apurit ja hirviön ja sijoittaa kaiken paikoilleen
     *
     * @param areaDao - tietokannassa olevat alueet
     * @param itemDao - tietokannassa olevat esineet
     * @param helperDao - tietokannassa olevat apurit
     * @param monsterdao - tietokannassa olevat hirviöt
     * @throws Exception - tietokannan käyttämisestä voi tulla virheilmoitus
     */
    public World(AreaDao areaDao, ItemDao itemDao, HelperDao helperDao, MonsterDao monsterdao) throws Exception {
        this.random = new Random();
        this.items = new ArrayList();
        this.areas = new ArrayList();
        this.helpers = new ArrayList();
        this.monsters = new ArrayList();
        this.home = new Area("koti", "Oma koti kullan kallis");
        this.createAreas(areaDao);
        this.createItems(itemDao);
        this.createHelpers(helperDao);
        this.createMonsters(monsterdao);
        this.createPlayer();
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

    public Player getPlayer() {
        return player;
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
        area.putMonster(monster);
        monster.setArea(area);
    }

    /**
     * Metodi luo uuden pelaajan ja sijoittaa hänen alueekseen kodin.
     */
    private void createPlayer() {
        this.player = new Player();
        player.setArea(home);
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
/*
        Area suo = new Area("suo", "Tunnet suopursun voimakkaan tuoksun sieraimissasi. Sinua yskittää.");
        Area metsa = new Area("metsä", "Seisot tiheäkasvuisessa paikassa, jossa et näe metsää puilta");
        Area aukio = new Area("aukio", "Olet pienellä aukiolla. Melkein näkymättömät, pienet polut vievät eri suuntiin.");
        Area lehto = new Area("lehto", "Auringonsäteet valaisevat lehtien läpi lehdossa ja saa sinulle lämpimän olon.");
        Area niitty = new Area("niitty", "Kukkaniityllä kasvaa orvokki, lehdokki, vuokko ja moni muu.");
        Area pusikko = new Area("pusikko", "Olet pusikossa. On vaikea nähdä minne mennä.");
        Area puro = new Area("puro", "Edessäsi on kylmävetinen puro, jonka vesi juoksee iloisesti pulisten.");
        Area lahde = new Area("lähde", "Näet kirkasvetisen lähteen. Kumarrut juomaan siitä huikan.");
        Area kumpu = new Area("kumpu", "Seisot kummulla, joka muistuttaa hautakeroja. Saat kylmiä väreitä");
        Area vuori = new Area("vuori", "Vuorelta on komeat näkymät yli koko maan.");
        Area laakso = new Area("laakso", "Laakso on kaunis ja laakea. Täällä sinun on hyvä olla.");
        Area luola = new Area("luola", "Tulet pimeään luolaan, jota ei voi ylittää eikä alittaa.");
        Area kallio = new Area("kallio", "Olet kalliolla kukkalalla ja sinun tekisi mieli rakentaa maja.");
        this.areas.add(suo);
        this.areas.add(metsa);
        this.areas.add(aukio);
        this.areas.add(lehto);
        this.areas.add(niitty);
        this.areas.add(pusikko);
        this.areas.add(puro);
        this.areas.add(lahde);
        this.areas.add(kumpu);
        this.areas.add(vuori);
        this.areas.add(laakso);
        this.areas.add(luola);
        this.areas.add(kallio);
        
        CREATE TABLE Areas (id integer PRIMARY KEY, name varchar(50), description varchar(500), picture varchar(200));
        INSERT INTO Areas (name, description) VALUES ('suo', 'Tunnet suopursun voimakkaan tuoksun sieraimissasi. Sinua yskittää.');
        INSERT INTO Areas (name, description) VALUES ('metsä', 'Seisot tiheäkasvuisessa paikassa, jossa et näe metsää puilta.');
        INSERT INTO Areas (name, description) VALUES ('aukio', 'Olet pienellä aukiolla. Melkein näkymättömät, pienet polut vievät eri suuntiin.');
        INSERT INTO Areas (name, description) VALUES ('lehto', 'Auringonsäteet valaisevat lehtien läpi lehdossa ja saa sinulle lämpimän olon.');
        INSERT INTO Areas (name, description) VALUES ('niitty', 'Kukkaniityllä kasvaa orvokki, lehdokki, vuokko ja moni muu.');
        INSERT INTO Areas (name, description) VALUES ('pusikko', 'Olet pusikossa. On vaikea nähdä minne mennä.');
        INSERT INTO Areas (name, description) VALUES ('puro', 'Edessäsi on kylmävetinen puro, jonka vesi juoksee iloisesti pulisten.');
        INSERT INTO Areas (name, description) VALUES ('lähde', 'Näet kirkasvetisen lähteen. Kumarrut juomaan siitä huikan.');
        INSERT INTO Areas (name, description) VALUES ('kumpu', 'Seisot kummulla, joka muistuttaa hautakeroja. Saat kylmiä väreitä.');
        INSERT INTO Areas (name, description) VALUES ('vuori', 'Vuorelta on komeat näkymät yli koko maan.');
        INSERT INTO Areas (name, description) VALUES ('laakso', 'Laakso on kaunis ja laakea. Täällä sinun on hyvä olla.');
        INSERT INTO Areas (name, description) VALUES ('luola', 'Tulet pimeään luolaan, jota ei voi ylittää eikä alittaa.');
        INSERT INTO Areas (name, description) VALUES ('kallio', 'Olet kalliolla kukkalalla ja sinun tekisi mieli rakentaa maja.');
         
        //luodaan esineet
        Item palantiri = new Item("palantiri", "kauaksi näkevä kivi.");
        Item google = new Item("google", "hakukone, jolla voi löytää maailman.");
        Item tv = new Item("tv", "loputon uusien ideoiden lähde.");
        Item kommunikaattori = new Item("kommunikaattori", "kone, jolla saa yhteyden ystäviin.");
        Item kartta = new Item("kartta", "esine, jota käytetään, kun ei voi kysyä tietä.");
        Item lokikirja = new Item("lokikirja", "tapahtumien tallennuspaikka.");
        this.items.add(palantiri);
        this.items.add(google);
        this.items.add(tv);
        this.items.add(kommunikaattori);
        this.items.add(kartta);
        this.items.add(lokikirja);
        
        CREATE TABLE Items (id integer PRIMARY KEY, name varchar(50), description varchar (500), picture varchar(200));
        INSERT INTO Items (name, description) VALUES ('palantiri', 'kauaksi näkevä kivi');
        INSERT INTO Items (name, description) VALUES ('google', 'hakukone, jolla voi löytää maailman');
        INSERT INTO Items (name, description) VALUES ('tv', 'loputon uusien ideoiden lähde.", rikospaikka');
        INSERT INTO Items (name, description) VALUES ('kommunikaattori', 'kone, jolla saa yhteyden ystäviin');
        INSERT INTO Items (name, description) VALUES ('kartta', 'esine, jota käytetään, kun ei voi kysyä tietä');
        INSERT INTO Items (name, description) VALUES ('lokikirja', 'tapahtumien tallennuspaikka');
         
 */
