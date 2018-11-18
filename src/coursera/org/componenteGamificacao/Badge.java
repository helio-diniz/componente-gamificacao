package coursera.org.componenteGamificacao;

public class Badge extends Achievement{

	public Badge() {
		this.type = AchievementType.BADGE;
	}

	@Override
	Achievement addAchievement(Achievement achievement) {
		if (achievement == null){
			return null;
		}
		if (!achievement.type.equals(AchievementType.BADGE)){
			return null;
		};
		if (achievement.getName().equals(this.getName())){
			return this;
		}
		return null;
	}



}
