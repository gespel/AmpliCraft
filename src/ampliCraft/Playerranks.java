package ampliCraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Playerranks {
	FileConfiguration config;
	final String devName = ChatColor.DARK_RED + "Dev" + ChatColor.RESET;
	final String adminName = ChatColor.RED + "Admin" + ChatColor.RESET;
	final String modName = ChatColor.DARK_PURPLE + "Moderator" + ChatColor.RESET;
	final String archName = ChatColor.GREEN + "Architekt" + ChatColor.RESET;
	final String vipName = ChatColor.LIGHT_PURPLE + "VIP" + ChatColor.RESET;
	final String memberName = ChatColor.AQUA + "Member" + ChatColor.RESET;
	final String guestName = ChatColor.GRAY + "Gast" + ChatColor.RESET;
	public Playerranks(FileConfiguration config) {
		this.config = config;
	}
	public void login(Player p) {
		if(config.getString(p.getName() + ".Rank").equals("dev")) {
			p.setDisplayName(devName + " | " + p.getName());
            p.setPlayerListName(devName + " | " + p.getName());
		}
		if(config.getString(p.getName() + ".Rank").equals("admin")) {
			p.setDisplayName(adminName + " | " + p.getName());
            p.setPlayerListName(adminName + " | " + p.getName());
		}
		if(config.getString(p.getName() + ".Rank").equals("guest")) {
			p.setDisplayName(guestName + " | " + p.getName());
            p.setPlayerListName(guestName + " | " + p.getName());
		}
		if(config.getString(p.getName() + ".Rank").equals("vip")) {
			p.setDisplayName(vipName + " | " + p.getName());
            p.setPlayerListName(vipName + " | " + p.getName());
		}
		if(config.getString(p.getName() + ".Rank").equals("member")) {
			p.setDisplayName(memberName + " | " + p.getName());
			p.setPlayerListName(memberName + " | " + p.getName());
		}
		if(config.getString(p.getName() + ".Rank").equals("moderator")) {
			p.setDisplayName(modName + " | " + p.getName());
			p.setPlayerListName(modName + " | " + p.getName());
		}
		if(config.getString(p.getName() + ".Rank").equals("architekt")) {
			p.setDisplayName(archName + " | " + p.getName());
			p.setPlayerListName(archName + " | " + p.getName());
		}
	}
	public void getRang(Player p) {
		String rang = config.getString(p.getName() + ".Rank");
		if(rang.equals("dev")) {
			p.sendMessage("Dein momentaner Rang ist " + devName + "!");
		}
		if(rang.equals("admin")) {
			p.sendMessage("Dein momentaner Rang ist " + adminName + "!");
		}
		if(rang.equals("vip")) {
			p.sendMessage("Dein momentaner Rang ist " + vipName + "!");
		}
		if(rang.equals("guest")) {
			p.sendMessage("Dein momentaner Rang ist " + guestName + "!");
		}
		if(rang.equals("member")) {
			p.sendMessage("Dein momentaner Rang ist " + memberName + "!");
		}
		if(rang.equals("moderator")) {
			p.sendMessage("Dein momentaner Rang ist " + modName + "!");
		}
		if(rang.equals("architekt")) {
			p.sendMessage("Dein momentaner Rang ist " + archName + "!");
		}
	}
	public void setRang(Player p, String playername, String rank) {
		if(rank.equalsIgnoreCase("dev")) {
			Player ranke = Bukkit.getPlayer(playername);
			config.set(ranke.getName() + ".Rank", "dev");
			ranke.sendMessage("Du wurdest zum " + devName + " befördert!");
			p.sendMessage("Rang wurde gesetzt!");
		}
		else if(rank.equalsIgnoreCase("admin")) {
			Player ranke = Bukkit.getPlayer(playername);
			config.set(ranke.getName() + ".Rank", "admin");
			ranke.sendMessage("Du wurdest zum " + adminName + " befördert!");
			p.sendMessage("Rang wurde gesetzt!");
		}
		else if(rank.equalsIgnoreCase("vip")) {
			Player ranke = Bukkit.getPlayer(playername);
			config.set(ranke.getName() + ".Rank", "vip");
			ranke.sendMessage("Du wurdest zum " + vipName + " befördert!");
			p.sendMessage("Rang wurde gesetzt!");
		}
		else if(rank.equalsIgnoreCase("guest")) {
			Player ranke = Bukkit.getPlayer(playername);
			config.set(ranke.getName() + ".Rank", "guest");
			ranke.sendMessage("Du wurdest zum " + guestName + " befördert!");
			p.sendMessage("Rang wurde gesetzt!");
		}
		else if(rank.equalsIgnoreCase("member")) {
			Player ranke = Bukkit.getPlayer(playername);
			config.set(ranke.getName() + ".Rank", "member");
			ranke.sendMessage("Du wurdest zum " + memberName + " befördert!");
			p.sendMessage("Rang wurde gesetzt!");
		}
		else if(rank.equalsIgnoreCase("moderator")) {
			Player ranke = Bukkit.getPlayer(playername);
			config.set(ranke.getName() + ".Rank", "moderator");
			ranke.sendMessage("Du wurdest zum " + modName + " befördert!");
			p.sendMessage("Rang wurde gesetzt!");
		}
		else if(rank.equalsIgnoreCase("architekt")) {
			Player ranke = Bukkit.getPlayer(playername);
			config.set(ranke.getName() + ".Rank", "architekt");
			ranke.sendMessage("Du wurdest zum " + archName + " befördert!");
			p.sendMessage("Rang wurde gesetzt!");
		}
		else {
			p.sendMessage(ChatColor.DARK_RED + "Diesen Rang gibt es nicht!");
		}
	}
	public boolean isOp(Player p) {
		String rank = config.getString(p.getName() + ".Rank");
		if(rank.equals("dev") || rank.equals("admin")) {
			return true;
		}
		return false;
	}
	public static void noPermissionMessage(Player p) {
		p.sendMessage(ChatColor.RED + "Du hast nicht die nötigen Rechte um diesen Befehl auszuführen!");
	}
}
