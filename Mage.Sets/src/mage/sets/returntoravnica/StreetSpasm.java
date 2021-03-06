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
package mage.sets.returntoravnica;

import java.util.UUID;
import mage.Constants;
import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.dynamicvalue.common.ManacostVariableValue;
import mage.abilities.effects.common.DamageAllEffect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.OverloadAbility;
import mage.cards.CardImpl;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.AbilityPredicate;
import mage.filter.predicate.permanent.ControllerPredicate;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author LevelX2
 */
public class StreetSpasm extends CardImpl<StreetSpasm> {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("creature without flying you don't control");

    static {
        filter.add(new ControllerPredicate(Constants.TargetController.NOT_YOU));
        filter.add(Predicates.not(new AbilityPredicate(FlyingAbility.class)));
    }

    public StreetSpasm(UUID ownerId) {
        super(ownerId, 106, "Street Spasm", Rarity.UNCOMMON, new CardType[]{CardType.INSTANT}, "{X}{R}");
        this.expansionSetCode = "RTR";

        this.color.setRed(true);

        // Street Spasm deals X damage to target creature without flying you don't control.
        this.getSpellAbility().addTarget(new TargetCreaturePermanent(filter));
        this.getSpellAbility().addEffect(new DamageTargetEffect(new ManacostVariableValue()));

        // Overload {X}{X}{R}{R} (You may cast this spell for its overload cost. If you do, change its text by replacing all instances of "target" with "each.")
        this.addAbility(new OverloadAbility(this, new DamageAllEffect(new ManacostVariableValue(), filter), new ManaCostsImpl("{X}{X}{R}{R}")));
    }

    public StreetSpasm(final StreetSpasm card) {
        super(card);
    }

    @Override
    public StreetSpasm copy() {
        return new StreetSpasm(this);
    }
}