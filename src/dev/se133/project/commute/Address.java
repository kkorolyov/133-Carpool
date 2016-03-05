package dev.se133.project.commute;	// TODO More generic package, used in BasicMember as well

/**
 * An address.
 */
public class Address {
	private String address;
	
	/**
	 * Constructs a new address.
	 * @param address address
	 */
	public Address(String address) {
		this.address = address;
	}
	
	/** @return address as a string */
	@Override
	public String toString() {
		return address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Address))
			return false;
		Address other = (Address) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}
}
