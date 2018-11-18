package coursera.org.componenteGamificacao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AchievementObserverTest {

	private static AchievementStorage memoryAchievementStorage;
	private Points point1;
	private Points point2;
	private Badge badge1;

	@BeforeClass
	public static void init() {
		if (AchievementStorageFactory.getAchievementStorage() == null){
			AchievementStorage memoryAchievementStorageApp = new MemoryAchievementStorage();
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

		point1 = new Points();
		point2 = new Points();
		badge1 = new Badge();
	}

	@Test
	public void createObserverCreationPointTest() {
		AchievementObserver pointObserver = new PointCreationObserver();
		((PointCreationObserver) pointObserver).setAchievementStorage(memoryAchievementStorage);
		assertNotNull(pointObserver);
		assertEquals(memoryAchievementStorage, ((PointCreationObserver) pointObserver).getAchievementMemory());
	}

	@Test
	public void createObserverParticipationPointTest() {
		AchievementObserver pointObserver = new PointParticipationObserver();
		((PointParticipationObserver) pointObserver).setAchievementStorage(memoryAchievementStorage);
		assertNotNull(pointObserver);
		assertEquals(memoryAchievementStorage, ((PointParticipationObserver) pointObserver).getAchievementMemory());
	}

	@Test
	public void badgeInventorTest() {
		point1.setName("CREATION");
		point1.setPoints(95);
		memoryAchievementStorage.addAchievement("maurício.félix", point1);

		point2.setName("CREATION");
		point2.setPoints(5);
		memoryAchievementStorage.addAchievement("maurício.félix", point2);

		List<Achievement> achievements = memoryAchievementStorage.getAchievements("maurício.félix");
		assertNotNull(achievements);
		assertEquals(2, achievements.size());
		assertEquals(Points.class, memoryAchievementStorage.getAchievement("maurício.félix", "CREATION").getClass());
		assertEquals(Badge.class, memoryAchievementStorage.getAchievement("maurício.félix", "INVENTOR").getClass());

		point1 = (Points) memoryAchievementStorage.getAchievement("maurício.félix", "CREATION");
		badge1 = (Badge) memoryAchievementStorage.getAchievement("maurício.félix", "INVENTOR");
		assertEquals("CREATION", point1.getName());
		assertEquals(new Integer(100), point1.getPoints());
		assertEquals("INVENTOR", badge1.getName());

	}

	@Test
	public void badgePartOfTheCommunityTest() {
		point1.setName("PARTICIPATION");
		point1.setPoints(99);
		memoryAchievementStorage.addAchievement("paula.bertone", point1);

		point2.setName("PARTICIPATION");
		point2.setPoints(1);
		memoryAchievementStorage.addAchievement("paula.bertone", point2);

		List<Achievement> achievements = memoryAchievementStorage.getAchievements("paula.bertone");
		assertNotNull(achievements);
		assertEquals(2, achievements.size());
		assertEquals(Points.class, memoryAchievementStorage.getAchievement("paula.bertone", "PARTICIPATION").getClass());
		assertEquals(Badge.class, memoryAchievementStorage.getAchievement("paula.bertone", "PART OF THE COMMUNITY").getClass());

		point1 = (Points) memoryAchievementStorage.getAchievement("paula.bertone", "PARTICIPATION");
		badge1 = (Badge) memoryAchievementStorage.getAchievement("paula.bertone", "PART OF THE COMMUNITY");
		assertEquals("PARTICIPATION", point1.getName());
		assertEquals(new Integer(100), point1.getPoints());
		assertEquals("PART OF THE COMMUNITY", badge1.getName());

	}

	@Test
	public void badgeUniqueInventorTest() {
		point1.setName("CREATION");
		point1.setPoints(120);
		memoryAchievementStorage.addAchievement("maria.soares", point1);

		point2.setName("CREATION");
		point2.setPoints(5);
		memoryAchievementStorage.addAchievement("maria.soares", point2);

		List<Achievement> achievements = memoryAchievementStorage.getAchievements("maria.soares");
		assertNotNull(achievements);
		assertEquals(2, achievements.size());
		assertEquals(Points.class, memoryAchievementStorage.getAchievement("maria.soares", "CREATION").getClass());
		assertEquals(Badge.class, memoryAchievementStorage.getAchievement("maria.soares", "INVENTOR").getClass());

		point1 = (Points) memoryAchievementStorage.getAchievement("maria.soares", "CREATION");
		badge1 = (Badge) memoryAchievementStorage.getAchievement("maria.soares", "INVENTOR");
		assertEquals("CREATION", point1.getName());
		assertEquals(new Integer(125), point1.getPoints());
		assertEquals("INVENTOR", badge1.getName());
	}

	@Test
	public void badgeUniquePartOfTheCommunityTest() {
		point1.setName("PARTICIPATION");
		point1.setPoints(130);
		memoryAchievementStorage.addAchievement("lucas.martineli", point1);

		point2.setName("PARTICIPATION");
		point2.setPoints(50);
		memoryAchievementStorage.addAchievement("lucas.martineli", point2);

		List<Achievement> achievements = memoryAchievementStorage.getAchievements("lucas.martineli");
		assertNotNull(achievements);
		assertEquals(2, achievements.size());
		assertEquals(Points.class, memoryAchievementStorage.getAchievement("lucas.martineli", "PARTICIPATION").getClass());
		assertEquals(Badge.class, memoryAchievementStorage.getAchievement("lucas.martineli", "PART OF THE COMMUNITY").getClass());

		point1 = (Points) memoryAchievementStorage.getAchievement("lucas.martineli", "PARTICIPATION");
		badge1 = (Badge)memoryAchievementStorage.getAchievement("lucas.martineli", "PART OF THE COMMUNITY");
		assertEquals("PARTICIPATION", point1.getName());
		assertEquals(new Integer(180), point1.getPoints());
		assertEquals("PART OF THE COMMUNITY", badge1.getName());

	}

}
