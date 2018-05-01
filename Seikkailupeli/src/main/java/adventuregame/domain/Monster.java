package seikkailupeli.domain;
/**
 * Monster-luokka ylläpitää hirviön tietoja
 * @author strajama
 */
public class Monster {

    private String name;
    private String slogan;
    private int life;
    private Area area;
/**
 * Luo hirviön
 * @param name - hirviön nimi
 * @param slogan - hirviön slogan, jonka sanoo nähdessään pelaajan
 */
    public Monster(String name, String slogan) {
        this.name = name;
        this.slogan = slogan;
        this.life = 5;
    }

    public String getName() {
        return name;
    }

    public String getSlogan() {
        return slogan;
    }

    public int getLife() {
        return life;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return name.toUpperCase() + " huutaa " + slogan.toUpperCase() + "!";
    }

}
