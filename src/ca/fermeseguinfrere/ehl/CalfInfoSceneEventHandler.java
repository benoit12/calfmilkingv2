package ca.fermeseguinfrere.ehl;

import ca.fermeseguinfrere.appstart.CalfMilking;
import ca.fermeseguinfrere.dal.Datasource;
import ca.fermeseguinfrere.dml.Calf;
import ca.fermeseguinfrere.fel.CalfInfoScene;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

/**
 *
 * @author Benoit
 * @since 10/12/2015
 * @version 2
 */
public class CalfInfoSceneEventHandler {

    private CalfInfoScene scene;

    public CalfInfoSceneEventHandler() {
        this.scene = new CalfInfoScene();
        this.scene.getBtnCreate().setOnAction(e -> createCalf());
        this.scene.getBtnDelete().setOnAction(e -> deleteCalf());
        this.scene.getBtnSave().setOnAction(e -> updateCalf());
    }

    public Scene getCalfCreateScene() {
        return scene.getCreateScene();
    }

    public Scene updateCalf(String calfId) {
        return scene.getUpdateScene(Datasource.getInstance().getRepository().getCalf(calfId));
    }

    public void createCalf() {
        if (scene.getIdCalf() != null && !scene.getIdCalf().equalsIgnoreCase("")) {
            Calf newCalf = new Calf(scene.getIdCalf(), scene.getCalfName(), scene.getMotherName(), scene.getSex(), Date.from(Instant.from(scene.getBirth().atStartOfDay(ZoneId.systemDefault()))));
            Datasource.getInstance().getRepository().createCalf(newCalf);
            CalfMilking.setCalfsScene();
        }
    }

    public void deleteCalf() {
        Datasource.getInstance().getRepository().deleteCalf(scene.getIdCalf());
        CalfMilking.setCalfsScene();
    }

    public void updateCalf() {
        try {
            Calf edited = Datasource.getInstance().getRepository().getCalf(scene.getIdCalf());

            edited.setName(scene.getCalfName());
            edited.setMother(scene.getMotherName());
            edited.setIsFemale(scene.getSex());
            edited.setBirth(Date.from(Instant.from(scene.getBirth().atStartOfDay(ZoneId.systemDefault()))));
            edited.setMilkType(scene.getMilkType());

            float correction = edited.getCorrectionMilk() == scene.getMilkCorrection() ? Math.abs(scene.getCorrectedMilk()) - edited.getNormalMilk() : scene.getMilkCorrection();
            edited.setCorrectionMilk(correction);

            Datasource.getInstance().getRepository().updateCalf();
            CalfMilking.setCalfsScene();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }
}
