package ca.fermeseguinfrere.bll;

import ca.fermeseguinfrere.dal.Datasource;
import ca.fermeseguinfrere.util.SeasonMode;

/**
 *
 * @author Benoit
 * @since 12/12/2015
 * @version 2
 */
public class CalfsController {

    public String getPowder() {
        float amount = Datasource.getInstance().getRepository().getAmountOfPowderMilk();
        if (Datasource.getInstance().getRepository().getSeasonMode() == SeasonMode.SU){
            return (int) (amount * 110) + "g";
        }
        else if (Datasource.getInstance().getRepository().getSeasonMode() == SeasonMode.W)
            return (int) (amount * 120) + "g";
        return 0 + "g";
    }

    public void changeSeason() {
        if (Datasource.getInstance().getRepository().getSeasonMode() == SeasonMode.SU)
            Datasource.getInstance().getRepository().setSeasonMode(SeasonMode.W);
        else if (Datasource.getInstance().getRepository().getSeasonMode() == SeasonMode.W)
            Datasource.getInstance().getRepository().setSeasonMode(SeasonMode.SU);
    }

    public String getSeason() {
        if (Datasource.getInstance().getRepository().getSeasonMode() == SeasonMode.SU)
            return "Été";
        else if (Datasource.getInstance().getRepository().getSeasonMode() == SeasonMode.W)
            return "Hiver";
        return "";
    }
    
    public String getAmountOfPowderMilk() {
        return Datasource.getInstance().getRepository().getAmountOfPowderMilk() + "L";
    }
    
    public String getAmountOfCowMilk() {
        return Datasource.getInstance().getRepository().getAmountOfCowMilk() + "L";
    }
    
    public String getAmountOfColostrum() {
        return Datasource.getInstance().getRepository().getAmountOfColostrum()+ "L";
    }
}
