/*
 * Frame Entity.
 */
public class Frame {

	/* Frame Identity */
	int id;
	/* First roll points count */
	private int firstRollPoints = -1;
	/* Second Roll points count */
	private int secondRollPoints = -1;
	/* Third roll points count for last frame */
	private int thirdRollPoints = -1;
	
	/* Total frame points count */
	private int framePointsCount = -1;

	private static int DEFAULT_MAX_POINTS = 10;

	/* Hold reference to previous frame */
	private Frame previousFrame = null;
	
	/* Hold temporary count addition */
	private int tempFrameCount = 0;
	
	private boolean isProcessed = false;

	public Frame(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFirstRollPoints() {
		return firstRollPoints;
	}

	public void setFirstRollPoints(int firstRollPoints) {
		this.firstRollPoints = firstRollPoints;
	}

	public int getSecondRollPoints() {
		return secondRollPoints;
	}

	public void setSecondRollPoints(int secondRollPoints) {
		this.secondRollPoints = secondRollPoints;
	}
	
	public int getThirdRollPoints() {
		return thirdRollPoints;
	}

	public void setThirdRollPoints(int thirdRollPoints) {
		this.thirdRollPoints = thirdRollPoints;
	}

	public void setFramePointsCount(int framePointCount) {
		this.framePointsCount = framePointCount;
	}

	public int getFramePointsCount() {
		return framePointsCount;
	}

	public Frame getPreviousFrame() {
		return previousFrame;
	}

	public void setPreviousFrame(Frame previousFrame) {
		this.previousFrame = previousFrame;
	}
	
	public int getTempFrameCount() {
		return tempFrameCount;
	}

	public void setTempFrameCount(int tempFrameCount) {
		this.tempFrameCount = tempFrameCount;
	}
	
	
	
	public boolean isProcessed() {
		return isProcessed;
	}

	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	/*
	 * Checks if First Roll is a Strike
	 */
	public boolean isStrike() {
		if (this.getFirstRollPoints() == DEFAULT_MAX_POINTS) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Checks if Second Roll is a Spare
	 */
	public boolean isSpare() {
		if (this.getSecondRollPoints() == (DEFAULT_MAX_POINTS - this.getFirstRollPoints()))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "Frame [id=" + id + ", firstRollPoints=" + firstRollPoints + ", secondRollPoints=" + secondRollPoints
				+ ", framePointsCount=" + framePointsCount + ", previousFrame=" + previousFrame + ", tempFrameCount="
				+ tempFrameCount + "]";
	}
	
	
}
