package ampliCraft;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

public abstract class StelaritQuest {
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
	String questLastText;
	int startStoryProgress;
	int lastStoryProgress;
	int questProgress;
	
	public StelaritQuest(String name, StelaritPlayer sp, FileConfiguration config) {
		this.id = name;
		this.sp = sp;
		this.config = config;
		this.questExp = new Levelsystem(sp.getPlayer(), config);
	}
	@SuppressWarnings("deprecation")
	public void killedOneQuestMob() {
		killCount++;
		if(killCount >= targetMobNumber) {
			this.questFinished();
		}
		else {
			sp.getPlayer().sendMessage(ChatColor.GRAY + "Du hast (" + killCount + "/" + targetMobNumber + ") " + targetMob.getName() + " getötet!");
		}
	}
	public void questFinished() {
		this.sp.getPlayer().sendMessage(ChatColor.GREEN + "Du hast die Quest " + ChatColor.GOLD + this.name + " beendet!");
		questExp.addExp(rewardExp);
		sp.setPlayerProgress(lastStoryProgress);
	}
	public void lastDialog() {
		sp.getPlayer().sendMessage(ChatColor.BLUE + questLastText);
		Geldsystem geld = new Geldsystem(sp.getPlayer(), config);
		geld.addMoney(rewardMoney);
		sp.setPlayerProgress(1);
		this.sp.removeQuest(id);
	}
	public void triggerStartText() {
		this.sp.getPlayer().sendMessage(questStartText);
	}
	public void setStartText(String input) {
		this.questStartText = input;
	}
	public void setLastText(String input) {
		this.questLastText = input;
	}
	public void setStartStoryProgress(int input) {
		this.startStoryProgress = input;
	}
	public int getQuestProgress() {
		return this.questProgress;
	}
	public abstract void subQuestRoutine();
}
