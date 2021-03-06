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

package mage.abilities.keyword;

import java.io.ObjectStreamException;
import mage.Constants.Zone;
import mage.abilities.MageSingleton;
import mage.abilities.StaticAbility;


/**
 * October 1, 2012
 * 702.71. Changeling
 *  702.71a Changeling is a characteristic-defining ability. "Changeling" means "This object
 *  is every creature type." This ability works everywhere, even outside the game. See rule 604.3.
 *  702.71b Multiple instances of changeling on the same object are redundant.
 *
 * @author nantuko
 */
public class ChangelingAbility extends StaticAbility<ChangelingAbility> implements MageSingleton {

    private static final ChangelingAbility fINSTANCE =  new ChangelingAbility();

    private Object readResolve() throws ObjectStreamException {
        return fINSTANCE;
    }

    public static ChangelingAbility getInstance() {
        return fINSTANCE;
    }

    private ChangelingAbility() {
        super(Zone.BATTLEFIELD, null);
    }

    @Override
    public String getRule() {
        return "Changeling <i>(This card is every creature type at all times.)<i/>";
    }

    @Override
    public ChangelingAbility copy() {
        return fINSTANCE;
    }
}
