package ampliCraft;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import stelarit.Stelarit;

public class AmpliCraft extends JavaPlugin implements Listener {
	public FileConfiguration config;
	public Playerranks ranks;
	public Stelarit stelarit;
	public void onEnable() {
		WorldCreator bauwelt = new WorldCreator("bauwelt");
		WorldCreator games = new WorldCreator("games");
		WorldCreator stela = new WorldCreator("stelarit");
		Bukkit.getPluginManager().registerEvents(new Events(this), this);
        config = this.getConfig();
        ranks = new Playerranks(config);
        bauwelt.createWorld();
        games.createWorld();
        stela.createWorld();
        stelarit = new Stelarit(config);
        System.out.println("=================== Amplitueden Minecraft Server wird gestartet ===================");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() { 
        	public void run() { 
        		//Geldsystem.zinsenTick(config);
        		Bukkit.getPluginManager().getPlugin("AmpliCraft").saveConfig();
        	} 
        }, 0, 500);
        setCommandExecutors();
    }
	public void onDisable() {
		System.out.println("=================== Amplitueden Minecraft Server wird beendet ===================");
	}
	public void setCommandExecutors() {
		getCommand("world").setExecutor(new Commands(this));
        getCommand("level").setExecutor(new Commands(this));
        getCommand("rang").setExecutor(new Commands(this));
        getCommand("rank").setExecutor(new Commands(this));
        getCommand("geld").setExecutor(new Commands(this));
        getCommand("money").setExecutor(new Commands(this));
        getCommand("pay").setExecutor(new Commands(this));
        getCommand("zahle").setExecutor(new Commands(this));
        getCommand("spawn").setExecutor(new Commands(this));
        getCommand("pvparena").setExecutor(new Commands(this));
        getCommand("games").setExecutor(new Commands(this));
        getCommand("pvearena").setExecutor(new Commands(this));
        getCommand("zone").setExecutor(new Commands(this));
        getCommand("teleporter").setExecutor(new Commands(this));
        getCommand("mysterybox").setExecutor(new Commands(this));
        getCommand("stelarit").setExecutor(new Commands(this));
	}
}
