package coursera.org.componenteGamificacao;

public interface ForumService {
	public void addTopic(String user, String topic);
	public void addComment(String user, String topic, String comment);
	public void likeTopic(String user, String topic, String topicUser);
	public void likeComment(String user, String topic, String comment, String commentUser);
}
