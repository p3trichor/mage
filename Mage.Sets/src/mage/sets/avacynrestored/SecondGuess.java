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
package mage.sets.avacynrestored;

import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.abilities.effects.common.CounterTargetEffect;
import mage.cards.CardImpl;
import mage.filter.FilterSpell;
import mage.game.Game;
import mage.game.stack.Spell;
import mage.filter.predicate.Predicate;
import mage.target.TargetSpell;
import mage.watchers.common.CastSpellLastTurnWatcher;

import java.util.UUID;

/**
 *
 * @author noxx
 */
public class SecondGuess extends CardImpl<SecondGuess> {

    private static final FilterSpell filter = new FilterSpell("spell that's the second spell cast this turn");

    static {
        filter.add(new SecondSpellPredicate());
    }

    public SecondGuess(UUID ownerId) {
        super(ownerId, 74, "Second Guess", Rarity.UNCOMMON, new CardType[]{CardType.INSTANT}, "{1}{U}");
        this.expansionSetCode = "AVR";

        this.color.setBlue(true);

        // Counter target spell that's the second spell cast this turn.
        this.getSpellAbility().addEffect(new CounterTargetEffect());
        this.getSpellAbility().addTarget(new TargetSpell(filter));
    }

    public SecondGuess(final SecondGuess card) {
        super(card);
    }

    @Override
    public SecondGuess copy() {
        return new SecondGuess(this);
    }
}

class SecondSpellPredicate implements Predicate<Spell> {

    @Override
    public boolean apply(Spell input, Game game) {
        CastSpellLastTurnWatcher watcher = (CastSpellLastTurnWatcher) game.getState().getWatchers().get("CastSpellLastTurnWatcher");

        if (watcher.getSpellOrder(input) == 2) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "SecondSpellThisTurn";
    }
}
