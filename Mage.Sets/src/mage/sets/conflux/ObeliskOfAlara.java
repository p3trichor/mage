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
package mage.sets.conflux;

import java.util.UUID;
import mage.Constants.CardType;
import mage.Constants.Duration;
import mage.Constants.Rarity;
import mage.Constants.Zone;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.DrawDiscardControllerEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.continious.BoostTargetEffect;
import mage.cards.CardImpl;
import mage.target.TargetPlayer;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author North
 */
public class ObeliskOfAlara extends CardImpl<ObeliskOfAlara> {

    public ObeliskOfAlara(UUID ownerId) {
        super(ownerId, 140, "Obelisk of Alara", Rarity.RARE, new CardType[]{CardType.ARTIFACT}, "{6}");
        this.expansionSetCode = "CON";

        // {1}{W}, {tap}: You gain 5 life.
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new GainLifeEffect(5), new ManaCostsImpl("{1}{W}"));
        ability.addCost(new TapSourceCost());
        this.addAbility(ability);
        // {1}{U}, {tap}: Draw a card, then discard a card.
        ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new DrawDiscardControllerEffect(), new ManaCostsImpl("{1}{U}"));
        ability.addCost(new TapSourceCost());
        this.addAbility(ability);
        // {1}{B}, {tap}: Target creature gets -2/-2 until end of turn.
        ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new BoostTargetEffect(-2, -2, Duration.EndOfTurn), new ManaCostsImpl("{1}{B}"));
        ability.addCost(new TapSourceCost());
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability);
        // {1}{R}, {tap}: Obelisk of Alara deals 3 damage to target player.
        ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new DamageTargetEffect(3), new ManaCostsImpl("{1}{R}"));
        ability.addCost(new TapSourceCost());
        ability.addTarget(new TargetPlayer());
        this.addAbility(ability);
        // {1}{G}, {tap}: Target creature gets +4/+4 until end of turn.
        ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new BoostTargetEffect(4, 4, Duration.EndOfTurn), new ManaCostsImpl("{1}{G}"));
        ability.addCost(new TapSourceCost());
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability);
    }

    public ObeliskOfAlara(final ObeliskOfAlara card) {
        super(card);
    }

    @Override
    public ObeliskOfAlara copy() {
        return new ObeliskOfAlara(this);
    }
}
