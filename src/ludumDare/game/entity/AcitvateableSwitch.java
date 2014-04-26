package ludumDare.game.entity;

import ludumDare.game.entity.mob.Player;
import ludumDare.game.level.Level;
import ludumDare.math.AABB;

public class AcitvateableSwitch extends AOESwitch {
	public AcitvateableSwitch(AABB bb) {
		super(bb);
	}
	
	public void onCollide(Entity e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			if (p.isInteracting())
				super.onActivate(e);
		}
	}
}