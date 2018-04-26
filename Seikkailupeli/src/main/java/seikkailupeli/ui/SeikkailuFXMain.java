package seikkailupeli.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seikkailupeli.dao.AreaDao;
import seikkailupeli.dao.Database;
import seikkailupeli.dao.HelperDao;
import seikkailupeli.dao.ItemDao;
import seikkailupeli.dao.MonsterDao;
import seikkailupeli.domain.Action;
import seikkailupeli.domain.Adventure;
import seikkailupeli.domain.Area;
import seikkailupeli.domain.Direction;
import seikkailupeli.domain.Finding;
import seikkailupeli.domain.Helper;
import seikkailupeli.domain.Item;
import seikkailupeli.domain.Monster;
import seikkailupeli.domain.World;

public class SeikkailuFXMain extends Application {

    private Scene playScene;
    private Scene loginScene;
    private Scene createScene;

    private AreaDao areaDao;
    private ItemDao itemDao;
    private HelperDao helperDao;
    private MonsterDao monsterDao;
    private Database database;
    private String table;
    private ArrayList allList;
    private Monster monster;
    private World world;
    private Area area;
    private Adventure adventure;

    private GridPane doGrid;
    private GridPane moveGrid;

//playscenen muutettavat Labelit
    private Label areaLabel;
    private Label descriptionLabel;
    private Label findingLabel;
    private Label bagLabel;
    private Label gameLabel;
    private Label doingLabel;
    private Label monsterLabel;
    private Label remainingTimeLabel;

    ChoiceBox cb;
    ChoiceBox cbAll;

    @Override
    public void init() throws Exception {
        this.database = new Database("jdbc:sqlite:adventure.db");
        database.init();
        this.areaDao = new AreaDao(database);
        this.itemDao = new ItemDao(database);
        this.helperDao = new HelperDao(database);
        this.monsterDao = new MonsterDao(database);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        world = new World(areaDao, itemDao, helperDao, monsterDao);
        Monster monster = world.getMonsters().get(0);
        //login-scene
        VBox loginPane = new VBox(10);
        loginPane.setPadding(new Insets(10, 10, 10, 10));
        loginPane.setAlignment(Pos.CENTER);
        Label loginLabel = new Label("Pelaa kirjautumatta randomisti seikkailua");
        Button playButton = new Button("Jee, pelaamaan!");
        playButton.setOnAction(e -> {
            adventure = new Adventure(world, monster);
            adventure.makeAGame(200);
            primaryStage.setScene(playScene);
        });
        Label createNewLabel = new Label("Tähän tulee uusien juttujen luominen tietokantaan.");
        Button createNewButton = new Button("Sitä tekemään");
        createNewButton.setOnAction(e -> {
            primaryStage.setScene(createScene);
        });

        loginPane.getChildren().addAll(loginLabel, playButton, createNewLabel, createNewButton);
        loginScene = new Scene(loginPane, 600, 400);

        BorderPane playBp = new BorderPane();
        playBp.setPadding(new Insets(10, 10, 10, 10));
        playBp.setTop(playUp());
        playBp.setBottom(playDown());
        
        createMoveButtons();
        createDoButtons();

        //paluu alkuun*/
        Button returnLogin = new Button("PALAA VALIKKOON");
        returnLogin.setOnAction((event) -> {
            primaryStage.setScene(loginScene);
        });
        doGrid.add(returnLogin, 2, 2);

        playScene = new Scene(playBp, 600, 400);

        //tietokantataulujen lisääminen
        GridPane createGrid = new GridPane();
        createGrid.setPadding(new Insets(10, 10, 10, 10));
        createGrid.setVgap(5);
        createGrid.setHgap(5);
        table = "";
        allList = new ArrayList();

        cbAll = new ChoiceBox();
        cbAll.setItems(FXCollections.observableArrayList(allList));
        GridPane.setConstraints(cbAll, 0, 4);
        createGrid.getChildren().add(cbAll);

        cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList("Alue", "Esine", "Apuri", "Hirviö"));
        GridPane.setConstraints(cb, 0, 0);
        createGrid.getChildren().add(cb);
        findDaos();

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

        cbAll.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov,
                        Number old_val, Number new_val) -> {
                }
        );

        Button submit = new Button("Lisää");
        GridPane.setConstraints(submit, 1, 1);
        createGrid.getChildren().add(submit);
        submit.setOnAction((ActionEvent e) -> {
            if ((!name.getText().isEmpty() && !des.getText().isEmpty())) {
                if (table.isEmpty()) {
                    label.setText("Valitse taulu.");
                } else {
                    /*                    if (table == "Area") {
                        Area newArea = new Area(name.getText(), des.getText());
                        try {
                            areaDao.saveOrUpdate(newArea);
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else*/ if ("Item".equals(table)) {
                        Item newItem = new Item(name.getText(), des.getText());
                        try {
                            itemDao.saveOrUpdate(newItem);
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if ("Helper".equals(table)) {
                        Helper newHelper = new Helper(name.getText(), des.getText());
                        try {
                            helperDao.saveOrUpdate(newHelper);
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if ("Monster".equals(table)) {
                        Monster newMonster = new Monster(name.getText(), des.getText());
                        try {
                            monsterDao.saveOrUpdate(newMonster);
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    label.setText("Uusi " + table + "-taulu lisätty. Paitsi Area.");
                    name.clear();
                    des.clear();
                }
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

        createScene = new Scene(createGrid, 600, 400);

        primaryStage.setTitle("Seikkailu");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void actionShow() {
        areaLabel.setText(world.getPlayer().getArea().getName().toUpperCase());
        descriptionLabel.setText(world.getPlayer().getArea().getDescription());
        findingLabel.setText(world.getPlayer().getArea().show());
        bagLabel.setText(world.getPlayer().bag());
        if (adventure.getTimeGoal() < 0) {
            adventure.getOver();
            gameLabel.setText("Aika loppui, sinä hävisit!");
        }
        if (world.getPlayer().getItems().containsValue(adventure.getItemGoal())) {
            adventure.getOver();
            gameLabel.setText("Sinä löysit etsimäsi! Se oli " + adventure.getItemGoal().toString() + ".");
        }
        if (world.getPlayer().getHelpers().containsValue(adventure.getHelperGoal())) {
            adventure.getOver();
            gameLabel.setText(adventure.getHelperGoal().toString() + ", oli kaipaamasi puhekumppani.");
        }
        monsterLabel.setText(world.getPlayer().getArea().showMonster());
        remainingTimeLabel.setText("Vuoroja on jäljellä " + adventure.getTimeGoal() + ".");
    }

    private VBox playUp() {

        VBox up = new VBox();
        up.setSpacing(10);
        areaLabel = new Label("KOTI");
        descriptionLabel = new Label("Oma koti kullan kallis");
        findingLabel = new Label("Kotona ei ole mitään mielenkiintoista. Paras lähteä matkaan.");
        bagLabel = new Label("Reppusi on tyhjä. Etsi siihen täytettä.");
        gameLabel = new Label("Tässä kerrotaan pelitilanteesi.");
        doingLabel = new Label("Tässä kerrotaan mitä viimeksi teit.");
        monsterLabel = new Label("");
        remainingTimeLabel = new Label("Tässä näkyy kuinka monta toimintaa ehdit vielä tehdä.");
        up.getChildren().add(areaLabel);
        up.getChildren().add(descriptionLabel);
        up.getChildren().add(findingLabel);
        up.getChildren().add(bagLabel);
        up.getChildren().add(gameLabel);
        up.getChildren().add(doingLabel);
        up.getChildren().add(monsterLabel);
        up.getChildren().add(remainingTimeLabel);

        return up;
    }

    private HBox playDown() {
        HBox down = new HBox();
        doGrid = new GridPane();
        moveGrid = new GridPane();
        doGrid.setPadding(new Insets(10, 10, 10, 10));
        doGrid.setVgap(5);
        doGrid.setHgap(5);
        moveGrid.setPadding(new Insets(10, 10, 10, 10));
        moveGrid.setVgap(5);
        moveGrid.setHgap(5);
        //liikkuminen vasemmalle puolelle ja muut toiminnot oikealle
        down.getChildren().add(moveGrid);
        down.getChildren().add(doGrid);

        return down;
    }

    private void createMoveButtons() {
        Image arrowImage = new Image("file:arrow.png");

        Button north = new Button("");
        ImageView northView = new ImageView(arrowImage);
        north.setGraphic(northView);
        north.setOnAction((event) -> {
            boolean move = new Action(world, world.getPlayer()).move(Direction.NORTH);
            adventure.takeTurn();
            actionShow();
            if (move) {
                doingLabel.setText("Matkasit pohjoiseen.");
            } else {
                doingLabel.setText("Et voi mennä pohjoiseen.");
            }
        });
        moveGrid.add(north, 1, 0);

        Button east = new Button("");
        ImageView eastView = new ImageView(arrowImage);
        eastView.setRotate(90);
        east.setGraphic(eastView);
        east.setOnAction((event) -> {
            boolean move = new Action(world, world.getPlayer()).move(Direction.EAST);
            adventure.takeTurn();
            actionShow();
            if (move) {
                doingLabel.setText("Matkasit itään.");
            } else {
                doingLabel.setText("Et voi mennä itään.");
            }
        });
        moveGrid.add(east, 2, 1);

        Button west = new Button("");
        ImageView westView = new ImageView(arrowImage);
        westView.setRotate(270);
        west.setGraphic(westView);
        west.setOnAction((event) -> {
            boolean move = new Action(world, world.getPlayer()).move(Direction.WEST);
            adventure.takeTurn();
            actionShow();
            if (move) {
                doingLabel.setText("Matkasit länteen.");
            } else {
                doingLabel.setText("Et voi mennä länteen.");
            }
        });
        moveGrid.add(west, 0, 1);

        Button south = new Button("");
        ImageView southView = new ImageView(arrowImage);
        southView.setRotate(180);
        south.setGraphic(southView);
        south.setOnAction((event) -> {
            boolean move = new Action(world, world.getPlayer()).move(Direction.SOUTH);
            adventure.takeTurn();
            actionShow();
            if (move) {
                doingLabel.setText("Matkasit etelään.");
            } else {
                doingLabel.setText("Et voi mennä etelään.");
            }
        });
        moveGrid.add(south, 1, 2);
    }

    private void createDoButtons() {
        Button pick = new Button("POIMI");
        pick.setOnAction((event) -> {
            Action a = new Action(world, world.getPlayer());
            Item item = a.take();
            adventure.takeTurn();
            actionShow();
            if (item != null) {
                doingLabel.setText("Poimit esineen " + item + ".");
            } else {
                doingLabel.setText("Täällä ei ole poimittavaa.");
            }
        });
        doGrid.add(pick, 0, 0);

        Button speak = new Button("PUHU");
        speak.setOnAction((event -> {
            Action a = new Action(world, world.getPlayer());
            Helper helper = a.speak();
            adventure.takeTurn();
            actionShow();
            if (helper != null) {
                doingLabel.setText("Sinä ja " + helper + ", puhutte kauan.");
            } else {
                doingLabel.setText("Täällä ei ole uusia keskusteluja käytävänä.");
            }
        }));
        doGrid.add(speak, 0, 1);
    }

    public void findDaos() {
        String[] daos = new String[]{"Area", "Item", "Helper", "Monster"};
        cb.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov,
                        Number old_val, Number new_val) -> {
                    table = daos[new_val.intValue()];
                    if (table.equals("Area")) {
                        try {
                            allList = areaDao.findAll();
                            cbAll.setItems(FXCollections.observableArrayList(allList));
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (table.equals("Item")) {
                        try {
                            allList = itemDao.findAll();
                            cbAll.setItems(FXCollections.observableArrayList(allList));
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (table.equals("Helper")) {
                        try {
                            allList = helperDao.findAll();
                            cbAll.setItems(FXCollections.observableArrayList(allList));
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (table.equals("Monster")) {
                        try {
                            allList = monsterDao.findAll();
                            cbAll.setItems(FXCollections.observableArrayList(allList));
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);

                        }
                    }
                }
        );
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
/*

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
