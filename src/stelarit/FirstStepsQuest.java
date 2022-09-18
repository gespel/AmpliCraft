package stelarit;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

import ampliCraft.AmpliPlayer;
//import levelmoney.Levelsystem;

public class FirstStepsQuest extends StelaritQuest {

	public FirstStepsQuest(StelaritPlayer sp, FileConfiguration config) {
		super("q1", sp, config);
		this.type = QuestType.KILLQUEST;
		this.name = "Erste Schritte";
		this.ap = new AmpliPlayer(this.sp.getPlayer(), config);
		this.rewardExp = 100;
		this.targetMob = EntityType.SPIDER;
		this.lastStoryProgress = 2;
		this.targetMobNumber = 3;
		this.questStartText = ChatColor.BLUE + "Du siehst so aus als w�rdest du Arbeit suchen? Wir haben vor der Taverne immer wieder Probeme mit Spinnen. " + ChatColor.GOLD + "T�te 3 davon und du erh�lst eine Belohnung" + ChatColor.BLUE + "!";
		this.questLastText = "Danke! Endlich muss ich mir keine sorgen mehr machen, dass die H�lfte meiner Kundschaft auf dem Weg zu meiner Kneipe aufgefressen wird. Falls du wieder Arbeit suchst sprich mich an. Achso hier ist noch ein bisschen Geld f�r dich!";
		this.rewardMoney = 50;
		this.type = QuestType.KILLQUEST;
	}

	@Override
	public void subQuestRoutine() {
		// TODO Auto-generated method stub
		
	}
}
