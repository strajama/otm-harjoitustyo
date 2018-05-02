package adventuregame.ui;

import java.sql.SQLException;
import java.util.ArrayList;
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
import adventuregame.dao.AreaDao;
import adventuregame.dao.Database;
import adventuregame.dao.HelperDao;
import adventuregame.dao.ItemDao;
import adventuregame.dao.MonsterDao;
import adventuregame.dao.ScoreDao;
import adventuregame.domain.Action;
import adventuregame.domain.Adventure;
import adventuregame.domain.Direction;
import adventuregame.domain.Helper;
import adventuregame.domain.Item;
import adventuregame.domain.Monster;
import adventuregame.domain.Score;
import adventuregame.domain.World;
import java.util.Iterator;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SeikkailuFXMain extends Application {

    private Scene playScene;
    private Scene loginScene;
    private Scene createScene;

    private AreaDao areaDao;
    private ItemDao itemDao;
    private HelperDao helperDao;
    private MonsterDao monsterDao;
    private ScoreDao scoreDao;
    private Database database;
    private String table;
    private ArrayList allList;
    private World world;
    private Adventure adventure;

    private BorderPane playBp;
    private GridPane doGrid;
    private GridPane moveGrid;

    private VBox scoreVBox;
    private TableView scoreTable;

    private GridPane createGrid;
    private TextField name;
    private TextField des;
    private Label label;

    private VBox center;
//playscenen muutettavat Labelit
    private Label areaLabel;
    private Label descriptionLabel;
    private Label findingLabel;
    private Label bagLabel;
    private Label gameLabel;
    private Label doingLabel;
    private Label monsterLabel;
    private Label remainingTimeLabel;

    private ChoiceBox cb;
    private ChoiceBox cbAll;

    @Override
    public void init() throws Exception {
        this.database = new Database("jdbc:sqlite:adventure.db");
        database.init();
        this.areaDao = new AreaDao(database);
        this.itemDao = new ItemDao(database);
        this.helperDao = new HelperDao(database);
        this.monsterDao = new MonsterDao(database);
        this.scoreDao = new ScoreDao(database);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        world = new World(areaDao, itemDao, helperDao, monsterDao);
        //login-scene
        VBox loginPane = new VBox(10);
        loginPane.setPadding(new Insets(10, 10, 10, 10));
        loginPane.setAlignment(Pos.CENTER);

        Label loginLabel = new Label("Pelaa kirjautumatta randomisti seikkailua");
        Button playButton = new Button("Jee, pelaamaan!");
        playButton.setOnAction(e -> {
            try {
                world = new World(areaDao, itemDao, helperDao, monsterDao);
            } catch (Exception ex) {
                Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            adventure = new Adventure(world);
            adventure.makeAGame();
            primaryStage.setScene(playScene);
        });
        Label createNewLabel = new Label("Tähän tulee uusien juttujen luominen tietokantaan.");
        Button createNewButton = new Button("Sitä tekemään");
        createNewButton.setOnAction(e -> {
            primaryStage.setScene(createScene);
        });
        Label exitLabel = new Label("Testataan sovelluksen sulkemista.");
        Button exitButton = new Button("Lopeta");
        exitButton.setOnAction(e -> Platform.exit());

        loginPane.getChildren().addAll(loginLabel, playButton, createNewLabel, createNewButton, exitLabel, exitButton);
        loginScene = new Scene(loginPane, 1000, 400);

        playSceneBorderPaneCreate();
        createMoveButtons();
        createDoButtons();
        createScoreSave();
//        createScoreTable();

        //paluu alkuun
        Button returnLogin = new Button("PALUU VALIKKOON");
        returnLogin.setOnAction((event) -> {
            primaryStage.setScene(loginScene);
        });
        doGrid.add(returnLogin, 2, 2);

        playScene = new Scene(playBp, 1000, 400);

        createCreateScene();
        createDaoSubmitButton();
        createClearButton();

        //Login-buttonilla palataan aloitusnäytölle
        Button rLogin = new Button("Palaa takaisin");
        GridPane.setConstraints(rLogin, 1, 3);
        createGrid.getChildren().add(rLogin);
        rLogin.setOnAction((event) -> {
            primaryStage.setScene(loginScene);
        });
        createScene = new Scene(createGrid, 1000, 400);

        primaryStage.setTitle("Seikkailu");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    /**
     * Metodia käytetään käyttöliittymän playscenen tekstien päivittämiseen
     */
    private void actionShow() {
        areaLabel.setText(world.getPlayer().getArea().getName().toUpperCase());
        descriptionLabel.setText(world.getPlayer().getArea().getDescription());
        findingLabel.setText(world.getPlayer().getArea().show());
        bagLabel.setText(world.getPlayer().bag());
        monsterLabel.setText(world.getPlayer().getArea().showMonster());
        remainingTimeLabel.setText("Sinulla on pisteitä " + adventure.getPoints() + ".");
    }

    private void playSceneBorderPaneCreate() throws SQLException {
        playBp = new BorderPane();
        playBp.setPadding(new Insets(10, 10, 10, 10));
        playBp.setLeft(playLeft());
        playBp.setBottom(playDown());
        playBp.setCenter(playRight());
        createScoreTable();
        playBp.setRight(updateScoreTable());
    }

    /**
     * Playscenen vasemman osan luominen
     *
     * @return - palauttaa itsensä näytölle sijoitettavaksi
     */
    private VBox playLeft() {
        VBox left = new VBox();
        left.setSpacing(10);
        areaLabel = new Label("KOTI");
        descriptionLabel = new Label("Oma koti kullan kallis");
        findingLabel = new Label("Kotona ei ole mitään mielenkiintoista. Paras lähteä matkaan.");
//        bagLabel = new Label("Reppusi on tyhjä. Etsi siihen täytettä.");
        gameLabel = new Label("Tässä kerrotaan pelitilanteesi.");
        doingLabel = new Label("Tässä kerrotaan mitä viimeksi teit.");
        monsterLabel = new Label("");
        remainingTimeLabel = new Label("Tässä näkyy pisteesi.");
        left.getChildren().add(areaLabel);
        left.getChildren().add(descriptionLabel);
        left.getChildren().add(findingLabel);
        //     left.getChildren().add(bagLabel);
        left.getChildren().add(gameLabel);
        left.getChildren().add(doingLabel);
        left.getChildren().add(monsterLabel);
        left.getChildren().add(remainingTimeLabel);

        return left;
    }

    /**
     * Playscenen alaosan luominen
     *
     * @return - palauttaa itsensä näytölle sijoitettavaksi
     */
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

    private VBox playRight() {
        center = new VBox();
        center.setSpacing(10);
        center.setAlignment(Pos.TOP_LEFT);
        center.setPadding(new Insets(20, 20, 20, 20));
        bagLabel = new Label("Reppusi on tyhjä.");
        center.getChildren().add(bagLabel);
        return center;
    }

    /**
     * Metodi luo napit, joilla liikkuminen tapahtuu
     */
    private void createMoveButtons() {
        Image arrowImage = new Image("file:arrow.png");

        Button north = new Button("");
        ImageView northView = new ImageView(arrowImage);
        north.setGraphic(northView);
        north.setOnAction((event) -> {
            boolean move = new Action(adventure).move(Direction.NORTH);
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
            boolean move = new Action(adventure).move(Direction.EAST);
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
            boolean move = new Action(adventure).move(Direction.WEST);
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
            boolean move = new Action(adventure).move(Direction.SOUTH);
            actionShow();
            if (move) {
                doingLabel.setText("Matkasit etelään.");
            } else {
                doingLabel.setText("Et voi mennä etelään.");
            }
        });
        moveGrid.add(south, 1, 2);
    }

    /**
     * Metodi luo napit, joilla tehdään asioita
     */
    private void createDoButtons() {
        Button pick = new Button("POIMI");
        pick.setOnAction((event) -> {
            Action a = new Action(adventure);
            Item item = a.take();
            actionShow();
            if (item != null) {
                doingLabel.setText("Poimit esineen " + item + ".");
                center.getChildren().add(new Label(item.getName().toUpperCase()));
            } else {
                doingLabel.setText("Täällä ei ole poimittavaa.");
            }
        });
        doGrid.add(pick, 0, 0);

        Button speak = new Button("PUHU");
        speak.setOnAction((event -> {
            Action a = new Action(adventure);
            Helper helper = a.speak();
            actionShow();
            if (helper != null) {
                doingLabel.setText(helper + " puuttuu " + helper.getItem().getDescription() + ".");
            } else {
                doingLabel.setText("Täällä ei ole uusia keskusteluja käytävänä.");
            }
        }));
        doGrid.add(speak, 0, 1);

        Button give = new Button("ANNA");
        give.setOnAction((event -> {
            Action a = new Action(adventure);
            Item item = a.give();
            actionShow();
            if (item != null) {
                doingLabel.setText("Reppusi on kevyempi, kun sieltä puuttuu " + item.getName() + ".");
                center.getChildren().clear();
                center.getChildren().add(bagLabel);
                Iterator<String> it = world.getPlayer().getItems().keySet().iterator();
                while (it.hasNext()) {
                    center.getChildren().add(new Label(it.next().toUpperCase()));
                }
            } else {
                doingLabel.setText("Sinulla ei ole mitään sopivaa annettavaa.");
            }
        }));
        doGrid.add(give, 1, 0);

        Button hit = new Button("LYÖ");
        hit.setOnAction((event -> {
            Action a = new Action(adventure);
            Monster monster = a.hit();
            actionShow();
            if (monster != null) {
                doingLabel.setText("Lyöt hirviötä " + monster.getName() + ", joka ottaa osuman.");
            } else {
                doingLabel.setText("Huidot ilmaa niin, että sinulle tulee hiki.");
            }
            if (monster.isDead()) {
                monsterLabel.setText(monster.getName().toUpperCase() + " on kuollut.");
            }
        }));
        doGrid.add(hit, 1, 1);
    }

    private void createScoreSave() {
        TextField playerName = new TextField();
        playerName.setPromptText("Anna uusi nimi.");
        doGrid.add(playerName, 2, 0);

        Button saveScore = new Button("TALLENNA PISTEET");
        saveScore.setOnAction((event) -> {
            if (!playerName.getText().equals("")) {
                try {
                    scoreDao.saveOrUpdate(new Score(playerName.getText(), adventure.getPoints()));
                } catch (SQLException ex) {
                    Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        doGrid.add(saveScore, 2, 1);
    }

    private void createScoreTable() throws SQLException {

        Label label = new Label("Parhaat pisteet");
        scoreTable = new TableView();
        TableColumn name = new TableColumn("nimi");
        TableColumn points = new TableColumn("pisteet");
        scoreTable.getColumns().addAll(name, points);

        name.setCellValueFactory(new PropertyValueFactory<Score, String>("name"));
        points.setCellValueFactory(new PropertyValueFactory<Score, Integer>("points"));

        scoreVBox = new VBox();
        scoreVBox.setSpacing(5);
        scoreVBox.setPadding(new Insets(20, 20, 20, 20));
        scoreVBox.getChildren().addAll(label, scoreTable);

        updateScoreTable();

//        center.getChildren().add(scoreVBox);
    }

    private VBox updateScoreTable() throws SQLException {

        ArrayList<Score> list = scoreDao.bestScores();

        ObservableList<Score> data = FXCollections.observableArrayList(list);
        scoreTable.setItems(data);

        for (Score s : list) {

        }

        return scoreVBox;
    }

    private void createCreateScene() {
        createGrid = new GridPane();
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
        cb.setItems(FXCollections.observableArrayList("Esine", "Apuri", "Hirviö"));
        GridPane.setConstraints(cb, 0, 0);
        createGrid.getChildren().add(cb);
        findDaos();

        name = new TextField();
        name.setPromptText("Anna uusi nimi.");
        GridPane.setConstraints(name, 0, 1);
        createGrid.getChildren().add(name);

        des = new TextField();
        des.setPromptText("Anna uusi kuvaus.");
        GridPane.setConstraints(des, 0, 2);
        createGrid.getChildren().add(des);
        label = new Label("Tähän tulee viesti");
        GridPane.setConstraints(label, 0, 3);
        GridPane.setColumnSpan(label, 2);
        createGrid.getChildren().add(label);

        cbAll.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov,
                        Number old_val, Number new_val) -> {
                }
        );
    }

    /**
     * Metodi luo tietokannassa olevien kaikkien tietojen näkemisen
     */
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
     * Metodi, joka luo napin, jolla voi lisätä tietokantaan uusia tietoja.
     */
    private void createDaoSubmitButton() {
        Button submit = new Button("Lisää");
        GridPane.setConstraints(submit, 1, 1);
        createGrid.getChildren().add(submit);
        submit.setOnAction((ActionEvent e) -> {
            if ((!name.getText().isEmpty() && !des.getText().isEmpty())) {
                if (table.isEmpty()) {
                    label.setText("Valitse taulu.");
                } else {
                    /* Kommentoitu ulos, koska tällä hetkellä ei ole varmaa halutaanko alueita muokata 
                    if (table == "Area") {
                        Area newArea = new Area(name.getText(), des.getText());
                        try {
                            areaDao.saveOrUpdate(newArea);
                    allList = areaDao.findAll();
                            cbAll.setItems(FXCollections.observableArrayList(allList));
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else*/ if ("Item".equals(table)) {
                        Item newItem = new Item(name.getText(), des.getText());
                        try {
                            itemDao.saveOrUpdate(newItem);
                            allList = itemDao.findAll();
                            cbAll.setItems(FXCollections.observableArrayList(allList));
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if ("Helper".equals(table)) {
                        Helper newHelper = new Helper(name.getText(), des.getText());
                        try {
                            helperDao.saveOrUpdate(newHelper);
                            allList = helperDao.findAll();
                            cbAll.setItems(FXCollections.observableArrayList(allList));
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if ("Monster".equals(table)) {
                        Monster newMonster = new Monster(name.getText(), des.getText());
                        try {
                            monsterDao.saveOrUpdate(newMonster);
                            allList = monsterDao.findAll();
                            cbAll.setItems(FXCollections.observableArrayList(allList));
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
    }

    /**
     * Metodi tyhjentää tekstikentät
     */
    private void createClearButton() {
        Button clear = new Button("Pyyhi");
        GridPane.setConstraints(clear, 1, 2);
        createGrid.getChildren().add(clear);
        clear.setOnAction((ActionEvent e) -> {
            name.clear();
            des.clear();
            label.setText("Tekstikentät tyhjennetty.");
        });
    }

    @Override
    public void stop() throws Exception {
        System.out.println("LOPPU!");
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
