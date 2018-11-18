package coursera.org.componenteGamificacao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AchievementTest {
	private Points point1;
	private Points point2;
	private Badge badge1;
	private Badge badge2;

	@Before
	public void inicializaTeste() {
		point1 = new Points();
		point2 = new Points();
		badge1 = new Badge();
		badge2 = new Badge();
	}

	@Test
	public void createPoint() {
		point1.setName("Points");
		assertEquals(new Integer(0), point1.getPoints());
		assertEquals("Points", point1.getName());
	}

	@Test
	public void addPointsWithSameNames() {
		point1.setName("Points");
		point2.setName("Points");
		point2.setPoints(10);
		assertEquals("Points", point1.getName());
		assertEquals(new Integer(0), point1.getPoints());
		assertEquals("Points", point2.getName());
		assertEquals(new Integer(10), point2.getPoints());
		point1 = (Points) point1.add(point2);
		assertEquals(new Integer(10), point1.getPoints());
	}

	@Test
	public void addPointNull() {
		point1.setName("Points");
		point1.setPoints(10);
		point2 = null;
		assertEquals(new Integer(10), point1.getPoints());
		point1 = (Points) point1.add(point2);
		assertNull(point1);
	}

	@Test
	public void addPointsWithDiferentNames() {
		point1.setName("Points");
		point2.setName("Especial Points");
		point2.setPoints(10);
		assertEquals("Points", point1.getName());
		assertEquals(new Integer(0), point1.getPoints());
		assertEquals("Especial Points", point2.getName());
		assertEquals(new Integer(10), point2.getPoints());
		point1 = (Points) point1.add(point2);
		assertNull(point1);
	}

	@Test
	public void createBadge() {
		badge1.setName("Badge");
		assertEquals("Badge", badge1.getName());
	}

	@Test
	public void addBagdesWithSameNames() {
		badge1.setName("Badge");
		badge2.setName("Badge");
		assertEquals("Badge", badge1.getName());
		assertEquals("Badge", badge2.getName());
		badge2 = (Badge) badge1.add(badge2);
		assertEquals(badge1, badge2);
	}

	@Test
	public void addBadgeNull() {
		badge1.setName("Badge");
		badge2 = null;
		assertEquals("Badge", badge1.getName());
		assertNull(badge2);
		badge2 = (Badge) badge1.add(badge2);
		assertNull(badge2);
	}

	@Test
	public void addBagdesWithDiferentNames() {
		badge1.setName("Badge");
		badge2.setName("Especial Badge");
		assertEquals("Badge", badge1.getName());
		assertEquals("Especial Badge", badge2.getName());
		badge2 = (Badge) badge1.add(badge2);
		assertNull(badge2);
	}

	@Test
	public void addBagdesToPoint() {
		badge1.setName("Badge");
		point1.setName("Points");
		point1.setPoints(10);
		assertEquals("Badge", badge1.getName());
		assertEquals("Points", point1.getName());
		assertEquals(new Integer(10), point1.getPoints());
		badge2 = (Badge) badge1.add(point1);
		assertNull(badge2);
		assertEquals("Badge", badge1.getName());
		assertEquals("Points", point1.getName());
		assertEquals(new Integer(10), point1.getPoints());
	}
}
