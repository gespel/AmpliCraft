package stelarit;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

import levelmoney.Geldsystem;
import levelmoney.Levelsystem;

public class Quest {
	String id;
	FileConfiguration config;
	String name;
	public QuestType type;
	int rewardExp;
	float rewardMoney;
	StelaritPlayer sp;
	Levelsystem questExp;
	public EntityType targetMob;
	int targetMobNumber;
	int killCount = 0;
	String questStartText;
	
	public Quest(String name, StelaritPlayer sp, FileConfiguration config) {
		if(name.equals("ersteSchritte")) {
			this.id = name;
			this.type = QuestType.KILLQUEST;
			this.name = "Erste Schritte";
			this.sp = sp;
			this.questExp = new Levelsystem(this.sp.getPlayer(), config);
			this.config = config;
			this.rewardExp = 100;
			this.targetMob = EntityType.SPIDER;
			this.targetMobNumber = 3;
			this.questStartText = ChatColor.BLUE + "Du siehst so aus als w�rdest du Arbeit suchen? Wir haben vor der Taverne immer wieder Probeme mit Spinnen. " + ChatColor.GOLD + "T�te 3 davon und du erh�lst eine Belohnung!";
			this.rewardMoney = 50;
		}
	}
	@SuppressWarnings("deprecation")
	public void killedOneQuestMob() {
		killCount++;
		if(killCount >= targetMobNumber) {
			this.questFinished();
		}
		else {
			sp.getPlayer().sendMessage(ChatColor.GRAY + "Du hast (" + killCount + "/" + targetMobNumber + ") " + targetMob.getName() + " get�tet!");
		}
	}
	public void questFinished() {
		this.sp.getPlayer().sendMessage(ChatColor.GREEN + "Du hast die Quest " + ChatColor.GOLD + this.name + " beendet!");
		if(this.id.equalsIgnoreCase("ersteSchritte")) {
			this.sp.setPlayerProgress(2);
		}
		questExp.addExp(rewardExp);
		//this.sp.removeQuest(id);
	}
	public void lastDialog() {
		if(this.id.equalsIgnoreCase("ersteSchritte")) {
			sp.getPlayer().sendMessage(ChatColor.BLUE + "Danke! Endlich muss ich mir keine sorgen mehr machen, dass die H�lfte meiner Kundschaft auf dem Weg zu meiner Kneipe aufgefressen wird. Falls du wieder Arbeit suchst sprich mich an. Achso hier ist noch ein bisschen Geld f�r dich!");
			Geldsystem geld = new Geldsystem(sp.getPlayer(), config);
			geld.addMoney(rewardMoney);
			sp.setPlayerProgress(3);
			this.sp.removeQuest(id);
		}
	}
	public void triggerStartText() {
		this.sp.getPlayer().sendMessage(questStartText);
	}
}
