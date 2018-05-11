package adventuregame.ui;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Rajapinta on luotu erilaisten käyttöliittymämahdollisuuksien toteuttamiselle
 *
 * @author strajama
 */
public interface UI {
    
    public boolean isTestUI();

    public Label getAreaLabel();
    
    public Label getDescriptionLabel();
    
    public Label getFindingLabel();
    
    public Label getBagLabel();
    
    public Label getDoingLabel();
    
    public Label getMonsterLabel();
    
    public Label getPointsLabel();
    
    public VBox getPlayCenter();
}
