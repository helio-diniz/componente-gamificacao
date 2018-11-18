package coursera.org.componenteGamificacao;

public class AchievementStorageFactory {
	public static AchievementStorage achievementStorage = null;
	
	public static AchievementStorage getAchievementStorage(){
		return achievementStorage;
	}

	public static void setAchievementStorage(AchievementStorage storage) {
		if (achievementStorage == null) {
			achievementStorage = storage;
		}
		
	}

}
