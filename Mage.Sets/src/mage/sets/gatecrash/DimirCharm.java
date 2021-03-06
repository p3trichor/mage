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
package mage.sets.gatecrash;

import java.util.UUID;
import mage.Constants;
import mage.Constants.CardType;
import mage.Constants.Outcome;
import mage.Constants.Rarity;
import mage.Constants.Zone;
import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CounterTargetEffect;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.Cards;
import mage.cards.CardsImpl;
import mage.filter.Filter;
import mage.filter.FilterCard;
import mage.filter.FilterSpell;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.CardTypePredicate;
import mage.filter.predicate.mageobject.PowerPredicate;
import mage.game.Game;
import mage.players.Player;
import mage.target.TargetCard;
import mage.target.TargetPlayer;
import mage.target.TargetSpell;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author Plopman
 */
public class DimirCharm extends CardImpl<DimirCharm> {
    
    private static final FilterSpell filterSorcery = new FilterSpell("sorcery spell");
    private static final FilterCreaturePermanent filterCreature = new FilterCreaturePermanent("creature with power 2 or less");
    
    static {
        filterSorcery.add(new CardTypePredicate(CardType.SORCERY));
        filterCreature.add(new PowerPredicate(Filter.ComparisonType.LessThan, 3));
    }

    public DimirCharm (UUID ownerId) {
        super(ownerId, 154, "Dimir Charm", Rarity.UNCOMMON, new CardType[]{CardType.INSTANT}, "{U}{B}");
        this.expansionSetCode = "GTC";

        this.color.setBlue(true);
        this.color.setBlack(true);

        //Choose one - Counter target sorcery spell
        this.getSpellAbility().addEffect(new CounterTargetEffect());
        this.getSpellAbility().addTarget(new TargetSpell(filterSorcery));
        //or destroy target creature with power 2 or less
        Mode mode = new Mode();
        mode.getEffects().add(new DestroyTargetEffect());
        mode.getTargets().add(new TargetCreaturePermanent(filterCreature));
        this.getSpellAbility().addMode(mode);
        //or look at the top three cards of target player's library, then put one back and the rest into that player's graveyard
        Mode mode2 = new Mode();
        mode2.getEffects().add(new DimirCharmEffect());
        mode2.getTargets().add(new TargetPlayer());
        this.getSpellAbility().addMode(mode2);
    }

    public DimirCharm(final DimirCharm card) {
        super(card);
    }

    @Override
    public DimirCharm  copy() {
        return new DimirCharm(this);
    }
}

class DimirCharmEffect extends OneShotEffect {


    public DimirCharmEffect() {
            super(Constants.Outcome.Benefit);
            
    }

    public DimirCharmEffect(final DimirCharmEffect effect) {
        super(effect);
    }

    @Override
    public DimirCharmEffect copy() {
        return new DimirCharmEffect(this);
    }
    
    
    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getFirstTarget());
        Player controller = game.getPlayer(source.getControllerId());
        if(player != null && controller != null){
            Cards cards = new CardsImpl();
            for(int i = 0; i < 3; i++){
                Card card = player.getLibrary().removeFromTop(game);
                if(card != null){
                    cards.add(card);
                    game.getState().setZone(card.getId(), Zone.PICK);
                }
            }
            
            if(cards.size() > 0){
                TargetCard target = new TargetCard(Zone.PICK, new FilterCard("Card to put back on top of library"));
                target.setRequired(true);
                if(controller.chooseTarget(Outcome.Benefit, cards, target, source, game)){
                    Card card = cards.get(target.getFirstTarget(), game);
                    if(card != null){
                        card.moveToZone(Zone.LIBRARY, source.getId(), game, true);
                        cards.remove(card);
                    }
                    for(Card card2 : cards.getCards(game)){
                        if(card2 != null){
                            card2.moveToZone(Zone.GRAVEYARD, source.getId(), game, true);
                        }
                    }
                }
            }
        }
        return false;
    }

    
  
    @Override
    public String getText(Mode mode) {
        return "look at the top three cards of target player's library, then put one back and the rest into that player's graveyard";
    }    

}
