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
package mage.sets.darkascension;

import java.util.Random;
import java.util.UUID;
import mage.Constants;
import mage.Constants.CardType;
import mage.Constants.Outcome;
import mage.Constants.Rarity;
import mage.Constants.Zone;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.mana.ColorlessManaAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.filter.common.FilterCreatureCard;
import mage.game.Game;
import mage.players.Player;

/**
 *
 * @author North
 */
public class HauntedFengraf extends CardImpl<HauntedFengraf> {

    public HauntedFengraf(UUID ownerId) {
        super(ownerId, 157, "Haunted Fengraf", Rarity.COMMON, new CardType[]{CardType.LAND}, "");
        this.expansionSetCode = "DKA";

        // {tap}: Add {1} to your mana pool.
        this.addAbility(new ColorlessManaAbility());
        // {3}, {tap}, Sacrifice Haunted Fengraf: Return a creature card at random from your graveyard to your hand.
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new HauntedFengrafEffect(), new GenericManaCost(3));
        ability.addCost(new TapSourceCost());
        ability.addCost(new SacrificeSourceCost());
        this.addAbility(ability);
    }

    public HauntedFengraf(final HauntedFengraf card) {
        super(card);
    }

    @Override
    public HauntedFengraf copy() {
        return new HauntedFengraf(this);
    }
}

class HauntedFengrafEffect extends OneShotEffect<HauntedFengrafEffect> {

    public HauntedFengrafEffect() {
        super(Outcome.ReturnToHand);
        this.staticText = "Return a creature card at random from your graveyard to your hand";
    }

    public HauntedFengrafEffect(final HauntedFengrafEffect effect) {
        super(effect);
    }

    @Override
    public HauntedFengrafEffect copy() {
        return new HauntedFengrafEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getControllerId());
        if (player != null) {
            Card[] cards = player.getGraveyard().getCards(new FilterCreatureCard(), game).toArray(new Card[0]);
            if (cards.length > 0) {
                Random rnd = new Random();
                Card card = cards[rnd.nextInt(cards.length)];
                card.moveToZone(Constants.Zone.HAND, source.getId(), game, true);
                game.informPlayers(card.getName() + " returned to the hand of " + player.getName());
                return true;
            }
        }
        return false;
    }
}