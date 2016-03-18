   package dev.se133.project.member;

import dev.se133.project.commute.Car;
import dev.se133.project.commute.Carpool;
import dev.se133.project.commute.CarpoolListener;

/**
 * A {@code CarpoolListener} implementation which rewards a carpool with points for each stop hit.
 */
public class CarpoolRewardListener implements CarpoolListener {
	private static int STOP_POINTS = 1;
	private static int END_BONUS = 10;
	
	@Override
	public void hitStop(Carpool carpool) {	// TODO Returning carpool may be too much
		Car car = carpool.getCar();
		for (Member inhabitant : car.getInhabitants()) {
			inhabitant.addPoints(STOP_POINTS);
		}
	}

	@Override
	public void hitEnd(Carpool carpool) {
		Member driver = carpool.getCar().getDriver();
		driver.addPoints(END_BONUS);
	}
}
