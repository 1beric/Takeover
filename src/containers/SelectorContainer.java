package containers;

import javafx.scene.shape.Rectangle;

public class SelectorContainer {

	private Rectangle redSelector;
	private Rectangle blueSelector;
	private Rectangle greenSelector;
	private Rectangle yellowSelector;
	private Rectangle pinkSelector;
	private Rectangle purpleSelector;
	
	public SelectorContainer(Rectangle red, Rectangle blue, 
			Rectangle green, Rectangle yellow, 
			Rectangle purple, Rectangle pink) {
		redSelector = red;
		blueSelector = blue;
		greenSelector = green;
		yellowSelector = yellow;
		purpleSelector = purple;
		pinkSelector = pink;
	}
	
	public Rectangle valueOf(String val) {
		switch (val) {
		case "red":
			return redSelector;
		case "blue":
			return blueSelector;
		case "pink":
			return pinkSelector;
		case "purple":
			return purpleSelector;
		case "green":
			return greenSelector;
		case "yellow":
			return yellowSelector;
		default:
			return null;
		}
	}

	/**
	 * @return the redSelector
	 */
	public Rectangle redSelector() {
		return redSelector;
	}

	/**
	 * @return the blueSelector
	 */
	public Rectangle blueSelector() {
		return blueSelector;
	}

	/**
	 * @return the greenSelector
	 */
	public Rectangle greenSelector() {
		return greenSelector;
	}

	/**
	 * @return the yellowSelector
	 */
	public Rectangle yellowSelector() {
		return yellowSelector;
	}

	/**
	 * @return the pinkSelector
	 */
	public Rectangle pinkSelector() {
		return pinkSelector;
	}

	/**
	 * @return the purpleSelector
	 */
	public Rectangle purpleSelector() {
		return purpleSelector;
	}
	
	
	
	
}
