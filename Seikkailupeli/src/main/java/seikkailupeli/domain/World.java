package seikkailupeli.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class World {

    private List<Area> areas;
    private List<Item> items;
    private Area[][] grid;
    private int size;
    private Player player;
    private Random ram;

    public World(int i, int j) {
        this.areas = new ArrayList<>();
        this.items = new ArrayList<>();
        this.grid = new Area[i][j];
        this.size = i * j;
        this.ram = new Random();
    }

    public void createWorld() {
        this.createAreas();
        this.createItems();
        this.createGrid();
        this.setItems();
        this.createPlayer();
    }

    private void createAreas() {
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
        /*
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
         */
    }

    private void createGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int r = ram.nextInt(areas.size());
                grid[i][j] = areas.get(r);
                Location location = new Location(i, j);
                areas.get(r).setLocation(location);
            }
        }
    }

    private void createItems() {
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
        /*
        CREATE TABLE Items (id integer PRIMARY KEY, name varchar(50), description varchar (500), picture varchar(200));
        INSERT INTO Items (name, description) VALUES ('palantiri', 'kauaksi näkevä kivi');
        INSERT INTO Items (name, description) VALUES ('google', 'hakukone, jolla voi löytää maailman');
        INSERT INTO Items (name, description) VALUES ('tv', 'loputon uusien ideoiden lähde.", rikospaikka');
        INSERT INTO Items (name, description) VALUES ('kommunikaattori', 'kone, jolla saa yhteyden ystäviin');
        INSERT INTO Items (name, description) VALUES ('kartta', 'esine, jota käytetään, kun ei voi kysyä tietä');
        INSERT INTO Items (name, description) VALUES ('lokikirja', 'tapahtumien tallennuspaikka');
         */
    }

    private void setItems() {

        if (!items.isEmpty()) {
            for (int i = 0; i < items.size(); i++) {
                Area place = findRandomPlace();
                place.putItem(items.get(i));
                items.get(i).setArea(place);
            }
        }
    }

    private Area findRandomPlace() {
        int r = ram.nextInt(size);

        Area place = grid[r / grid[0].length][r % grid.length];

        return place;
    }

    public void createPlayer() {

        this.player = new Player();
        Area place = findRandomPlace();
        player.setArea(place);
    }

    public Area[][] getGrid() {
        return grid;
    }

    public Player getPlayer() {
        return player;
    }

    public Area findArea(Location location) {
        return grid[location.getI()][location.getJ()];
    }

    public int getSize() {
        return size;
    }

}
