package adventuregame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Luokka Area ylläpitää tietoa siitä mitä eri alueilla on
 *
 * @author strajama
 */
public class Area {

    private String name;
    private String description;
    private HashMap<String, Finding> findings;
    private HashMap<Direction, Area> neighbors;
    private ArrayList<Direction> directions;
    private Monster monster;

    /**
     * Metodi luo uuden Area-olion
     *
     * @param name - nimi
     * @param description - kuvailu
     */
    public Area(String name, String description) {
        this.name = name;
        this.description = description;
        this.findings = new HashMap<>();
        this.neighbors = new HashMap<>();
        this.monster = null;
        directions = new ArrayList();
        directions.add(Direction.WEST);
        directions.add(Direction.EAST);
        directions.add(Direction.NORTH);
        directions.add(Direction.SOUTH);
        for (Direction a : directions) {
            neighbors.put(a, null);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<String, Finding> getFindings() {
        return findings;
    }

    public HashMap<Direction, Area> getNeighbors() {
        return neighbors;
    }

    public Monster getMonster() {
        return monster;
    }

    /**
     * Kertoo käyttäjälle mitä hän alueella näkee
     *
     * @return - alueen sisällön kuvailu
     */
    public String show() {
        if (!findings.isEmpty()) {
            Iterator<String> itemiterator = findings.keySet().iterator();
            StringBuilder builder = new StringBuilder();
            builder.append("Näet jotain mielenkiintoista: ");
            while (itemiterator.hasNext()) {
                builder.append(itemiterator.next().toUpperCase());
                if (itemiterator.hasNext()) {
                    builder.append(", ");
                }
            }
            builder.append(".");
            return builder.toString();
        }
        return "Täällä ei ole mitään mielenkiintoista.";
    }

    /**
     * Metodi kertoo käyttäjälle onko alueella hirviötä
     *
     * @return - kuvailu hirviöistä
     */
    public String showMonster() {
        if (monster == null) {
            return "Täällä ei ole hirviöitä.";
        }
        return "Edessäsi on hirvittävä " + monster.getName().toUpperCase() + ". Se sanoo: '" + monster.getSlogan() + "'.";
    }

    /**
     * Metodi laittaa alueelle hirviön, jonka saa
     *
     * @param monster - parametrina annetaan hirviö
     */
    public void putMonster(Monster monster) {
        this.monster = monster;
    }

    /**
     * Metodi poistaa alueella olevan hirviön
     */
    public void removeMonster() {
        this.monster = null;
    }

    /**
     * Metodi laittaa alueelle abstraktin luokan Finding toteuttavan olion
     *
     * @param finding - parametrina saatava, alueelle laitettava olio
     */
    public void putFinding(Finding finding) {
        findings.put(finding.getName(), finding);
    }

    /**
     * Metodi palauttaa Item-olion, jos sellainen on alueella ja ottaa sen
     * alueelta pois
     *
     * @return alueella oleva esine tai null
     */
    public Item giveSomeItem() {
        if (!findings.isEmpty()) {
            Iterator<String> it = findings.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (findings.get(next).isItem()) {
                    Finding item = findings.get(next);
                    findings.remove(next);
                    return (Item) item;
                }
            }
        }
        return null;
    }

    /**
     * Metodi palauttaa Helper-olion, jos sellainen on alueella ja pelaaja ei
     * ole puhunut hänen kanssaan.
     *
     * @param pelaaja, joka apurin kanssa haluaa puhua
     * @return alueella oleva apuri tai null
     */
    public Helper speakWithNewHelper(Player player) {
        if (!findings.isEmpty()) {
            Iterator<String> it = findings.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (!findings.get(next).isItem() && !player.spokenWith(next)) {
                    return (Helper) findings.get(next);
                }
            }
        }
        return null;
    }

    public Helper findHelper() {
        if (!findings.isEmpty()) {
            Iterator<Finding> it = findings.values().iterator();
            while (it.hasNext()) {
                Finding next = it.next();
                if (!next.isItem()) {
                    return (Helper) next;
                }
            }
        }
        return null;
    }

    public void removeHelper(Helper helper) {
        if (!findings.isEmpty()) {
            if (findings.containsKey(helper.getName())) {
                findings.remove(helper.getName());
            }
        }
    }

    /**
     * Metodi sijoittaa alueen naapuri-mappiin parametrina annetut alueet
     * naapureiksi
     *
     * @param n pohjoinen naapuri tai null
     * @param e itäinen naapuri tai null
     * @param w läntinen naapuri tai null
     * @param s eteläinen naapuri tai null
     */
    public void putNeighbors(Area n, Area e, Area w, Area s) {
        if (n != null) {
            neighbors.put(Direction.NORTH, n);
        }
        if (e != null) {
            neighbors.put(Direction.EAST, e);
        }
        if (w != null) {
            neighbors.put(Direction.WEST, w);
        }
        if (s != null) {
            neighbors.put(Direction.SOUTH, s);
        }
    }

    /**
     * Metodi palauttaa alueen satunnaisen naapurin
     *
     * @return naapurissa oleva Area tai null
     */
    public Area randomNeighbor() {
        Collections.shuffle(directions);
        for (Direction a : directions) {
            if (neighbors.get(a) != null) {
                return neighbors.get(a);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Area other = (Area) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}
