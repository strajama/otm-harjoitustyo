/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seikkailupeli.ui;

import java.awt.Color;
import java.awt.Insets;
import seikkailupeli.domain.Adventure;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seikkailupeli.domain.Action;
import seikkailupeli.domain.Direction;
import seikkailupeli.domain.Item;
import seikkailupeli.domain.World;

/**
 *
 * @author strajama
 */
public class SeikkailuFXMain extends Application {

    private Scene playScene;
    private Scene loginScene;

    @Override
    public void start(Stage primaryStage) {

        //login-scene
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        Label withoutLabel = new Label("Tadaa!");

        inputPane.getChildren().addAll(withoutLabel);
        Label loginMessage = new Label();

        Button withoutButton = new Button("Pelaa kirjautumatta yhtä seikkailua");
        withoutButton.setOnAction(e -> {
            primaryStage.setScene(playScene);
        });
        loginPane.getChildren().addAll(loginMessage, inputPane, withoutButton);

        loginScene = new Scene(loginPane, 300, 250);

        //luo maailma ja seikkailu
        World world = new World(3, 4);
        world.createWorld();
        Adventure adventure = new Adventure(world);
        Item goalitem = new Item("palantiri", "kauaksi näkevä kivi.");
        adventure.itemGoal(goalitem);
        adventure.timeGoal(10);

        BorderPane bp = new BorderPane();
        //asettelun ylaosaan tulee tietoa mitä pelaaja näkee
        VBox up = new VBox();
        // kuvailussa kerrotaan mitä pelaaja näkee. Aloituspaikka on metsä
        Label area = new Label(world.getPlayer().getArea().getName().toUpperCase());
        Label description = new Label(world.getPlayer().getArea().getDescription());
        Label finding = new Label(world.getPlayer().getArea().show());
        Label bag = new Label(world.getPlayer().bag());
        Label happening = new Label("Tässä kerrotaan mitä on juuri tapahtunut");
        up.getChildren().add(area);
        up.getChildren().add(description);
        up.getChildren().add(finding);
        up.getChildren().add(bag);
        up.getChildren().add(happening);
        bp.setTop(up);

        // tehdään kaksi ruudukkoa, johon sijoitetaan toimintonapit ja sijoitetaan ne alas
        HBox down = new HBox();
        GridPane doStuff = new GridPane();
        GridPane move = new GridPane();

        //liikkuminen vasemmalle puolelle ja muut toiminnot oikealle
        down.getChildren().add(move);
        down.getChildren().add(doStuff);
        bp.setBottom(down);

        //tehdään lista, johon liikkumis-napit laitetaan helpottamaan niiden käyttöä
        List<Button> moveButtons = new ArrayList<>();
        //napit liikkumiseen
        Button north = new Button("POHJOINEN");
        moveButtons.add(north);
        north.setOnAction((event) -> {
            new Action().move(world, world.getPlayer(), Direction.NORTH);
            area.setText(world.getPlayer().getArea().getName().toUpperCase());
            description.setText(world.getPlayer().getArea().getDescription());
            finding.setText(world.getPlayer().getArea().show());
            adventure.takeTurn();
            if (adventure.getTimeGoal() < 0) {
                happening.setText("Aika loppui, sinä hävisit!");
            }
        });
        move.add(north, 1, 0);
        Button east = new Button("ITÄ");
        moveButtons.add(east);
        east.setOnAction((event) -> {
            new Action().move(world, world.getPlayer(), Direction.EAST);
            area.setText(world.getPlayer().getArea().getName().toUpperCase());
            description.setText(world.getPlayer().getArea().getDescription());
            finding.setText(world.getPlayer().getArea().show());
            adventure.takeTurn();
            if (adventure.getTimeGoal() < 0) {
                happening.setText("Aika loppui, sinä hävisit!");
            }
        });
        move.add(east, 2, 1);
        Button west = new Button("LÄNSI");
        moveButtons.add(west);
        west.setOnAction((event) -> {
            new Action().move(world, world.getPlayer(), Direction.WEST);
            area.setText(world.getPlayer().getArea().getName().toUpperCase());
            description.setText(world.getPlayer().getArea().getDescription());
            finding.setText(world.getPlayer().getArea().show());
            adventure.takeTurn();
            if (adventure.getTimeGoal() < 0) {
                happening.setText("Aika loppui, sinä hävisit!");
            }
        });
        move.add(west, 0, 1);
        Button south = new Button("ETELÄ");
        moveButtons.add(south);
        south.setOnAction((event) -> {
            new Action().move(world, world.getPlayer(), Direction.SOUTH);
            area.setText(world.getPlayer().getArea().getName().toUpperCase());
            description.setText(world.getPlayer().getArea().getDescription());
            finding.setText(world.getPlayer().getArea().show());
            adventure.takeTurn();
            if (adventure.getTimeGoal() < 0) {
                happening.setText("Aika loppui, sinä hävisit!");
            }
        });
        move.add(south, 1, 2);

        // muita toimintoja
        List<Button> doButtons = new ArrayList<>();
        Button pick = new Button("POIMI");
        doButtons.add(pick);
        pick.setOnAction((event) -> {
            Action a = new Action();
            a.take(world, world.getPlayer());
            finding.setText(world.getPlayer().getArea().show());
            bag.setText(world.getPlayer().bag());
            adventure.takeTurn();
            if (world.getPlayer().getItems().containsValue(adventure.getItemGoal())) {
                happening.setText("Sinä löysit etsimäsi!");
            } else if (adventure.getTimeGoal() < 0) {
                happening.setText("Aika loppui, sinä hävisit!");
            }

        });
        doStuff.add(pick, 0, 0);

        playScene = new Scene(bp, 600, 300);
        primaryStage.setTitle("Seikkailu");
        primaryStage.setScene(loginScene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
