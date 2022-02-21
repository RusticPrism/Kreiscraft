package de.rusticprism.kreiscraft.utils;

import org.bukkit.entity.Player;

public class User {
    private Player player;
    private boolean incombat;
    private int combattime;
    public User(Player player) {this.player = player;}

    public Player getPlayer(){return player;}
    public boolean isinCombat(){return incombat;}
    public void setCombat(boolean incombat) {this.incombat = incombat;}
    public int getCombattime() {return combattime;}
    public void setCombattime(int combattime) {this.combattime = combattime;}

}
