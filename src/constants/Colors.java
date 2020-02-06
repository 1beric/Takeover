package constants;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Colors {

	public static final Color BLUE 		= Color.rgb(0, 0, 255);
	public static final Color BLUEH		= Color.rgb(100, 100, 255);	
	public static final Color BLUED		= Color.rgb(0, 0, 155);		
	
	public static final Color RED 		= Color.rgb(255, 0, 0);
	public static final Color REDH		= Color.rgb(255, 70, 70);
	public static final Color REDD 		= Color.rgb(155, 0, 0);

	public static final Color GREEN 	= Color.rgb(0, 255, 0);
	public static final Color GREENH 	= Color.rgb(100, 255, 100);
	public static final Color GREEND 	= Color.rgb(0, 155, 0);

	public static final Color YELLOW 	= Color.rgb(255,255,0);
	public static final Color YELLOWH 	= Color.rgb(255,255,150);
	public static final Color YELLOWD 	= Color.rgb(155,155,0);

	public static final Color PURPLE 	= Color.rgb(150, 0, 255);
	public static final Color PURPLEH 	= Color.rgb(150, 100, 255);
	public static final Color PURPLED 	= Color.rgb(100, 0, 155);

	public static final Color PINK 		= Color.rgb(255, 100, 150);
	public static final Color PINKH		= Color.rgb(255, 150, 200);
	public static final Color PINKD		= Color.rgb(155, 0, 100);

	
	public static Paint valueOf(String color) {
		switch (color) {
		case "blue":
			return BLUE;
		case "red":
			return RED;
		case "green":
			return GREEN;
		case "yellow":
			return YELLOW;
		case "purple":
			return PURPLE;
		case "pink":
			return PINK;
		case "blueh":
			return BLUEH;
		case "redh":
			return REDH;
		case "greenh":
			return GREENH;
		case "yellowh":
			return YELLOWH;
		case "purpleh":
			return PURPLEH;
		case "pinkh":
			return PINKH;
		case "blued":
			return BLUED;
		case "redd":
			return REDD;
		case "greend":
			return GREEND;
		case "yellowd":
			return YELLOWD;
		case "purpled":
			return PURPLED;
		case "pinkd":
			return PINKD;
		default:
			return Color.BLACK;
		}
	}
	
	
}
