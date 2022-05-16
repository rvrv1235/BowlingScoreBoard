/**
* Bowling Game Application to simulate score board.
* 
* System auto generates Roll1, Roll2 scores for player1. Conditionally generates Roll3 for last frame.
* 
* Scoring Rules:
* Strike:
* If you knock down all 10 pins in the first shot of a frame, you get a strike.
* How to score: A strike earns 10 points plus the sum of your next two shots.
* 
* Spare:
* 
* If you knock down all 10 pins using both shots of a frame, you get a spare.
* How to score: A spare earns 10 points plus the sum of your next one shot.
* 
* Open Frame:
* 
* If you do not knock down all 10 pins using both shots of your frame (9 or fewer pins knocked down), you have an open frame.
* How to score: An open frame only earns the number of pins knocked down.
* 
* The 10th Frame:

* The 10th frame is a bit different:
* If you roll a strike in the first shot of the 10th frame, you get 2 more shots.
* If you roll a spare in the first two shots of the 10th frame, you get 1 more shot.
* If you leave the 10th frame open after two shots, the game is over and you do not get an additional shot.
* How to Score: The score for the 10th frame is the total number of pins knocked down in the 10th frame.

 * @author Ravi Varma
 *
 */

public class BowlingGameMain {

	public static void main(String[] args) {

		ScoreBoard sBoard = new ScoreBoard();
		sBoard.run();
		sBoard.printScoreBoard();
	}

}
