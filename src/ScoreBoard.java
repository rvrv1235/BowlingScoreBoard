import java.util.ArrayList;
import java.util.List;

/**
 * Score board class to trigger the auto run and dispaly the final score board
 * @author Ravi Varma
 *
 */
public class ScoreBoard {

	/* Maximum Frames */
	public static final int MAX_FRAME = 10;

	/* List of all Frames */
	private List<Frame> frames;

	/* Initialize rolledFrame to null to keep track of previous frames */
	private static Frame rolledFrame = null;

	/* Default Frames */
	public static final int FINAL_FRAME = 9;

	/**
	 * Initialize score board with max frames - 10
	 */
	public ScoreBoard() {

		frames = new ArrayList<>(MAX_FRAME);
		for (int i = 0; i < MAX_FRAME; i++) {
			frames.add(new Frame(i));
		}

	}

	/**
	 * Roll for each frame. Additional roll for last Frame.
	 * @param currentFrame reference to current frame
	 */
	private Frame bowl(Frame currentFrame) {

		currentFrame = ScoreBoardUtils.roll(currentFrame); // FIRST ROLL
		//currentFrame = ScoreBoardUtils.testroll(currentFrame); // ** TEST FIRST ROLL

		Frame previousFrame = currentFrame.getPreviousFrame();

		ScoreBoardUtils.updatePreviousStrikeFrameCount(currentFrame, previousFrame);

		if (currentFrame.getId() != FINAL_FRAME && currentFrame.isStrike()) {
			return currentFrame;
		} else {
			currentFrame = ScoreBoardUtils.roll(currentFrame);  //SECOND ROLL
			//currentFrame = ScoreBoardUtils.testroll(currentFrame);  // ** SECOND TEST ROLL
			ScoreBoardUtils.updatePreviousFrameCount(currentFrame, previousFrame);
			ScoreBoardUtils.updateCurrentFrameCount(previousFrame, currentFrame);

			if (currentFrame.getId() == FINAL_FRAME && (currentFrame.isStrike() || currentFrame.isSpare())) {

				currentFrame = ScoreBoardUtils.roll(currentFrame); // THIRD ROLL
				//currentFrame = ScoreBoardUtils.testroll(currentFrame); // ** THIRD TEST ROLL
				ScoreBoardUtils.updateCurrentFrameCount(previousFrame, currentFrame);
			}
		}

		return currentFrame;
	}


	/* Retrieve frames list */
	public List<Frame> getFrames() {
		return frames;
	}

	
	/**
	 * Loop through 10 - 12 frames
	 */

	public void run() {

		List<Frame> frames = this.getFrames();

		for (int idx = 0; idx < frames.size(); idx++) {

			Frame currentFrame = frames.get(idx);
			currentFrame.setPreviousFrame(rolledFrame);
			rolledFrame = this.bowl(currentFrame);

		}
	}


	/**
	 * Print score board
	 */
	public void printScoreBoard() {

		List<Frame> frames = this.getFrames();

		for (int i = 0; i < frames.size(); i++) {
			Frame frame = frames.get(i);
			if (i < 9) {
				System.out
						.println("Frame : " + (i+1) + " -- Roll1 Score: " + frame.getFirstRollPoints() + " -- Roll2 Score: "
								+ frame.getSecondRollPoints() + " -- Frame Score: " + frame.getFramePointsCount());
			} else {
				System.out.println("Frame : " + (i+1) + " -- Roll1 Score: " + frame.getFirstRollPoints()
						+ " -- Roll2 Score: " + frame.getSecondRollPoints() + " -- Roll3 Score: "
						+ frame.getThirdRollPoints() + " -- Frame Score: " + frame.getFramePointsCount());
			}
		}
	}

}
