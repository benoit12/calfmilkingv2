package ca.fermeseguinfrere.fel;

import ca.fermeseguinfrere.appstart.CalfMilking;
import ca.fermeseguinfrere.dto.CalfDto;
import ca.fermeseguinfrere.util.MilkType;
import java.util.Collections;
import java.util.List;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author Benoit
 * @since 06/12/2015
 * @version 2
 */
public class CalfsScene {

    private MenuVBox menu;
    private Label lbTitle;
    private TableView<CalfDto> tvCalfs;

    private TableColumn tcCalfNumber;
    private TableColumn tcCalfName;
    private TableColumn tcCalfChildOf;
    private TableColumn tcCalfSex;
    private TableColumn tcCalfAge;
    private TableColumn tcCalfCorrectedMilk;
    private TableColumn tcCalfMilkType;

    private Label lbAmountOfPowder;
    private Label lbAmountOfMilk;
    private Label lbSeasonMode;
    private Label lbAmountOfCowMilk;

    private Button btnSeasonMode;

    public CalfsScene() {
        lbTitle = new Label("Gestion des veaux");

        lbAmountOfPowder = new Label("0g de poudre");
        lbAmountOfMilk = new Label("0L de lait en poudre");
        lbSeasonMode = new Label("Mode : été");
        lbAmountOfCowMilk = new Label("0L de lait de vache");
        btnSeasonMode = new Button("Changer de saison");

        lbAmountOfMilk.getStyleClass().add("powdermilk");
        lbAmountOfCowMilk.getStyleClass().add("cowmilk");

        menu = new MenuVBox();

        tvCalfs = new TableView();
        tvCalfs.setEditable(true);
        tvCalfs.setMinWidth(525);
        tvCalfs.setPrefWidth(700);
        tvCalfs.setMaxWidth(975);
        tvCalfs.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tvCalfs.setEditable(true);

        tvCalfs.setRowFactory(tv -> {
            TableRow<CalfDto> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()))
                    CalfMilking.setUpdateCalfScene(row.getItem().getIdCalf());
            });
            return row;
        });

        tcCalfNumber = new TableColumn("Numéro");
        tcCalfNumber.setCellValueFactory(new PropertyValueFactory("idCalf"));
        tcCalfNumber.setMinWidth(50);
        tcCalfNumber.setPrefWidth(75);
        tcCalfNumber.setMaxWidth(100);
        tcCalfNumber.getStyleClass().add("table-column");

        tcCalfName = new TableColumn("Nom");
        tcCalfName.setCellValueFactory(new PropertyValueFactory("name"));
        tcCalfName.setMinWidth(75);
        tcCalfName.setPrefWidth(100);
        tcCalfName.setMaxWidth(128);
        tcCalfName.getStyleClass().add("table-column");

        tcCalfChildOf = new TableColumn("Enfant de");
        tcCalfChildOf.setCellValueFactory(new PropertyValueFactory("motherName"));
        tcCalfChildOf.setMinWidth(75);
        tcCalfChildOf.setPrefWidth(100);
        tcCalfChildOf.setMaxWidth(125);
        tcCalfChildOf.getStyleClass().add("table-column");

        tcCalfSex = new TableColumn("Sexe");
        tcCalfSex.setCellValueFactory(new PropertyValueFactory("sex"));
        tcCalfSex.setMinWidth(50);
        tcCalfSex.setPrefWidth(75);
        tcCalfSex.setMaxWidth(100);
        tcCalfSex.getStyleClass().add("table-column");

        tcCalfAge = new TableColumn("Age");
        tcCalfAge.setCellValueFactory(new PropertyValueFactory("birth"));
        tcCalfAge.setMinWidth(75);
        tcCalfAge.setPrefWidth(100);
        tcCalfAge.setMaxWidth(125);
        tcCalfAge.getStyleClass().add("table-column");

        tcCalfCorrectedMilk = new TableColumn("Lait");
        tcCalfCorrectedMilk.setCellValueFactory(new PropertyValueFactory("milk"));
        tcCalfCorrectedMilk.setMinWidth(50);
        tcCalfCorrectedMilk.setPrefWidth(75);
        tcCalfCorrectedMilk.setMaxWidth(100);
        tcCalfCorrectedMilk.getStyleClass().add("table-column");

        tcCalfMilkType = new TableColumn("Type de lait");
        tcCalfMilkType.setCellValueFactory(new PropertyValueFactory("milkType"));
        tcCalfMilkType.setMinWidth(75);
        tcCalfMilkType.setPrefWidth(100);
        tcCalfMilkType.setMaxWidth(125);
        tcCalfMilkType.getStyleClass().add("table-column");

        tvCalfs.getColumns().addAll(tcCalfNumber, tcCalfName, tcCalfChildOf, tcCalfSex, tcCalfAge, tcCalfCorrectedMilk, tcCalfMilkType);

        lbTitle.getStyleClass().add("title");
    }

    public Scene getCalfsScene() {
        VBox mainHolder = new VBox(10);
        HBox globalHolder = new HBox(10);
        globalHolder.setAlignment(Pos.CENTER);
        VBox contentHolder = new VBox(10);
        contentHolder.setAlignment(Pos.CENTER);
        contentHolder.getChildren().addAll(lbTitle, tvCalfs);

        globalHolder.getChildren().addAll(menu.getMenu(), contentHolder);

        HBox summaryHolder = new HBox(10);
        VBox amountOfMilkHolder = new VBox(10);
        amountOfMilkHolder.getChildren().addAll(lbAmountOfMilk, lbAmountOfCowMilk);
        amountOfMilkHolder.setAlignment(Pos.BASELINE_CENTER);

        summaryHolder.getChildren().addAll(amountOfMilkHolder, lbAmountOfPowder, lbSeasonMode, btnSeasonMode);
        summaryHolder.setAlignment(Pos.CENTER);
        summaryHolder.getStyleClass().add("bigtext");

        mainHolder.getChildren().addAll(globalHolder, summaryHolder);

        Scene calfsScene = new Scene(mainHolder, 900, 600);
        tvCalfs.minHeightProperty().bind(calfsScene.heightProperty().multiply(0.8));

        calfsScene.getStylesheets().add("ca/fermeseguinfrere/util/style.css");

        return calfsScene;
    }

    public void setTableItems(List<CalfDto> calfs) {
        tvCalfs.getItems().clear();
//        tvCalfs.setRowFactory(new Callback<TableView<CalfDto>, TableRow<CalfDto>>() {
//            @Override
//            public TableRow<CalfDto> call(TableView<CalfDto> tableView) {
//                final TableRow<CalfDto> row = new TableRow<CalfDto>() {
//                    @Override
//                    protected void updateItem(CalfDto calf, boolean empty) {
//                        super.updateItem(calf, empty);
//                        if (calf != null)
//                            if (calf.getMilkType().equalsIgnoreCase(MilkType.COWMILK.toString()))
//                                this.getStyleClass().add("cowmilk");
//                            else if (calf.getMilkType().equalsIgnoreCase(MilkType.POWDER.toString()))
//                                this.getStyleClass().add("powdermilk");
//                            else {
//                                this.getStyleClass().remove("cowmilk");
//                                this.getStyleClass().remove("powdermilk");
//                            }
//                    }
//                };
//                return row;
//            }
//        });

        tvCalfs.getItems().addAll(calfs);
    }

    public void setLbSeason(String season) {
        lbSeasonMode.setText(season);
    }

    public void setLBAmountOfPowder(String amount) {
        lbAmountOfPowder.setText(amount);
    }

    public void setLBAmountOfMilk(String amount) {
        lbAmountOfMilk.setText(amount);
    }

    public Button getBtnSeasonMode() {
        return btnSeasonMode;
    }
}
