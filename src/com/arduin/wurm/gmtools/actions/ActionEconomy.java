package com.arduin.wurm.gmtools.actions;

import com.arduin.wurm.gmtools.handlers.PropertyHandler;
import com.arduin.wurm.gmtools.utils.Utils;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.players.Player;
import com.wurmonline.server.players.PlayerInfo;
import com.wurmonline.server.players.PlayerInfoFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActionEconomy extends ModActionTemplate{


    public ActionEconomy(String entryName){
        super(entryName);
    }

    @Override
    void doAction (Creature performer){

        long total = 0;
        ArrayList<Node> ranking = new ArrayList<>();

        try {
            for (PlayerInfo player : PlayerInfoFactory.getPlayerInfos()) {
                Player tmp = new Player(player);
                total += tmp.getMoney();
                ranking.add(new Node(player.getName(), tmp.getMoney()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.sort(ranking, new Comparator<Node>() {
            @Override
            public int compare(Node node2, Node node1) {
                return node1.money > node2.money ? 1 : (node1.money < node2.money) ? -1 : 0 ;
            }
        });

        Utils.chatMessage("Money Ranking", performer);
        Utils.chatMessage("Total money on server: "+convertToReadable(total), performer);
        Utils.chatMessage("", performer);

        int maxRank = PropertyHandler.ranking;
        if(ranking.size()<PropertyHandler.ranking) {
            maxRank = ranking.size();
        }
            for(int i = 0; i < maxRank; i++){
                Utils.chatMessage((i+1)+": "+ranking.get(i).name+" | "+convertToReadable(ranking.get(i).money), performer);
            }


        Utils.chatMessage("", performer);
    }

    private String convertToReadable(long money){

        String sum  = String.valueOf(money);
        int length  = sum.length();
        int iron   = Integer.valueOf(sum.substring(length-2));
        int copper = Integer.valueOf(sum.substring(length-4, length-2));
        int silver = Integer.valueOf(sum.substring(length-6, length-4));
        int gold   = (int) money/1000000;

        return gold+" Gold | "+silver+" Silver | "+copper+" Copper | "+iron+" Iron";
    }

    class Node{
        String name;
        long money;
        Node(String name, long money){
            this.money = money;
            this.name = name;
        }
    }

}
