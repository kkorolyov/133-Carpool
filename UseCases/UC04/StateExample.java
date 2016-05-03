package UC04;

import dev.se133.project.car.Car;
import dev.se133.project.carpool.Carpool;
import dev.se133.project.carpool.CarpoolState;
import dev.se133.project.commute.*;
import dev.se133.project.member.Member;

public class StateExample {


	public static void main(String[] args) {
		Commute commute = new Commute();
		Car car = new Car();
		Member a = new Member(01, "Ryan", false, new Address("123 Fake St."), null, null, null);
		Member b = new Member(02, "Matt", false, new Address("124 Fake St."), null, null, null);
		commute.addStop(new Stop(new Time(), a.getAddress()));
		
		Carpool carpool = new Carpool(commute, car);
		
		car.addPassenger(a);
		car.addPassenger(b);
		car.removePassenger(a);
		b.setDriverStatus(true);
		car.addDriver(b);
		car.addPassenger(a);

		//This will throw an exception bc the carpool is in the incorrect state.
		carpool.dispatch();
	}

}
