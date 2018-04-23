package seikkailupeli.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Area {

    private String name;
    private String description;
    private Map<String, Finding> findings;
    private Location location;

    public Area(String name, String description) {
        this.name = name;
        this.description = description;
        this.findings = new HashMap<>();
    }

    public Map<String, Finding> getFindings() {
        return findings;
    }

    public void setFindings(Map<String, Finding> findings) {
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
                if (findings.get(next).isItem() ) {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    

}
