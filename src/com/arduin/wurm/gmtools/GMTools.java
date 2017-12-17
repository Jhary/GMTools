package com.arduin.wurm.gmtools;

import com.arduin.wurm.gmtools.handlers.ActionHandler;
import com.arduin.wurm.gmtools.handlers.PropertyHandler;

import com.arduin.wurm.gmtools.utils.StringsENG;
import org.gotti.wurmunlimited.modloader.interfaces.Configurable;
import org.gotti.wurmunlimited.modloader.interfaces.PreInitable;
import org.gotti.wurmunlimited.modloader.interfaces.ServerStartedListener;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;
import org.gotti.wurmunlimited.modsupport.actions.ModActions;

import java.util.Properties;


public class GMTools implements WurmServerMod, ServerStartedListener, PreInitable, Configurable{

    @Override
    public void configure(Properties properties){
        PropertyHandler.init(properties);
    }

    @Override
    public void onServerStarted(){
        ActionHandler.init();
    }

    @Override
    public void preInit(){
        ModActions.init();
    }

    @Override public String getVersion(){
        return StringsENG.VERSION;
    }

}