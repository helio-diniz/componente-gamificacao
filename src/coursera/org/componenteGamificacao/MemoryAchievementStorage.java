package coursera.org.componenteGamificacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryAchievementStorage implements AchievementStorage {

	private Map<String, List<Achievement>> users = new HashMap<>();
	private List<AchievementObserver> observers = new ArrayList<>();

	@Override
	public void addAchievement(String user, Achievement achievement) {
		List<Achievement> achievements = this.getAchievements(user);
		achievements = addAchievement(achievements, achievement);
		users.put(user, achievements);
		observer(user, achievement);
	}

	@Override
	public List<Achievement> getAchievements(String user) {
		return users.get(user);
	}

	@Override
	public Achievement getAchievement(String user, String achievementName) {
		List<Achievement> achievements = this.getAchievements(user);
		for (Achievement achievement : achievements) {
			if (achievement.getName().equals(achievementName)) {
				return achievement;
			}
		}

		return null;
	}

	private List<Achievement> addAchievement(List<Achievement> achievements, Achievement achievement) {
		if (achievements == null) {
			achievements = new ArrayList<>();
			achievements.add(achievement);
			return achievements;
		}
		int index = 0;
		boolean achou = false;
		while (index < achievements.size()) {
			Achievement achievementSelected = achievements.get(index);
			// achou = (achievementSelected.add(achievement) != null);
			if (!achou && achievementSelected.add(achievement) != null) {
				achou = true;
			}

			index++;
		}
		if (!achou) {
			achievements.add(achievement);
		}
		return achievements;
	}

	private void observer(String user, Achievement achievement) {
		for (AchievementObserver observer : observers) {
			observer.achievementUpdate(user, achievement);
		}
	}

	protected List<AchievementObserver> getObservers() {
		return observers;
	}

}
