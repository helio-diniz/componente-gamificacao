package coursera.org.componenteGamificacao;

import java.util.List;

public class PointParticipationObserver implements AchievementObserver {

	private AchievementStorage achievementStorage;

	@Override
	public void achievementUpdate(String user, Achievement a) {
		if (!(a instanceof Points)){
			return;
		}
		if (!(a.getName().equals("PARTICIPATION"))){
			return;
		}
		
		List<Achievement> achievements = achievementStorage.getAchievements(user);
		for(Achievement achievement: achievements){
			if (achievement instanceof Points){
				Points point = (Points) achievement;
				if (point.getName().equals("PARTICIPATION") &&
					point.getPoints() >= 100){
					Badge badge = new Badge();
					badge.setName("PART OF THE COMMUNITY");
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
