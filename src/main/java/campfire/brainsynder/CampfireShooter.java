package campfire.brainsynder;

import campfire.brainsynder.listeners.ArrowLandListener;
import campfire.brainsynder.listeners.ArrowShootListener;
import campfire.brainsynder.utils.ArrowTracker;
import campfire.brainsynder.utils.CleanMe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CampfireShooter extends JavaPlugin {
    private List<CleanMe> cleanList;
    private ArrowTracker tracker;
    private ArrowShootListener shootListener;
    private ArrowLandListener landListener;

    @Override
    public void onEnable() {
        cleanList = new ArrayList<>();
        tracker = new ArrowTracker();
        shootListener = new ArrowShootListener(this);
        landListener = new ArrowLandListener(this);

        cleanList.add(tracker);
        cleanList.add(shootListener);
        cleanList.add(landListener);
    }

    @Override
    public void onDisable() {
        cleanList.forEach(CleanMe::cleanup);

        tracker = null;
        shootListener = null;
        landListener = null;
        cleanList = null;
    }

    public ArrowTracker getTracker() {
        return tracker;
    }
}
