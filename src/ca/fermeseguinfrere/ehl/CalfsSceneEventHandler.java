package ca.fermeseguinfrere.ehl;

import ca.fermeseguinfrere.bll.CalfsController;
import ca.fermeseguinfrere.dal.Datasource;
import ca.fermeseguinfrere.fel.CalfsScene;
import javafx.scene.Scene;

/**
 *
 * @author Benoit
 * @since 10/12/2015
 * @version 2
 */
public class CalfsSceneEventHandler {

    private CalfsScene scene;
    private CalfsController controller;

    public CalfsSceneEventHandler() {
        this.scene = new CalfsScene();
        controller = new CalfsController();
        this.scene.getBtnSeasonMode().setOnAction(e -> changeSeason());
    }

    public Scene getCalfsScene() {
        scene.setTableItems(Datasource.getInstance().getRepository().getCalfs());
        scene.setLBAmountOfMilk("Lait à préparer : " + controller.getAmountOfPowderMilk());
        scene.setLBAmountOfPowder("Poudre à mettre : " + controller.getPowder());
        scene.setLbSeason("Mode : " + controller.getSeason());
        return scene.getCalfsScene();
    }

    private void changeSeason() {
        controller.changeSeason();
        this.scene.setLbSeason("Mode : " + controller.getSeason());
        this.scene.setLBAmountOfPowder("Poudre à mettre : " + controller.getPowder());
    }
}
