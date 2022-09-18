package ampliCraft;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import games.Game;
import games.MysteryBox;
import games.PVEArena;
import games.PVPArena;
//import levelmoney.Geldsystem;
//import levelmoney.Levelsystem;
import net.md_5.bungee.api.ChatColor;
import stelarit.StelaritPlayer;

public class Commands implements CommandExecutor {
	private AmpliCraft plugin;
	public Commands(AmpliCraft plugin) {
		this.plugin = plugin;
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player p = ((Player) sender).getPlayer();
			if(cmd.getName().equalsIgnoreCase("world")) {
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("list")) {
						Worlds.listWorlds(p);
					}
					else {
						p.sendMessage(ChatColor.RED + "Unbekanntes Argument!");
					}
				}
				if(args.length >= 2) {
					if(args[0].equalsIgnoreCase("go")) {
						Worlds.teleport(args[1], p);
					}
					else if(args[0].equalsIgnoreCase("create")) {
						if(plugin.ranks.isOp(p)) {
							Worlds.createWorld(args[1], p);
						}
						else {
							Playerranks.noPermissionMessage(p);
						}
					}
					else {
						p.sendMessage(ChatColor.RED + "Unbekanntes Argument!");
					}
				}
			}
			if(cmd.getName().equalsIgnoreCase("level")) {
				AmpliPlayer ap = new AmpliPlayer(p, plugin.config);
				ap.getPlayerInfo();
			}
			if(cmd.getName().equalsIgnoreCase("stelarit")) {
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("enter")) {
						StelaritPlayer sp = new StelaritPlayer(p, plugin.config);
						plugin.stelarit.addPlayerToStelarit(p, sp);
					}
					else if(args[0].equalsIgnoreCase("leave")) {
						plugin.stelarit.removePlayerFromStelarit(p);
					}
					
				}
				if(args.length == 3) {
					if(args[0].equalsIgnoreCase("setprogress")) {
						Player target = Bukkit.getPlayer(args[1]);
						if(PlayerSets.stelaritPlayer.containsKey(target)) {
							StelaritPlayer sptarget = PlayerSets.stelaritPlayer.get(target);
							sptarget.setPlayerProgress(Integer.parseInt(args[2]));
							p.sendMessage(ChatColor.RED + "Stelaritfortschritt gesetzt!");
							target.sendMessage(ChatColor.ITALIC + "" + ChatColor.RED + "Dein Stelaritfortschritt wurde ge�ndert!");
						}
					}
				}
			}
			if(cmd.getName().equalsIgnoreCase("rang") || cmd.getName().equalsIgnoreCase("rank")) {
				if(args.length == 0) {
					plugin.ranks.getRang(p);
				}
				else if(args.length == 3) {
					if(plugin.ranks.isOp(p)) {
						if(args[0].equalsIgnoreCase("set")) {
							plugin.ranks.setRang(p, args[1], args[2]);
						}
					}
					else {
						Playerranks.noPermissionMessage(p);
					}
				}
			}
			if(cmd.getName().equalsIgnoreCase("pay") || cmd.getName().equalsIgnoreCase("zahle")) {
				AmpliPlayer ap = new AmpliPlayer(p, plugin.config);
				ap.sendMoney(args[0], Float.parseFloat(args[1]));
				Bukkit.getPluginManager().getPlugin("AmpliCraft").saveConfig();
			}
			if(cmd.getName().equalsIgnoreCase("geld") || cmd.getName().equalsIgnoreCase("money")) {
				AmpliPlayer geld = new AmpliPlayer(p, plugin.config);
				if(args.length == 0) {
					geld.getMoney();
				}
				if(args.length == 3) {
					if(plugin.ranks.isOp(p)) {
						if(args[0].equalsIgnoreCase("set")) {
							geld.setMoney(args[1], Float.parseFloat(args[2]));
						}
					}
					else {
						Playerranks.noPermissionMessage(p);
					}
				}
			}
			if(cmd.getName().equalsIgnoreCase("spawn")) {
				p.teleport(Bukkit.getWorld("world").getSpawnLocation());
				p.sendMessage(ChatColor.GREEN + "Willkommen zurück am spawn!");
			}
			if(cmd.getName().equalsIgnoreCase("pvparena")) {
				if(args.length == 1) {
					PVPArena.joinFight(p, args[0]);
				}
			}
			if(cmd.getName().equalsIgnoreCase("games")) {
				if(args.length == 0) {
					Teleporter.teleportToGamesLobby(p);
					p.sendMessage(ChatColor.GREEN + "Du kannst auch mit dem Befehl " + ChatColor.GOLD + "/games [spielname]" + ChatColor.GREEN + " einem Spiel beitreten!");
				}
				else if(args.length > 0) {
					Game game = new Game(p);
					game.startGame(args[0]);
				}
			}
			if(cmd.getName().equalsIgnoreCase("pvearena")) {
				PVEArena pve = new PVEArena(p, plugin.getConfig(), plugin);
				pve.startFight();
			}
			if(cmd.getName().equalsIgnoreCase("zone")) {
				if(args.length == 0) {
					ZoneManager.printZoneInfo(p);
				}
				else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("claim")) {
						p.sendMessage(ChatColor.RED + "Du musst den Namen der Zone angeben die du claimen möchtest!");
						p.sendMessage(ChatColor.RED + "Mit /zone erfährst du den Namen der Zone in der du dich befindest.");
					}
					else {
						p.sendMessage(ChatColor.RED + "Unbekanntes Argument!");
					}
				}
				else if(args.length == 2) {
					if(args[0].equalsIgnoreCase("claim")) {
						ZoneManager lnd = new ZoneManager(args[1]);
						lnd.claim(p);
					}
					else {
						p.sendMessage(ChatColor.RED + "Unbekanntes Argument!");
					}
				}
				else if(args.length == 3) {
					if(args[0].equalsIgnoreCase("addplayer")) {
						ZoneManager lnd = new ZoneManager(args[1]);
						lnd.addOwner(args[2], p);
					}
				}
			}
			if(cmd.getName().equalsIgnoreCase("teleporter")) {
				Teleporter.teleportToWorldTeleporterIsland(p);
			}
			if(cmd.getName().equalsIgnoreCase("mysterybox")) {
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("open")) {
						MysteryBox box = new MysteryBox(p, plugin);
						box.openMysteryBox();
					}
				}
			}
		}
		return true;
	}
}
