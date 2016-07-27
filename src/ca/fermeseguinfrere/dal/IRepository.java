package ca.fermeseguinfrere.dal;

import ca.fermeseguinfrere.dml.Calf;
import ca.fermeseguinfrere.dto.CalfDto;
import ca.fermeseguinfrere.util.SeasonMode;
import java.util.List;

/**
 *
 * @author Benoit
 * @since 11/12/2015
 * @version 2
 */
public interface IRepository {
    public List<CalfDto> getCalfs();
    public Calf getCalf(String idCalf);
    public void deleteCalf(String idCalf);
    public void updateCalf();
    public void createCalf(Calf calf);
    public float getAmountOfPowderMilk();
    public float getAmountOfCowMilk();
    public float getAmountOfColostrum();
    public SeasonMode getSeasonMode();
    public void setSeasonMode(SeasonMode season);
}
