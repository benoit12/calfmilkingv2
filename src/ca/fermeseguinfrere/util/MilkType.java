package ca.fermeseguinfrere.util;

/**
 *
 * @author Benoit
 * @since 17/12/2015
 * @version 2
 */
public enum MilkType {
    COLOSTRUM("Colostrum"), POWDER("Lait en poudre"), COWMILK("Lait de vaches");
    private String text;

    private MilkType(String text) {
        this.text = text;
    }
    
    public String toString() {
        return text;
    }
}
