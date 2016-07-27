package ca.fermeseguinfrere.appstart;

import ca.fermeseguinfrere.ehl.CalfInfoSceneEventHandler;
import ca.fermeseguinfrere.ehl.CalfsSceneEventHandler;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

/**
 * @author Benoit
 * @version 2
 * @since 04/12/2015
 */
public class CalfMilking extends Application {

    private static Stage deployedStage;
    private static CalfsSceneEventHandler calfsSceneEV;
    private static CalfInfoSceneEventHandler calfInfoSceneEV;

    @Override
    public void start(Stage primaryStage) {
        calfsSceneEV = new CalfsSceneEventHandler();
        calfInfoSceneEV = new CalfInfoSceneEventHandler();
        deployedStage = primaryStage;

        primaryStage.setTitle("Gestion des veaux");
        primaryStage.setScene(calfsSceneEV.getCalfsScene());
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void setCalfsScene() {
        deployedStage.setScene(calfsSceneEV.getCalfsScene());
    }

    public static void setCreateCalfScene() {
        deployedStage.setScene(calfInfoSceneEV.getCalfCreateScene());
    }

    public static void setUpdateCalfScene(String idCalf) {
        deployedStage.setScene(calfInfoSceneEV.updateCalf(idCalf));
    }

}
