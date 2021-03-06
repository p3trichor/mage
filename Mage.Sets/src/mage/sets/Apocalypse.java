package mage.sets;

import java.util.GregorianCalendar;
import mage.Constants;
import mage.cards.ExpansionSet;


public class Apocalypse extends ExpansionSet {
    private static final Apocalypse fINSTANCE =  new Apocalypse();

    public static Apocalypse getInstance() {
        return fINSTANCE;
    }

    private Apocalypse() {
        super("Apocalypse", "APC", "", "mage.sets.apocalypse", new GregorianCalendar(2001, 5, 1).getTime(), Constants.SetType.EXPANSION);
        this.blockName = "Invasion";
        this.parentSet = Invasion.getInstance();
        this.hasBasicLands = false;
        this.hasBoosters = true;
        this.numBoosterLands = 0;
        this.numBoosterCommon = 11;
        this.numBoosterUncommon = 3;
        this.numBoosterRare = 1;
        this.ratioBoosterMythic = 8;
    }
}
