package ludumDare.game.entity;

import ludumDare.audio.Audio;
import ludumDare.game.entity.mob.Mob;
import ludumDare.game.entity.mob.Player;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.math.AABB;

public class Ball extends Mob {
	Audio moving = new Audio("/slide.wav");
	int count;
	int counter;
	float[] first = new float[5];

	boolean playing = false;

	public Ball(int x, int y) {
		super(x, y);
	}

	public void render(Bitmap b, float xOff, float yOff) {
		b.blit((int) (x - xOff), (int) (y - yOff), Art.sprites[12][2], false, false, 1.0f, 1.0f);
	}

	public void onYCollide(Entity e){
		if (e instanceof JumpBlock || e instanceof SpeedBlock)
			this.setYA(0);
		
	}
	
	public void onXCollide(Entity e) {
		if (e instanceof Player) {
			this.xa = e.getXA();
			e.setXA(0);
			if(!playing && Math.abs(xa) != 0.765625){
				moving.play(true);
				playing = true;
			}else if (e instanceof Ball){
			e.setXA(xa);
			}
		}
	}

	public boolean mayPassY(Entity e) {
		if (e instanceof Player || e instanceof Ball || e instanceof JumpBlock || e instanceof SpeedBlock || e instanceof XMovingPlatform)
			return false; 
		return true;
	}
	
	public boolean mayPassX(Entity e){
		if (e instanceof Ball || e instanceof JumpBlock || e instanceof SpeedBlock || e instanceof XMovingPlatform)
			return false;
		return true;
	}

	public AABB getAABB() {
		return new AABB(x, y, x + 8, y + 8);
	}

	public void tick() {
		super.tick();
		
		if (Math.abs(xa) < 0.1) {
			moving.play(false);
			playing = false;
		}else{
				moving.play(true);
				playing = true;
				}
			
		
		ya += 0.045;
		xa *= 0.875;
		

		super.move(xa, ya);
	}

}