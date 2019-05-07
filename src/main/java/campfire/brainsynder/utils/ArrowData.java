package campfire.brainsynder.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ArrowData {
    private Player shooter; // The Player who fired the arrow
    private Location start; // Where the arrow was fired from
    private int level; // The level of the Flame Enchantment on the bow
    private long startTime;

    public ArrowData(Player player, int level) {
        this.shooter = player;
        this.start = player.getEyeLocation();
        this.level = level;
        startTime = System.currentTimeMillis();
    }

    public Location getLocation() {
        return start;
    }
    public Player getShooter() {
        return shooter;
    }
    public int getLevel() {
        return level;
    }

    public void arrowHit (Location location) {

    }

    void cleanup() {
        this.start = null;
        this.shooter = null;
    }
}
