package ludumDare.game.entity;

import ludumDare.audio.Audio;
import ludumDare.game.entity.mob.Mob;
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
	
	public void onCollide(Entity e) {
		if (e instanceof JumpBlock) {
			JumpBlock jb = (JumpBlock) e;
			if (jb.enabled){
				ya = -jb.boostHeight;
//				jumpBoost.play(true);
			}
		}
		if (e instanceof SpeedBlock) {
			SpeedBlock sb = (SpeedBlock) e;
			if (sb.active) {
				System.out.println("hit");
				if (xa < 0)
					xa = -sb.speed;
				if (xa > 0)
					xa = sb.speed;
			}
		}
	}

	public void onYCollide(Entity e) {
//		if (e instanceof JumpBlock || e instanceof SpeedBlock)
//			this.setYA(0);
		if (e instanceof Mob) {
//			setYA(0);
//			e.setYA(0);
			e.setYA(getYA());
			this.ya = -ya;
		}
	}
	
	public void onXCollide(Entity e) {
		if (e instanceof Mob) {
			if (e.getXA() != 0) {
				float addSpeed = e.getXA() < 0 ? -0.7f : 0.7f;
				this.xa = (float) (e.getXA() + addSpeed);
//				e.setXA(0);
			}
			if(!playing && Math.abs(xa) != 0.765625) {
				moving.play(true);
				playing = true;
			}
		} 
	}
	
	public float getXA() {
		return previousFrameXA;
	}
	
	public boolean mayPass(Entity e) {
		if (e instanceof Mob)
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
		
		xa += nextFrameXA;
		nextFrameXA = 0;

		if (getXA() < 0) {
			if (xa > getXA() * 0.875)
				xa = (float) (getXA() * 0.875);
		}
		if (getXA() > 0) {
			if (xa < getXA() * 0.875)
				xa = (float) (getXA() * 0.875);
		}
		
		move(xa, ya);

		previousFrameXA = xa;
		xa = 0;
		
		isOnMovingPlatform = false;
	}

}