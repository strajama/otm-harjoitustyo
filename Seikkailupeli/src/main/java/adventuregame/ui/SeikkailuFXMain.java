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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import adventuregame.dao.DaoService;
import adventuregame.dao.Database;
import adventuregame.domain.Action;
import adventuregame.domain.Adventure;
import adventuregame.domain.Area;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class SeikkailuFXMain extends Application {

    private Scene playScene;
    private Scene beginScene;
    private Scene createScene;

    private Stage primaryStage;

    private DaoService daoService;
    private String table;
    private ArrayList allList;
    
    private World world;
    private Adventure adventure;
    private Language f;

//    private BorderPane playBp;
    private GridPane doGrid;
    private GridPane moveGrid;
    private VBox playCenter;

    private VBox scoreVBox;
    private TableView scoreTable;

    private GridPane createGrid;
    private TextField nameTextField;
    private TextField descriptionTextField;
    private Label createMessageLabel;

    

    private Label areaLabel;
    private Label descriptionLabel;
    private Label findingLabel;
    private Label bagLabel;
    private Label doingLabel;
    private Label monsterLabel;
    private Label pointsLabel;

    private ChoiceBox cb;
    private ChoiceBox cbAll;

    @Override
    public void init() throws Exception {
        Database database = new Database("jdbc:sqlite:adventure.db");
        database.init();
        daoService = new DaoService(database);
        this.world = new World(daoService);
        f = new Finnish();
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        createBeginScene();
        createPlayScene();
        createCreateScene();

        primaryStage.setTitle(f.getTitle());
        primaryStage.setScene(beginScene);
        primaryStage.show();
    }

    /**
     * Metodia käytetään käyttöliittymän playscenen tekstien päivittämiseen
     */
    private void actionShow() {
        areaLabel.setText(adventure.getPlayer().getArea().getName().toUpperCase());
        descriptionLabel.setText(adventure.getPlayer().getArea().getDescription());
        findingLabel.setText(adventure.getPlayer().getArea().show());
        bagLabel.setText(adventure.getPlayer().bag());
        monsterLabel.setText(adventure.getPlayer().getArea().showMonster());
        pointsLabel.setText(adventure.printPoints());
        doingLabel.setText(adventure.getLastAction());
    }

    /**
     * Metodi luo alkunäkymän
     */
    private void createBeginScene() {
        VBox beginPane = new VBox(10);
        beginPane.setPadding(new Insets(10, 10, 10, 10));
        beginPane.setAlignment(Pos.CENTER);

        Label beginLabel = new Label(f.getLoginLabel());
        Button playButton = new Button(f.getPlayButton());
        playButton.setOnAction(e -> {
            adventure = new Adventure(world);
            adventure.makeAGame();
            primaryStage.setScene(playScene);
        });

        Label createNewLabel = new Label(f.getCreateNewLabel());
        Button createNewButton = new Button(f.getCreateNewButton());
        createNewButton.setOnAction(e -> {
            primaryStage.setScene(createScene);
        });
        Label exitLabel = new Label(f.getExitLabel());
        Button exitButton = new Button(f.getExitButton());
        exitButton.setOnAction(e -> Platform.exit());

        beginPane.getChildren().addAll(beginLabel, playButton, createNewLabel, createNewButton, exitLabel, exitButton);
        beginScene = new Scene(beginPane, 1000, 400);
    }

    /**
     * Metodi luo pelaamisnäkymän
     */
    private void createPlayScene() {
        BorderPane playBp = new BorderPane();
        playBp.setPadding(new Insets(10, 10, 10, 10));
        playBp.setLeft(playLeft());
        playBp.setBottom(playDown());
        playBp.setCenter(playCenter());
        createScoreTable();
        playBp.setRight(updateScoreTable());
        createMoveButtons();
        createDoButtons();
        createScoreSave();

        Button returnLogin = new Button(f.getReturnLogin());
        returnLogin.setMinSize(60, 40);
        returnLogin.setOnAction((event) -> {
            primaryStage.setScene(beginScene);
        });
        doGrid.add(returnLogin, 2, 2);
        playScene = new Scene(playBp, 1000, 400);
    }

    /**
     * Playscenen vasemman osan luominen
     *
     * @return - palauttaa itsensä näytölle sijoitettavaksi
     */
    private VBox playLeft() {
        VBox left = new VBox();
        left.setSpacing(10);
        areaLabel = new Label(f.getAreaLabel());
        descriptionLabel = new Label(f.getDescriptionLabel());
        findingLabel = new Label(f.getFindingLabel());
        doingLabel = new Label(f.getDoingLabel());
        monsterLabel = new Label(f.getMonsterLabel());
        pointsLabel = new Label(f.getPointsLabel());
        left.getChildren().add(areaLabel);
        left.getChildren().add(descriptionLabel);
        left.getChildren().add(findingLabel);
        left.getChildren().add(doingLabel);
        left.getChildren().add(monsterLabel);
        left.getChildren().add(pointsLabel);

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

    /**
     * Playscenen keskiosan luominen
     *
     * @return - palauttaa itsensä näytölle sijoitettavaksi
     */
    private VBox playCenter() {
        playCenter = new VBox();
        playCenter.setSpacing(10);
        playCenter.setAlignment(Pos.TOP_LEFT);
        playCenter.setPadding(new Insets(20, 20, 20, 20));
        bagLabel = new Label(f.getBagLabel());
        playCenter.getChildren().add(bagLabel);
        return playCenter;
    }

    /**
     * Metodi luo napit, joilla liikkuminen tapahtuu
     */
    private void createMoveButtons() {

        Polygon triangleNorth = new Polygon();
        triangleNorth.getPoints().addAll(20.0, 0.0, 0.0, 20.0, 40.0, 20.0);
        triangleNorth.setFill(Color.BLUE);
        triangleNorth.setStroke(Color.DARKBLUE);

        Polygon triangleEast = new Polygon();
        triangleEast.getPoints().addAll(20.0, 0.0, 0.0, 20.0, 40.0, 20.0);
        triangleEast.setFill(Color.GREEN);
        triangleEast.setStroke(Color.DARKGREEN);
        triangleEast.setRotate(90);

        Polygon triangleSouth = new Polygon();
        triangleSouth.getPoints().addAll(20.0, 0.0, 0.0, 20.0, 40.0, 20.0);
        triangleSouth.setFill(Color.RED);
        triangleSouth.setStroke(Color.DARKRED);
        triangleSouth.setRotate(180);

        Polygon triangleWest = new Polygon();
        triangleWest.getPoints().addAll(20.0, 0.0, 0.0, 20.0, 40.0, 20.0);
        triangleWest.setFill(Color.ORANGE);
        triangleWest.setStroke(Color.DARKORANGE);
        triangleWest.setRotate(270);

        Button north = new Button();
        north.setMinSize(50, 50);
        north.setGraphic(triangleNorth);
        north.setOnAction((event) -> {
            boolean move = new Action(adventure).move(Direction.NORTH);
            actionShow();

        });
        moveGrid.add(north, 1, 0);

        Button east = new Button();
        east.setMinSize(50, 50);
        east.setGraphic(triangleEast);
        east.setOnAction((event) -> {
            boolean move = new Action(adventure).move(Direction.EAST);
            actionShow();

        });
        moveGrid.add(east, 2, 1);

        Button west = new Button();
        west.setMinSize(50, 50);
        west.setGraphic(triangleWest);
        west.setOnAction((event) -> {
            boolean move = new Action(adventure).move(Direction.WEST);
            actionShow();
        });
        moveGrid.add(west, 0, 1);

        Button south = new Button();
        south.setMinSize(50, 50);
        south.setGraphic(triangleSouth);
        south.setOnAction((event) -> {
            boolean move = new Action(adventure).move(Direction.SOUTH);
            actionShow();
        });
        moveGrid.add(south, 1, 2);
    }

    /**
     * Metodi luo napit, joilla pelaaja tekee asioita
     */
    private void createDoButtons() {
        Button pick = new Button(f.getPick());
        pick.setMinSize(60, 40);
        pick.setOnAction((event) -> {
            Action a = new Action(adventure);
            Item item = a.take();
            actionShow();
            if (item != null) {
                playCenter.getChildren().clear();
                playCenter.getChildren().add(bagLabel);
                Iterator<String> it = adventure.getPlayer().getItems().keySet().iterator();
                while (it.hasNext()) {
                    playCenter.getChildren().add(new Label(it.next().toUpperCase()));
                }
            }
        });
        doGrid.add(pick, 0, 0);

        Button speak = new Button(f.getSpeak());
        speak.setMinSize(60, 40);
        speak.setOnAction((event -> {
            Action a = new Action(adventure);
            Helper helper = a.speak();
            actionShow();
        }));
        doGrid.add(speak, 0, 1);

        Button give = new Button(f.getGive());
        give.setMinSize(60, 40);
        give.setOnAction((event -> {
            Action a = new Action(adventure);
            Item item = a.give();
            actionShow();
            if (item != null) {
                playCenter.getChildren().clear();
                playCenter.getChildren().add(bagLabel);
                Iterator<String> it = adventure.getPlayer().getItems().keySet().iterator();
                while (it.hasNext()) {
                    playCenter.getChildren().add(new Label(it.next().toUpperCase()));
                }
            }
        }));
        doGrid.add(give, 1, 0);

        Button hit = new Button(f.getHit());
        hit.setMinSize(60, 40);
        hit.setOnAction((event -> {
            Action a = new Action(adventure);
            Monster monster = a.hit();
            actionShow();
            if (monster != null) {
                if (monster.isDead()) {
                    monsterLabel.setText("");
                }
            }

        }));
        doGrid.add(hit, 1, 1);
    }

    /**
     * Metodi luo pisteiden tallennusmahdollisuuden
     */
    private void createScoreSave() {
        TextField playerName = new TextField();
        playerName.setMinSize(60, 40);
        playerName.setPromptText(f.getNewName());
        doGrid.add(playerName, 2, 0);

        Button saveScoreButton = new Button(f.getSaveScoreButton());
        saveScoreButton.setMinSize(60, 40);
        saveScoreButton.setOnAction((event) -> {
            if (!playerName.getText().isEmpty()) {
                try {
                    daoService.getScoreDao().saveOrUpdate(new Score(playerName.getText(), adventure.getPoints()));
                } catch (SQLException ex) {
                    Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                }
                playerName.clear();
                updateScoreTable();
            }
        });
        doGrid.add(saveScoreButton, 2, 1);
    }

    /**
     * Metodi luo top5-pistelistauksen
     */
    private void createScoreTable() {

        Label scoreLabel = new Label(f.getScoreLabel());
        scoreTable = new TableView();
        TableColumn name = new TableColumn(f.getName());
        TableColumn points = new TableColumn(f.getPoints());
        scoreTable.getColumns().addAll(name, points);

        name.setCellValueFactory(new PropertyValueFactory<Score, String>("name"));
        points.setCellValueFactory(new PropertyValueFactory<Score, Integer>("points"));

        scoreVBox = new VBox();
        scoreVBox.setSpacing(5);
        scoreVBox.setPadding(new Insets(20, 20, 20, 20));
        scoreVBox.getChildren().addAll(scoreLabel, scoreTable);

        updateScoreTable();
    }

    /**
     * Metodi päivittää pistetaulua
     *
     * @return palauttaa päivitetyn itsensä näytölle sijoitettavaksi
     * @throws SQLException - koska pistetaulu on ajan tasalla
     */
    private VBox updateScoreTable() {
        ArrayList<Score> scoreList = new ArrayList<>();
        try {
            scoreList = daoService.getScoreDao().bestScores();
        } catch (SQLException ex) {
            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Score> data = FXCollections.observableArrayList(scoreList);
        scoreTable.setItems(data);

        return scoreVBox;
    }

    /**
     * Metodi luo näkymän, jossa voi lisätä uusia asioita tietokantatauluihin
     */
    private void createCreateScene() {
        createGrid = new GridPane();
        createGrid.setPadding(new Insets(10, 10, 10, 10));
        createGrid.setVgap(5);
        createGrid.setHgap(5);
        createGrid.setAlignment(Pos.TOP_CENTER);
        table = "";
        allList = new ArrayList();

        cbAll = new ChoiceBox();
        cbAll.setItems(FXCollections.observableArrayList(allList));
        GridPane.setConstraints(cbAll, 0, 4);
        createGrid.getChildren().add(cbAll);

        cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList(f.getTables()));
        GridPane.setConstraints(cb, 0, 0);
        createGrid.getChildren().add(cb);
        findDaos();

        nameTextField = new TextField();
        nameTextField.setPromptText(f.getNewName());
        GridPane.setConstraints(nameTextField, 0, 1);
        createGrid.getChildren().add(nameTextField);

        descriptionTextField = new TextField();
        descriptionTextField.setPromptText(f.getDescriptionTextField());
        GridPane.setConstraints(descriptionTextField, 0, 2);
        createGrid.getChildren().add(descriptionTextField);
        createMessageLabel = new Label(f.getMessage());
        GridPane.setConstraints(createMessageLabel, 0, 3);
        GridPane.setColumnSpan(createMessageLabel, 2);
        createGrid.getChildren().add(createMessageLabel);

        cbAll.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov,
                        Number old_val, Number new_val) -> {
                }
        );
        //Login-buttonilla palataan aloitusnäytölle
        Button rLogin = new Button(f.getReturnLogin());
        GridPane.setConstraints(rLogin, 1, 3);
        createGrid.getChildren().add(rLogin);
        rLogin.setOnAction((event) -> {
            primaryStage.setScene(beginScene);
        });
        createDaoSubmitButton();
        createDeleteButton();
        createScene = new Scene(createGrid, 1000, 400);
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
                            allList = daoService.getAreaDao().findAll();
                            cbAll.setItems(FXCollections.observableArrayList(allList));
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (table.equals("Item")) {
                        try {
                            allList = daoService.getItemDao().findAll();
                            cbAll.setItems(FXCollections.observableArrayList(allList));
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (table.equals("Helper")) {
                        try {
                            allList = daoService.getHelperDao().findAll();
                            cbAll.setItems(FXCollections.observableArrayList(allList));
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (table.equals("Monster")) {
                        try {
                            allList = daoService.getMonsterDao().findAll();
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
        Button submit = new Button(f.getSave());
        GridPane.setConstraints(submit, 1, 1);
        createGrid.getChildren().add(submit);
        submit.setOnAction((ActionEvent e) -> {
            if ((!nameTextField.getText().isEmpty() && !descriptionTextField.getText().isEmpty())) {
                if (table.isEmpty()) {
                    createMessageLabel.setText(f.getTableIsEmpty());
                } else {
                    if (table == "Area") {
                        Area newArea = new Area(nameTextField.getText(), descriptionTextField.getText());
                        try {
                            daoService.getAreaDao().saveOrUpdate(newArea);
                            allList = daoService.getAreaDao().findAll();
                            cbAll.setItems(FXCollections.observableArrayList(allList));
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if ("Item".equals(table)) {
                        Item newItem = new Item(nameTextField.getText(), descriptionTextField.getText());
                        try {
                            daoService.getItemDao().saveOrUpdate(newItem);
                            allList = daoService.getItemDao().findAll();
                            cbAll.setItems(FXCollections.observableArrayList(allList));
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if ("Helper".equals(table)) {
                        Helper newHelper = new Helper(nameTextField.getText(), descriptionTextField.getText());
                        try {
                            daoService.getHelperDao().saveOrUpdate(newHelper);
                            allList = daoService.getHelperDao().findAll();
                            cbAll.setItems(FXCollections.observableArrayList(allList));
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if ("Monster".equals(table)) {
                        Monster newMonster = new Monster(nameTextField.getText(), descriptionTextField.getText());
                        try {
                            daoService.getMonsterDao().saveOrUpdate(newMonster);
                            allList = daoService.getMonsterDao().findAll();
                            cbAll.setItems(FXCollections.observableArrayList(allList));
                        } catch (SQLException ex) {
                            Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    createMessageLabel.setText(f.getNewTable());
                    nameTextField.clear();
                    descriptionTextField.clear();
                }
            } else if (nameTextField.getText().isEmpty() && descriptionTextField.getText().isEmpty()) {
                createMessageLabel.setText(f.getNameAndDescription());
            } else if (descriptionTextField.getText().isEmpty()) {
                createMessageLabel.setText(f.getDescriptionTextField());
            } else if (nameTextField.getText().isEmpty()) {
                createMessageLabel.setText(f.getNewName());
            }
        });
    }

    /**
     * Metodi luo napin, jolla voi poistaa tietoja tietokannasta
     */
    private void createDeleteButton() {
        Button delete = new Button(f.getDelete());
        GridPane.setConstraints(delete, 1, 2);
        createGrid.getChildren().add(delete);
        delete.setOnAction((ActionEvent e) -> {
            if (!nameTextField.getText().isEmpty()) {
                String name = nameTextField.getText();
                if (table.isEmpty()) {
                    createMessageLabel.setText(f.getTableIsEmpty());
                }
                if (table == "Area") {
                    try {
                        Integer key = daoService.getAreaDao().findIdByName(name);
                        daoService.getAreaDao().delete(key);
                        allList = daoService.getAreaDao().findAll();
                        cbAll.setItems(FXCollections.observableArrayList(allList));
                    } catch (SQLException ex) {
                        Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if ("Item".equals(table)) {
                    try {
                        Integer key = daoService.getItemDao().findIdByName(name);
                        daoService.getItemDao().delete(key);
                        allList = daoService.getItemDao().findAll();
                        cbAll.setItems(FXCollections.observableArrayList(allList));
                    } catch (SQLException ex) {
                        Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if ("Helper".equals(table)) {
                    try {
                        Integer key = daoService.getHelperDao().findIdByName(name);
                        daoService.getHelperDao().delete(key);
                        allList = daoService.getHelperDao().findAll();
                        cbAll.setItems(FXCollections.observableArrayList(allList));
                    } catch (SQLException ex) {
                        Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if ("Monster".equals(table)) {
                    try {
                        Integer key = daoService.getMonsterDao().findIdByName(name);
                        daoService.getMonsterDao().delete(key);
                        allList = daoService.getMonsterDao().findAll();
                        cbAll.setItems(FXCollections.observableArrayList(allList));
                    } catch (SQLException ex) {
                        Logger.getLogger(SeikkailuFXMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    @Override
    public void stop() {
        System.out.println(f.getTheEnd());
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
        createvb.getChildren().add(tfDes);

    private void createClearButton() {
        Button clear = new Button("Pyyhi");
        GridPane.setConstraints(clear, 1, 3);
        createGrid.getChildren().add(clear);
        clear.setOnAction((ActionEvent e) -> {
            nameTextField.clear();
            descriptionTextField.clear();
            label.setText("Tekstikentät tyhjennetty.");
        });
    }
 */
