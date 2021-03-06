/*
* Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
*
* Redistribution and use in source and binary forms, with or without modification, are
* permitted provided that the following conditions are met:
*
*    1. Redistributions of source code must retain the above copyright notice, this list of
*       conditions and the following disclaimer.
*
*    2. Redistributions in binary form must reproduce the above copyright notice, this list
*       of conditions and the following disclaimer in the documentation and/or other materials
*       provided with the distribution.
*
* THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
* WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
* FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
* SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
* ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
* NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
* ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
* The views and conclusions contained in the software and documentation are those of the
* authors and should not be interpreted as representing official policies, either expressed
* or implied, of BetaSteward_at_googlemail.com.
*/

package mage.abilities.effects.common;

import java.util.UUID;
import mage.Constants.Outcome;
import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.effects.OneShotEffect;
import mage.cards.Card;
import mage.game.Game;
import mage.game.permanent.Permanent;

/**
 *
 * @author BetaSteward_at_googlemail.com
 */
public class ExileTargetForSourceEffect extends OneShotEffect<ExileTargetForSourceEffect> {

    private String exileZone = null;

    public ExileTargetForSourceEffect(String exileZone) {
        this();
        this.exileZone = exileZone;
    }

    public ExileTargetForSourceEffect() {
        super(Outcome.Exile);
    }

    public ExileTargetForSourceEffect(final ExileTargetForSourceEffect effect) {
        super(effect);
        this.exileZone = effect.exileZone;
    }

    @Override
    public ExileTargetForSourceEffect copy() {
        return new ExileTargetForSourceEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getPermanent(targetPointer.getFirst(game, source));
        UUID exileId = source.getSourceId();
        if (permanent != null) {
            return permanent.moveToExile(exileId, exileZone, source.getId(), game);
        } else {
            Card card = game.getCard(targetPointer.getFirst(game, source));
            if (card != null) {
                return card.moveToExile(exileId, exileZone, source.getId(), game);
            }
        }
        return false;
    }

    @Override
    public String getText(Mode mode) {
        if (mode.getTargets().isEmpty()) {
            return "Exile it";
        } else {
            return "Exile target " + mode.getTargets().get(0).getTargetName();
        }
    }
}
