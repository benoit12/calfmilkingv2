package ca.fermeseguinfrere.dto;

import ca.fermeseguinfrere.dml.Calf;
import ca.fermeseguinfrere.util.MilkType;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Benoit
 * @since 06/12/2015
 * @version 2
 */
public class CalfDto {

    private final SimpleStringProperty idCalf;
    private final SimpleStringProperty name;
    private final SimpleStringProperty motherName;
    private final SimpleStringProperty sex;
    private final SimpleObjectProperty<LocalDate> birth;
    private final SimpleStringProperty milk;
    private final SimpleStringProperty milkType;

    public CalfDto(Calf calf) {
        this.idCalf = new SimpleStringProperty(calf.getIdCalf());
        this.name = new SimpleStringProperty(calf.getName());
        this.motherName = new SimpleStringProperty(calf.getMother());
        this.sex = new SimpleStringProperty(calf.isIsFemale() ? "Femelle" : "Mâle");
        this.birth = new SimpleObjectProperty(calf.getBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.milk = new SimpleStringProperty(calf.getCorrectedMilk() == 0 ? "Aucun" : String.format("%.02f", calf.getCorrectedMilk()) + "L");
        this.milkType = new SimpleStringProperty(calf.getMilkType().toString());
    }

    public String getIdCalf() {
        return idCalf.get();
    }

    public String getName() {
        return name.get();
    }

    public String getMotherName() {
        return motherName.get();
    }

    public String getSex() {
        return sex.get();
    }

    public LocalDate getBirth() {
        return birth.get();
    }

    public String getMilk() {
        return milk.get();
    }

    public String getMilkType() {
        return milkType.get();
    }

}
