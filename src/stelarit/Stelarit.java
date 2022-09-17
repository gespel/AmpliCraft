package stelarit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import ampliCraft.PlayerSets;
import net.md_5.bungee.api.ChatColor;

public class Stelarit {
	FileConfiguration config;
	StelaritStory story;
	
	public Stelarit(FileConfiguration config) {
		this.config = config;
		story = new StelaritStory(config);
		Entity birk = Bukkit.getWorld("stelarit").spawnEntity(new Location(Bukkit.getWorld("stelarit"), -175, 77, -172), EntityType.VILLAGER);
		PlayerSets.stelaritNPCS.put("birk", birk);
		System.out.println("Stelarit wurde gestartet!");
	}
	public StelaritStory getStory() {
		return this.story;
	}
	public void addPlayerToStelarit(Player p, StelaritPlayer sp) {
		if(!PlayerSets.stelaritPlayer.containsKey(p)) {
			PlayerSets.stelaritPlayer.put(p, sp);
			if(sp.getPlayerProgress() == 0) {
				sp.getPlayer().sendMessage(ChatColor.GREEN + "Willkommen in der Welt von Stelarit! Du erwachst und bist ersch�pft. Du hast allem anschein nach deine Errinerung verloren...");
				sp.setPlayerProgress(1);
			}
			else {
				sp.getPlayer().sendMessage(ChatColor.GREEN + "Willkommen zur�ck!");
			}
		}
		else {
			sp.getPlayer().sendMessage(ChatColor.RED + "Du befindest dich bereits in der Welt von Stelarit!");
		}
	}
	public void removePlayerFromStelarit(Player p) {
		if(PlayerSets.stelaritPlayer.containsKey(p)) {
			PlayerSets.stelaritPlayer.remove(p);
			p.sendMessage(ChatColor.GREEN + "Du hast Stelarit verlassen!");
		}
		else {
			p.sendMessage(ChatColor.RED + "Du befindest dich nicht in der Welt von Stelarit!");
		}
	}
}
