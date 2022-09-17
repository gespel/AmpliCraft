package ampliCraft;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import net.md_5.bungee.api.ChatColor;

public class ZoneManager {
	
	RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
	RegionManager regions = container.get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
	ProtectedRegion region;
	
	public ZoneManager(String name) {
		this.region = regions.getRegion(name);
	}
	public void claim(Player p) {
		DefaultDomain owner = region.getOwners();
		if(owner.getUniqueIds().isEmpty()) {
			owner.addPlayer(p.getUniqueId());
			p.sendMessage(ChatColor.GREEN + "Du hast das Grundst�ck " + ChatColor.GOLD + region.getId() + ChatColor.GREEN + " f�r dich beansprucht!");
		}
		else {
			p.sendMessage(ChatColor.RED + "Dieses Land wurde bereits beansprucht!");
		}
	}
	public void addOwner(String name, Player p) {
		boolean hasPerms = false;
		for(UUID id : region.getOwners().getUniqueIds()) {
			if(id.equals(p.getUniqueId())) {
				hasPerms = true;
				Player add = Bukkit.getPlayer(name);
				DefaultDomain owners = region.getOwners();
				owners.addPlayer(add.getUniqueId());
				add.sendMessage(ChatColor.GREEN + "Du wurdest als Besitzer zum Grundst�ck " + ChatColor.GOLD + name + ChatColor.GREEN + " hinzugef�gt!");
				break;
			}
		}
		if(!hasPerms) {
			p.sendMessage(ChatColor.RED + "Du besitzt diese Zone nicht!");
		}
		else {
			p.sendMessage(ChatColor.GOLD + name + ChatColor.GREEN +  " wurde hinzugef�gt!");
		}
	}
	public static void printZoneInfo(Player p) {
		com.sk89q.worldedit.util.Location loc = BukkitAdapter.adapt(p.getLocation());
		RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
		RegionQuery query = container.createQuery();
		ApplicableRegionSet set = query.getApplicableRegions(loc);
		p.sendMessage(ChatColor.GREEN + "Zone(n):");
		for(ProtectedRegion region : set) {
			p.sendMessage(ChatColor.GOLD + "  " + region.getId() + ":");
			p.sendMessage(ChatColor.GREEN + "    Owner:");
			for(UUID id : region.getOwners().getUniqueIds()) {
				OfflinePlayer op = Bukkit.getOfflinePlayer(id);
				String playerName = op.getName();
				p.sendMessage(ChatColor.GOLD + "      " + playerName);
			}
		}
	}
}
