/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.sets.championsofkamigawa;

import java.util.UUID;
import mage.Constants;
import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.condition.common.ControlsPermanentCondition;
import mage.abilities.common.CantBlockAbility;
import mage.abilities.costs.mana.ColoredManaCost;
import mage.abilities.decorator.ConditionalActivatedAbility;
import mage.abilities.effects.common.RegenerateSourceEffect;
//import mage.abilities.effects.common.continious.GainAbilitySourceEffect;
import mage.cards.CardImpl;
import mage.filter.Filter;
import mage.filter.common.FilterControlledPermanent;

/**
 *
 * @author LevelX
 */
public class VillainousOgre extends CardImpl<VillainousOgre> {
    
    private static final String rule = "As long as you control a Demon, {this} has {B}: Regenerate Villainous Ogre";
    private static final FilterControlledPermanent filter = new FilterControlledPermanent("Demon");
    static {
        filter.getSubtype().add("Demon");
        filter.setScopeSubtype(Filter.ComparisonScope.Any);
    }
    
    public VillainousOgre(UUID ownerId) {
        super(ownerId, 148, "Villainous Ogre", Rarity.COMMON, new CardType[]{CardType.CREATURE}, "{2}{B}");
        this.expansionSetCode = "CHK";
        this.subtype.add("Ogre");
        this.subtype.add("Warrior");

        this.color.setBlack(true);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        this.addAbility(CantBlockAbility.getInstance());
        
        // As long as you control a Demon, Villainous Ogre has "{B}: Regenerate Villainous Ogre.
        this.addAbility( new ConditionalActivatedAbility(
                Constants.Zone.BATTLEFIELD, 
                new RegenerateSourceEffect(), 
                new ColoredManaCost(Constants.ColoredManaSymbol.B), 
                new ControlsPermanentCondition(filter), 
                rule));        
    }

    public VillainousOgre(final VillainousOgre card) {
        super(card);
    }

    @Override
    public VillainousOgre copy() {
        return new VillainousOgre(this);
    }    
     
}