package dev.se133.project.entity.schedule;

import java.util.Set;
import java.util.TreeSet;

/**
 * A {@code Schedule} implementation which consistently keeps its entities in sorted order.
 * @param <E> sorted scheduled entity
 */
public abstract class SortedSchedule<E extends Comparable<E>> implements Schedule<E> {
	private Set<E> scheduledEntities = new TreeSet<E>();	// O(logn) operations on sorted set
	
	// TODO Everything
}
