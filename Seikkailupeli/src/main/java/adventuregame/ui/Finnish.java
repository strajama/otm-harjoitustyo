package adventuregame.ui;

/**
 * Luokka toteuttaa Language-rajapinnan ja sisältää suomenkieliset nimikkeet
 * käyttöliittymää varten
 *
 * @author strajama
 */
public class Finnish implements Language {

    private final String loginLabel;
    private final String playButton;
    private final String createNewLabel;
    private final String createNewButton;
    private final String exitLabel;
    private final String exitButton;
    private final String returnLogin;
    private final String title;
    private final String newName;
    private final String saveScoreButton;
    private final String[] tables;
    private final String bagLabel;
    private final String pick;
    private final String speak;
    private final String hit;
    private final String give;
    private final String areaLabel;
    private final String descriptionLabel;
    private final String findingLabel;
    private final String doingLabel;
    private final String monsterLabel;
    private final String pointsLabel;
    private final String scoreLabel;
    private final String name;
    private final String points;
    private final String descriptionTextField;
    private final String tableIsEmpty;
    private final String theEnd;
    private final String delete;
    private final String save;
    private final String message;
    private final String nameAndDescription;
    private final String newTable;
    private final String deleteTable;

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
