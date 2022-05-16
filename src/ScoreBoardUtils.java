import java.util.Random;
/**
 * Score Board Utility Class
 * @author Ravi Varma
 *
 */
public final class ScoreBoardUtils {

	/* Minimum points scored */
	private static int MIN_POINTS = 0;

	/* Maximum points scored */
	private static int MAX_POINTS = 10;

	/* Default Frames */
	public static final int FINAL_FRAME = 9;

	/* Private constructor to prevent instantiation */
	private ScoreBoardUtils() {

	}

	
	/**
	 * Roll method to randomly generate values between 1 - 10
	 * @param frame
	 * @return
	 */
	public static Frame roll(Frame frame) {

		if (frame.getFirstRollPoints() == -1) {
			frame.setFirstRollPoints(randomPoints(frame));
			if (frame.getId() != FINAL_FRAME && frame.isStrike()) {
				frame.setSecondRollPoints(MIN_POINTS);
			}

		} else if (frame.getSecondRollPoints() == -1) {

			if (frame.getId() == FINAL_FRAME && frame.getFirstRollPoints() == MAX_POINTS) {
				frame.setSecondRollPoints(defaultRandomPoints(frame));
			} else {
				frame.setSecondRollPoints(randomPoints(frame));
			}
		} else if (frame.getThirdRollPoints() == -1) {
				frame.setThirdRollPoints(defaultRandomPoints(frame));
		}

		updateFrameCount(frame);
		return frame;
	}

	
	/**
	 * Generate random points between roll 1 and roll2 from 0 - 10 in Roll 1 and the
	 * remaining for roll2
	 * @param frame
	 * @return
	 */
	private static int randomPoints(Frame frame) {
		int MIN_POINTS = 0, MAX_POINTS = 10;
		Random random = new Random();
		if (frame.getFirstRollPoints() != -1) {
			MAX_POINTS = MAX_POINTS - frame.getFirstRollPoints();
		}
		int points = random.nextInt((MAX_POINTS - MIN_POINTS) + 1) + MIN_POINTS;
		return points;
	}


	/**
	 * Default random points between 0 - 10
	 * @param frame
	 * @return
	 */
	private static int defaultRandomPoints(Frame frame) {
		int MIN_POINTS = 0, MAX_POINTS = 10;
		Random random = new Random();
		if (frame.getSecondRollPoints()!= -1 && frame.getSecondRollPoints() != MAX_POINTS) {
			MAX_POINTS = MAX_POINTS - frame.getSecondRollPoints();
		}
		int points = random.nextInt((MAX_POINTS - MIN_POINTS) + 1) + MIN_POINTS;
		return points;
	}

		
	/**
	 * Updates Current Frame counts with totals from current frame and previous frame
	 * @param currentFrame
	 */
	public static void updateFrameCount(Frame currentFrame) {

		if (currentFrame.getFirstRollPoints() >= 0 && currentFrame.getSecondRollPoints() < 0) {
			currentFrame.setFramePointsCount(currentFrame.getFirstRollPoints());
			return;
		}
		if (currentFrame.getFirstRollPoints() >= 0 && currentFrame.getSecondRollPoints() >= 0) {
			currentFrame.setFramePointsCount(currentFrame.getFirstRollPoints() + currentFrame.getSecondRollPoints());
		}
		if (currentFrame.getFirstRollPoints() >= 0 && currentFrame.getThirdRollPoints() >= 0) {
			currentFrame.setFramePointsCount(currentFrame.getFirstRollPoints() + currentFrame.getSecondRollPoints()
					+ currentFrame.getThirdRollPoints());
		}

	}

	
	/**
	 * Update previous strike frame count
	 * @param currentFrame
	 * @param previousFrame
	 */
	public static void updatePreviousStrikeFrameCount(Frame currentFrame, Frame previousFrame) {
		if (previousFrame != null && !previousFrame.isStrike() && !previousFrame.isSpare()) {
			updateCurrentFrameCount(previousFrame, currentFrame);
		}
		int attempt = 1;
		updatePreviousStrikeFrameCount(currentFrame, previousFrame, attempt);

	}

	/* recursively check previous strikes / spares and updates the frame counts */
	
	private static void updatePreviousStrikeFrameCount(Frame currentFrame, Frame previousFrame, int attempt) {

		if (previousFrame != null) {
			if (previousFrame.isStrike() && !previousFrame.isProcessed()) {

				previousFrame.setTempFrameCount(previousFrame.getFramePointsCount() + currentFrame.getFramePointsCount());

				if (attempt == 2) {
					previousFrame.setFramePointsCount(previousFrame.getFramePointsCount() + currentFrame.getTempFrameCount());
					previousFrame.setProcessed(true);
					updateCurrentFrameCount(previousFrame, currentFrame);
					return;
				} else {
					currentFrame = previousFrame;
					previousFrame = currentFrame.getPreviousFrame();
				}

				updatePreviousStrikeFrameCount(currentFrame, previousFrame, ++attempt);

			} else if (previousFrame.isSpare() && !previousFrame.isProcessed()) {
				previousFrame.setFramePointsCount(previousFrame.getFramePointsCount() + currentFrame.getFirstRollPoints());
				previousFrame.setProcessed(true);
				updateCurrentFrameCount(previousFrame, currentFrame);
			}
		}
	}

	
	/**
	 * Updates current frame count
	 * @param preivousFrame
	 * @param currentFrame
	 */

	public static void updateCurrentFrameCount(Frame preivousFrame, Frame currentFrame) {
		if (preivousFrame != null) {
			currentFrame.setFramePointsCount(currentFrame.getFramePointsCount() + preivousFrame.getFramePointsCount());
		}
	}

		
	/**
	 * Updates previous frame count
	 * @param currentFrame
	 * @param previousFrame
	 */
	public static void updatePreviousFrameCount(Frame currentFrame, Frame previousFrame) {

		if (previousFrame != null && previousFrame.isStrike()) {
			previousFrame.setFramePointsCount(previousFrame.getFramePointsCount() + currentFrame.getFramePointsCount());
		}
	}
	
	/**
	 * Test Roll method
	 * @param frame
	 * @return
	 */
	public static Frame testroll(Frame frame) {
			
			
			
			if (frame.getId() == 0 && frame.getFirstRollPoints() == -1) {frame.setFirstRollPoints(10);}
			else if (frame.getId() == 0 && frame.getSecondRollPoints() == -1) {	frame.setSecondRollPoints(0);}
			
		    else if (frame.getId() == 1 && frame.getFirstRollPoints() == -1) {frame.setFirstRollPoints(10);	}
			else if (frame.getId() == 1 && frame.getSecondRollPoints() == -1) {frame.setSecondRollPoints(0);}
			
		    else if (frame.getId() == 2 && frame.getFirstRollPoints() == -1) {frame.setFirstRollPoints(0); }
	    	else if (frame.getId() == 2 && frame.getSecondRollPoints() == -1) {frame.setSecondRollPoints(10); }
		
			else if (frame.getId() == 3 && frame.getFirstRollPoints() == -1) {frame.setFirstRollPoints(0); }
			else if (frame.getId() == 3 && frame.getSecondRollPoints() == -1) {	frame.setSecondRollPoints(10);}
			 
			else if (frame.getId() == 4 && frame.getFirstRollPoints() == -1) {frame.setFirstRollPoints(0); }
			else if (frame.getId() == 4 && frame.getSecondRollPoints() == -1) {	frame.setSecondRollPoints(10);}
			
		    else if (frame.getId() == 5 && frame.getFirstRollPoints() == -1) {frame.setFirstRollPoints(5); }
	    	else if (frame.getId() == 5 && frame.getSecondRollPoints() == -1) {frame.setSecondRollPoints(0); }
	
	    	else if (frame.getId() == 6 && frame.getFirstRollPoints() == -1) {frame.setFirstRollPoints(0); }
			else if (frame.getId() == 6 && frame.getSecondRollPoints() == -1) {	frame.setSecondRollPoints(5);}
			
			else if (frame.getId() == 7 && frame.getFirstRollPoints() == -1) {frame.setFirstRollPoints(10); }
			else if (frame.getId() == 7 && frame.getSecondRollPoints() == -1) {	frame.setSecondRollPoints(0);}
			
	    	else if (frame.getId() == 8 && frame.getFirstRollPoints() == -1) {frame.setFirstRollPoints(0); }
			else if (frame.getId() == 8 && frame.getSecondRollPoints() == -1) {	frame.setSecondRollPoints(3);}
			
			else if(frame.getId() == 9 && frame.getFirstRollPoints() == -1) {frame.setFirstRollPoints(7);	}
			else if(frame.getId() == 9 && frame.getSecondRollPoints() == -1) {frame.setSecondRollPoints(3);}
			else if(frame.getId() == 9 && frame.getThirdRollPoints() == -1) {frame.setThirdRollPoints(0); }
			
			
			 
			else if (frame.getFirstRollPoints() == -1) {
				frame.setFirstRollPoints(randomPoints(frame));
				if (frame.getId() != 9 && frame.isStrike()) {
					frame.setSecondRollPoints(MIN_POINTS);
				}
	
			} else if (frame.getSecondRollPoints() == -1) {
	
				if (frame.getId() == 9 && frame.getFirstRollPoints() == MAX_POINTS) {
					frame.setSecondRollPoints(defaultRandomPoints(frame));
				} else {
					frame.setSecondRollPoints(randomPoints(frame));
				}
			} else if (frame.getThirdRollPoints() == -1) {
				frame.setThirdRollPoints(defaultRandomPoints(frame));
			}
			
			updateFrameCount(frame);
			return frame;
		}

}
