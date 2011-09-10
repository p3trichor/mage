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
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.cards.CardImpl;
import mage.filter.Filter;
import mage.filter.common.FilterAttackingCreature;
import mage.target.common.TargetAttackingCreature;

/**
 *
 * @author Loki
 */
public class TerashisVerdict extends CardImpl<TerashisVerdict> {

    private final static FilterAttackingCreature filter = new FilterAttackingCreature("attacking creature with power 3 or less");

    static {
        filter.setPower(4);
        filter.setPowerComparison(Filter.ComparisonType.LessThan);
    }

    public TerashisVerdict(UUID ownerId) {
        super(ownerId, 27, "Terashi's Verdict", Rarity.UNCOMMON, new CardType[]{CardType.INSTANT}, "{1}{W}");
        this.expansionSetCode = "BOK";
        this.subtype.add("Arcane");
        this.color.setWhite(true);
        // Destroy target attacking creature with power 3 or less.
        this.getSpellAbility().addEffect(new DestroyTargetEffect());
        this.getSpellAbility().addTarget(new TargetAttackingCreature(1, 1, filter, false));
    }

    public TerashisVerdict(final TerashisVerdict card) {
        super(card);
    }

    @Override
    public TerashisVerdict copy() {
        return new TerashisVerdict(this);
    }
}