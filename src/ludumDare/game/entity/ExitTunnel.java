package ludumDare.game.entity;

import ludumDare.audio.Audio;
import ludumDare.game.entity.mob.Mob;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class ExitTunnel extends Entity implements ActivateableEntity{
	
	private boolean open = false;
	
	public ExitTunnel(int x, int y) {
		super(x, y);
	}
	
	
	public void onActivate(Entity e) {
		open = !open;
		
	}
	
	public AABB getAABB() {
		if (!open)
			return new AABB(x, y, 16 + x, 24 + y);
		return null;
	}

	public boolean mayPassX(){
		return false;
	}
	
	public boolean mayPassY(Entity e) {
		if (e instanceof Mob) 
			return false;
		return true;
	}
	
	
	public void render(Bitmap b, float xOff, float yOff) {
		int state = open ? 16 : 18;
		b.blit((int) (x - xOff), (int) (y - yOff), Art.sprites[state][2], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff), (int) (y - yOff) + 8, Art.sprites[state][3], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff), (int) (y - yOff) + 16, Art.sprites[state][4], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff)+8, (int) (y - yOff), Art.sprites[state+1][2], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff)+8, (int) (y - yOff) +8, Art.sprites[state+1][3], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff)+8, (int) (y - yOff) + 16, Art.sprites[state+1][4], false, false, 1.0f, 1.0f);
		
	}
	
}