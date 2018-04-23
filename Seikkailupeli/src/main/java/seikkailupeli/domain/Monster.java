package seikkailupeli.domain;

public class Monster {

    private String name;
    private String slogan;
    private int life;
    private Area area;

    public Monster(String name, String description) {
        this.name = name;
        this.slogan = description;
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
