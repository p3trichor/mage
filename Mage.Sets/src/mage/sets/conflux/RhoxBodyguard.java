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

package mage.sets.conflux;

import java.util.UUID;
import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.MageInt;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.keyword.ExaltedAbility;
import mage.cards.CardImpl;

/**
 *
 * @author Loki
 */
public class RhoxBodyguard extends CardImpl<RhoxBodyguard> {

    public RhoxBodyguard(UUID ownerId) {
        super(ownerId, 122, "Rhox Bodyguard", Rarity.COMMON, new CardType[]{CardType.CREATURE}, "{3}{G}{W}");
        this.expansionSetCode = "CON";
        this.color.setGreen(true);
        this.color.setWhite(true);
        this.subtype.add("Rhino");
        this.subtype.add("Monk");
        this.subtype.add("Soldier");
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);
        this.addAbility(new ExaltedAbility());
        this.addAbility(new EntersBattlefieldTriggeredAbility(new GainLifeEffect(3)));
    }

    public RhoxBodyguard(final RhoxBodyguard card) {
        super(card);
    }

    @Override
    public RhoxBodyguard copy() {
        return new RhoxBodyguard(this);
    }

}
