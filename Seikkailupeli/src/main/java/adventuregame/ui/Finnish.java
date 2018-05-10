package adventuregame.ui;

/**
 * Luokka toteuttaa Language-rajapinnan ja sisältää suomenkieliset nimikkeet
 * käyttöliittymää varten
 *
 * @author strajama
 */
public class Finnish implements Language {

    private String loginLabel;
    private String playButton;
    private String createNewLabel;
    private String createNewButton;
    private String exitLabel;
    private String exitButton;
    private String returnLogin;
    private String title;
    private String newName;
    private String saveScoreButton;
    private String[] tables;
    private String bagLabel;
    private String pick;
    private String speak;
    private String hit;
    private String give;
    private String areaLabel;
    private String descriptionLabel;
    private String findingLabel;
    private String doingLabel;
    private String monsterLabel;
    private String pointsLabel;
    private String scoreLabel;
    private String name;
    private String points;
    private String descriptionTextField;
    private String tableIsEmpty;
    private String theEnd;
    private String delete;
    private String save;
    private String message;
    private String nameAndDescription;
    private String newTable;
    private String deleteTable;

    public Finnish() {
        loginLabel = "Pelaa randomisti seikkailua";
        playButton = "Jee, pelaamaan!";
        createNewLabel = "Mene leikkimään tietokannoilla";
        createNewButton = "Tauluille!";
        exitLabel = "Sulje ohjelma";
        exitButton = "Lopeta";
        returnLogin = "Palaa valikkoon";
        title = "Seikkailu";
        newName = "Anna uusi nimi";
        saveScoreButton = "TALLENNA PISTEET";
        tables = new String[]{"Alue", "Esine", "Apuri", "Hirviö"};
        bagLabel = "Reppusi on tyhjä.";
        pick = "POIMI";
        speak = "PUHU";
        hit = "LYÖ";
        give = "ANNA";
        areaLabel = "KOTI";
        descriptionLabel = "Oma koti kullan kallis";
        findingLabel = "Kotona ei ole mitään mielenkiintoista. Paras lähteä matkaan.";
        doingLabel = "Tässä kerrotaan mitä viimeksi teit.";
        monsterLabel = "";
        pointsLabel = "Tässä näkyy pisteesi.";
        scoreLabel = "Parhaat pisteet";
        name = "nimi";
        points = "pisteet";
        descriptionTextField = "Anna kuvaus";
        tableIsEmpty = "Valitse taulu";
        theEnd = "LOPPU!";
        delete = "POISTA";
        save = "LISÄÄ";
        message = "Tähän tulee viesti";
        nameAndDescription = "Anna nimi ja kuvaus.";
        newTable = "Uusi taulu lisätty.";
        deleteTable = "Taulu on nyt poistettu.";
    }

    public String getNewTable() {
        return newTable;
    }

    public String getNameAndDescription() {
        return nameAndDescription;
    }

    public String getMessage() {
        return message;
    }

    public String getTheEnd() {
        return theEnd;
    }

    public String getTableIsEmpty() {
        return tableIsEmpty;
    }

    public String getLoginLabel() {
        return loginLabel;
    }

    public String getPlayButton() {
        return playButton;
    }

    public String getCreateNewLabel() {
        return createNewLabel;
    }

    public String getCreateNewButton() {
        return createNewButton;
    }

    public String getExitLabel() {
        return exitLabel;
    }

    public String getExitButton() {
        return exitButton;
    }

    public String getReturnLogin() {
        return returnLogin;
    }

    public String getTitle() {
        return title;
    }

    public String getNewName() {
        return newName;
    }

    public String getSaveScoreButton() {
        return saveScoreButton;
    }

    public String[] getTables() {
        return tables;
    }

    public String getBagLabel() {
        return bagLabel;
    }

    public String getPick() {
        return pick;
    }

    public String getSpeak() {
        return speak;
    }

    public String getHit() {
        return hit;
    }

    public String getGive() {
        return give;
    }

    public String getDescriptionTextField() {
        return descriptionTextField;
    }

    public String getAreaLabel() {
        return areaLabel;
    }

    public String getDescriptionLabel() {
        return descriptionLabel;
    }

    public String getFindingLabel() {
        return findingLabel;
    }

    public String getDoingLabel() {
        return doingLabel;
    }

    public String getMonsterLabel() {
        return monsterLabel;
    }

    public String getPointsLabel() {
        return pointsLabel;
    }

    public String getScoreLabel() {
        return scoreLabel;
    }

    public String getName() {
        return name;
    }

    public String getPoints() {
        return points;
    }

    public String getDelete() {
        return delete;
    }

    public String getSave() {
        return save;
    }

    public String getDeleteTable() {
        return deleteTable;
    }
    
    
}
