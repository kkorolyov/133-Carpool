package dev.se133.project.map;

import java.util.HashSet;
import java.util.Set;

import dev.se133.project.commute.Address;

/**
 * {@code AddressMap} implementation using an array to define the map.
 */
public class ArrayAddressMap implements AddressMap {
	Address[][] map;
	
	/**
	 * Constructs an empty map of the specified dimensions.
	 * @param xSize number of points in the map along the x-axis
	 * @param ySize	number of points in the map along the y-axis
	 */
	public ArrayAddressMap(int xSize, int ySize) {
		map = new Address[xSize][ySize];
	}
	
	/**
	 * Returns the coordinates of the specified address, if it is contained in this map.
	 * @param address address to search for
	 * @return coordinates of address, in the format <code>{x, y}</code>, or <code>{-1, -1}</code> if this map does not contain the specified address
	 */
	public int[] getCoordinates(Address address) {
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				if (map[x][y] != null && map[x][y].equals(address))
					return new int[]{x, y};
			}
		}
		return new int[]{-1, -1};
	}
	
	/**
	 * Returns the address at the specified coordinates.
	 * @param x x-coordinate of address to get
	 * @param y y-coordinate of address to get
	 * @return address at the specified coordinates, or {@code null} if the coordinates are not set
	 */
	public Address getAddress(int x, int y) {
		return map[x][y];
	}

	/**
	 * Attempts to add an address to the map at the specified coordinates.
	 * The address is added only if the map does not yet contain an address at the specified coordinates.
	 * @param address address to add
	 * @param x x-coordinate of new address
	 * @param y y-coordinate of new address
	 * @return {@code true} if address added successfully
	 */
	public boolean addAddress(Address address, int x, int y) {
		return addAddress(address, x, y, false);
	}
	/**
	 * Adds an address to the map at specified coordinates.
	 * If the map already contains an address at those coordinates, it is overwritten with the new address.
	 * @return {@code true} if the operation overwrote an existing address
	 * @see #addAddress(Address, int, int)
	 */
	public boolean overwriteAddress(Address address, int x, int y) {
		return !addAddress(address, x, y, true);	// True if something overwritten
	}
	private boolean addAddress(Address address, int x, int y, boolean force) {
		boolean success = false;
		
		if (map[x][y] == null) {
			map[x][y] = address;
			
			success = true;	// Added address without overwriting
		}
		else if (force) {
			map[x][y] = address;
		}
		return success;
	}
	
	/**
	 * Removes an address from the map at the specified coordinates.
	 * @param x x-coordinate of address to remove
	 * @param y y-coordinate of address to remove
	 * @return removed address, or {@code null} if no address at the specified coordinates
	 */
	public Address removeAddress(int x, int y) {
		Address toReturn = map[x][y];
		
		map[x][y] = null;
		
		return toReturn;
	}
	
	/**
	 * @param address address to search for
	 * @return {@code true} if this map contains the specified address
	 */
	public boolean containsAddress(Address address) {
		int[] coordinates = getCoordinates(address);
		boolean success = false;
		
		if (coordinates[0] >= 0)
			success = true;
		
		return success;
	}
	
	@Override
	public double getDistance(Address address1, Address address2) {
		double distance = -1;
		int[] c1, c2;
		
		if ((c1 = getCoordinates(address1))[0] >= 0) {	// No reason to search for 2nd address if 1st does not exist
			if ((c2 = getCoordinates(address2))[0] >= 0) {
				int xDist = Math.abs(c1[0] - c2[0]),
						yDist = Math.abs(c1[1] - c2[1]);
				
				distance = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
			}
		}
		//System.out.println("Address1: " + address1 + " Address2: " + address2 + " distance: " + distance);
		return distance;
	}

	@Override
	public Set<Address> getAllAddresses() {
		Set<Address> toReturn = new HashSet<>();
		
		for (Address[] column : map) {
			for (Address address : column) {
				if (address != null)
					toReturn.add(address);
			}
		}
		return toReturn;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int longest = 0;
		for(int i = 0; i < map[0].length; i++) {
			for(int j = 0; j < map.length; j++) {
				if(map[i][j] != null && map[i][j].toString().length() > longest) {
					longest = map[i][j].toString().length();
				}
			}
		}
		for (int y = 0; y < map[0].length; y++) {
			for (int x = 0; x < map.length; x++) {
				if(map[x][y] != null)
					builder.append(center(map[x][y].toString(), longest));
				else
					builder.append(center("*", longest));
				if (y < map.length)
					builder.append("|");
			}
			builder.append("\n");
			for (int i = 0; i < map.length * longest + map.length; i++) {
				builder.append('-');
			}
			builder.append("\n");
		}
		return builder.toString();
	}
	
	private static String center(String source, int length) {
		String expanded = source;
		boolean addToEnd = true;
		if (expanded.length() < length) {
			for (int i = expanded.length(); i < length; i++) {
				if (addToEnd)	// Add filler to end
					expanded += " ";	// Fill with spaces
				else	// Add filler to beginning
					expanded = " " + expanded;
				addToEnd = !addToEnd;	// Alternate adding to end and start
			}
		}
		return expanded;
	}
}
