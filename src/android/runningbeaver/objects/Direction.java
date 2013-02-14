package android.runningbeaver.objects;

public final class Direction {

	private final double dx;
	private final double dy;

	public Direction(double dx, double dy) {
		super();
		this.dx = dx;
		this.dy = dy;
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

	public float getDxFloat() {
		return (float) dx;
	}

	public float getDyFloat() {
		return (float) dy;
	}

}
