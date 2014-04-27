package ludumDare.game.entity.mob;

import ludumDare.audio.Audio;
import ludumDare.game.entity.Entity;
import ludumDare.game.entity.JumpBlock;
import ludumDare.game.entity.SpeedBlock;
import ludumDare.game.entity.XMovingPlatform;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.input.InputHandler;
import ludumDare.math.AABB;

public class Player extends Mob {
	Audio jumpNormal = new Audio("/jump1.wav");
	Audio jumpBoost = new Audio("/boostjump.wav");
	Audio speedBoost = new Audio("/boostspeed.wav");
	private float previousFrameXA = 0;

	private InputHandler input;

	private int jumpKey, leftKey, rightKey, interactKey;

	private int selectedChar;
	
	private boolean isOnMovingPlatform;
	
	public float getXA() {
		return previousFrameXA;
	}

	public Player(int cs, InputHandler i, int x, int y, int u, int r, int l, int interactKey) {
		super(x, y);
		input = i;
		jumpKey = u;
		leftKey = l;
		rightKey = r;
		this.interactKey = interactKey;
		selectedChar = cs;
	}

	public void setSelectedCharacter(int sc) {
		selectedChar = sc;
	}

	public AABB getAABB() {
		return new AABB(x, y, x + 7, y + 15);
	}

	public boolean isInteracting() {
		return input.keyStream[interactKey];
	}

	boolean headFlip;

	public void render(Bitmap b, float xOff, float yOff) {
		int frame = 0;
		if (Math.abs(getXA()) >= 0.5)
			frame = (int) (lifeTicks / 20.0f) % 2;
		if (getXA() != 0)
			headFlip = getXA() < 0 ? true : false;
		b.blit((int) (x - xOff), (int) (y - yOff), Art.sprites[frame + selectedChar * 2][0], headFlip, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff), (int) (y - yOff) + 8, Art.sprites[frame + selectedChar * 2][1], headFlip, false, 1.0f, 1.0f);
	}

	public void onCollide(Entity e) {
		if (e instanceof JumpBlock) {
			JumpBlock jb = (JumpBlock) e;
			if (jb.enabled){
				ya = -jb.boostHeight;
				jumpBoost.play(true);
			}
		}
		if (e instanceof SpeedBlock) {
			SpeedBlock sb = (SpeedBlock) e;
			if (sb.active) {
				if (xa < 0)
					xa = -sb.speed;
				if (xa > 0)
					xa = sb.speed;
			}
		}
	}

	public void tick() {
		super.tick();
		
		xa = nextFrameXA;
		
		if (xa >= -1 && xa <= 1) {
			if (input.keys[rightKey])
				xa += 1;
			if (input.keys[leftKey])
				xa += -1;
		}
		if (input.keys[jumpKey]) {
			if (isOnFloor) {
				ya = -2;
				jumpNormal.play(true);
			}
		}

		ya += 0.045;
		xa *= 0.875;
		
		if (xa < 0 && getXA() < 0) {
			if (xa > getXA() * 0.875)
				xa = (float) (getXA() * 0.875);
		}
		if (xa > 0 && getXA() > 0) {
			if (xa < getXA() * 0.875)
				xa = (float) (getXA() * 0.875);
		}

		move(xa, ya);
		
		previousFrameXA = xa;
		xa = 0;
		nextFrameXA = 0;
		
		isOnMovingPlatform = false;
	}
	
	public void onYCollide(Entity e) {
		if (e instanceof SpeedBlock) {
			SpeedBlock sb = (SpeedBlock) e;
			if (sb.active) {
				if (Math.abs(xa)!= 0.0f)
				speedBoost.play(true);
			}
		}
	}
}