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
package mage.sets.torment;

import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DamageEverythingEffect;
import mage.abilities.keyword.MadnessAbility;
import mage.cards.CardImpl;

import java.util.UUID;

/**
 *
 * @author magenoxx_at_gmail.com
 */
public class PsychoticHaze extends CardImpl<PsychoticHaze> {

    public PsychoticHaze(UUID ownerId) {
        super(ownerId, 76, "Psychotic Haze", Rarity.COMMON, new CardType[]{CardType.INSTANT}, "{2}{B}{B}");
        this.expansionSetCode = "TOR";

        this.color.setBlack(true);

        // Psychotic Haze deals 1 damage to each creature and each player.
        this.getSpellAbility().addEffect(new DamageEverythingEffect(1));

        // Madness {1}{B}
        this.addAbility(new MadnessAbility(this, new ManaCostsImpl("{1}{B}")));
    }

    public PsychoticHaze(final PsychoticHaze card) {
        super(card);
    }

    @Override
    public PsychoticHaze copy() {
        return new PsychoticHaze(this);
    }
}
