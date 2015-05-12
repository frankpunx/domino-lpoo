package domino.logic;

import java.io.Serializable;

/**
 * Represents a pair of values.
 */
public class Pair implements Serializable {

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = -2249342788419181329L;
	private int first;
	private int second;
	
	/**
	 * Creates a new pair of values
	 * @param f The first value
	 * @param s The second value
	 */
	public Pair(int f, int s) {
		first = f;
		second = s;
	}

	/**
	 * Returns the first value of the pair
	 * @return The pair's first value
	 */
	public final int getFirst() {
		return first;
	}

	/**
	 * Returns the second value of the pair
	 * @return The pair's second value
	 */
	public final int getSecond() {
		return second;
	}

	/**
	 * Allows to change the first value of the pair, while not changing the second one
	 * @param first The new value 
	 */
	public final void setFirst(int first) {
		this.first = first;
	}

	/**
	 * Allows to change the second value of the pair, while not changing the first one
	 * @param second The new value 
	 */
	public final void setSecond(int second) {
		this.second = second;
	}
	
	public final int getSum() {
		return first + second;
	}
	
	public final boolean isSameValues() {
		return this.first == this.second;
	}
	
	public void swapValues() {
		int temp = this.first;
		this.first = this.second;
		this.second = temp;
	}
	
	public String toString() {
		return "[" + first + ", " + second + "]";
	}
}
