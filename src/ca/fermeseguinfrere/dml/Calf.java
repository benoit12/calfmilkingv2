package ca.fermeseguinfrere.dml;

import ca.fermeseguinfrere.util.MilkType;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benoit
 * @since 04/12/2015
 * @version 2
 */
@XmlRootElement
public class Calf {

    private String idCalf;
    private String name;
    private String mother;
    private boolean isFemale;
    private Date birth;
    private float normalMilk;
    private float correctionMilk;
    private MilkType milkType;

    public Calf() {
    }

    public Calf(String idCalf, String name, String mother, boolean isFemale, Date birth) {
        this.idCalf = idCalf;
        this.name = name;
        this.mother = mother;
        this.isFemale = isFemale;
        this.birth = birth;
        this.milkType = MilkType.COLOSTRUM;
        computeMilk();
    }

    private void computeMilk() {

        Date today = new Date();
        long difference = TimeUnit.DAYS.convert(today.getTime() - birth.getTime(), TimeUnit.MILLISECONDS);

        if (difference < 2)
            normalMilk = 2.0f;
        else if (difference < 5)
            normalMilk = 2.0f;
        else if (difference < 8)
            normalMilk = 2.5f;
        else if (difference < 11)
            normalMilk = 3.0f;
        else if (difference < 15)
            normalMilk = 3.5f;
        else if (difference < 43)
            normalMilk = 4.0f;
        else if (difference < 50)
            normalMilk = 3.5f;
        else if (difference < 57)
            normalMilk = 1.0f;
        else 
            normalMilk = 0.0f;
    }

    @XmlElement
    public String getIdCalf() {
        return idCalf;
    }

    public void setIdCalf(String idCalf) {
        this.idCalf = idCalf;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    @XmlElement
    public boolean isIsFemale() {
        return isFemale;
    }

    public void setIsFemale(boolean isFemale) {
        this.isFemale = isFemale;
    }

    @XmlElement
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date date) {
        this.birth = date;
    }

    @XmlElement
    public float getNormalMilk() {
        computeMilk();
        return normalMilk;
    }

    @XmlElement
    public float getCorrectionMilk() {
        return correctionMilk;
    }

    public void setCorrectionMilk(float correction) {
        this.correctionMilk = correction;
    }

    @XmlElement
    public float getCorrectedMilk() {
        computeMilk();
        return normalMilk + correctionMilk;
    }

    @XmlElement
    public MilkType getMilkType() {
        return milkType;
    }

    public void setMilkType(MilkType milkType) {
        this.milkType = milkType;
    }

    @Override
    public String toString() {
        return "Calf{" + "idCalf=" + idCalf + ", name=" + name + ", mother=" + mother + ", isFemale=" + isFemale + ", birth=" + birth + ", normalMilk=" + normalMilk + ", correctionMilk=" + correctionMilk + ", milkType=" + milkType + '}';
    }
}
