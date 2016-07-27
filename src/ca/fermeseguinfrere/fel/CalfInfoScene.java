package ca.fermeseguinfrere.fel;

import ca.fermeseguinfrere.dml.Calf;
import ca.fermeseguinfrere.util.MilkType;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Benoit
 * @since 04/12/2015
 * @version 2
 */
public class CalfInfoScene {

    private MenuVBox menu;

    private Label lbtitle;
    private Label lbIdCalf;
    private Label lbName;
    private Label lbChildOf;
    private Label lbSex;
    private Label lbBirth;
    private Label lbNormalMilk;
    private Label lbMilkCorrection;
    private Label lbCorrectedMilk;
    private Label lbMilkType;

    // Inputs
    private TextField tfIdCalf;
    private TextField tfName;
    private TextField tfChildOf;
    private TextField tfNormalMilkAmount;       //  normal
    private TextField tfMilkCorrection;         //- correction
    //------------
    private TextField tfCorrectedMilkAmount;    //   Corrected

    private ToggleGroup sex;
    private RadioButton rbMale;
    private RadioButton rbFemale;

    private DatePicker dpBirth;

    private ComboBox<MilkType> cobMilkType;

    private Button btnSave;
    private Button btnCreate;
    private Button btnDelete;

    public CalfInfoScene() {
        menu = new MenuVBox();

        lbtitle = new Label();
        lbIdCalf = new Label("Numéro");
        lbName = new Label("Nom");
        lbChildOf = new Label("Enfant de");
        lbSex = new Label("Sexe");
        lbBirth = new Label("Date de naissance");
        lbMilkType = new Label("Type de lait");

        lbNormalMilk = new Label("Montant normal");
        lbMilkCorrection = new Label("Correction");
        lbCorrectedMilk = new Label("Montant corrigé");

        tfIdCalf = new TextField();
        tfName = new TextField();
        tfChildOf = new TextField();
        tfNormalMilkAmount = new TextField();
        tfMilkCorrection = new TextField();
        tfCorrectedMilkAmount = new TextField();

        sex = new ToggleGroup();
        rbFemale = new RadioButton("Femelle");
        rbFemale.setToggleGroup(sex);
        rbMale = new RadioButton("Male");
        rbMale.setToggleGroup(sex);

        dpBirth = new DatePicker(LocalDate.now());
        cobMilkType = new ComboBox();
        cobMilkType.getItems().setAll(MilkType.values());

        btnSave = new Button("Sauvegarder");
        btnCreate = new Button("Créer");
        btnDelete = new Button("Supprimer");

        lbtitle.getStyleClass().add("title");
    }

    public Scene getCreateScene() {
        clearForm();

        lbtitle.setText("Ajouter un veau");
        VBox globalHolder = new VBox(10);
        globalHolder.setAlignment(Pos.CENTER);
        HBox mainHolder = new HBox(10);
        mainHolder.setAlignment(Pos.CENTER);
        VBox leftLabelHolder = new VBox(15);
        leftLabelHolder.setAlignment(Pos.CENTER_LEFT);
        VBox rightInputsHolder = new VBox(10);
        rightInputsHolder.setAlignment(Pos.CENTER);
        mainHolder.getChildren().addAll(menu.getMenu(), leftLabelHolder, rightInputsHolder);
        globalHolder.getChildren().addAll(lbtitle, mainHolder, btnCreate);

        leftLabelHolder.getChildren().addAll(lbIdCalf, lbName, lbChildOf, lbSex, lbBirth);
        HBox radioHolder = new HBox(10);
        radioHolder.getChildren().addAll(rbFemale, rbMale);
        rightInputsHolder.getChildren().addAll(tfIdCalf, tfName, tfChildOf, radioHolder, dpBirth);

        Scene createScene = new Scene(globalHolder, 500, 400);
        createScene.getStylesheets().add("ca/fermeseguinfrere/util/style.css");

        return createScene;
    }

    public Scene getUpdateScene(Calf calf) {
        fillCalfInScene(calf);

        updateScenePreSet();

        lbtitle.setText("Modifier ");
        VBox globalHolder = new VBox(10);
        globalHolder.setAlignment(Pos.CENTER);
        HBox mainHolder = new HBox(10);
        mainHolder.setAlignment(Pos.CENTER);
        VBox leftLabelHolder = new VBox(17);
        leftLabelHolder.setAlignment(Pos.CENTER_LEFT);
        VBox rightInputsHolder = new VBox(10);
        rightInputsHolder.setAlignment(Pos.CENTER);
        mainHolder.getChildren().addAll(menu.getMenu(), leftLabelHolder, rightInputsHolder);

        HBox btnHolder = new HBox(10);
        btnHolder.getChildren().addAll(btnSave, btnDelete);
        btnHolder.setAlignment(Pos.CENTER);

        globalHolder.getChildren().addAll(lbtitle, mainHolder, btnHolder);

        leftLabelHolder.getChildren().addAll(lbIdCalf, lbName, lbChildOf, lbSex, lbBirth, lbMilkType, lbNormalMilk, lbMilkCorrection, lbCorrectedMilk);
        HBox radioHolder = new HBox(10);
        radioHolder.getChildren().addAll(rbFemale, rbMale);
        rightInputsHolder.getChildren().addAll(tfIdCalf, tfName, tfChildOf, radioHolder, dpBirth, cobMilkType, tfNormalMilkAmount, tfMilkCorrection, tfCorrectedMilkAmount);

        Scene updateScene = new Scene(globalHolder, 550, 450);
        updateScene.getStylesheets().add("ca/fermeseguinfrere/util/style.css");
        return updateScene;
    }

    private void disableControls(boolean disable) {
        btnSave.setDisable(disable);
        tfIdCalf.setDisable(disable);
        tfName.setDisable(disable);
        tfChildOf.setDisable(disable);
        tfNormalMilkAmount.setDisable(disable);
        tfMilkCorrection.setDisable(disable);
        tfCorrectedMilkAmount.setDisable(disable);
        rbFemale.setDisable(disable);
        rbMale.setDisable(disable);
        dpBirth.setDisable(disable);
    }

    // TODO : move to eventhandler
    private void fillCalfInScene(Calf calf) {
        tfIdCalf.setText(calf.getIdCalf());
        tfName.setText(calf.getName());
        tfChildOf.setText(calf.getMother());
        dpBirth.setValue(calf.getBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        cobMilkType.getSelectionModel().select(calf.getMilkType());
        isFemale(calf.isIsFemale());
        tfNormalMilkAmount.setText((int) calf.getNormalMilk() == 0 ? "Aucun" : calf.getMilkType() == MilkType.COLOSTRUM ? calf.getCorrectedMilk() + "L Colostrum" : String.format("%.2f", calf.getNormalMilk()) + "L");

        tfMilkCorrection.setText(String.format("%.2f", calf.getCorrectionMilk()));
        if (calf.getCorrectionMilk() < 0) {
            tfMilkCorrection.getStyleClass().add("redText");
        } else {
            tfMilkCorrection.getStyleClass().remove("redText");
        }

        tfCorrectedMilkAmount.setText(String.format("%.2f", calf.getCorrectedMilk()));
    }

    private void clearForm() {
        tfIdCalf.setDisable(false);

        tfIdCalf.setText("");
        tfName.setText("");
        tfChildOf.setText("");
        dpBirth.setValue(LocalDate.now());
        isFemale(true);
    }

    private void isFemale(boolean isFemale) {
        if (isFemale) {
            rbFemale.setSelected(true);
            rbMale.setSelected(false);
        } else {
            rbFemale.setSelected(false);
            rbMale.setSelected(true);
        }
    }

    private void updateScenePreSet() {
        tfNormalMilkAmount.setDisable(true);
        tfIdCalf.setDisable(true);
    }

    public String getIdCalf() {
        return tfIdCalf.getText();
    }

    public String getCalfName() {
        return tfName.getText();
    }

    public String getMotherName() {
        return tfChildOf.getText();
    }

    public boolean getSex() {
        return rbFemale.selectedProperty().get();
    }

    public LocalDate getBirth() {
        return dpBirth.getValue();
    }

    public MilkType getMilkType() {
        return cobMilkType.getSelectionModel().getSelectedItem();
    }

    public float getMilkCorrection() {
        try {
            return new DecimalFormat().parse(tfMilkCorrection.getText()).floatValue();
        } catch (ParseException ex) {
            return 0f;
        }
    }

    public float getCorrectedMilk() {
        try {
            return new DecimalFormat().parse(tfCorrectedMilkAmount.getText()).floatValue();
        } catch (ParseException ex) {
            return 0f;
        }
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public Button getBtnCreate() {
        return btnCreate;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }
}
