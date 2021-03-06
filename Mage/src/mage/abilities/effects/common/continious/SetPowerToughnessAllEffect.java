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

package mage.abilities.effects.common.continious;

import mage.Constants.Duration;
import mage.Constants.Layer;
import mage.Constants.Outcome;
import mage.Constants.SubLayer;
import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.StaticValue;
import mage.abilities.effects.ContinuousEffectImpl;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.filter.common.FilterCreaturePermanent;
import mage.game.Game;
import mage.game.permanent.Permanent;

/**
 *
 * @author LevelX2
 */
public class SetPowerToughnessAllEffect extends ContinuousEffectImpl<SetPowerToughnessAllEffect> {

    private FilterPermanent filter;
    private DynamicValue power;
    private DynamicValue toughness;
    private boolean lockedIn;

    public SetPowerToughnessAllEffect(int power, int toughness, Duration duration) {
        this(new StaticValue(power), new StaticValue(toughness), duration, new FilterCreaturePermanent("Creatures"), true);
    }

    public SetPowerToughnessAllEffect(int power, int toughness, Duration duration, FilterPermanent filter, boolean lockedIn) {
        this(new StaticValue(power), new StaticValue(toughness), duration, filter, lockedIn);
    }

    public SetPowerToughnessAllEffect(DynamicValue power, DynamicValue toughness, Duration duration, FilterPermanent filter, boolean lockedIn) {
        super(duration, Layer.PTChangingEffects_7, SubLayer.SetPT_7b, Outcome.BoostCreature);
        this.power = power;
        this.toughness = toughness;
        this.filter = filter;
        this.lockedIn = lockedIn;
    }

    public SetPowerToughnessAllEffect(final SetPowerToughnessAllEffect effect) {
        super(effect);
        this.power = effect.power;
        this.toughness = effect.toughness;
        this.filter = effect.filter;
        this.lockedIn = effect.lockedIn;
    }

    @Override
    public SetPowerToughnessAllEffect copy() {
        return new SetPowerToughnessAllEffect(this);
    }

    @Override
    public void init(Ability source, Game game) {
        super.init(source, game);
        for (Permanent perm: game.getBattlefield().getActivePermanents(new FilterControlledCreaturePermanent(), source.getControllerId(), source.getSourceId(), game)) {
            objects.add(perm.getId());
        }
        if (lockedIn) {
            power = new StaticValue(power.calculate(game, source));
            toughness = new StaticValue(toughness.calculate(game, source));
        }
    }

    @Override
    public boolean apply(Game game, Ability source) {
        int newPower = power.calculate(game, source);
        int newToughness = toughness.calculate(game, source);
        for (Permanent permanent: game.getBattlefield().getActivePermanents(filter, source.getControllerId(), source.getSourceId(), game)) {
            if(objects.contains(permanent.getId())){
                permanent.getPower().setValue(newPower);
                permanent.getToughness().setValue(newToughness);
            }
        }
        return true;
    }

    @Override
    public String getText(Mode mode) {
        StringBuilder sb = new StringBuilder();
        sb.append(filter.getMessage());
        if (filter.getMessage().startsWith("Each ")) {
            sb.append(" becomes ");
        } else {
            sb.append(" become ");
        }
        sb.append(power).append("/").append(toughness);
        if (!duration.toString().isEmpty()) {
            sb.append(" ").append(duration.toString());
        }
        return sb.toString();
    }
}
