package com.arduin.wurm.gmtools.handlers;

import java.util.ArrayList;

import com.arduin.wurm.gmtools.utils.StringsENG;
import org.gotti.wurmunlimited.modsupport.actions.ModActions;

import com.arduin.wurm.gmtools.actions.*;


public class ActionHandler {

    public static ArrayList<ModActionTemplate> modActions = new ArrayList<>();

    public static void init(){
        if(PropertyHandler.checkDeedRules)
            modActions.add(new ActionCheckVillageRules(StringsENG.ACTION_NAME_CHECK_VILLAGE_RULES));

        if(PropertyHandler.checkInactivePlayers)
            modActions.add(new ActionCheckInactivePlayers(StringsENG.ACTION_NAME_CHECK_INACTIVE_PLAYERS));

        if(PropertyHandler.checkEconomy)
            modActions.add(new ActionEconomy(StringsENG.ACTION_NAME_ECONOMY_OVERVIEW));

        if(PropertyHandler.checkSameIP)
            modActions.add(new ActionCheckForSameIP(StringsENG.ACTION_NAME_CHECK_SAME_IP));

        if(!modActions.isEmpty()){
            for (ModActionTemplate action : modActions) {
                ModActions.registerAction(action);
            }
        }

        ModActions.registerAction(new ServerToolsBehaviour());
    }

}
