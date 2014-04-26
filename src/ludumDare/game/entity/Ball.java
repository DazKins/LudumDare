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
	float first;
	float second;
	float third;
	boolean triggered = false;
	
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
			if (xa != 0.0f) {
				moving.play(true);
			}else{
				moving.play(false);
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
		
		System.out.println(xa);
		if (Math.abs(xa) < 0.1) {moving.play(false);}
		if (Math.abs(first) == 0.765625 && Math.abs(second) == 0.765625 && Math.abs(third) == 0.765625) moving.play(false);
		
		super.move(xa, ya);
	}

}