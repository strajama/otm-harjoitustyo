package adventuregame.domain;

/**
 * Monster-luokka ylläpitää hirviön tietoja
 *
 * @author strajama
 */
public class Monster {

    private String name;
    private String slogan;
    private int life;
    private Area area;

    /**
     * Metodi luo uuden Monster-olion ja asettaa tämän lifeksi arvon 5
     *
     * @param name - hirviön nimi
     * @param slogan - hirviön slogan, jonka se sanoo nähdessään pelaajan
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

    /**
     * Metodi vähentää hirviön elämäpisteitä
     *
     * @param hit - pistemäärä, joka vähennetään
     * @return boolean - tieto siitä tappoiko isku hirviön
     */
    public boolean hitMonster(int hit) {
        life = life - hit;
        return isDead();
    }

    /**
     * Metodi kertoo onko hirviö kuollut
     *
     * @return boolean - tieto siitä onko hirviö elossa (false) vai kuollut
     * (true)
     */
    public boolean isDead() {
        return life <= 0;
    }
    
    

}
