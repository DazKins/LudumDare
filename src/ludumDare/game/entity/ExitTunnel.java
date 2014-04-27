package ludumDare.game.entity;

import ludumDare.audio.Audio;
import ludumDare.game.entity.mob.Player;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class ExitTunnel extends AOESwitch implements ActivateableEntity {
	Audio ding = new Audio("/success.wav");
	Audio openExit = new Audio("/openexit.wav");
	Entity ping;
	
	private int ticksSinceLastActivate;
	private static boolean open = false;
	public boolean success = false;

	public ExitTunnel(float x, float y) {
		super(new AABB(x, 23 + y, 16 + x, 24 + y));
		this.x = x;
		this.y = y;
	}

	public void onCollide(Entity e) {
		super.onCollide(e);
		ticksSinceLastActivate = lifeTicks;
		ping = e;
	}

	public void onActivate(Entity e) {
		open = !open;
		openExit.play(true);

	}

	public AABB getAABB() {
		if (!open)
			return new AABB(x, y, 16 + x, 24 + y);
		//trigger point for success
		return new AABB(x, y + 23, 16 + x, 24 + y);
	}

	public boolean mayPass(Entity e) {
		if (open) {
			//allows player to fall unhindered when open, else collision halts movement at trigger point
			return true;
		} else {
			return false;
		}
	}

	public void tick() {
		super.tick();
		if (open && !success && isPlayer(ping)) {
			if (lifeTicks - ticksSinceLastActivate > 1 && activated) {
				activated = false;
			}
			if (activated) {
				
				success = true;
				ding.play(true);
				System.out.println("Success!");
			}
		}
	}
	

	public boolean isPlayer(Entity e){
		if (e.getClass().equals(ludumDare.game.entity.mob.Player.class))
			return true;
		
		return false;
	}
	
	public void render(Bitmap b, float xOff, float yOff) {
		int state = open ? 16 : 18;
		//column 1
		b.blit((int) (x - xOff), (int) (y - yOff), Art.sprites[state][2], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff), (int) (y - yOff) + 8, Art.sprites[state][3], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff), (int) (y - yOff) + 16, Art.sprites[state][4], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff), (int) (y - yOff) + 24, Art.sprites[state][5], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff), (int) (y - yOff) + 32, Art.sprites[state][6], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff), (int) (y - yOff) + 40, Art.sprites[state][7], false, false, 1.0f, 1.0f);
		//column 2
		b.blit((int) (x - xOff) + 8, (int) (y - yOff), Art.sprites[state + 1][2], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff) + 8, (int) (y - yOff) + 8, Art.sprites[state + 1][3], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff) + 8, (int) (y - yOff) + 16, Art.sprites[state + 1][4], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff) + 8, (int) (y - yOff) + 24, Art.sprites[state + 1][5], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff) + 8, (int) (y - yOff) + 32, Art.sprites[state + 1][6], false, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff) + 8, (int) (y - yOff) + 40, Art.sprites[state + 1][7], false, false, 1.0f, 1.0f);
	}

}