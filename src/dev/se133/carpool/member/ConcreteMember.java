package dev.se133.carpool.member;

import dev.se133.carpool.member.property.Address;
import dev.se133.carpool.member.state.MemberState;

/**
 * A concrete member implementation
 */
public class ConcreteMember implements Member {
	private String name;
	private Address address;
	
	private MemberState state;
	
	/** 
	 * Constructs a new member without an initial state.
	 * @see #ConcreteMember(String, Address, MemberState)
	 */
	public ConcreteMember(String name, Address address) {
		this(name, address, null);
	}
	/**
	 * Constructs a new member.
	 * @param name member's name
	 * @param address member's address
	 * @param state member's initial state
	 */
	public ConcreteMember(String name, Address address, MemberState state) {
		setName(name);
		setAddress(address);
		setState(state);
	}
	
	/** @return member's name */
	public String getName() {
		return name;
	}
	/** @param name new name */
	public void setName(String name) {
		this.name = name;
	}
	
	/** @return member address */
	public Address getAddress() {
		return address;
	}
	/** @param address new address */
	public void setAddress(Address address) {
		this.address = address;
	}
	
	/** @return current state */
	public MemberState getState() {
		return state;
	}
	/** @param state new state */
	public void setState(MemberState state) {
		this.state = state;
	}
}
