package dev.se133.carpool.commute;

import dev.se133.carpool.member.ConcreteMember;
public class RideScheduleDriver{

	ConcreteMember m1 = new ConcreteMember(52, "Joe", new Address("23 First St."));
	ConcreteMember m2 = new ConcreteMember(34, "Jack", new Address("123 Fake St."));
	ConcreteMember m3 = new ConcreteMember(15, "Robert", new Address("52 2nd St."));
	ConcreteMember[] passengers = {m2, m3};
	CommutePoint[] stops = {new CommutePoint(m3.getAddress(), new Time(9, 45))};
	Carpool testPool = new Carpool(new Commute(Day.MONDAY, new CommutePoint(m1.getAddress(), new Time(9, 30)), new CommutePoint(m2.getAddress(), new Time(10, 00)), stops), m1, passengers);
}
