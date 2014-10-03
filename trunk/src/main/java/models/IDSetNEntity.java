package models;

public class IDSetNEntity {
	private Long min;
	private Long max;

	public IDSetNEntity(Long min, Long max) {
		this.setMin(min);
		this.setMax(max);
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(Long min) {
		this.min = min;
	}

	/**
	 * @return the min
	 */
	public Long getMin() {
		return min;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(Long max) {
		this.max = max;
	}

	/**
	 * @return the max
	 */
	public Long getMax() {
		return max;
	}
}
