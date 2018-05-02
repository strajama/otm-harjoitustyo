/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventuregame.domain;

/**
 *
 * @author strajama
 */
public class Score {

    private String name;
    private int points;

    public Score(String name, int score) {
        this.name = name;
        this.points = score;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

}
