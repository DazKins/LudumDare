package ludumDare.game.level.tile;

import ludumDare.game.level.Level;
import ludumDare.math.AABB;

public class RockTile extends Tile {
	public RockTile(int id) {
		super(id, 0, 4, true);
	}
	
	public AABB getAABB(Level l, int x, int y) {
		return new AABB(x * 8, y * 8, x * 8 + 8, y * 8 + 8);
	}
}