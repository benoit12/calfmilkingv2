package ca.fermeseguinfrere.dal;

import ca.fermeseguinfrere.dml.Calf;
import ca.fermeseguinfrere.dto.CalfDto;
import ca.fermeseguinfrere.util.MilkType;
import ca.fermeseguinfrere.util.SeasonMode;
import ca.fermeseguinfrere.util.Settings;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Benoit
 * @since 11/12/2015
 * @version 2
 */
public class XMLFormater implements IRepository {

    private final File file;

    private Calfs calfs;

    public XMLFormater() {
        calfs = new Calfs();
        file = new File(Settings.SAVED_GAMES_PATH);
        getData();
    }

    private void getData() {
        try {
            if (!file.exists())
                saveData();

            JAXBContext jaxbContext = JAXBContext.newInstance(Calfs.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            calfs = (Calfs) jaxbUnmarshaller.unmarshal(file);
            
            calfs.getCalfs().sort(new Comparator<Calf>() {

                @Override
                public int compare(Calf t, Calf t1) {
                    return t.getBirth().compareTo(t1.getBirth());
                }
            });

        } catch (JAXBException e) {
            Logger.getLogger(XMLFormater.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void saveData() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Calfs.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(calfs, file);

        } catch (JAXBException e) {
            Logger.getLogger(Calfs.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public List<CalfDto> getCalfs() {
        List<CalfDto> calfsDto = new ArrayList();

        for (Calf c : calfs.getCalfs())
            calfsDto.add(new CalfDto(c));
        
        return calfsDto;
    }

    @Override
    public Calf getCalf(String idCalf) {
        for (Calf c : calfs.getCalfs())
            if (c.getIdCalf().equals(idCalf))
                return c;

        return null;
    }

    @Override
    public void deleteCalf(String idCalf) {
        for (Calf c : calfs.getCalfs())
            if (c.getIdCalf().equals(idCalf)) {
                calfs.getCalfs().remove(c);
                saveData();
                return;
            }

    }

    @Override
    public void updateCalf() {
        saveData();
    }

    @Override
    public void createCalf(Calf calf) {
        calfs.getCalfs().add(calf);
        saveData();
    }

    @Override
    public float getAmountOfPowderMilk() {
        float amount = 0;
        for (Calf c : calfs.getCalfs())
            amount += c.getMilkType() == MilkType.POWDER ?c.getCorrectedMilk():0;

        return amount;
    }

    @Override
    public float getAmountOfCowMilk() {
        float amount = 0;
        for (Calf c : calfs.getCalfs())
            amount += c.getMilkType() == MilkType.COWMILK ?c.getCorrectedMilk():0;

        return amount;
    }
    
    @Override
    public float getAmountOfColostrum() {
        float amount = 0;
        for (Calf c : calfs.getCalfs())
            amount += c.getMilkType() == MilkType.COLOSTRUM ?c.getCorrectedMilk():0;

        return amount;
    }
    
    @Override
    public SeasonMode getSeasonMode() {
        return calfs.getMode();
    }

    @Override
    public void setSeasonMode(SeasonMode season) {
        calfs.setMode(season);
        saveData();
    }

}
