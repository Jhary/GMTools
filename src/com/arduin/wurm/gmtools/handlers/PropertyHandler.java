package com.arduin.wurm.gmtools.handlers;

import java.util.Properties;


public class PropertyHandler {

    public static boolean checkDeedRules = false;
    public static int maxPerimeter = 0;
    public static int maxGuards = 0;

    public static boolean checkInactivePlayers = false;
    public static int inactiveDaysThreshold = 90;
    public static boolean onlyMayors = false;

    public static boolean checkSameIP = false;

    public static boolean checkEconomy = false;
    public static int ranking = 0;


    public static void init(Properties properties){
        maxPerimeter = Integer.parseInt(properties.getProperty("maxPerimeter", Integer.toString(maxPerimeter)));
        maxGuards = Integer.parseInt(properties.getProperty("maxGuards", Integer.toString(maxGuards)));
        inactiveDaysThreshold = Integer.parseInt(properties.getProperty("inactiveDays", Integer.toString(inactiveDaysThreshold)));
        onlyMayors = Boolean.parseBoolean(properties.getProperty("onlyMayors", Boolean.toString(onlyMayors)));

        checkDeedRules = Boolean.parseBoolean(properties.getProperty("checkDeedRules", Boolean.toString(checkDeedRules)));
        checkInactivePlayers = Boolean.parseBoolean(properties.getProperty("checkInactivePlayers", Boolean.toString(checkInactivePlayers)));
        checkSameIP = Boolean.parseBoolean(properties.getProperty("checkSameIP", Boolean.toString(checkSameIP)));
        checkEconomy = Boolean.parseBoolean(properties.getProperty("checkEconomy", Boolean.toString(checkEconomy)));

        ranking = Integer.parseInt(properties.getProperty("ranking", Integer.toString(ranking)));
    }

}
