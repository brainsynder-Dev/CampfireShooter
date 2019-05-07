package campfire.brainsynder.listeners;

import campfire.brainsynder.CampfireShooter;
import campfire.brainsynder.utils.ArrowData;
import campfire.brainsynder.utils.CleanMe;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Campfire;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowLandListener implements Listener, CleanMe {
    private CampfireShooter instance;

    public ArrowLandListener(CampfireShooter instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onShoot (ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile instanceof Arrow) {
            Arrow arrow = (Arrow) projectile;
            ArrowData data = instance.getTracker().getDataFromArrow(arrow);
            if (data == null) return;

            Block block = event.getHitBlock();
            if (block == null) return;
            BlockData blockData = block.getBlockData();
            if (blockData instanceof Campfire) {
                Campfire campfire = (Campfire) blockData;
                if (!campfire.isLit()) {
                    campfire.setLit(true);
                    block.setBlockData(campfire, false);
                }
            }
        }
    }

    @Override
    public void cleanup() {
        instance = null;
    }
}
