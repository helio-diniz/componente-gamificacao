package coursera.org.componenteGamificacao;

public class ForumServiceGamificationProxy implements ForumService {

	private AchievementStorage achievementStorage;
	private ForumService forumServieImplemention;

	@Override
	public void addTopic(String user, String topic) {
		try {
			forumServieImplemention.addTopic(user, topic);
		} catch (Exception e) {
			return;
		}

		Points point = new Points();
		point.setName("CREATION");
		point.setPoints(5);
		achievementStorage.addAchievement(user, point);

		Badge badge = new Badge();
		badge.setName("I CAN TALK");
		achievementStorage.addAchievement(user, badge);
	}

	@Override
	public void addComment(String user, String topic, String comment) {
		try {
			forumServieImplemention.addComment(user, topic, comment);
		} catch (Exception e) {
			return;
		}

		Points point = new Points();
		point.setName("PARTICIPATION");
		point.setPoints(3);
		achievementStorage.addAchievement(user, point);

		Badge badge = new Badge();
		badge.setName("LET ME ADD");
		achievementStorage.addAchievement(user, badge);

	}

	@Override
	public void likeTopic(String user, String topic, String topicUser) {
		try {
			forumServieImplemention.likeTopic(user, topic, topicUser);
		} catch (Exception e) {
			return;
		}

		Points point = new Points();
		point.setName("CREATION");
		point.setPoints(1);
		achievementStorage.addAchievement(user, point);

	}

	@Override
	public void likeComment(String user, String topic, String comment, String commentUser) {
		try {
			forumServieImplemention.likeComment(user, topic, comment, commentUser);
		} catch (Exception e) {
			return;
		}

		Points point = new Points();
		point.setName("PARTICIPATION");
		point.setPoints(1);
		achievementStorage.addAchievement(user, point);

	}

	public void setAchievementStorage(AchievementStorage achievementStorage) {
		this.achievementStorage = achievementStorage;

	}

	protected void setForumServieImplemention(ForumService forumServieImplemention) {
		this.forumServieImplemention = forumServieImplemention;
	}

}
