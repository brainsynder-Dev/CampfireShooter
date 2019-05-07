package campfire.brainsynder.listeners;

import campfire.brainsynder.CampfireShooter;
import campfire.brainsynder.utils.CleanMe;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class ArrowShootListener implements Listener, CleanMe {
    private CampfireShooter instance;

    public ArrowShootListener (CampfireShooter instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onShoot (EntityShootBowEvent event) {
        LivingEntity entity = event.getEntity();
        Entity projectile = event.getProjectile();
        if ((entity instanceof Player) && (projectile instanceof Arrow)) {
            Player player = (Player) entity;
            Arrow arrow = (Arrow) projectile;
            instance.getTracker().register(player, event.getBow(), arrow);
        }
    }

    @Override
    public void cleanup() {
        instance = null;
    }
}
