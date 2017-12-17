package com.arduin.wurm.gmtools.actions;

import com.arduin.wurm.gmtools.utils.Utils;

import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.players.PlayerInfo;
import com.wurmonline.server.players.PlayerInfoFactory;

import java.util.ArrayList;


public class ActionCheckForSameIP extends ModActionTemplate {


    public ActionCheckForSameIP(String entryName) {
        super(entryName);
    }

    @Override
    void doAction(Creature performer){

        ArrayList<PlayerInfo> onlinePlayers = new ArrayList<>();
        ArrayList<String> foundClones = new ArrayList<>();

        for(PlayerInfo info : PlayerInfoFactory.getPlayerInfos()){
            if(info.isOnlineHere()){
                onlinePlayers.add(info);
            }
        }

        for(PlayerInfo currentPlayer : onlinePlayers){
            if(!foundClones.contains(currentPlayer.getName())){
                for(PlayerInfo otherPlayer : onlinePlayers){
                    if(!currentPlayer.getName().equals(otherPlayer.getName()) && currentPlayer.getIpaddress().equals(otherPlayer.getIpaddress())){
                        foundClones.add(otherPlayer.getName());
                        Utils.chatMessage("Same IP detected: "+currentPlayer.getName() + " and "+ otherPlayer.getName() +" got the same IP Address! ("+currentPlayer.getIpaddress()+")", performer);
                    }
                }
            }
        }

        if(foundClones.isEmpty()){
            Utils.chatMessage("No players with same IP found", performer);
        }

    }


}
