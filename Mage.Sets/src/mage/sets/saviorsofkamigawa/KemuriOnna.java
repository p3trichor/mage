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
package mage.sets.saviorsofkamigawa;

import java.util.UUID;
import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SpellCastTriggeredAbility;
import mage.abilities.effects.common.DiscardTargetEffect;
import mage.abilities.effects.common.ReturnToHandSourceEffect;
import mage.cards.CardImpl;
import mage.filter.common.FilterSpiritOrArcaneCard;
import mage.target.TargetPlayer;

/**
 *
 * @author Loki
 */
public class KemuriOnna extends CardImpl<KemuriOnna> {

    public KemuriOnna(UUID ownerId) {
        super(ownerId, 76, "Kemuri-Onna", Rarity.UNCOMMON, new CardType[]{CardType.CREATURE}, "{4}{B}");
        this.expansionSetCode = "SOK";
        this.subtype.add("Spirit");
        this.color.setBlack(true);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);
        // When Kemuri-Onna enters the battlefield, target player discards a card.
        Ability ability = new EntersBattlefieldTriggeredAbility(new DiscardTargetEffect(1), false);
        ability.addTarget(new TargetPlayer());
        this.addAbility(ability);
        // Whenever you cast a Spirit or Arcane spell, you may return Kemuri-Onna to its owner's hand.
        this.addAbility(new SpellCastTriggeredAbility(new ReturnToHandSourceEffect(), FilterSpiritOrArcaneCard.getDefault(), true));
    }

    public KemuriOnna(final KemuriOnna card) {
        super(card);
    }

    @Override
    public KemuriOnna copy() {
        return new KemuriOnna(this);
    }
}