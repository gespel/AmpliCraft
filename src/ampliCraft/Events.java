package ampliCraft;

import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;

import games.Game;
import games.MysteryBox;
import games.PVEArena;
import games.PVPArena;
import shops.Shops;
//import levelmoney.Geldsystem;
import stelarit.StelaritPlayer;
import stelarit.StelaritQuest;

public class Events implements Listener {
	private AmpliCraft plugin;
	public Events(AmpliCraft plugin) {
		this.plugin = plugin;
	}/*
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		
	}*/
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if(plugin.config.getString(p.getName() + ".Rank") != null) {
        	plugin.ranks.login(p);
        }
        else {
	        plugin.config.set(p.getName() + ".Exp", 0);
	        plugin.config.set(p.getName() + ".Level", 1);
	        plugin.config.set(p.getName() + ".Rank", "guest");
	        plugin.config.set(p.getName() + ".Money", 100);
	        plugin.saveConfig();
	        plugin.ranks.login(p);
        }
    }
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player p = event.getEntity();
		PVPArena.onDeathCheckForArenaFight(p, event, plugin.config);
	}
	@EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
		Entity enemy = event.getEntity();
		Entity attacker = event.getDamager();
		if(attacker instanceof Player) {
			if(enemy instanceof Player) {
				Player opfer = (Player) event.getEntity();
				Player angreifer = (Player) event.getDamager();
				if(PlayerSets.blueTeam.contains(opfer) && PlayerSets.blueTeam.contains(angreifer)) {
					event.setCancelled(true);
				}
				if(PlayerSets.redTeam.contains(opfer) && PlayerSets.redTeam.contains(angreifer)) {
					event.setCancelled(true);
				}
			}
		}
    }
	@EventHandler
	public void onPlayermove(PlayerMoveEvent event) {
		//event.getPlayer().getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, event.getPlayer().getLocation(), 10);
	}
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		Entity mob = event.getEntity();
		Entity killer = event.getEntity().getKiller();
		if (killer instanceof Player) {
			Player p = (Player)killer;
			if(PlayerSets.stelaritPlayer.containsKey(p)) {
				StelaritPlayer sp = PlayerSets.stelaritPlayer.get(p);
				for(StelaritQuest q : sp.activeQuests.values()) {
					if(mob.getType().equals(q.targetMob)) {
						q.killedOneQuestMob();
					}
				}
			}
        }
	}
	@EventHandler 
	public void onEntity(PlayerInteractEntityEvent event) {
		Entity entity = event.getRightClicked();
		Player p = event.getPlayer();
		if(entity.equals(PlayerSets.stelaritNPCS.get("birk"))) {
			StelaritPlayer sp = PlayerSets.stelaritPlayer.get(p);
			plugin.stelarit.getStory().updateCurrentQuest(sp);
		}
	}
	@EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(e.getClickedInventory().getLocation().equals(Locations.weaponShopChest)) {
        	Shops.weaponShop.buyItem(e, plugin.getConfig());
        }
    }
	@EventHandler
	public void onActivate(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock().getLocation().equals(Locations.jnrChest)) {
				if(PlayerSets.jnrPlayer.contains(p)) {
					Game.finishedJnr(p, event, plugin.config);
					event.setCancelled(true);
				}
			}
			else if(event.getClickedBlock().getLocation().equals(Locations.kistenHebel)) {
				AmpliPlayer ap = new AmpliPlayer(p, plugin.config);
				if(ap.payMoney(100)) {
					MysteryBox box = new MysteryBox(p, plugin);
					box.openMysteryBox();
				}
				event.setCancelled(true);
			}
			else if(event.getClickedBlock().getLocation().equals(Locations.folterkammerChest)) {
				if(PlayerSets.folterkammerPlayer.contains(p)) {
					Game.finishedFolterkammer(p, event, plugin.config);
					event.setCancelled(true);
				}
			}
			else if(event.getClickedBlock().getLocation().equals(Locations.elytraChest)) {
				if(PlayerSets.elytraPlayer.contains(p)) {
					Game.finishedEyltra(p, event, plugin.config);
					event.setCancelled(true);
				}
			}
			else if(event.getClickedBlock().getLocation().equals(Locations.teleporterMainToGamesDoorLower) || event.getClickedBlock().getLocation().equals(Locations.teleporterMainToGamesDoorUpper)) {
				Teleporter.teleportToGamesLobby(p);
				event.setCancelled(true);
			}
			else if(event.getClickedBlock().getLocation().equals(Locations.teleporterGamesToMainDoorLower) || event.getClickedBlock().getLocation().equals(Locations.teleporterGamesToMainDoorUpper)) {
				Teleporter.teleportToWorldDoorToGames(p);
				event.setCancelled(true);
			}
			else if(event.getClickedBlock().getLocation().equals(Locations.teleporterGamesToJnrDoorLower) || event.getClickedBlock().getLocation().equals(Locations.teleporterGamesToJnrDoorUpper)) {
				Game game = new Game(p);
				game.startGame("jnr");
				event.setCancelled(true);
			}
			else if(event.getClickedBlock().getLocation().equals(Locations.teleporterGamesToFolterKammerDoorLower) || event.getClickedBlock().getLocation().equals(Locations.teleporterGamesToFolterKammerDoorUpper)) {
				Game game = new Game(p);
				game.startGame("folterkammer");
				event.setCancelled(true);
			}
			else if(event.getClickedBlock().getLocation().equals(Locations.teleporterGamesToPveArenaDoorLower) || event.getClickedBlock().getLocation().equals(Locations.teleporterGamesToPveArenaDoorUpper)) {
				PVEArena arena = new PVEArena(p, plugin.getConfig(), plugin);
				arena.startFight();
				event.setCancelled(true);
			}   
		}
	}
}
