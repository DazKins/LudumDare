package ludumDare.game.entity.mob;

import ludumDare.audio.Audio;
import ludumDare.game.entity.Entity;
import ludumDare.game.entity.JumpBlock;
import ludumDare.game.entity.SpeedBlock;
import ludumDare.gfx.Art;
import ludumDare.gfx.Bitmap;
import ludumDare.input.InputHandler;
import ludumDare.math.AABB;

public class Player extends Mob {
	Audio jumpNormal = new Audio("/jump1.wav");
	Audio jumpBoost = new Audio("/boostjump.wav");

	private InputHandler input;

	private int jumpKey, leftKey, rightKey, interactKey;

	private int selectedChar;

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
		if (Math.abs(xa) >= 0.5)
			frame = (int) (lifeTicks / 20.0f) % 2;
		if (xa != 0)
			headFlip = xa < 0 ? true : false;
		b.blit((int) (x - xOff), (int) (y - yOff), Art.sprites[frame + selectedChar * 2][0], headFlip, false, 1.0f, 1.0f);
		b.blit((int) (x - xOff), (int) (y - yOff) + 8, Art.sprites[frame + selectedChar * 2][1], headFlip, false, 1.0f, 1.0f);
	}

	public void onCollide(Entity e) {
		if (e instanceof JumpBlock) {
			JumpBlock jb = (JumpBlock) e;
			if (jb.enabled){
				ya = -jb.boostHeight;
				jumpBoost.play();
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
		if (xa >= -1 && xa <= 1) {
			if (input.keys[rightKey])
				xa = 1;
			if (input.keys[leftKey])
				xa = -1;
		}
		if (input.keys[jumpKey]) {
			if (isOnFloor) {
				ya = -2;
				jumpNormal.play();
			}
		}

		ya += 0.045;
		xa *= 0.875;

		move(xa, ya);
	}
}