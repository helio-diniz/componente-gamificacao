package coursera.org.componenteGamificacao;


public class Points extends Achievement {
	private Integer points = 0;

	public Points() {
		this.type = AchievementType.POINTS;
	}

	public Integer getPoints() {
		return this.points;
	}
	
	protected void setPoints(Integer points) {
		this.points = points;
	}

	@Override
	Achievement addAchievement(Achievement achievement) {
		if (achievement == null){
			return null;
		}
				
		if (!achievement.type.equals(AchievementType.POINTS)) {
			return null;
		}
		
		if (achievement.getName().equals(this.getName()) ){
			this.points += ((Points) achievement).getPoints();
			return this;
		} else{
			return null;
		}
		
	}
	

}
