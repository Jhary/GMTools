package com.arduin.wurm.gmtools.actions;

import com.arduin.wurm.gmtools.handlers.PropertyHandler;
import com.arduin.wurm.gmtools.utils.Utils;

import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.villages.Village;
import com.wurmonline.server.villages.Villages;

import java.util.ArrayList;


public class ActionCheckVillageRules extends ModActionTemplate{

    public ActionCheckVillageRules(String entryName){
        super(entryName);
    }


    @Override
    void doAction(Creature performer){

        ArrayList<Village> illegalPerimeter = new ArrayList<>();
        ArrayList<Village> illegalGuards = new ArrayList<>();

        for(Village village : Villages.getVillages()){

            if(village.getTotalPerimeterSize() > PropertyHandler.maxPerimeter){
                illegalPerimeter.add(village);
            }
            if(village.getGuards().length > PropertyHandler.maxGuards){
                illegalGuards.add(village);
            }

        }

        Utils.chatMessage(" ", performer);
        Utils.chatMessage("Looking for illegal amounts of guards or too big perimeter sizes...", performer);
        Utils.chatMessage(" ", performer);

        if(illegalPerimeter.size()>0) {

            Utils.chatMessage(" ", performer);
            Utils.chatMessage("The following Deeds have an illegal perimeter size (size > "+ PropertyHandler.maxPerimeter+"):", performer);

            for (Village village : illegalPerimeter) {
                Utils.chatMessage("-------------------------------------------------------------", performer);
                Utils.chatMessage("Name: "+village.getName(), performer);
                Utils.chatMessage("Current Mayor: "+village.getMayor().getName(), performer);
                Utils.chatMessage("Founder: "+village.getFounderName(), performer);
                Utils.chatMessage("Perimeter Size: "+village.getTotalPerimeterSize(), performer);
            }
        }else{
            Utils.chatMessage("There are no deeds with an illegal perimeter size on this map (size > "+ PropertyHandler.maxPerimeter+")", performer);
        }

        Utils.chatMessage(" ", performer);

        if(illegalGuards.size()>0) {

            Utils.chatMessage("The following Deeds have an illegal amount of Spirit Templars (amount > "+ PropertyHandler.maxGuards+"):", performer);

            for (Village village : illegalGuards) {
                Utils.chatMessage("-------------------------------------------------------------", performer);
                Utils.chatMessage("Name: "+village.getName(), performer);
                Utils.chatMessage("Current Mayor: "+village.getMayor().getName(), performer);
                Utils.chatMessage("Founder: "+village.getFounderName(), performer);
                Utils.chatMessage("CountGuards: "+village.getGuards().length, performer);
            }
        }else{
            Utils.chatMessage("There are no deeds with an illegal amount of Spirit Templars on this map (amount > "+ PropertyHandler.maxGuards+")", performer);
        }

        Utils.chatMessage(" ", performer);
        Utils.chatMessage("End of survey", performer);
        Utils.chatMessage(" ", performer);
    }


}
