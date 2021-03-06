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

package mage.abilities.costs.mana;

import mage.Mana;
import mage.abilities.Ability;
import mage.abilities.costs.VariableCost;
import mage.filter.FilterMana;
import mage.game.Game;
import mage.players.ManaPool;

/**
 *
 * @author BetaSteward_at_googlemail.com
 */
public class VariableManaCost extends ManaCostImpl<VariableManaCost> implements VariableCost {

    protected int multiplier;
    protected FilterMana filter;
    protected int minX = 0;

    public VariableManaCost() {
        this(1);
    }

    public VariableManaCost(int multiplier) {
        this.multiplier = multiplier;
        this.cost = new Mana();
        options.add(new Mana());
    }

    public VariableManaCost(VariableManaCost manaCost) {
        super(manaCost);
        this.multiplier = manaCost.multiplier;
        if (manaCost.filter != null) {
            this.filter = manaCost.filter.copy();
        }
        this.minX = manaCost.minX;
    }

    @Override
    public int convertedManaCost() {
        return 0;
    }

    @Override
    public void assignPayment(Game game, Ability ability, ManaPool pool) {
        payment.add(pool.getMana(filter));
        payment.add(pool.getAllConditionalMana(ability, game, filter));
        pool.payX(ability, game, filter);
    }

    @Override
    public String getText() {
        if (multiplier > 1) {
            String symbol = "";
            for (int i = 0; i < multiplier; i++) {
                symbol += "{X}";
            }
            return symbol;
        }
        else {
            return "{X}";
        }
    }

    @Override
    public VariableManaCost getUnpaid() {
        return this;
    }

    @Override
    public int getAmount() {
        return payment.count() / multiplier;
    }

    @Override
    public void setAmount(int amount) {
        payment.setColorless(amount);
    }

    @Override
    public boolean testPay(Mana testMana) {
        return true;
    }

    @Override
    public void setFilter(FilterMana filter) {
        this.filter = filter;
    }

    @Override
    public VariableManaCost copy() {
        return new VariableManaCost(this);
    }

    public int getMultiplier() {
       return multiplier;
    }

    public int getMinX() {
       return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }
}
