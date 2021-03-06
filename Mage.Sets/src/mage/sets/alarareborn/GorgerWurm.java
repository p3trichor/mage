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
package mage.sets.alarareborn;

import java.util.UUID;
import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.MageInt;
import mage.abilities.effects.common.DevourEffect;
import mage.abilities.keyword.DevourAbility;
import mage.cards.CardImpl;

/**
 *
 * @author LevelX2
 */
public class GorgerWurm extends CardImpl<GorgerWurm> {

    public GorgerWurm(UUID ownerId) {
        super(ownerId, 56, "Gorger Wurm", Rarity.COMMON, new CardType[]{CardType.CREATURE}, "{3}{R}{G}");
        this.expansionSetCode = "ARB";
        this.subtype.add("Wurm");
        this.color.setGreen(true);
        this.color.setRed(true);
        this.power = new MageInt(5);
        this.toughness = new MageInt(5);

        // Devour 1 (As this enters the battlefield, you may sacrifice any number of creatures. This creature enters the battlefield with twice that many +1/+1 counters on it.)
        this.addAbility(new DevourAbility(DevourEffect.DevourFactor.Devour1));
    }

    public GorgerWurm(final GorgerWurm card) {
        super(card);
    }

    @Override
    public GorgerWurm copy() {
        return new GorgerWurm(this);
    }
}
