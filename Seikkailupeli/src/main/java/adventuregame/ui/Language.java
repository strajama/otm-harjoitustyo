package adventuregame.ui;

/**
 * Rajapinnan tarkoitus on mahdollistaa käyttöliittymän kielen vaihtaminen
 * suomesta johonkin muuhun kieleen kohtuullisen helposti
 *
 * @author strajama
 */
public interface Language {

    public String getNewTable();

    public String getNameAndDescription();

    public String getMessage();

    public String getTheEnd();

    public String getTableIsEmpty();

    public String getLoginLabel();

    public String getPlayButton();

    public String getCreateNewLabel();

    public String getCreateNewButton();

    public String getExitLabel();

    public String getExitButton();

    public String getReturnLogin();

    public String getTitle();

    public String getNewName();

    public String getSaveScoreButton();

    public String[] getTables();

    public String getBagLabel();

    public String getPick();

    public String getSpeak();

    public String getHit();

    public String getGive();

    public String getDescriptionTextField();

    public String getAreaLabel();

    public String getDescriptionLabel();

    public String getFindingLabel();

    public String getDoingLabel();

    public String getMonsterLabel();

    public String getPointsLabel();

    public String getScoreLabel();

    public String getName();

    public String getPoints();

    public String getDelete();

    public String getSave();
}
