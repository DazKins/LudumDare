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

	public void onXCollide(Entity e) {
		if (e instanceof Player) {
			this.xa = e.getXA();
			e.setXA(0);
			if (counter == 10) {
				moving.play(true);
				playing = true;
			}
		}
	}

	public boolean mayPassY(Entity e) {
		if (e instanceof Player)
			return false;
		return true;
	}

	public AABB getAABB() {
		return new AABB(x, y, x + 8, y + 8);
	}

	public void tick() {
		super.tick();
		if (counter < 10 && Math.abs(xa) > 0.1 && Math.abs(xa) == 0.765625)
			counter++;
		if (Math.abs(xa) < 0.1) {
			moving.play(false);
			playing = false;
		}

		if (counter == 10) {
			if (count == 0) {
				first[0] = xa;
				count++;
			} else if (count == 1) {
				first[1] = xa;
				count++;
			} else if (count == 2) {
				first[2] = xa;
				count++;
			} else if (count == 3) {
				first[3] = xa;
				count++;
			} else if (count == 4) {
				first[4] = xa;
				count = 0;
			}
			if (playing) {
				// && Math.abs(first[2]) < 0.76 && Math.abs(first[2]) >=
				// 0.765625 && Math.abs(first[3]) < 0.76 && Math.abs(first[3])
				// >= 0.765625 && Math.abs(first[4]) < 0.76 &&
				// Math.abs(first[4]) >= 0.765625)
				if (Math.abs(first[0]) < 0.76 && Math.abs(first[0]) >= 0.765625 && Math.abs(first[1]) < 0.76 && Math.abs(first[1]) >= 0.765625) {
					moving.play(false);
					playing = false;
					counter = 0;
				} else if (xa > 0.1f) {
					moving.play(true);
					playing = true;
				}
			}
		}
		ya += 0.045;
		xa *= 0.875;
		
		if (count == 0){
			first = xa;
			count++;
		}else if(count == 1){
			second = xa;
			count++;
		}else if(count == 2){
			third = xa;
			count = 0;
		}
		
		if (Math.abs(xa) < 0.1) {moving.play(false);}
		if (Math.abs(first) == 0.765625 && Math.abs(second) == 0.765625 && Math.abs(third) == 0.765625) moving.play(false);
		

		super.move(xa, ya);
	}

}