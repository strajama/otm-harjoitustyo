package seikkailupeli.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Area {

    private String name;
    private String description;
    private Map<String, Item> items;
    private Location location;

    public Area(String name, String description) {
        this.name = name;
        this.description = description;
        this.items = new HashMap<>();
    }

    public Map<String, Item> getItems() {
        return items;
    }

    public void setItems(Map<String, Item> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String show() {
        if (!items.isEmpty()) {
            Iterator<String> itemiterator = items.keySet().iterator();
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

    public void removeItem(String item) {
        if (items.containsKey(item)) {
            items.remove(item);
        }
    }

    public void putItem(Item item) {
        items.put(item.getName(), item);
    }

    public Item giveSomeItem() {
        if (!items.isEmpty()) {
            Item item = items.values().toArray(new Item[items.size()])[0];
            items.remove(item.getName());
            return item;
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
