package ec2;

import java.util.ArrayList;
import java.util.List;





public class GameOfLifeModel {
	
	private List<GameOfLifeObserver> observers;
	private boolean[][] modelArray;
	private int sizeSliderValue;
	private boolean torusModeOn;
	
	private int lowBirthThreshold;
	private int highBirthThreshold;
	private int lowSurvivalThreshold;
	private int highSurvivalThreshold;
	private int time;
	private boolean isRunning;
	private boolean isFirstTime;
	public GameOfLifeModel() {
		
		observers = new ArrayList<GameOfLifeObserver>();
		modelArray = new boolean[10][10];
		sizeSliderValue = 10;
		torusModeOn = false;
	
		lowBirthThreshold = 3;
		highBirthThreshold = 3;
		lowSurvivalThreshold = 2;
		highSurvivalThreshold = 3;
		time = 250;
		isRunning = false;
		isFirstTime = false;
	}
	
	public void toggleTorusMode() {
		torusModeOn = !torusModeOn;
	} 
	public int getTime() {
		return time;
	}
	public void setTime(int t) {
		time = t;
	}
	public boolean getIsRunning() {
		return isRunning;
	}
	public void toggleIsRunning() {
		
			isRunning = !isRunning;
		
	}
	public void setIsRunning(boolean run) {
		isRunning = run;
	}
	public boolean getIsTorusModeOn() {
		return torusModeOn;
	}
	public int getLowBirthThreshold() {
		return lowBirthThreshold;
	}
	public int getHighBirthThreshold() {
		return highBirthThreshold;
	}
	public int getLowSurvivalThreshold() {
		return lowSurvivalThreshold;
	}
	public int getHighSurvivalThreshold() {
		return highSurvivalThreshold;
	}
	public void advance() {
		if (!torusModeOn) {
			
		
			boolean[][] advancedArray = new boolean[modelArray.length][modelArray.length];
			boolean[][] largeDeathGrid = new boolean[modelArray.length + 2][modelArray.length + 2];
			/*
			 * Load into largeDeathGrid
			 * Edges should be false by default
			 */
			for (int i = 0; i < modelArray.length; i++) {
				for (int j = 0; j < modelArray.length; j++) {

					largeDeathGrid[i+1][j+1] = modelArray[i][j];

				}
			}

			for (int i = 1; i < largeDeathGrid.length - 1; i++) {
				for (int j = 1; j < largeDeathGrid.length - 1; j++) {
					//int totalDead = 0;
					int totalAlive = 0;
					//right square
					if (largeDeathGrid[i+1][j])
						totalAlive++;

					//left square
					if (largeDeathGrid[i-1][j])
						totalAlive++;
					//bottom square
					if (largeDeathGrid[i][j + 1])
						totalAlive++;
					//top square
					if (largeDeathGrid[i][j - 1])
						totalAlive++;

					//top right
					if (largeDeathGrid[i+1][j-1])
						totalAlive++;

					//top left
					if (largeDeathGrid[i-1][j-1])
						totalAlive++;

					//bottom right
					if (largeDeathGrid[i+1][j + 1])
						totalAlive++;

					//bottom left
					if (largeDeathGrid[i-1][j + 1])
						totalAlive++;

					//check if the cell is alive
					if (largeDeathGrid[i][j]) {
						if (totalAlive < lowSurvivalThreshold)
							advancedArray[i-1][j-1] = false;
						else if (totalAlive > highSurvivalThreshold)
							advancedArray[i-1][j-1] = false;
						else
							advancedArray[i-1][j-1] = true;
					}
					else
						if (totalAlive >= lowBirthThreshold && totalAlive <= highBirthThreshold)
							advancedArray[i-1][j-1] = true;

				}
			}
			setArray(advancedArray);
		}
		
		else {
			boolean[][] advancedArray = new boolean[modelArray.length][modelArray.length];
			//check top cell

			for (int i = 0; i < modelArray.length; i++) {
				for (int j = 0; j < modelArray.length; j++) {
					int totalAlive = 0;
					//right square
					
					if (modelArray[Math.floorMod(i + 1, modelArray.length)][j])
						totalAlive++;

					//left square
					//System.out.println((i - 1) % modelArray.length);
					if (modelArray[Math.floorMod((i - 1), modelArray.length)][j])
						totalAlive++;
					//bottom square
					if (modelArray[i][Math.floorMod(j + 1, modelArray.length)])
						totalAlive++;
					//top square
					if (modelArray[i][Math.floorMod(j - 1, modelArray.length)])
						totalAlive++;

					//top right
					if (modelArray[Math.floorMod(i + 1,  modelArray.length)]
							[Math.floorMod(j - 1, modelArray.length)])
						totalAlive++;

					//top left
					if (modelArray[Math.floorMod(i - 1, modelArray.length)]
							[Math.floorMod(j - 1, modelArray.length)])
						totalAlive++;

					//bottom right
					if (modelArray[Math.floorMod(i + 1,  modelArray.length)]
							[Math.floorMod(j + 1, modelArray.length)])
						totalAlive++;

					//bottom left i - 1, j + 1
					if (modelArray[Math.floorMod(i - 1, modelArray.length)]
							[Math.floorMod(j + 1,  modelArray.length)])
						totalAlive++;
					
					
					if (modelArray[i][j]) { // alive
						if (totalAlive < lowSurvivalThreshold)
							advancedArray[i][j] = false;
						else if (totalAlive > highSurvivalThreshold)
							advancedArray[i][j] = false;
						else
							advancedArray[i][j] = true;
					}
					else
						if (totalAlive <= highBirthThreshold && totalAlive >= lowBirthThreshold)
							advancedArray[i][j] = true;
				}
			}
			
			setArray(advancedArray);
			
				
			
			
		}
		//notifyObservers();


	}
	
	public void setHighBirthThreshold(int hbt) {
		highBirthThreshold = hbt;
	}
	
	public void setHighSurvivalThreshold(int hst) {
		highSurvivalThreshold = hst;
	}
	
	public void setLowBirthThreshold(int lbt) {
		lowBirthThreshold = lbt;
	}
	public void setLowSurvivalThreshold(int lst) {
		lowSurvivalThreshold = lst;
	}
	public void setArray(boolean[][] changedModelArray) {
		modelArray = changedModelArray;
		sizeSliderValue = modelArray.length;
		notifyObservers();
	}
	public boolean[][] getArray(){
		return modelArray;
	}
	public void changeSpace(int pixelX, int pixelY) {
		boolean[][] clonedArray = modelArray.clone();
		//System.out.println("Pixel X is " + pixelX + " Pixel Y is " + pixelY);
		int gridX = pixelX / (500 / sizeSliderValue);
		int gridY = pixelY / (500 / sizeSliderValue);
		//System.out.println("Real x is " + gridX + " Real y is " + gridY);
		clonedArray[gridX][gridY] = !clonedArray[gridX][gridY];
		
		modelArray = clonedArray;
		notifyObservers();
	}
	public void reset() {
		modelArray = new boolean[modelArray.length][modelArray.length];
		sizeSliderValue = modelArray.length;
		notifyObservers();
		//observers = new ArrayList<GameOfLifeObserver>();
	}
	
	
	public void addObserver(GameOfLifeObserver o) {
		observers.add(o);
	}
	
	public void removeObserver() {
		observers = new ArrayList<GameOfLifeObserver>();
	}
	public void notifyObservers() {
		for (GameOfLifeObserver o : observers) {
			o.update(this);
		}
	}
	
}