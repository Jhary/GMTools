package com.arduin.wurm.gmtools.actions;

import com.arduin.wurm.gmtools.handlers.ActionHandler;

import com.arduin.wurm.gmtools.utils.StringsENG;
import com.wurmonline.server.behaviours.ActionEntry;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.items.Item;
import org.gotti.wurmunlimited.modsupport.actions.BehaviourProvider;
import org.gotti.wurmunlimited.modsupport.actions.ModAction;

import java.util.ArrayList;
import java.util.List;


public class ServerToolsBehaviour implements BehaviourProvider, ModAction {


    private List<ActionEntry> getMyBehaviours(Creature performer, Item activated, Item target, int tilex, int tiley, boolean onSurface, int tile) {

        short menuEntries = (short) (ActionHandler.modActions.size() * (-1));
        List<ActionEntry> list = new ArrayList<>();


        if (menuEntries!=0 && performer.getPower() > 1 && activated != null && activated.getTemplateId() == 176){
            list.add(0, new ActionEntry(menuEntries, StringsENG.ACTION_CATEGORY, StringsENG.ACTION_CATEGORY));

            for(ModActionTemplate modAction : ActionHandler.modActions){
                list.add(modAction.getActionEntry());
            }
        }

        if (list.isEmpty())
            list = null;

        return list;
    }

    @Override
    public List<ActionEntry> getBehavioursFor(Creature performer, Item activated, Item target) {
        return getMyBehaviours(performer, activated, target, 0, 0, false, 0);
    }

    @Override
    public List<ActionEntry> getBehavioursFor(Creature performer, Item target) {
        return getMyBehaviours(performer, null, target, 0, 0, false, 0);
    }

    @Override
    public List<ActionEntry> getBehavioursFor(Creature performer, Item activated, int tilex, int tiley, boolean onSurface, int tile) {
        return getMyBehaviours(performer, activated, null, tilex, tiley, onSurface, tile);
    }

}
