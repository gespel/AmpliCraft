package ampliCraft;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

public class FirstStepsQuest extends StelaritQuest {

	public FirstStepsQuest(StelaritPlayer sp, FileConfiguration config) {
		super("q1", sp, config);
		this.type = QuestType.KILLQUEST;
		this.name = "Erste Schritte";
		this.questExp = new Levelsystem(this.sp.getPlayer(), config);
		this.rewardExp = 100;
		this.targetMob = EntityType.SPIDER;
		this.lastStoryProgress = 2;
		this.targetMobNumber = 3;
		this.questStartText = ChatColor.BLUE + "Du siehst so aus als würdest du Arbeit suchen? Wir haben vor der Taverne immer wieder Probeme mit Spinnen. " + ChatColor.GOLD + "Töte 3 davon und du erhälst eine Belohnung" + ChatColor.BLUE + "!";
		this.questLastText = "Danke! Endlich muss ich mir keine sorgen mehr machen, dass die Hälfte meiner Kundschaft auf dem Weg zu meiner Kneipe aufgefressen wird. Falls du wieder Arbeit suchst sprich mich an. Achso hier ist noch ein bisschen Geld für dich!";
		this.rewardMoney = 50;
		this.type = QuestType.KILLQUEST;
	}

	@Override
	public void subQuestRoutine() {
		// TODO Auto-generated method stub
		
	}
}
