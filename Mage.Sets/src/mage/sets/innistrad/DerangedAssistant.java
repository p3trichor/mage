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
package mage.sets.innistrad;

import java.util.UUID;
import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.Constants.Zone;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.costs.CostImpl;
import mage.abilities.mana.ColorlessManaAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.game.Game;
import mage.players.Player;

/**
 *
 * @author North
 */
public class DerangedAssistant extends CardImpl<DerangedAssistant> {

    public DerangedAssistant(UUID ownerId) {
        super(ownerId, 52, "Deranged Assistant", Rarity.COMMON, new CardType[]{CardType.CREATURE}, "{1}{U}");
        this.expansionSetCode = "ISD";
        this.subtype.add("Human");
        this.subtype.add("Wizard");

        this.color.setBlue(true);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // {tap}, Put the top card of your library into your graveyard: Add {1} to your mana pool.
        ColorlessManaAbility ability = new ColorlessManaAbility();
        ability.addCost(new DerangedAssistantCost());
        this.addAbility(ability);
    }

    public DerangedAssistant(final DerangedAssistant card) {
        super(card);
    }

    @Override
    public DerangedAssistant copy() {
        return new DerangedAssistant(this);
    }
}

class DerangedAssistantCost extends CostImpl<DerangedAssistantCost> {

    public DerangedAssistantCost() {
        this.text = "Put the top card of your library into your graveyard";
    }

    public DerangedAssistantCost(DerangedAssistantCost cost) {
        super(cost);
    }

    @Override
    public boolean pay(Ability ability, Game game, UUID sourceId, UUID controllerId, boolean noMana) {
        Player player = game.getPlayer(controllerId);
        if (player != null) {
            Card card = player.getLibrary().removeFromTop(game);
            if (card != null) {
                paid = card.moveToZone(Zone.GRAVEYARD, sourceId, game, true);

            }
        }
        return paid;
    }

    @Override
    public boolean canPay(UUID sourceId, UUID controllerId, Game game) {
        Player player = game.getPlayer(controllerId);
        if (player != null && player.getLibrary().size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public DerangedAssistantCost copy() {
        return new DerangedAssistantCost(this);
    }
}