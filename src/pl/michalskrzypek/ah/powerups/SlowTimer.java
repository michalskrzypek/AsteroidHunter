package pl.michalskrzypek.ah.powerups;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import pl.michalskrzypek.ah.main.AsteroidGameBoard;
import pl.michalskrzypek.ah.objects.Asteroid;

@SuppressWarnings("serial")
public class SlowTimer extends Polygon implements PowerUp {

	private boolean onScreen;
	private static int[] polygonXCoordinates = { -8, 8, 8, -8, -8 };
	private static int[] polygonYCoordinates = { -8, -8, 8, 8, -8 };
	private static int numberOfCorners = 5;
	double centerX;
	double centerY;
	private double yVelocity = 2;

	public static int brokenAsteroids = 0;
	public static int howMany = 0;
	public int which = 0;

	public SlowTimer(int[] polygonXCoordinates) {
		super(polygonXCoordinates, polygonYCoordinates, numberOfCorners);
		onScreen = true;
	}

	// Bounds for collision detection
	public Rectangle getBounds() {
		return new Rectangle(this.xpoints[0], this.ypoints[0], 40, 30);
	}

	public void setOnScreen(boolean bool) {
		this.onScreen = bool;
	}

	public boolean getOnScreen() {
		return this.onScreen;
	}

	public void setYVelovity(double yVel) {
		this.yVelocity = yVel;
	}

	public double getYVelocity() {
		return this.yVelocity;
	}

	public double getXCenter() {
		return centerX;
	}

	public double getYCenter() {
		return centerY;
	}

	public void setXCenter(double xCent) {
		this.centerX = xCent;
	}

	public void setYCenter(double yCent) {
		this.centerY = yCent;
	}

	public int[] getPolyYCoordinates() {
		return polygonYCoordinates;
	}

	public void move() {
		if (this.onScreen == true) {
			Rectangle rectToCheck = this.getBounds();
			Rectangle shipBounds = AsteroidGameBoard.ship.getBounds();

			for (SlowTimer st : AsteroidGameBoard.slowTimers) {
				if (st.onScreen == true) {
					Rectangle otherRect = st.getBounds();
					Point p3 = otherRect.getLocation();

					// collision detection with ship
					if (otherRect.intersects(shipBounds)) {
						st.setOnScreen(false);

						AsteroidGameBoard.slowTime = true;
						AsteroidGameBoard.playSound("./sounds/collect.wav");

						// ArrayList<Double> asteroidsXVelo = new
						// ArrayList<Double>(AsteroidGameBoard.asteroids.size());
						// ArrayList<Double> asteroidsYVelo = new
						// ArrayList<Double>(AsteroidGameBoard.asteroids.size());
						for (Asteroid as : AsteroidGameBoard.asteroids) {
							// asteroidsXVelo.add(Math.abs(as.getXVelocity()));
							// asteroidsYVelo.add(Math.abs(as.getYVelocity()));
							as.setXVelovity(as.getXVelocity() * 0.5);
							as.setYVelovity(as.getYVelocity() * 0.5);
							
							if (as.getXVelocity() >= 2) {
						
							}

							if (as.getYVelocity() >= 2) {

							}

						}

						ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
						executor.schedule(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								AsteroidGameBoard.slowTime = false;
								for (Asteroid as : AsteroidGameBoard.asteroids) {
									as.setXVelovity(as.getXVelocity() * 2);
									as.setYVelovity(as.getYVelocity() * 2);
								}

							}
						}, 5000, TimeUnit.MILLISECONDS);

					}

				}
				if (st.ypoints[0] > AsteroidGameBoard.frameHeight) {
					st.setOnScreen(false);
				}

			}

			for (int i = 0; i < super.ypoints.length; i++) {
				super.ypoints[i] += yVelocity;
			}
		}
	}

	public static int[] getInitialXPosition(int randomInitialXPosition) {
		int[] tempXPosition = (int[]) polygonXCoordinates.clone();
		for (int i = 0; i < tempXPosition.length; i++) {
			tempXPosition[i] += randomInitialXPosition;
		}

		return tempXPosition;
	}

}
