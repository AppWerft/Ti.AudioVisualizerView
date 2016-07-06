package ti.audiovisualizerview;

public class BargraphDict {
	private int color;
	private float strokeWidth;
	private float height = 50f;

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	private int verticalAlign = 0;
	private int divisions = 16;

	/**
	 * @return the divisions
	 */
	public int getDivisions() {
		return divisions;
	}

	/**
	 * @param divisions
	 *            the divisions to set
	 */
	public void setDivisions(int divisions) {
		this.divisions = divisions;
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

	public int getVerticalAlign() {
		return verticalAlign;
	}

	public void setVerticalAlign(int verticalAlign) {
		this.verticalAlign = verticalAlign;
	}

	public String toString() {
		return "BarGraphDict:\n\tstrokeWidth = " + this.strokeWidth
				+ "\n\tdivissions = " + this.divisions + "\n\tcolor = "
				+ this.color;
	}

	public BargraphDict() {
		super();
	}
}