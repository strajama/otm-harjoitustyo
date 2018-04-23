package seikkailupeli.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Area {

    private String name;
    private String description;
    private HashMap<String, Finding> findings;
    private HashMap<Direction, Area> neighbors;

    public Area(String name, String description) {
        this.name = name;
        this.description = description;
        this.findings = new HashMap<>();
        this.neighbors = new HashMap<>();
        neighbors.put(Direction.NORTH, null);
        neighbors.put(Direction.EAST, null);
        neighbors.put(Direction.WEST, null);
        neighbors.put(Direction.SOUTH, null);;
    }

    public Map<String, Finding> getFindings() {
        return findings;
    }

    public void setFindings(HashMap<String, Finding> findings) {
        this.findings = findings;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

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

    public void putFinding(Finding finding) {
        findings.put(finding.getName(), finding);
    }

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

    public Helper speakHelper(Player player) {
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

    @Override
    public String toString() {
        return name;
    }

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

    public HashMap<Direction, Area> getNeighbors() {
        return neighbors;
    }

    public Area randomNeighbor() {
        ArrayList<Direction> d = new ArrayList();
        d.add(Direction.WEST);
        d.add(Direction.EAST);
        d.add(Direction.NORTH);
        d.add(Direction.SOUTH);
        Collections.shuffle(d);
        for (Direction a : d) {
            if (neighbors.get(a) != null) {
                return neighbors.get(a);
            }
        }
        return null;
    }
}
