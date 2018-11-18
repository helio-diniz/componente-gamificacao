package coursera.org.componenteGamificacao;

import java.util.List;

public class PointCreationObserver implements AchievementObserver {

	private AchievementStorage achievementStorage;

	@Override
	public void achievementUpdate(String user, Achievement a) {
		if (!(a instanceof Points)){
			return;
		}
		if (!(a.getName().equals("CREATION"))){
			return;
		}
		
		List<Achievement> achievements = achievementStorage.getAchievements(user);
		for(Achievement achievement: achievements){
			if (achievement instanceof Points){
				Points point = (Points) achievement;
				if (point.getName().equals("CREATION") &&
					point.getPoints() >= 100){
					Badge badge = new Badge();
					badge.setName("INVENTOR");
					achievementStorage.addAchievement(user, badge);
					break;
				}
			}
		}

	}

	public void setAchievementStorage(AchievementStorage achievementStorage) {
		this.achievementStorage = achievementStorage;
	}

	public AchievementStorage getAchievementMemory() {
		return achievementStorage;
	}

}
