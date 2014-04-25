package ludumDare.math;

public class AABB {
	private float x0;
	private float y0;
	private float x1;
	private float y1;
	
	public AABB(float x0, float y0, float x1, float y1) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
	}
	
	public boolean intersects(AABB a) {
		if (a.x0 > x1) 
			return false;
		if (a.y0 > y1)
			return false;
		if (a.x1 < x0)
			return false;
		if (a.y1 < y0)
			return false;
		return true;
	}
	
	public AABB shifted(float xa, float ya) {
		x0 += xa;
		x1 += xa;
		y0 += ya;
		y1 += ya;
		return this;
	}
}