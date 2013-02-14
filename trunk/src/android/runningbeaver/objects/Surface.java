package android.runningbeaver.objects;

public final class Surface {

	private float x;
	private float y;

	private int width;
	private int height;

	public Surface(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean contact(Surface surface) {

		// prove x cords
		if ((int) (surface.getX() + surface.getWidth()) < this.x) {
			return false;
		}

		if (surface.getX() > (int) (this.x + this.width)) {
			return false;
		}

		// prove y cords
		if ((int) (surface.getY() + surface.getHeight()) < this.y) {
			return false;
		}

		if (surface.getY() > (int) (this.y + this.height)) {
			return false;
		}

		return true;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}
