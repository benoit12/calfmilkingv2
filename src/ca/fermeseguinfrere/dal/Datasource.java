package ca.fermeseguinfrere.dal;

/**
 *
 * @author Benoit
 * @since 11/12/2015
 * @version 2
 */
public class Datasource {
    private static Datasource __INSTANCE;
    private IRepository repository;
    
    private Datasource() {
        repository = new XMLFormater();
    }
    
    public static Datasource getInstance() {
        if(__INSTANCE == null)
            __INSTANCE = new Datasource();
        return __INSTANCE;
    }
    
    public IRepository getRepository() {
        return this.repository;
    }
}
