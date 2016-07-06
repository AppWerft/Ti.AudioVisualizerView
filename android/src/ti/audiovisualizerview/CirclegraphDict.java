package ti.audiovisualizerview;

public class CirclegraphDict {
	private int color;
	private float strokeWidth;
	boolean cycleColor = true;

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

	public float getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(float strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public CirclegraphDict() {
		super();
	}
}