package coursera.org.componenteGamificacao;

import java.util.List;

public interface AchievementStorage {
	public void addAchievement(String user, Achievement achievement);
	public List<Achievement> getAchievements(String user);
	public Achievement getAchievement(String user, String achievementName);

}
