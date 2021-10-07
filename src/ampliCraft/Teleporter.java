package ampliCraft;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Teleporter {
	public static void teleportToGamesLobby(Player p) {
		p.teleport(Locations.gamesLobby);
		p.sendMessage("Du wurdest zur " + ChatColor.GREEN + "Games Lobby" + ChatColor.RESET + " teleportiert!");
	}
	public static void teleportToPveArena(Player p) {
		p.teleport(Locations.pveArena);
		p.sendMessage("Du wurdest zur " + ChatColor.GREEN + "PVE Arena" + ChatColor.RESET + " teleportiert!");
	}
	public static void teleportToWorldDoorToGames(Player p) {
		p.teleport(Locations.worldDoorToGames);
		p.sendMessage("Du wurdest zur " + ChatColor.GREEN + "Hauptwelt" + ChatColor.RESET + " teleportiert!");
	}
	public static void teleportToWorldTeleporterIsland(Player p) {
		p.teleport(Locations.teleporterIsland);
		p.sendMessage("Du wurdest zur " + ChatColor.GREEN + "Teleporter-Insel" + ChatColor.RESET + " teleportiert!");
	}
}
