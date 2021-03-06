package pl.michalskrzypek.ah.spaceobjects;

import java.awt.Rectangle;

import pl.michalskrzypek.ah.gui.AsteroidGameBoard;
import pl.michalskrzypek.ah.spaceobjects.common.SpaceObject;

public class Asteroid extends SpaceObject {

	private static double speed;
	private static final int ASTEROID_WIDTH = 40;
	private static final int ASTEROID_HEIGHT = 40;
	private static int[] polygonXCoordinates = { -20, -10, -1, 0, 10, 8, 20, 4, -2, -10, -20 };
	private static int[] polygonYCoordinates = { -5, -13, -10, -20, -7, -3, 5, 20, 8, 12, -5 };
	private static int numberOfCorners = 11;

	public Asteroid(int[] polygonXCoordinates, int[] polygonYCoordinates) {
		super(polygonXCoordinates, polygonYCoordinates, numberOfCorners);

		this.setXVelocity(Math.random() * speed + 1);
		this.setYVelocity(Math.random() * speed + 1);

		this.setWidth(ASTEROID_WIDTH);
		this.setHeight(ASTEROID_HEIGHT);

		this.setOnScreen(true);
	}

	// Bounds for collision detection
	public Rectangle getBounds() {
		return new Rectangle(this.xpoints[0], this.ypoints[3], 40, 40);
	}

	public double setXMoveAngle(double xMoveAngle) {
		return (double) (Math.cos(xMoveAngle * Math.PI / 180));
	}

	public double setYMoveAngle(double yMoveAngle) {
		return (double) (Math.sin(yMoveAngle * Math.PI / 180));
	}

	public void move() {
		if (super.xpoints[0] <= 0 || super.xpoints[6] >= AsteroidGameBoard.FRAME_WIDTH) {
			this.setXVelocity(this.getXVelocity()*(-1));
		}
		if (super.ypoints[3] <= 0 || super.ypoints[7] >= AsteroidGameBoard.FRAME_HEIGHT) {
			this.setYVelocity(this.getYVelocity()*(-1));
		}
		for (int i = 0; i < super.xpoints.length; i++) {
			super.xpoints[i] += this.getXVelocity();
		}
		for (int i = 0; i < super.ypoints.length; i++) {
			super.ypoints[i] += this.getYVelocity();
		}
	}

	public static int[] getInitialXPosition(int randomInitialXPosition) {
		int[] tempXPosition = (int[]) polygonXCoordinates.clone();
		for (int i = 0; i < tempXPosition.length; i++) {
			tempXPosition[i] += randomInitialXPosition;
		}
		return tempXPosition;
	}

	public static int[] getInitialYPosition(int randomInitialYPosition) {
		int[] tempYPosition = (int[]) polygonYCoordinates.clone();
		for (int i = 0; i < tempYPosition.length; i++) {
			tempYPosition[i] += randomInitialYPosition;
		}
		return tempYPosition;
	}

	public static double getSpeed() {
		return speed;
	}

	public static void setSpeed(double theSpeed) {
		speed = theSpeed;
	}
}
