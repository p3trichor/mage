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
package mage.sets.championsofkamigawa;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import mage.Constants;
import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.abilities.Ability;
import mage.abilities.ActivatedAbility;
import mage.abilities.effects.common.CounterTargetEffect;
import mage.abilities.effects.common.DrawCardControllerEffect;
import mage.cards.CardImpl;
import mage.filter.Filter;
import mage.filter.FilterAbility;
import mage.game.Game;
import mage.game.stack.StackAbility;
import mage.game.stack.StackObject;
import mage.target.TargetObject;

/**
 *
 * @author LevelX2
 */
public class Squelch extends CardImpl<Squelch> {

    public Squelch(UUID ownerId) {
        super(ownerId, 92, "Squelch", Rarity.UNCOMMON, new CardType[]{CardType.INSTANT}, "{1}{U}");
        this.expansionSetCode = "CHK";

        this.color.setBlue(true);

        // Counter target activated ability.
        this.getSpellAbility().addEffect(new CounterTargetEffect());
        this.getSpellAbility().addTarget(new ActivatedAbilityTarget());
        // Draw a card.
        this.getSpellAbility().addEffect(new DrawCardControllerEffect(1));
    }

    public Squelch(final Squelch card) {
        super(card);
    }

    @Override
    public Squelch copy() {
        return new Squelch(this);
    }
}

class ActivatedAbilityTarget extends TargetObject<ActivatedAbilityTarget> {

    public ActivatedAbilityTarget() {
        this.minNumberOfTargets = 1;
        this.maxNumberOfTargets = 1;
        this.zone = Constants.Zone.STACK;
        this.targetName = "activated ability";
    }

    public ActivatedAbilityTarget(final ActivatedAbilityTarget target) {
        super(target);
    }


    @Override
    public boolean canTarget(UUID id, Ability source, Game game) {
        if (source != null && source.getId().equals(id)) {
            return false;
        }

        StackObject stackObject = game.getStack().getStackObject(id);
        if (stackObject.getStackAbility() != null && (stackObject.getStackAbility() instanceof ActivatedAbility)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canChoose(UUID sourceId, UUID sourceControllerId, Game game) {
        return canChoose(sourceControllerId, game);
    }

    @Override
    public boolean canChoose(UUID sourceControllerId, Game game) {
        for (StackObject stackObject :  game.getStack()) {
            if (stackObject.getStackAbility() != null && (stackObject.getStackAbility() instanceof ActivatedAbility) && game.getPlayer(sourceControllerId).getInRange().contains(stackObject.getStackAbility().getControllerId())) {
                    return true;
                }
            }
        return false;
    }

    @Override
    public Set<UUID> possibleTargets(UUID sourceId, UUID sourceControllerId, Game game) {
        return possibleTargets(sourceControllerId, game);
    }

    @Override
    public Set<UUID> possibleTargets(UUID sourceControllerId, Game game) {
        Set<UUID> possibleTargets = new HashSet<UUID>();
        for (StackObject stackObject :  game.getStack()) {
            if (stackObject.getStackAbility() != null && (stackObject.getStackAbility() instanceof ActivatedAbility) && game.getPlayer(sourceControllerId).getInRange().contains(stackObject.getStackAbility().getControllerId())) {
                possibleTargets.add(stackObject.getStackAbility().getId());
            }
        }
        return possibleTargets;
    }

    @Override
    public ActivatedAbilityTarget copy() {
        return new ActivatedAbilityTarget(this);
    }

    @Override
    public Filter getFilter() {
        return new FilterAbility();
    }
    
    @Override
    public String getTargetedName(Game game) {
        StringBuilder sb = new StringBuilder("activated ability (");
        for (UUID targetId: getTargets()) {
            StackAbility object = (StackAbility) game.getObject(targetId);
            if (object != null) {
                sb.append(object.getRule()).append(" ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

}
