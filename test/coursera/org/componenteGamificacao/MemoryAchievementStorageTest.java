package coursera.org.componenteGamificacao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MemoryAchievementStorageTest {

	private static AchievementStorage memoryAchievementStorageApp;
	private AchievementStorage memoryAchievementStorage;
	private Points point1;
	private Points point2;
	private Badge badge1;
	private Badge badge2;
	private Points point3;

	@BeforeClass
	public static void init() {
		if (AchievementStorageFactory.getAchievementStorage() == null){
			memoryAchievementStorageApp = new MemoryAchievementStorage();
			AchievementStorageFactory.setAchievementStorage(memoryAchievementStorageApp);			
		}else{
			memoryAchievementStorageApp = AchievementStorageFactory.getAchievementStorage();
		}

	}

	@Before
	public void initTest() {
		point1 = new Points();
		point2 = new Points();
		point3 = new Points();
		badge1 = new Badge();
		badge2 = new Badge();
		memoryAchievementStorage = AchievementStorageFactory.getAchievementStorage();
	}

	@Test
	public void achieviementMemoryTest() {
		assertEquals(memoryAchievementStorage, memoryAchievementStorageApp);
		assertNull(memoryAchievementStorage.getAchievements("marcos"));
	}

	@Test
	public void pointOfAUser() {
		point1.setName("CREATION");
		point1.setPoints(5);

		assertEquals(memoryAchievementStorage, memoryAchievementStorageApp);
		assertNull(memoryAchievementStorage.getAchievements("carlos.silveira"));
		memoryAchievementStorage.addAchievement("carlos.silveira", point1);

		assertNotNull(memoryAchievementStorage.getAchievements("carlos.silveira"));
		assertEquals(1, memoryAchievementStorage.getAchievements("carlos.silveira").size());

		Achievement actualAchievement = memoryAchievementStorage.getAchievement("carlos.silveira", "CREATION");
		Points actualPoint = (Points) actualAchievement;

		assertEquals("CREATION", actualPoint.getName());
		assertEquals(new Integer(5), actualPoint.getPoints());
	}

	@Test
	public void twoPointsWithSameNamesOfAUser() {
		point1.setName("CREATION");
		point1.setPoints(5);
		point2.setName("CREATION");
		point2.setPoints(5);

		assertEquals(memoryAchievementStorage, memoryAchievementStorageApp);
		assertNull(memoryAchievementStorage.getAchievements("paula.siqueira"));
		memoryAchievementStorage.addAchievement("paula.siqueira", point1);
		memoryAchievementStorage.addAchievement("paula.siqueira", point2);

		assertNotNull(memoryAchievementStorage.getAchievements("paula.siqueira"));
		assertEquals(1, memoryAchievementStorage.getAchievements("paula.siqueira").size());

		Achievement actualAchievement = memoryAchievementStorage.getAchievement("paula.siqueira", "CREATION");
		Points actualPoint = (Points) actualAchievement;

		assertEquals("CREATION", actualPoint.getName());
		assertEquals(new Integer(10), actualPoint.getPoints());
	}

	@Test
	public void twoPointsWithDiferentNamesOfAUser() {
		point1.setName("CREATION");
		point1.setPoints(5);
		point2.setName("PARTICIPATION");
		point2.setPoints(3);

		assertEquals(memoryAchievementStorage, memoryAchievementStorageApp);
		assertNull(memoryAchievementStorage.getAchievements("maria.silva"));
		memoryAchievementStorage.addAchievement("maria.silva", point1);
		memoryAchievementStorage.addAchievement("maria.silva", point2);

		assertNotNull(memoryAchievementStorage.getAchievements("maria.silva"));
		assertEquals(2, memoryAchievementStorage.getAchievements("maria.silva").size());

		Achievement actualAchievement = memoryAchievementStorage.getAchievement("maria.silva", "CREATION");;
		Points actualPoint = (Points) actualAchievement;
		assertEquals("CREATION", actualPoint.getName());
		assertEquals(new Integer(5), actualPoint.getPoints());

		actualAchievement = memoryAchievementStorage.getAchievements("maria.silva").get(1);
		actualPoint = (Points) actualAchievement;
		assertEquals("PARTICIPATION", actualPoint.getName());
		assertEquals(new Integer(3), actualPoint.getPoints());
	}

	@Test
	public void badgeOfAUser() {
		badge1.setName("I CAN TALK");

		assertEquals(memoryAchievementStorage, memoryAchievementStorageApp);
		assertNull(memoryAchievementStorage.getAchievements("francisco.martins"));
		memoryAchievementStorage.addAchievement("francisco.martins", badge1);

		assertNotNull(memoryAchievementStorage.getAchievements("francisco.martins"));
		assertEquals(1, memoryAchievementStorage.getAchievements("francisco.martins").size());

		Achievement actualAchievement = memoryAchievementStorage.getAchievement("francisco.martins", "I CAN TALK");
		Badge actualBadge = (Badge) actualAchievement;

		assertEquals("I CAN TALK", actualBadge.getName());
	}

	@Test
	public void twoBadgeWithSameNamesOfUser() {
		badge1.setName("I CAN TALK");
		badge2.setName("I CAN TALK");

		assertEquals(memoryAchievementStorage, memoryAchievementStorageApp);
		assertNull(memoryAchievementStorage.getAchievements("lucas.menezes"));

		memoryAchievementStorage.addAchievement("lucas.menezes", badge1);
		memoryAchievementStorage.addAchievement("lucas.menezes", badge2);

		assertNotNull(memoryAchievementStorage.getAchievements("lucas.menezes"));
		assertEquals(1, memoryAchievementStorage.getAchievements("lucas.menezes").size());

		Achievement actualAchievement = memoryAchievementStorage.getAchievement("lucas.menezes", "I CAN TALK");
		Badge actualBadge = (Badge) actualAchievement;
		assertEquals("I CAN TALK", actualBadge.getName());

	}

	@Test
	public void twoBadgeWithDiferentNamesOfUser() {
		badge1.setName("I CAN TALK");
		badge2.setName("LET ME ADD");

		assertEquals(memoryAchievementStorage, memoryAchievementStorageApp);
		assertNull(memoryAchievementStorage.getAchievements("miriam.soares"));

		memoryAchievementStorage.addAchievement("miriam.soares", badge1);
		memoryAchievementStorage.addAchievement("miriam.soares", badge2);

		assertNotNull(memoryAchievementStorage.getAchievements("miriam.soares"));
		assertEquals(2, memoryAchievementStorage.getAchievements("miriam.soares").size());

		Achievement actualAchievement = memoryAchievementStorage.getAchievement("miriam.soares", "I CAN TALK");
		Badge actualBadge = (Badge) actualAchievement;
		assertEquals("I CAN TALK", actualBadge.getName());

		actualAchievement = memoryAchievementStorage.getAchievement("miriam.soares", "LET ME ADD");
		actualBadge = (Badge) actualAchievement;
		assertEquals("LET ME ADD", actualBadge.getName());

	}

	@Test
	public void badgeAndPointsOfAUser() {
		point1.setName("CREATION");
		point1.setPoints(5);
		point2.setName("PARTICIPATION");
		point2.setPoints(3);
		badge1.setName("I CAN TALK");
		badge2.setName("LET ME ADD");
		point3.setName("CREATION");
		point3.setPoints(3);

		assertEquals(memoryAchievementStorage, memoryAchievementStorageApp);
		assertNull(memoryAchievementStorage.getAchievements("fabiana.bertone"));
		memoryAchievementStorage.addAchievement("fabiana.bertone", point1);
		memoryAchievementStorage.addAchievement("fabiana.bertone", point2);
		memoryAchievementStorage.addAchievement("fabiana.bertone", badge1);
		memoryAchievementStorage.addAchievement("fabiana.bertone", badge2);
		memoryAchievementStorage.addAchievement("fabiana.bertone", point3);

		assertNotNull(memoryAchievementStorage.getAchievements("fabiana.bertone"));
		assertEquals(4, memoryAchievementStorage.getAchievements("fabiana.bertone").size());

		Achievement actualAchievement = memoryAchievementStorage.getAchievement("fabiana.bertone", "CREATION");
		Points actualPoint = (Points) actualAchievement;
		assertEquals("CREATION", actualPoint.getName());
		assertEquals(new Integer(8), actualPoint.getPoints());

		actualAchievement = memoryAchievementStorage.getAchievement("fabiana.bertone", "PARTICIPATION");
		actualPoint = (Points) actualAchievement;
		assertEquals("PARTICIPATION", actualPoint.getName());
		assertEquals(new Integer(3), actualPoint.getPoints());

		actualAchievement = memoryAchievementStorage.getAchievement("fabiana.bertone", "I CAN TALK");
		Badge actualBadge = (Badge) actualAchievement;
		assertEquals("I CAN TALK", actualBadge.getName());

		actualAchievement = memoryAchievementStorage.getAchievement("fabiana.bertone", "LET ME ADD");
		actualBadge = (Badge) actualAchievement;
		assertEquals("LET ME ADD", actualBadge.getName());
	}

}
