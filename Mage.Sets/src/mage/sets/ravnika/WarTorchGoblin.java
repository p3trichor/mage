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
package mage.sets.ravnika;

import java.util.UUID;

import mage.Constants;
import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.mana.ColoredManaCost;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.cards.CardImpl;
import mage.filter.common.FilterBlockingCreature;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author Loki
 */
public class WarTorchGoblin extends CardImpl<WarTorchGoblin> {

    public WarTorchGoblin(UUID ownerId) {
        super(ownerId, 151, "War-Torch Goblin", Rarity.COMMON, new CardType[]{CardType.CREATURE}, "{R}");
        this.expansionSetCode = "RAV";
        this.subtype.add("Goblin");
        this.subtype.add("Warrior");

        this.color.setRed(true);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // {R}, Sacrifice War-Torch Goblin: War-Torch Goblin deals 2 damage to target blocking creature.
        Ability ability = new SimpleActivatedAbility(Constants.Zone.BATTLEFIELD, new DamageTargetEffect(2), new ColoredManaCost(Constants.ColoredManaSymbol.R));
        ability.addCost(new SacrificeSourceCost());
        ability.addTarget(new TargetCreaturePermanent(new FilterBlockingCreature()));
        this.addAbility(ability);
    }

    public WarTorchGoblin(final WarTorchGoblin card) {
        super(card);
    }

    @Override
    public WarTorchGoblin copy() {
        return new WarTorchGoblin(this);
    }
}