import java.util.ArrayList;
import java.util.List;

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
	 * Initialize board with max frames - 12
	 */
	public ScoreBoard() {

		frames = new ArrayList<>(MAX_FRAME);
		for (int i = 0; i < MAX_FRAME; i++) {
			frames.add(new Frame(i));
		}

	}

	/*
	 * Roll for each frame. Special case for last Frame.
	 */
	private Frame bowl(Frame currentFrame) {

		currentFrame = ScoreBoardUtils.roll(currentFrame); // FIRST ROLL

		Frame previousFrame = currentFrame.getPreviousFrame();

		ScoreBoardUtils.updatePreviousStrikeFrameCount(currentFrame, previousFrame);

		if (currentFrame.getId() != FINAL_FRAME && currentFrame.isStrike()) {
			return currentFrame;
		} else {
			currentFrame = ScoreBoardUtils.roll(currentFrame);
			ScoreBoardUtils.updatePreviousFrameCount(currentFrame, previousFrame);
			ScoreBoardUtils.updateCurrentFrameCount(previousFrame, currentFrame);

			if (currentFrame.getId() == FINAL_FRAME && (currentFrame.isStrike() || currentFrame.isSpare())) {

				currentFrame = ScoreBoardUtils.roll(currentFrame); // SECOND ROLL
				ScoreBoardUtils.updateCurrentFrameCount(previousFrame, currentFrame);
			}
		}

		return currentFrame;
	}

	public static void rollAgain(Frame currentFrame, Frame previousFrame) {

		ScoreBoardUtils.updatePreviousStrikeFrameCount(currentFrame, previousFrame);
		currentFrame = ScoreBoardUtils.roll(currentFrame); // SECOND ROLL
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

	/*
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
