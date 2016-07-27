package ca.fermeseguinfrere.fel;

import ca.fermeseguinfrere.appstart.CalfMilking;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 *
 * @author Benoit
 * @since 05/12/2015
 * @version 2
 */
public class MenuVBox {

    private Button btnAddCalfScene;
    private Button btnCalfsScene;
    private Button btnSettingsScene;

    private VBox menuBox;

    public MenuVBox() {
        this.btnAddCalfScene = new Button("Ajouter un veau");
        this.btnAddCalfScene.setOnAction(e -> CalfMilking.setCreateCalfScene());
        
        this.btnCalfsScene = new Button("Voir les veaux");
        this.btnCalfsScene.setOnAction(e -> CalfMilking.setCalfsScene()); 
        
        this.btnSettingsScene = new Button("Options");
        this.btnSettingsScene.setOnAction(null);

        menuBox = new VBox(10);
        menuBox.setAlignment(Pos.CENTER_LEFT);
        
        menuBox.getChildren().addAll(btnAddCalfScene, btnCalfsScene, btnSettingsScene);
    }

    public VBox getMenu() {
        return menuBox;
    }
}
