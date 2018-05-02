package adventuregame.domain;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Player-luokka ylläpitää tietoa siitä mitä pelaaja on tehnyt
 *
 * @author strajama
 */
public class Player {

    private Area area;
    private HashMap<String, Item> items;
    private HashMap<String, Helper> helpers;

    /**
     * Metodi luo uuden pelaajan
     */
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

    /**
     * Metodi kuvailee mitä pelaajan repussa on
     *
     * @return kuvauksen repun sisällöstä
     */
    public String bag() {
        if (items.isEmpty()) {
            return "Reppusi on tyhjä.";
        }
        Iterator<String> itemirator = items.keySet().iterator();
        StringBuilder builder = new StringBuilder();
        builder.append("Repussasi on jotain ");
/*        while (itemirator.hasNext()) {
            builder.append(itemirator.next().toUpperCase());
            if (itemirator.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append(".");*/
        return builder.toString();
    }
/**
 * Metodi laittaa parametrina annetun esineen pelaajan reppuun
 * @param item - esine
 */
    public void putInBag(Item item) {
        items.put(item.getName(), item);
    }
    
    public void removeFromBag(Item item) {
        items.remove(item.getName());
    }
/**
 * Metodi kertoo onko pelaaja jo puhunut apurin kanssa
 * @param helper - apuri, jonka kanssa halutaan puhua
 * @return - true jos pelaaja on puhunut apurin kanssa aiemmin, muuten false
 */
    public boolean spokenWith(String helper) {
        return helpers.containsKey(helper);
    }
/**
 * Metodi kirjaa ylös, että pelaaja puhuu apurin kanssa
 * @param helper - apuri, jonka kanssa puhutaan
 */
    public void speakWith(Helper helper) {
        helpers.put(helper.getName(), helper);
    }

}