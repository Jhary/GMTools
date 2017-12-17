package com.arduin.wurm.gmtools.utils;

import com.wurmonline.server.creatures.Creature;


public abstract class Utils{

    public static void chatMessage(String message, Creature performer){
        performer.getCommunicator().sendAlertServerMessage(message);
    }

}
