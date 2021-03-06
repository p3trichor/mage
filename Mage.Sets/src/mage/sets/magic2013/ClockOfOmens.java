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
package mage.sets.magic2013;

import java.util.UUID;
import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.Constants.Zone;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.TapTargetCost;
import mage.abilities.effects.common.UntapTargetEffect;
import mage.cards.CardImpl;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.CardTypePredicate;
import mage.filter.predicate.permanent.TappedPredicate;
import mage.target.common.TargetArtifactPermanent;
import mage.target.common.TargetControlledPermanent;

/**
 *
 * @author North
 */
public class ClockOfOmens extends CardImpl<ClockOfOmens> {

    private static final FilterControlledPermanent filter = new FilterControlledPermanent("untapped artifacts you control");

    static {
        filter.add(new CardTypePredicate(CardType.ARTIFACT));
        filter.add(Predicates.not(new TappedPredicate()));
    }

    public ClockOfOmens(UUID ownerId) {
        super(ownerId, 202, "Clock of Omens", Rarity.UNCOMMON, new CardType[]{CardType.ARTIFACT}, "{4}");
        this.expansionSetCode = "M13";

        // Tap two untapped artifacts you control: Untap target artifact.
        SimpleActivatedAbility ability = new SimpleActivatedAbility(Zone.BATTLEFIELD,
                new UntapTargetEffect(),
                new TapTargetCost(new TargetControlledPermanent(2, 2, filter, true)));
        ability.addTarget(new TargetArtifactPermanent());
        this.addAbility(ability);
    }

    public ClockOfOmens(final ClockOfOmens card) {
        super(card);
    }

    @Override
    public ClockOfOmens copy() {
        return new ClockOfOmens(this);
    }
}
