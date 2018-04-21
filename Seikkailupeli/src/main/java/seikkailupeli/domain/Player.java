package seikkailupeli.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Player {

    private Area area;
    private Map<String, Item> items;

    public Player() {
        this.items = new HashMap<>();
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Map<String, Item> getItems() {
        return items;
    }

    public void setItems(Map<String, Item> items) {
        this.items = items;
    }

    public String bag() {
        if (items.isEmpty()) {
            return "Reppusi on tyhjä.";
        }
        Iterator<String> itemirator = items.keySet().iterator();
        StringBuilder builder = new StringBuilder();
        builder.append("Repussa on ");
        while (itemirator.hasNext()) {
            builder.append(itemirator.next().toUpperCase());
            if (itemirator.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append(".");
        return builder.toString();
    }

    public void putInBag(Item item) {
        items.put(item.getName(), item);
    }
}
