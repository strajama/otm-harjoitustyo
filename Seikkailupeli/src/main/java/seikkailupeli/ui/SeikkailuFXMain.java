package seikkailupeli.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seikkailupeli.dao.AreaDao;
import seikkailupeli.dao.Database;
import seikkailupeli.dao.ItemDao;
import seikkailupeli.domain.Action;
import seikkailupeli.domain.Adventure;
import seikkailupeli.domain.Area;
import seikkailupeli.domain.Direction;
import seikkailupeli.domain.Item;
import seikkailupeli.domain.World;

public class SeikkailuFXMain extends Application {

    private Scene playScene;
    private Scene loginScene;
    private Scene createScene;
    private AreaDao areaDao;
    private ItemDao itemDao;
    private Database database;
    private String table;

    @Override
    public void init() throws Exception {
        this.database = new Database("jdbc:sqlite:seikkailu.db");
        database.init();
        this.areaDao = new AreaDao(database);
        this.itemDao = new ItemDao(database);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //login-scene
        VBox loginPane = new VBox(10);

        Label loginMessage = new Label("Pelaa kirjautumatta randomisti seikkailua");
        Button withoutButton = new Button("Jee, pelaamaan!");
        Label createNew = new Label("Tähän tulee uusien juttujen luominen tietokantaan.");
        Button createNewButton = new Button("Sitten myöhemmin");

        withoutButton.setOnAction(e -> {
            primaryStage.setScene(playScene);
        });
        loginPane.getChildren().addAll(loginMessage, withoutButton, createNew, createNewButton);

        loginScene = new Scene(loginPane, 600, 300);

        createNewButton.setOnAction(e -> {
            primaryStage.setScene(createScene);
        });

        //luo maailma ja seikkailu
        World world = new World(3, 4);
        world.createWorld(areaDao, itemDao);
        Adventure adventure = new Adventure(world);
        adventure.randomItemGoal();
        adventure.setTimeGoal(20);
        BorderPane playBp = new BorderPane();
        //asettelun ylaosaan tulee tietoa mitä pelaaja näkee
        VBox up = new VBox();
        up.setSpacing(10);
        // kuvailussa kerrotaan mitä pelaaja näkee.
        Label area = new Label(world.getPlayer().getArea().getName().toUpperCase());
        Label description = new Label(world.getPlayer().getArea().getDescription());
        Label finding = new Label(world.getPlayer().getArea().show());
        Label bag = new Label(world.getPlayer().bag());
        Label happening = new Label("Tässä kerrotaan pelitilanteesi.");
        Label remainingTime = new Label("Tässä näkyy kuinka monta toimintaa ehdit vielä tehdä. Alussa vuoroja on " + adventure.getTimeGoal() + ".");
        up.getChildren().add(area);
        up.getChildren().add(description);
        up.getChildren().add(finding);
        up.getChildren().add(bag);
        up.getChildren().add(happening);
        up.getChildren().add(remainingTime);
        playBp.setTop(up);

        // tehdään kaksi ruudukkoa, johon sijoitetaan toimintonapit ja sijoitetaan ne alas
        HBox down = new HBox();
        GridPane doGrid = new GridPane();
        GridPane moveGrid = new GridPane();
        doGrid.setPadding(new Insets(10, 10, 10, 10));
        doGrid.setVgap(5);
        doGrid.setHgap(5);
        moveGrid.setPadding(new Insets(10, 10, 10, 10));
        moveGrid.setVgap(5);
        moveGrid.setHgap(5);

        //liikkuminen vasemmalle puolelle ja muut toiminnot oikealle
        down.getChildren().add(moveGrid);
        down.getChildren().add(doGrid);
        playBp.setBottom(down);

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
            remainingTime.setText("Vuoroja on jäljellä " + adventure.getTimeGoal() + ".");
        });
        moveGrid.add(north, 1, 0);
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
            remainingTime.setText("Vuoroja on jäljellä " + adventure.getTimeGoal() + ".");
        });
        moveGrid.add(east, 2, 1);
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
            remainingTime.setText("Vuoroja on jäljellä " + adventure.getTimeGoal() + ".");
        });
        moveGrid.add(west, 0, 1);
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
            remainingTime.setText("Vuoroja on jäljellä " + adventure.getTimeGoal() + ".");
        });
        moveGrid.add(south, 1, 2);

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
            remainingTime.setText("Vuoroja on jäljellä " + adventure.getTimeGoal() + ".");
        });
        doGrid.add(pick, 0, 0);
        //paluu alkuun
        Button returnLogin = new Button("LOPETA PELI");
        doButtons.add(returnLogin);
        returnLogin.setOnAction((event) -> {
            primaryStage.setScene(loginScene);
        });
        doGrid.add(returnLogin, 2, 2);

        playScene = new Scene(playBp, 600, 300);

        GridPane createGrid = new GridPane();
        createGrid.setPadding(new Insets(10, 10, 10, 10));
        createGrid.setVgap(5);
        createGrid.setHgap(5);
        table = "";

        ChoiceBox cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList("Alue", "Esine"));
        GridPane.setConstraints(cb, 0, 0);
        createGrid.getChildren().add(cb);
        String[] daos = new String[]{"Area", "Item"};
        cb.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov,
                        Number old_val, Number new_val) -> {
                    table = daos[new_val.intValue()];
                }
        );

        final TextField name = new TextField();
        name.setPromptText("Anna uusi nimi.");
        GridPane.setConstraints(name, 0, 1);
        createGrid.getChildren().add(name);

        final TextField des = new TextField();
        des.setPromptText("Anna uusi kuvaus.");
        GridPane.setConstraints(des, 0, 2);
        createGrid.getChildren().add(des);
        final Label label = new Label("Tähän tulee viesti");
        GridPane.setConstraints(label, 0, 3);
        GridPane.setColumnSpan(label, 2);
        createGrid.getChildren().add(label);

        Button submit = new Button("Lisää");
        GridPane.setConstraints(submit, 1, 1);
        createGrid.getChildren().add(submit);
        submit.setOnAction((ActionEvent e) -> {
            if ((!name.getText().isEmpty() && !des.getText().isEmpty())) {
                if (table.isEmpty()) {
                    label.setText("Valitse taulu.");
                } else {
                    if (table == "Area") {
                        Area newArea = new Area (name.getText(), des.getText());
                        try {
                            areaDao.saveOrUpdate(newArea);
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (table == "Item") {
                        Item newItem = new Item (name.getText(), des.getText());
                        try {
                            itemDao.saveOrUpdate(newItem);
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                label.setText("Uusi " + table + "-taulu lisätty.");
                name.clear();
                des.clear();}
            } else if (name.getText().isEmpty() && des.getText().isEmpty()) {
                label.setText("Anna nimi ja kuvaus.");
            } else if (des.getText().isEmpty()) {
                label.setText("Anna kuvaus.");
            } else if (name.getText().isEmpty()) {
                label.setText("Anna nimi.");
            }
        });
        Button clear = new Button("Pyyhi");
        GridPane.setConstraints(clear, 1, 2);
        createGrid.getChildren().add(clear);
        clear.setOnAction((ActionEvent e) -> {
            name.clear();
            des.clear();
            label.setText("Tekstikentät tyhjennetty.");
        });

        Button rLogin = new Button("Palaa takaisin");
        GridPane.setConstraints(rLogin, 1, 3);
        createGrid.getChildren().add(rLogin);
        rLogin.setOnAction((event) -> {
            primaryStage.setScene(loginScene);
        });

        /*
        VBox createvb = new VBox();
        createvb.setSpacing(10);
        createvb.getChildren().add(new Label("Lisää uusi"));
        Label labelName = new Label("Tietokantataulun nimi");
        createvb.getChildren().add(labelName);

        ChoiceBox cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList("Alue", "Esine"));
        createvb.getChildren().add(cb);
        String[] daos = new String[] {"Area", "Item"};
        cb.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) -> {
                    labelName.setText(daos[new_val.intValue()]);
                }
        );
        Label daoName = new Label("Uusi nimi");
        createvb.getChildren().add(daoName);
        TextField tfName = new TextField();
        createvb.getChildren().add(tfName);
        Label daoDes = new Label("Uusi kuvaus");
        createvb.getChildren().add(daoDes);
        TextField tfDes = new TextField();
        createvb.getChildren().add(tfDes);*/
        createScene = new Scene(createGrid, 600, 300);

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
