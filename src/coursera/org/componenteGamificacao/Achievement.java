package coursera.org.componenteGamificacao;

public abstract class Achievement {

	private String name;
	protected AchievementType type;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;	
	}
	
	abstract Achievement addAchievement(Achievement achievement);
	
	public Achievement add(Achievement achievement){
		return addAchievement(achievement);
	};
	
	

}
