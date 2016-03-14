package dev.se133.project.commute;

import static org.junit.Assert.*;

import org.junit.*;

import dev.se133.project.member.BasicMember;
import dev.se133.project.member.MemberState;
import dev.se133.project.schedule.SortedCommuteSchedule;

public class CarpoolTest {
	private static final int NUM_STOPS = 10;
	private static final Day DAY = Day.MONDAY;
	private static final int START_TIME = 7 * 60;
	private static final int CAR_CAPACITY = 5;
	
	private static Commute commute;
	private static Car car;
	
	private Carpool carpool;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		commute = new Commute();
		populateCommute();
		
		car = new Car(CAR_CAPACITY);
		populateCar();
	}
	private static void populateCommute() throws Exception {
		Address currentAddress;
		Time currentTime = new Time(DAY, START_TIME);
		for (int i = 0; i < NUM_STOPS; i++) {
			currentAddress = new Address("Stop" + i);
			currentTime = Time.timeAfter(currentTime, i);
			
			commute.addStop(new CommutePoint(currentAddress, currentTime));
		}
	}
	private static void populateCar() throws Exception {
		for (int i = 0; i < car.getCapacity(); i++) {
			car.addPassenger(new BasicMember(0, "Member" + i, new Address("MemberAddress" + i), new SortedCommuteSchedule(), i == 0 ? new MemberState.Driver() : new MemberState.Passenger()));
			car.setDriver();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		carpool = new Carpool(commute, car);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNextStop() {
		int stopCounter = 0;
		
		while (carpool.nextStop() != null) {
			stopCounter++;
		}
		assertEquals(stopCounter, carpool.getCommute().getStops().size());
	}

	@Test
	public void testAddListener() {
		carpool.addListener(new CarpoolListener() {
			@Override
			public void hitStop(CommutePoint stop) {
				System.out.println(	"Carpool hit stop\n"
													+ "\tAddress: " + stop.getAddress() + "\n"
													+ "\tTime: " + stop.getTime());
			}
			@Override
			public void hitEnd() {
				System.out.println(	"Carpool hit destination");;
			}
		});
		while (carpool.nextStop() != null);	// Loop through all stos
	}
}
