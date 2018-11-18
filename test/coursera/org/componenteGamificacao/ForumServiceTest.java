package coursera.org.componenteGamificacao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ForumServiceTest {

	private static AchievementStorage memoryAchievementStorageApp;
	private AchievementStorage memoryAchievementStorage;
	private ForumService forumServiceImplementation;
	private ForumServiceGamificationProxy forumServiceGamificatioProxy;
	private Points point;
	private Badge badge1;
	private Badge badge2;

	@BeforeClass
	public static void init() {
		if (AchievementStorageFactory.getAchievementStorage() == null){
			memoryAchievementStorageApp = new MemoryAchievementStorage();
			AchievementStorageFactory.setAchievementStorage(memoryAchievementStorageApp);			
		}
	}

	@Before
	public void initTest() {
		memoryAchievementStorage = AchievementStorageFactory.getAchievementStorage();

		AchievementObserver pointCreationObserver = new PointCreationObserver();
		AchievementObserver pointParticipationObserver = new PointParticipationObserver();

		((PointCreationObserver) pointCreationObserver).setAchievementStorage(memoryAchievementStorage);
		((PointParticipationObserver) pointParticipationObserver).setAchievementStorage(memoryAchievementStorage);

		((MemoryAchievementStorage) memoryAchievementStorage).getObservers().add(pointCreationObserver);
		((MemoryAchievementStorage) memoryAchievementStorage).getObservers().add(pointParticipationObserver);

		forumServiceImplementation = new MockForumServiceImplementation();
		ForumService proxy = new ForumServiceGamificationProxy();

		forumServiceGamificatioProxy = (ForumServiceGamificationProxy) proxy;
		forumServiceGamificatioProxy.setForumServieImplemention(forumServiceImplementation);
		forumServiceGamificatioProxy.setAchievementStorage(memoryAchievementStorage);
	}

	@Test
	public void addTopicTest() {
		forumServiceGamificatioProxy.addTopic("joão.albuquerque", "Padrão de Projeto Proxy");

		List<Achievement> achievements = memoryAchievementStorage.getAchievements("joão.albuquerque");
		assertNotNull(achievements);
		assertEquals(2, achievements.size());

		Achievement achievementPoint = memoryAchievementStorage.getAchievement("joão.albuquerque", "CREATION");
		point = (Points) achievementPoint;
		Achievement achievementBadge = memoryAchievementStorage.getAchievement("joão.albuquerque", "I CAN TALK");
		badge1 = (Badge) achievementBadge;

		assertEquals("CREATION", point.getName());
		assertEquals(new Integer(5), point.getPoints());
		assertEquals("I CAN TALK", badge1.getName());

	}

	@Test
	public void addCommentTest() {
		forumServiceGamificatioProxy.addComment("carlos.junqueira", "Padrão de Projeto Proxy",
				"Interface, Classe Proxy e Implementação");

		List<Achievement> achievements = memoryAchievementStorage.getAchievements("carlos.junqueira");
		assertNotNull(achievements);
		assertEquals(2, achievements.size());

		Achievement achievementPoint = memoryAchievementStorage.getAchievement("carlos.junqueira", "PARTICIPATION");
		point = (Points) achievementPoint;

		Achievement achievementBadge = memoryAchievementStorage.getAchievement("carlos.junqueira", "LET ME ADD");
		badge1 = (Badge) achievementBadge;

		assertEquals("PARTICIPATION", point.getName());
		assertEquals(new Integer(3), point.getPoints());
		assertEquals("LET ME ADD", badge1.getName());

	}

	@Test
	public void likeTopicTest() {
		forumServiceGamificatioProxy.likeTopic("jose.silva", "Padrão de Projeto Proxy", "joão.albuquerque");

		List<Achievement> achievements = memoryAchievementStorage.getAchievements("jose.silva");
		assertNotNull(achievements);
		assertEquals(1, achievements.size());

		Achievement achievementPoint = memoryAchievementStorage.getAchievement("jose.silva", "CREATION");
		point = (Points) achievementPoint;

		assertEquals("CREATION", point.getName());
		assertEquals(new Integer(1), point.getPoints());

	}

	@Test
	public void likeCommentTest() {
		forumServiceGamificatioProxy.likeComment("joão.martineli", "Padrão de Projeto Proxy",
				"Interface, Classe Proxy e Implementação", "carlos.junqueira");

		List<Achievement> achievements = memoryAchievementStorage.getAchievements("joão.martineli");
		assertNotNull(achievements);
		assertEquals(1, achievements.size());

		Achievement achievementPoint = memoryAchievementStorage.getAchievement("joão.martineli", "PARTICIPATION");
		point = (Points) achievementPoint;

		assertEquals("PARTICIPATION", point.getName());
		assertEquals(new Integer(1), point.getPoints());

	}

	@Test
	public void addTwoTopicsTest() {
		forumServiceGamificatioProxy.addTopic("márcio.silvério", "Padrão de Projeto Singleton");
		forumServiceGamificatioProxy.addTopic("márcio.silvério", "Padrão de Projeto Builder");

		List<Achievement> achievements = memoryAchievementStorage.getAchievements("márcio.silvério");
		assertNotNull(achievements);
		assertEquals(2, achievements.size());

		Achievement achievementPoint = memoryAchievementStorage.getAchievement("márcio.silvério", "CREATION");
		point = (Points) achievementPoint;
		Achievement achievementBadge = memoryAchievementStorage.getAchievement("márcio.silvério", "I CAN TALK");;
		badge1 = (Badge) achievementBadge;

		assertEquals("CREATION", point.getName());
		assertEquals(new Integer(10), point.getPoints());
		assertEquals("I CAN TALK", badge1.getName());

	}

	@Test
	public void addTopicsCommnetsAndLikesTest() {
		forumServiceGamificatioProxy.addTopic("lucas.silveira", "Padrão de Projeto Observer");
		forumServiceGamificatioProxy.addComment("lucas.silveira", "Padrão de Projeto Observer",
				"Observadores comportamentais");
		forumServiceGamificatioProxy.addTopic("lucas.silveira", "Padrão de Projeto State");
		forumServiceGamificatioProxy.likeTopic("lucas.silveira", "Padrão de Projeto Proxy", "joão.albuquerque");
		forumServiceGamificatioProxy.likeComment("lucas.silveira", "Padrão de Projeto Proxy",
				"Interface, Classe Proxy e Implementação", "carlos.junqueira");

		List<Achievement> achievements = memoryAchievementStorage.getAchievements("lucas.silveira");
		assertNotNull(achievements);
		assertEquals(4, achievements.size());

		Achievement achievementPoint = memoryAchievementStorage.getAchievement("lucas.silveira", "CREATION");
		point = (Points) achievementPoint;
		Achievement achievementBadge = memoryAchievementStorage.getAchievement("lucas.silveira", "I CAN TALK");
		badge1 = (Badge) achievementBadge;
		achievementPoint = memoryAchievementStorage.getAchievement("lucas.silveira", "PARTICIPATION");
		Points point2 = (Points) achievementPoint;
		achievementBadge = memoryAchievementStorage.getAchievement("lucas.silveira", "LET ME ADD");
		Badge badge2 = (Badge) achievementBadge;

		assertEquals("CREATION", point.getName());
		assertEquals(new Integer(11), point.getPoints());
		assertEquals("I CAN TALK", badge1.getName());
		assertEquals("PARTICIPATION", point2.getName());
		assertEquals(new Integer(4), point2.getPoints());
		assertEquals("LET ME ADD", badge2.getName());

	}

	@Test
	public void addTopicsCommnetsAndLikesErrorTest() {
		ForumService forumServiceImplementationError = new MockForumServiceImplementationError();
		forumServiceGamificatioProxy.setForumServieImplemention(forumServiceImplementationError);

		forumServiceGamificatioProxy.addTopic("maria.monteiro", "Padrão de Projeto Observer");
		forumServiceGamificatioProxy.addComment("maria.monteiro", "Padrão de Projeto Observer",
				"Observadores comportamentais");
		forumServiceGamificatioProxy.addTopic("maria.monteiro", "Padrão de Projeto State");
		forumServiceGamificatioProxy.likeTopic("maria.monteiro", "Padrão de Projeto Proxy", "joão.albuquerque");
		forumServiceGamificatioProxy.likeComment("maria.monteiro", "Padrão de Projeto Proxy",
				"Interface, Classe Proxy e Implementação", "carlos.junqueira");

		List<Achievement> achievements = memoryAchievementStorage.getAchievements("maria.monteiro");
		assertNotNull(achievements);
		assertEquals(4, achievements.size());

		Achievement achievementPoint = memoryAchievementStorage.getAchievement("maria.monteiro", "CREATION");
		point = (Points) achievementPoint;
		Achievement achievementBadge = memoryAchievementStorage.getAchievement("maria.monteiro", "I CAN TALK");
		badge1 = (Badge) achievementBadge;
		achievementPoint = memoryAchievementStorage.getAchievement("maria.monteiro", "PARTICIPATION");
		Points point2 = (Points) achievementPoint;
		achievementBadge = memoryAchievementStorage.getAchievement("maria.monteiro", "LET ME ADD");
		Badge badge2 = (Badge) achievementBadge;

		assertEquals("CREATION", point.getName());
		assertEquals(new Integer(10), point.getPoints());
		assertEquals("I CAN TALK", badge1.getName());
		assertEquals("PARTICIPATION", point2.getName());
		assertEquals(new Integer(3), point2.getPoints());
		assertEquals("LET ME ADD", badge2.getName());
	}

	@Test
	public void inventorTest() {
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Método de Fábrica");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Singleton");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Build");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Abstract");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Prototype");

		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Bridge");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Composite");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Proxy");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Decorator");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Adapter");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Flyweight");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Facade");

		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Strategy");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Template Method");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto State");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Observer");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Chain of Responsability");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Command");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Interator");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Mediator");
		forumServiceGamificatioProxy.addTopic("eduardo.cavalcanti", "Padrão de Projeto Interpreter");

		List<Achievement> achievements = memoryAchievementStorage.getAchievements("eduardo.cavalcanti");
		assertNotNull(achievements);
		assertEquals(3, achievements.size());

		Achievement achievementPoint = memoryAchievementStorage.getAchievement("eduardo.cavalcanti", "CREATION");
		point = (Points) achievementPoint;
		Achievement achievementBadge = memoryAchievementStorage.getAchievement("eduardo.cavalcanti", "I CAN TALK");
		badge1 = (Badge) achievementBadge;
		achievementBadge = memoryAchievementStorage.getAchievement("eduardo.cavalcanti", "INVENTOR");
		badge2 = (Badge) achievementBadge;

		assertEquals("CREATION", point.getName());
		assertEquals(new Integer(105), point.getPoints());
		assertEquals("I CAN TALK", badge1.getName());
		assertEquals("INVENTOR", badge2.getName());
	}

	@Test
	public void partOfTheCommunityTest() {
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Método de Fábrica",
				"Padrão de Criação");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Método de Fábrica", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Singleton", "Padrão de Criação");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Singleton", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Build", "Padrão de Criação");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Build", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Abstract", "Padrão de Criação");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Abstract", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Prototype", "Padrão de Criação");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Prototype", "Gang of Four");

		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Bridge", "Padrão Estrutural");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Bridge", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Composite", "Padrão Estrutural");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Composite", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Proxy", "Padrão Estrutural");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Proxy", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Decorator", "Padrão Estrutural");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Decorator", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Adapter", "Padrão Estrutural");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Adapter", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Flyweight", "Padrão Estrutural");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Flyweight", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Facade", "Padrão Estrutural");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Facade", "Gang of Four");

		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Strategy", "Padrão Comportamental");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Strategy", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Template Method",
				"Padrão Comportamental");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Template Method", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto State", "Padrão Comportamental");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto State", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Observer", "Padrão Comportamental");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Observer", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Chain of Responsability",
				"Padrão Comportamental");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Chain of Responsability",
				"Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Command", "Padrão Comportamental");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Command", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Interator",
				"Padrão Comportamental");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Interator", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Mediator", "Padrão Comportamental");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Mediator", "Gang of Four");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Interpreter",
				"Padrão Comportamental");
		forumServiceGamificatioProxy.addComment("geraldo.assis", "Padrão de Projeto Interpreter", "Gang of Four");

		List<Achievement> achievements = memoryAchievementStorage.getAchievements("geraldo.assis");
		assertNotNull(achievements);
		assertEquals(3, achievements.size());

		Achievement achievementPoint = memoryAchievementStorage.getAchievement("geraldo.assis", "PARTICIPATION");
		point = (Points) achievementPoint;
		Achievement achievementBadge = memoryAchievementStorage.getAchievement("geraldo.assis", "LET ME ADD");
		badge1 = (Badge) achievementBadge;
		achievementBadge = memoryAchievementStorage.getAchievement("geraldo.assis", "PART OF THE COMMUNITY");
		badge2 = (Badge) achievementBadge;

		assertEquals("PARTICIPATION", point.getName());
		assertEquals(new Integer(126), point.getPoints());
		assertEquals("LET ME ADD", badge1.getName());
		assertEquals("PART OF THE COMMUNITY", badge2.getName());
	}

}
