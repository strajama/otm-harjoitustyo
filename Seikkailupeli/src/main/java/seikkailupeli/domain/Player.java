package seikkailupeli.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Player {

    private Area area;
    private HashMap<String, Item> items;
    private HashMap<String, Helper> helpers;

    public Player() {
        this.items = new HashMap<>();
        this.helpers = new HashMap<>();
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public HashMap<String, Helper> getHelpers() {
        return helpers;
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

    public void speakWith(Helper helper) {
        helpers.put(helper.getName(), helper);
    }

    public boolean spokenWith(String helper) {
        return helpers.containsKey(helper);
    }
}
