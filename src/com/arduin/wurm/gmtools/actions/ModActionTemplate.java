package com.arduin.wurm.gmtools.actions;

import com.wurmonline.server.behaviours.Action;
import com.wurmonline.server.behaviours.ActionEntry;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.items.Item;
import org.gotti.wurmunlimited.modsupport.actions.ActionPerformer;
import org.gotti.wurmunlimited.modsupport.actions.ModAction;
import org.gotti.wurmunlimited.modsupport.actions.ModActions;

public abstract class ModActionTemplate implements ModAction, ActionPerformer {


    private final short actionId;
    private final ActionEntry actionEntry;
    private final int[] emptyTypeArray = new int[0];


    ModActionTemplate(String entryName){
        actionId = (short) ModActions.getNextActionId();
        actionEntry = ActionEntry.createEntry(actionId, entryName, entryName, emptyTypeArray);
        ModActions.registerAction(actionEntry);
    }


    public ActionEntry getActionEntry() {
        return actionEntry;
    }


    @Override
    public short getActionId() {
        return actionId;
    }

    boolean performMyAction(Creature performer, Item activated) {
        if (performer.getPower() <= 2 || activated == null || activated.getTemplateId() != 176)
            return true;
        else
            doAction(performer);
        return true;
    }


    abstract void doAction(Creature performer);


    @Override
    public boolean action(Action action, Creature performer, Item activated, Item target, short num, float counter) {
        return performMyAction(performer, activated);
    }


    @Override
    public boolean action(Action action, Creature performer, Item activated, Creature target, short num, float counter) {
        return performMyAction(performer, activated);
    }


    @Override
    public boolean action(Action action, Creature performer, Item activated, int tilex, int tiley, boolean onSurface, int heightOffset, int tile, short num, float counter) {
        return performMyAction(performer, activated);
    }
}
