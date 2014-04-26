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
	
	public float xDifference(AABB a) {
		System.out.println("p " + a.x0 + " c " + x1);
		System.out.println("p1 " + a.x1 + " c1 " + x0);
		if (a.x0 > x1) {
			System.out.println("hit");
			return (a.x0 - x1);
		}
		if (a.x1 < x0)
			return (x0 - a.x1);
		return 0;
	}
	
	public float yDifference(AABB a) {
		if (a.y0 > y1)
			return (int) (a.y0 - y1);
		if (a.y1 < y0)
			return (int) (y0 - a.y1);
		return 0;
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