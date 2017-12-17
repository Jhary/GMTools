package com.arduin.wurm.gmtools.actions;

import com.arduin.wurm.gmtools.handlers.PropertyHandler;
import com.arduin.wurm.gmtools.utils.Utils;

import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.players.PlayerInfo;
import com.wurmonline.server.players.PlayerInfoFactory;
import com.wurmonline.server.villages.Village;
import com.wurmonline.server.villages.Villages;


public class ActionCheckInactivePlayers extends ModActionTemplate{


    public ActionCheckInactivePlayers(String entryName){
        super(entryName);
    }


    @SuppressWarnings("Duplicates")
    @Override
    void doAction(Creature performer){

        Utils.chatMessage(" ", performer);
        Utils.chatMessage("Searching for inactive players (days offline > "+ PropertyHandler.inactiveDaysThreshold+")...", performer);
        boolean found=false;
        for(PlayerInfo info : PlayerInfoFactory.getPlayerInfos()){

            int daysInactive = (int) ((System.currentTimeMillis()-info.getLastLogout()) / (1000*60*60*24));

            if(daysInactive > PropertyHandler.inactiveDaysThreshold){

                int daysPlayed = (int) (info.playingTime / (1000*60*60*24));
                String deed = "";
                for(Village village : Villages.getVillages()){
                    if(info.getName().equals(village.getMayor().getName())){
                        deed = village.getName();
                    }
                }

                if(PropertyHandler.onlyMayors && !deed.equals("")){
                    found = true;
                    Utils.chatMessage("-------------------------------------------------------------", performer);
                    Utils.chatMessage("Name: " + info.getName(), performer);
                    Utils.chatMessage("Days played: " + daysPlayed, performer);
                    Utils.chatMessage("DaysInactive: " + daysInactive, performer);
                    Utils.chatMessage("Mayor of: " + deed, performer);
                }else if(!PropertyHandler.onlyMayors){
                    found = true;
                    Utils.chatMessage("-------------------------------------------------------------", performer);
                    Utils.chatMessage("Name: " + info.getName(), performer);
                    Utils.chatMessage("Days played: " + daysPlayed, performer);
                    Utils.chatMessage("DaysInactive: " + daysInactive, performer);
                    Utils.chatMessage("Mayor of: " + deed, performer);
                }

            }

        }

        if(!found) Utils.chatMessage("There were no inactive players", performer);

    }

}
