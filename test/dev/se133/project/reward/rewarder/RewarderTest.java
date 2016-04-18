package dev.se133.project.reward.rewarder;

import static org.junit.Assert.fail;

import org.junit.*;

import dev.se133.project.car.Car;
import dev.se133.project.carpool.Carpool;
import dev.se133.project.commute.Address;
import dev.se133.project.commute.Commute;
import dev.se133.project.commute.Stop;
import dev.se133.project.commute.Time;
import dev.se133.project.member.BasicMember;
import dev.se133.project.member.Member;
import dev.se133.project.member.MemberState;
import dev.se133.project.reward.CashReward;
import dev.se133.project.reward.PointReward;
import dev.se133.project.reward.RewardType;
import dev.se133.project.schedule.CommuteSchedule;
import dev.se133.project.schedule.SortedCommuteSchedule;

public class RewarderTest {
	private static final long CASH_REWARD_COUNT = 10,
														POINT_REWARD_COUNT = 50;
	private static final RewardType[] REWARD_TYPES = {new CashReward(CASH_REWARD_COUNT),
																										new PointReward(POINT_REWARD_COUNT)};
	private static final int 	NUM_MEMBERS = 5,
														NUM_STOPS = 3;
	private static final int SECONDS_BETWEEN_STOPS = 60 * 5;
	
	private static Carpool carpool;
	
	private Rewarder rewarder;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRewardType() {
		fail("Not yet implemented");
	}

	@Test
	public void testReward() {	// TODO Assertions
		carpool = buildCarpool(NUM_MEMBERS, NUM_STOPS);
		System.out.println("Wallets at start:");
		printWallets(carpool);
		
		for (RewardType rewardType : REWARD_TYPES) {
			testRewarder(new DriverRewarder(carpool, rewardType));
			testRewarder(new CarpoolRewarder(carpool, rewardType));
		}
	}
	private void testRewarder(Rewarder rewarder) {
		System.out.println(	"Reward type = " + rewarder.getRewardType().getClass().getSimpleName() + ", "
											+ "Rewarder = " + rewarder.getClass().getSimpleName());
		
		rewarder.reward();
		
		printWallets(carpool);
	}
	
	private static void printWallets(Carpool carpool) {
		for (Member member : carpool.getCar().getInhabitants()) {
			System.out.println(member.getName() + " - " + member.getState() + "\n\t" + member.getWallet());
		}
		System.out.println();
	}

	private static Carpool buildCarpool(int numMembers, int numStops) {
		Carpool carpool = new Carpool(buildCommute(numStops), buildCar(numMembers));
		
		return carpool;
	}
	private static Commute buildCommute(int numStops) {
		Commute commute = new Commute();
		
		Time startTime = new Time();
		for (int i = 0; i < numStops; i++) {
			Time currentTime = Time.timeAfter(startTime, SECONDS_BETWEEN_STOPS * i);
			Address currentAddress = new Address("Stop " + i);
			Stop currentStop = new Stop(currentTime, currentAddress);
			
			commute.addStop(currentStop);
		}
		return commute;
	}
	private static Car buildCar(int numMembers) {
		Car car = new Car(numMembers);
		
		for (Member member : buildMembers(numMembers)) {
			car.addPassenger(member);
			
			car.setDriver();
		}		
		return car;
	}
	private static Member[] buildMembers(int numMembers) {
		Member[] members = new Member[numMembers];
		
		for (int i = 0; i < members.length; i++) {
			Address currentAddress = new Address("MemberAddress" + i);
			CommuteSchedule currentSchedule = new SortedCommuteSchedule();
			MemberState currentState = i == 0 ? new MemberState.Driver() : new MemberState.Passenger();
			
			members[i] = new BasicMember(i, "Member " + i, currentAddress, currentSchedule, currentState);
		}
		return members;
	}
}
