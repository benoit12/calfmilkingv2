package ca.fermeseguinfrere.dal;

import ca.fermeseguinfrere.dml.Calf;
import ca.fermeseguinfrere.util.SeasonMode;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benoit
 * @since 11/12/2015
 * @version 2
 */
@XmlRootElement
public class Calfs {

    private List<Calf> calfsList;
    private SeasonMode mode;

    public Calfs() {
        calfsList = new ArrayList();
        mode = SeasonMode.SU;
    }

    @XmlElement
    public List<Calf> getCalfs() {
        return calfsList;
    }

    public void setCalfs(List<Calf> calfs) {
        this.calfsList = calfs;
    }

    @XmlElement
    public SeasonMode getMode() {
        return mode;
    }

    public void setMode(SeasonMode mode) {
        this.mode = mode;
    }
}
