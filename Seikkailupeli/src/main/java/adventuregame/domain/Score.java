package adventuregame.domain;

/**
 * Score-luokan avulla tallennetaan pistetietoja muistiin
 *
 * @author strajama
 */
public class Score {

    private String name;
    private int points;

    /**
     * Metodi luo uuden Score-olion
     *
     * @param name - nimi
     * @param points - kerätyt pisteet
     */
    public Score(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

}
