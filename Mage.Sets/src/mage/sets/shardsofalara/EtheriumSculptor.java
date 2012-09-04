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
package mage.sets.shardsofalara;

import java.util.UUID;
import mage.Constants.CardType;
import mage.Constants.Duration;
import mage.Constants.Outcome;
import mage.Constants.Rarity;
import mage.Constants.Zone;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.SpellAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.CostModificationEffectImpl;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.game.Game;
import mage.util.CardUtil;

/**
 *
 * @author North
 */
public class EtheriumSculptor extends CardImpl<EtheriumSculptor> {

    public EtheriumSculptor(UUID ownerId) {
        super(ownerId, 42, "Etherium Sculptor", Rarity.COMMON, new CardType[]{CardType.ARTIFACT, CardType.CREATURE}, "{1}{U}");
        this.expansionSetCode = "ALA";
        this.subtype.add("Vedalken");
        this.subtype.add("Artificer");

        this.color.setBlue(true);
        this.power = new MageInt(1);
        this.toughness = new MageInt(2);

        // Artifact spells you cast cost {1} less to cast.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new EtheriumSculptorEffect()));
    }

    public EtheriumSculptor(final EtheriumSculptor card) {
        super(card);
    }

    @Override
    public EtheriumSculptor copy() {
        return new EtheriumSculptor(this);
    }
}

class EtheriumSculptorEffect extends CostModificationEffectImpl<EtheriumSculptorEffect> {

    EtheriumSculptorEffect() {
        super(Duration.WhileOnBattlefield, Outcome.Benefit);
        staticText = "Artifact spells you cast cost {1} less to cast";
    }

    EtheriumSculptorEffect(EtheriumSculptorEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source, Ability abilityToModify) {
        SpellAbility spellAbility = (SpellAbility) abilityToModify;
        CardUtil.adjustCost(spellAbility, 1);
        return true;
    }

    @Override
    public boolean applies(Ability abilityToModify, Ability source, Game game) {
        if (abilityToModify instanceof SpellAbility) {
            Card sourceCard = game.getCard(((SpellAbility) abilityToModify).getSourceId());
            if (sourceCard != null && sourceCard.getCardType().contains(CardType.ARTIFACT) && sourceCard.getOwnerId().equals(source.getControllerId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public EtheriumSculptorEffect copy() {
        return new EtheriumSculptorEffect(this);
    }
}