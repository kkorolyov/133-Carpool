package dev.se133.project.commute;

/**
 * Describes a fixed, unique address.
 */
public class Address {
	private String addressName;
	
	/**
	 * Constructs an address with a specified name
	 * @param addressName name of address
	 */
	public Address(String addressName) {
		this.addressName = addressName;
	}
	
	/** @return name of this address */
	public String getAddressName() {
		return addressName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addressName == null) ? 0 : addressName.hashCode());
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
		if (addressName == null) {
			if (other.addressName != null)
				return false;
		} else if (!addressName.equals(other.addressName))
			return false;
		return true;
	}
	
	/** @return name of this address */
	@Override
	public String toString() {
		String toString = addressName;
		
		return toString;
	}
}
