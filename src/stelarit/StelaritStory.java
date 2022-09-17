package stelarit;

import org.bukkit.configuration.file.FileConfiguration;

public class StelaritStory {
	
	FileConfiguration config;
	
	public StelaritStory(FileConfiguration config) {
		this.config = config;
	}
	public void updateCurrentQuest(StelaritPlayer sp) {
		if(sp.getPlayerProgress() == 1 && !sp.activeQuests.containsKey("q1")) {
			sp.addQuest("q1", new FirstStepsQuest(sp, this.config));
			sp.activeQuests.get("q1").triggerStartText();
		}
		else if(sp.getPlayerProgress() == 2 && sp.activeQuests.containsKey("q1")) {
			sp.activeQuests.get("q1").lastDialog();
		}
	}
}
