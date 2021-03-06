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

package mage.abilities.effects.common.search;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import mage.Constants.Outcome;
import mage.Constants.Zone;
import mage.abilities.Ability;
import mage.abilities.effects.SearchEffect;
import mage.cards.Card;
import mage.cards.Cards;
import mage.cards.CardsImpl;
import mage.game.Game;
import mage.players.Player;
import mage.target.common.TargetCardInLibrary;

/**
 *
 * @author BetaSteward_at_googlemail.com
 */
public class SearchLibraryPutOnLibraryEffect extends SearchEffect<SearchLibraryPutOnLibraryEffect> {
    
    private boolean reveal;
    private boolean forceShuffle;

    public SearchLibraryPutOnLibraryEffect(TargetCardInLibrary target) {
        this(target, false, true);
        setText();
    }
    
    public SearchLibraryPutOnLibraryEffect(TargetCardInLibrary target, boolean reveal, boolean forceShuffle) {
        super(target, Outcome.DrawCard);
        this.reveal = reveal;
        this.forceShuffle = forceShuffle;
        setText();
    }

    public SearchLibraryPutOnLibraryEffect(final SearchLibraryPutOnLibraryEffect effect) {
        super(effect);
        this.reveal = effect.reveal;
    }

    @Override
    public SearchLibraryPutOnLibraryEffect copy() {
        return new SearchLibraryPutOnLibraryEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getControllerId());
        if (player == null)
            return false;
        if (player.searchLibrary(target, game)) {
            List<Card> cards = new ArrayList<Card>();
            for (UUID cardId: (List<UUID>)target.getTargets()) {
                Card card = player.getLibrary().remove(cardId, game);
                if (card != null)
                    cards.add(card);
            }
            Cards foundCards = new CardsImpl();
            foundCards.addAll(cards);
            if (reveal) {
                player.revealCards("Revealed", foundCards, game);
            }
            if (forceShuffle) {
                player.shuffleLibrary(game);
            }
            for (Card card: cards) {
                card.moveToZone(Zone.LIBRARY, source.getId(), game, true);
            }
            return true;
        }
        // shuffle
        if (forceShuffle)
            player.shuffleLibrary(game);
        return false;
    }

    private void setText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Search your library for a ").append(target.getTargetName()).append(", then shuffle your library and put that card on top of it");
        staticText = sb.toString();
    }

}
