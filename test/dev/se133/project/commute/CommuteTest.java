package dev.se133.project.commute;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dev.se133.project.commute.Address;
import dev.se133.project.commute.Commute;
import dev.se133.project.commute.Stop;
import dev.se133.project.commute.Time;

@SuppressWarnings("javadoc")
public class CommuteTest {
	private static final int NUM_TEST_STOPS = 1024;
	
	private Commute commute;

	@Before
	public void setUp() throws Exception {
		commute = new Commute();
	}

	@Test
	public void testHasNextStop() {
		Stop[] testStops = buildStops(NUM_TEST_STOPS);
		
		for (Stop testStop : testStops) {
			commute.addStop(testStop);
		}
		
		for (int i = 0; i < testStops.length; i++) {
			assertTrue(commute.hasNextStop());
			commute.nextStop();
		}
		assertFalse(commute.hasNextStop());
	}

	@Test
	public void testNextStop() {
		Stop[] testStops = buildStops(NUM_TEST_STOPS);
		
		for (Stop testStop : testStops) {
			commute.addStop(testStop);
		}
		for (int i = 0; i < testStops.length; i++) {
			assertEquals(testStops[i], commute.nextStop());
		}
	}

	@Test
	public void testGetCurrentStop() {
		Stop[] testStops = buildStops(NUM_TEST_STOPS);
		
		for (Stop testStop : testStops) {
			commute.addStop(testStop);
		}
		
		assertEquals(null, commute.getCurrent());
		for (int i = 0; i < testStops.length; i++) {
			commute.nextStop();
			assertEquals(testStops[i], commute.getCurrent());
		}
	}
	
	@Test
	public void testToString() {
		for (Stop stop : buildStops(5)) {
			commute.addStop(stop);
		}
		System.out.println(commute.toString());
	}

	private static Stop[] buildStops(int numStops) {
		Stop[] stops = new Stop[numStops];
		Time initialTime = new Time();
		int commuteGapMinutes = 5,
				commuteGapSeconds = commuteGapMinutes * 60;
		
		for (int i = 0; i < stops.length; i++) {
			Time currentTime = Time.timeAfter(initialTime, i * commuteGapSeconds);
			Address currentAddress = new Address("StopAddress" + i);
			stops[i] = new Stop(currentTime, currentAddress);
		}
		return stops;
	}
}
