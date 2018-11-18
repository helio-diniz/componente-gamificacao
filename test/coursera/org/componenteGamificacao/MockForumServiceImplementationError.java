package coursera.org.componenteGamificacao;

public class MockForumServiceImplementationError implements ForumService {

	@Override
	public void addTopic(String user, String topic) {

	}

	@Override
	public void addComment(String user, String topic, String comment) {

	}

	@Override
	public void likeTopic(String user, String topic, String topicUser) {
		throw new NegocioException("Erro ao adicionar Comentário");

	}

	@Override
	public void likeComment(String user, String topic, String comment, String commentUser) {
		throw new NegocioException("Erro ao adicionar Like Comentário");

	}

}
