package ti.audiovisualizerview;

//import org.appcelerator.titanium.TiLifecycle.OnLifecycleEvent;

public class CirclebargraphDict {
	private int color;
	private float width;
	boolean cycleColor = true;
	int divisions = 16;
	float strokeWidth = 4;

	public float getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(float strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public int getDivisions() {
		return divisions;
	}

	public void setDivisions(int divisions) {
		this.divisions = divisions;
	}

	public boolean isCycleColor() {
		return cycleColor;
	}

	public void setCycleColor(boolean cycleColor) {
		this.cycleColor = cycleColor;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public CirclebargraphDict() {
		super();
	}
}