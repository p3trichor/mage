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

package mage.filter.common;

import mage.game.Game;
import mage.game.permanent.Permanent;

import java.util.UUID;

/**
 *
 * @author noxx
 */
public class FilterNotPairedControlledCreaturePermanent extends FilterControlledCreaturePermanent<FilterNotPairedControlledCreaturePermanent> {

	public FilterNotPairedControlledCreaturePermanent() {
		this("not paired creature you control");
	}

	public FilterNotPairedControlledCreaturePermanent(String name) {
		super(name);
	}

	public FilterNotPairedControlledCreaturePermanent(final FilterNotPairedControlledCreaturePermanent filter) {
		super(filter);
	}

    @Override
    public boolean match(Permanent permanent) {
        if (!super.match(permanent))
            return notFilter;

        if (permanent.getPairedCard() != null)
            return notFilter;

        return !notFilter;
    }

    @Override
    public boolean match(Permanent permanent, UUID sourceId, UUID playerId, Game game) {
        if (!super.match(permanent, sourceId, playerId, game))
            return notFilter;

        if (permanent.getPairedCard() != null)
            return notFilter;

        return !notFilter;
    }

	@Override
	public FilterNotPairedControlledCreaturePermanent copy() {
		return new FilterNotPairedControlledCreaturePermanent(this);
	}

}