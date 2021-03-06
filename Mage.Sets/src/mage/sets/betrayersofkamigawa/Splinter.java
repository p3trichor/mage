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
package mage.sets.betrayersofkamigawa;

import java.util.UUID;
import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.abilities.effects.common.ExileTargetAndSearchGraveyardHandLibraryEffect;
import mage.cards.CardImpl;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterArtifactPermanent;
import mage.target.TargetPermanent;

/**
 *
 * @author LevelX2
 */
public class Splinter extends CardImpl<Splinter> {
    private static final FilterPermanent filter = new FilterArtifactPermanent();

    public Splinter(UUID ownerId) {
        super(ownerId, 146, "Splinter", Rarity.UNCOMMON, new CardType[]{CardType.SORCERY}, "{2}{G}{G}");
        this.expansionSetCode = "BOK";

        this.color.setGreen(true);

        // Exile target artifact. Search its controller's graveyard, hand, and library for all cards
        // with the same name as that artifact and exile them. Then that player shuffles his or her library.
        this.getSpellAbility().addTarget(new TargetPermanent(filter));
        this.getSpellAbility().addEffect(new ExileTargetAndSearchGraveyardHandLibraryEffect(false, "its controller's","all cards with the same name as that artifact"));
    }

    public Splinter(final Splinter card) {
        super(card);
    }

    @Override
    public Splinter copy() {
        return new Splinter(this);
    }
}
