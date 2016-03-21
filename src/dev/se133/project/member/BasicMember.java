package dev.se133.project.member;

import java.util.LinkedList;
import java.util.List;

import dev.se133.project.carpool.Carpool;
import dev.se133.project.commute.Address;
import dev.se133.project.member.car.Car;
import dev.se133.project.member.garage.Garage;
import dev.se133.project.member.wallet.Wallet;
import dev.se133.project.observer.CarEvent;
import dev.se133.project.observer.Observer;
import dev.se133.project.schedule.CommuteSchedule;

/**
 * A basis member implementation
 */
public class BasicMember implements Member, Observer {
	private int id;
	private String name;
	private Address address;
	private Garage garage = new Garage();
	private Wallet wallet;
	private CommuteSchedule preferredCommutes;	// TODO Change to only destination points
	private Carpool currentCarpool;
	private MemberState state;
	private List<MemberListener> listeners = new LinkedList<>();
	
	// TODO Ref to set of carpools?
	private int distanceFromDestination = 10;
	
	
	//Driver attributes
	private int maxTime;	//The maximum amount of time for which the driver is willing to commute
	private int maxDistance;//The maximum amount of distance the driver is willing to drive

	/** 
	 * Constructs a new member.
	 * <p>The member has an initial state of {@code MemberState.Passenger}
	 * @see #BasicMember(int, String, Address, CommuteSchedule, MemberState)
	 */
	public BasicMember(int id, String name, Address address, CommuteSchedule preferredCommutes) {
		this(id, name, address, preferredCommutes, new MemberState.Passenger());
	}
	/**
	 * Constructs a new member.
	 * @param id member's unique ID
	 * @param name member's name
	 * @param address member's address
	 * @param preferredCommutes schedule of preferred commutes
	 * @param state member's initial state
	 */
	public BasicMember(int id, String name, Address address, CommuteSchedule preferredCommutes, MemberState state) {
		this(id, name, address, new Wallet(), preferredCommutes, state);
	}
	/**
	 * Constructs a new member.
	 * @param id member's unique ID
	 * @param name member's name
	 * @param address member's address
	 * @param wallet member's wallet
	 * @param preferredCommutes schedule of preferred commutes
	 * @param state member's initial state
	 */
	public BasicMember(int id, String name, Address address, Wallet wallet, CommuteSchedule preferredCommutes, MemberState state) {
		this.id = id;
		setName(name);
		setAddress(address);
		this.wallet = wallet;
		setPreferredCommutes(preferredCommutes);
		setState(state);
	}
	
	@Override
	public Car getCar() {
		return state.getCar(this);
	}
	
	@Override
	public Wallet getWallet() {
		return wallet;
	}
	
	/*
	private void notifyObserver(int obv){
		observers[obv].stateChanged(new ChangeEvent(this));
	}
	*/
	
	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Address getAddress() {
		return address;
	}
	@Override
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public Garage getGarage() {
		return garage;
	}
	@Override
	public void setGarage(Garage garage) {
		this.garage = garage;
	}
	
	@Override
	public CommuteSchedule getPreferredCommutes() {
		return preferredCommutes;
	}
	@Override
	public void setPreferredCommutes(CommuteSchedule preferredCommutes) {
		this.preferredCommutes = preferredCommutes;
	}
	
	@Override
	public Carpool getCurrentCarpool() {
		return currentCarpool;
	}
	@Override
	public void setCurrentCarpool(Carpool carpool) {
		this.currentCarpool = carpool;
	}
	
	@Override
	public MemberState getState() {
		return state;
	}
	@Override
	public void setState(MemberState state) {
		if (this.state != null && this.state.getClass() == state.getClass())
			return;	// No need to set to same state
		
		this.state = state;
		
		notifyStateChanged();	// Notify all listeners of state change
	}
	private void notifyStateChanged() {
		for (MemberListener listener : listeners)
			listener.stateChanged(state);
	}
	
	@Override
	public void addListener(MemberListener listener) {
		listeners.add(listener);
	}
	@Override
	public void removeListener(MemberListener listener) {
		listeners.remove(listener);
	}
	@Override
	public void clearListeners() {
		listeners.clear();
	}
	
	@Override
	public int compareTo(Member o) {
		return Integer.compare(id, o.getId());
	}
	
	@Override
	public double getDistanceFromDestination() {
		return distanceFromDestination;
	}
	
	@Override
	public double getMaxTime() {
		return maxTime;
	}
	
	@Override
	public double getMaxDistance() {
		return maxDistance;
	}
	@Override
	public void stateChanged(CarEvent e) 
	{
		System.out.println(this.getName() + ": " + e.getMessage());
	}
}
