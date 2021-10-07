package ampliCraft;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class Levelsystem {
	FileConfiguration config;
	Player p;
	public Levelsystem(Player p, FileConfiguration config) {
		this.config = config;
		this.p = p;
	}
	public void getPlayerInfo() {
		Integer expInt = config.getInt(p.getName() + ".Exp");
		Integer levelInt = config.getInt(p.getName() + ".Level");
		p.sendMessage("Du bist Level " + ChatColor.RED + levelInt.toString() + ChatColor.RESET + ".");
		p.sendMessage("Du hast " + ChatColor.GREEN + expInt.toString() + ChatColor.RESET + " Erfahrungspunkte.");
	}
	public void addExp(int exp) {//a
		config.set(p.getName() + ".Exp", config.getInt(p.getName() + ".Exp") + exp);
		p.sendMessage(ChatColor.GOLD + "Du erhälst " + ChatColor.GREEN + exp + ChatColor.GOLD +" Erfahrungspunkte!");
		updateLevel();
	}
	public void updateLevel() {
		Integer expInt = config.getInt(p.getName() + ".Exp");
		Integer levelInt = config.getInt(p.getName() + ".Level");
		if(expInt >= levelInt * 50) {
			levelInt++;
			expInt = 0;
		}
		config.set(p.getName() + ".Exp", expInt);
		config.set(p.getName() + ".Level", levelInt);
	}
}
