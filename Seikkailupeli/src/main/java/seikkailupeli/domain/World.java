
package seikkailupeli.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World {
    
    private List<Area> areas;
    private Map<String, Area> areamap;
    private List<Item> items;
    private Map<String, Item> itemmap;


    public World() {
        this.areas = new ArrayList<>();
        this.areamap = new HashMap<>();
        this.items = new ArrayList<>();
        this.itemmap = new HashMap<>();       
    }

    public void createWorld() {
        this.createAreas();
        this.createItems();
    }
    
    public void createAreas() {
        /*
        CREATE TABLE Areas (id integer PRIMARY KEY, name varchar(50), description varchar(500), picture varchar(200);
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
    
    public void createItems() {
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
}
