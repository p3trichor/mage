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

package mage.view;

import mage.Constants;
import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.MageObject;
import mage.ObjectColor;
import mage.cards.Card;
import mage.counters.CounterType;
import mage.game.permanent.Permanent;
import mage.game.permanent.PermanentToken;
import mage.game.permanent.token.Token;
import mage.game.stack.StackAbility;
import mage.target.Target;
import mage.target.Targets;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import mage.abilities.Mode;
import mage.abilities.costs.mana.ManaCosts;
import mage.cards.SplitCard;
import mage.counters.Counter;
import mage.game.stack.Spell;

/**
 * @author BetaSteward_at_googlemail.com
 */
public class CardView extends SimpleCardView {
    private static final long serialVersionUID = 1L;

    protected UUID parentId;
    protected String name;
    protected String displayName;
    protected List<String> rules;
    protected String power;
    protected String toughness;
    protected String loyalty;
    protected List<CardType> cardTypes;
    protected List<String> subTypes;
    protected List<String> superTypes;
    protected ObjectColor color;
    protected List<String> manaCost;
    protected int convertedManaCost;
    protected Rarity rarity;
    protected boolean isAbility;
    protected CardView ability;
    protected int type;

    protected boolean canTransform;
    protected CardView secondCardFace;
    protected boolean transformed;

    protected boolean isSplitCard;
    protected String leftSplitName;
    protected ManaCosts leftSplitCosts;
    protected List<String> leftSplitRules;
    protected String rightSplitName;
    protected ManaCosts rightSplitCosts;
    protected List<String> rightSplitRules;

    protected List<UUID> targets;

    protected UUID pairedCard;
    protected boolean paid;
    protected List<CounterView> counters;

    public CardView(Card card, UUID cardId) {
        this(card);
        this.id = cardId;
    }

    public CardView(Card card) {
        super(card.getId(), card.getExpansionSetCode(), card.getCardNumber(), card.isFaceDown(), card.getUsesVariousArt());

        // no information available for face down cards
        if (this.faceDown) {
            fillEmpty();
            return;
        }
        Card cardHalf = null;
        SplitCard splitCard = null;
        this.isSplitCard = card.isSplitCard();
        if (card instanceof Spell) {
            if (((Spell) card).getSpellAbility().getSpellAbilityType().equals(Constants.SpellAbilityType.SPLIT_LEFT)) {
                splitCard = (SplitCard) ((Spell) card).getCard();
                cardHalf = ((SplitCard) splitCard).getLeftHalfCard();
            } else if (((Spell) card).getSpellAbility().getSpellAbilityType().equals(Constants.SpellAbilityType.SPLIT_RIGHT)) {
                splitCard = (SplitCard) ((Spell) card).getCard();
                cardHalf = ((SplitCard) splitCard).getRightHalfCard();
            } else if (((Spell) card).getSpellAbility().getSpellAbilityType().equals(Constants.SpellAbilityType.SPLIT_FUSED)) {
                isSplitCard = true;
                splitCard = (SplitCard) ((Spell) card).getCard();
            }
        } else if (card.isSplitCard()) {
            splitCard = (SplitCard) card;
        }
        if (this.isSplitCard && splitCard != null) {
            leftSplitName = splitCard.getLeftHalfCard().getName();
            leftSplitCosts = splitCard.getLeftHalfCard().getManaCost();
            leftSplitRules = splitCard.getLeftHalfCard().getRules();
            rightSplitName = splitCard.getRightHalfCard().getName();
            rightSplitCosts = splitCard.getRightHalfCard().getManaCost();
            rightSplitRules = splitCard.getRightHalfCard().getRules();
        }

        this.name = card.getName();
        if (cardHalf != null) {
            this.displayName = cardHalf.getName();
            this.rules = cardHalf.getRules();
            this.manaCost = cardHalf.getManaCost().getSymbols();
            this.convertedManaCost = cardHalf.getManaCost().convertedManaCost();
        } else {
            this.displayName = card.getName();
            this.rules = card.getRules();
            this.manaCost = card.getManaCost().getSymbols();
            this.convertedManaCost = card.getManaCost().convertedManaCost();
        }
        if (card instanceof Permanent) {
            Permanent permanent = (Permanent)card;
            this.power = Integer.toString(card.getPower().getValue());
            this.toughness = Integer.toString(card.getToughness().getValue());
            this.loyalty = Integer.toString(permanent.getCounters().getCount(CounterType.LOYALTY));
            this.pairedCard = permanent.getPairedCard();
        } else {
            this.power = card.getPower().toString();
            this.toughness = card.getToughness().toString();
            this.loyalty = "";
        }
        this.cardTypes = card.getCardType();
        this.subTypes = card.getSubtype();
        this.superTypes = card.getSupertype();
        this.color = card.getColor();
        this.canTransform = card.canTransform();


        if (card instanceof PermanentToken) {
            this.rarity = Rarity.COMMON;
            this.expansionSetCode = ((PermanentToken) card).getExpansionSetCode();
            this.rules = ((PermanentToken) card).getRules();
            this.type = ((PermanentToken)card).getToken().getTokenType();
        } else {
            this.rarity = card.getRarity();
        }
        if (card.getCounters() != null && !card.getCounters().isEmpty()) {
            counters = new ArrayList<CounterView>();
            for (Counter counter: card.getCounters().values()) {
                counters.add(new CounterView(counter));
            }
        }
        if (card.getSecondCardFace() != null) {
            this.secondCardFace = new CardView(card.getSecondCardFace());
        }

        if (card instanceof Spell) {
            Spell<?> spell = (Spell<?>) card;
            if (spell.getSpellAbility().getTargets().size() > 0) {
                setTargets(spell.getSpellAbility().getTargets());
            }
            // show for modal spell, which mode was choosen
            if (spell.getSpellAbility().isModal()) {
                Mode activeMode = spell.getSpellAbility().getModes().getMode();
                if (activeMode != null) {
                    this.rules.add("<span color='green'><i>Chosen mode: " + activeMode.getEffects().getText(activeMode)+"</i></span>");
                }
            }
        }
        
    }

    public CardView(MageObject card) {
        super(card.getId(), "", 0, false, false);
        this.name = card.getName();
        if (card instanceof Permanent) {
            this.power = Integer.toString(card.getPower().getValue());
            this.toughness = Integer.toString(card.getToughness().getValue());
            this.loyalty = Integer.toString(((Permanent) card).getCounters().getCount(CounterType.LOYALTY));
        } else {
            this.power = card.getPower().toString();
            this.toughness = card.getToughness().toString();
            this.loyalty = "";
        }
        this.cardTypes = card.getCardType();
        this.subTypes = card.getSubtype();
        this.superTypes = card.getSupertype();
        this.color = card.getColor();
        this.manaCost = card.getManaCost().getSymbols();
        this.convertedManaCost = card.getManaCost().convertedManaCost();

        if (card instanceof PermanentToken) {
            PermanentToken permanentToken = (PermanentToken) card;
            this.rarity = Rarity.COMMON;
            this.expansionSetCode = permanentToken.getExpansionSetCode();
            this.rules = permanentToken.getRules();
            this.type = permanentToken.getToken().getTokenType();
        }
        if (this.rarity == null && card instanceof StackAbility) {
            StackAbility stackAbility = (StackAbility)card;
            this.rarity = Rarity.NA;
            this.rules = new ArrayList<String>();
            this.rules.add(stackAbility.getRule());
            if (stackAbility.getZone().equals(Constants.Zone.COMMAND)) {
                this.expansionSetCode = stackAbility.getExpansionSetCode();
            }
        }
    }

    protected CardView() {
        super(null, "", 0, false, false);
    }

    public CardView(boolean empty) {
        super(null, "", 0, false, false);
        if (!empty) {
            throw new IllegalArgumentException("Not supported.");
        }
        fillEmpty();
    }

    public CardView(String name) {
        this(true);
        this.name = name;
    }

    private void fillEmpty() {
        this.name = "Face Down";
        this.rules = new ArrayList<String>();
        this.power = "";
        this.toughness = "";
        this.loyalty = "";
        this.cardTypes = new ArrayList<CardType>();
        this.subTypes = new ArrayList<String>();
        this.superTypes = new ArrayList<String>();
        this.color = new ObjectColor();
        this.manaCost = new ArrayList<String>();
        this.convertedManaCost = 0;
        this.rarity = Rarity.COMMON;
        this.expansionSetCode = "";
        this.cardNumber = 0;
    }

    CardView(Token token) {
        super(token.getId(), "", 0, false, false);
        this.id = token.getId();
        this.name = token.getName();
        this.rules = token.getAbilities().getRules(this.name);
        this.power = token.getPower().toString();
        this.toughness = token.getToughness().toString();
        this.loyalty = "";
        this.cardTypes = token.getCardType();
        this.subTypes = token.getSubtype();
        this.superTypes = token.getSupertype();
        this.color = token.getColor();
        this.manaCost = token.getManaCost().getSymbols();
        this.rarity = Rarity.NA;
        this.type = token.getTokenType();
    }

    protected final void setTargets(Targets targets) {
        for (Target target : targets) {
            if (target.isChosen()) {
                for (UUID targetUUID : target.getTargets()) {
                    if (this.targets == null) {
                        this.targets = new ArrayList<UUID>();
                    }
                    this.targets.add(targetUUID);
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<String> getRules() {
        return rules;
    }

    public void overrideRules(List<String> rules) {
        this.rules = rules;
    }

    public void setIsAbility(boolean isAbility) {
        this.isAbility = isAbility;
    }

    public boolean isAbility() {
        return isAbility;
    }

    public String getPower() {
        return power;
    }

    public String getToughness() {
        return toughness;
    }

    public String getLoyalty() {
        return loyalty;
    }

    public List<CardType> getCardTypes() {
        return cardTypes;
    }

    public List<String> getSubTypes() {
        return subTypes;
    }

    public List<String> getSuperTypes() {
        return superTypes;
    }

    public ObjectColor getColor() {
        return color;
    }

    public List<String> getManaCost() {
        return manaCost;
    }

    public int getConvertedManaCost() {
        return convertedManaCost;
    }

    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public String getExpansionSetCode() {
        return expansionSetCode;
    }
    
    public void setExpansionSetCode(String expansionSetCode) {
        this.expansionSetCode = expansionSetCode;
    }
    
    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * Returns UUIDs for targets.
     * Can be null if there is no target selected.
     *
     * @return
     */
    public List<UUID> getTargets() {
        return targets;
    }

    public void overrideTargets(List<UUID> newTargets) {
        this.targets = newTargets;
    }

    public void overrideId(UUID id) {
        if (parentId == null) {
            parentId = this.id;
        }
        this.id = id;
    }

    public UUID getParentId() {
        if (parentId != null) {
            return parentId;
        }
        return id;
    }

    public void setAbility(CardView ability) {
        this.ability = ability;
    }

    public CardView getAbility() {
        return this.ability;
    }

    @Override
    public String toString() {
        return getName() + " [" + getId() + "]";
    }

    @Override
    public boolean isFaceDown() {
        return faceDown;
    }

    public boolean canTransform() {
        return this.canTransform;
    }

    public boolean isSplitCard() {
        return this.isSplitCard;
    }

    public String getLeftSplitName() {
        return leftSplitName;
    }

    public ManaCosts getLeftSplitCosts() {
        return leftSplitCosts;
    }

    public List<String> getLeftSplitRules() {
        return leftSplitRules;
    }

    public String getRightSplitName() {
        return rightSplitName;
    }

    public ManaCosts getRightSplitCosts() {
        return rightSplitCosts;
    }

    public List<String> getRightSplitRules() {
        return rightSplitRules;
    }

    public CardView getSecondCardFace() {
        return this.secondCardFace;
    }

    public void setTransformed(boolean transformed) {
        this.transformed = transformed;
    }

    public boolean isTransformed() {
        return this.transformed;
    }

    public UUID getPairedCard() {
        return pairedCard;
    }

    public int getType() {
        return type;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public List<CounterView> getCounters() {
        return counters;
    }
}
