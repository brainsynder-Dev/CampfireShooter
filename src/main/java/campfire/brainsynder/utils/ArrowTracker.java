package campfire.brainsynder.utils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ArrowTracker implements CleanMe {
    private Map<String, ArrowData> dataMap;

    public ArrowTracker () {
        dataMap = new HashMap<>();
    }

    public void cleanup () {
        dataMap.clear();
        dataMap = null;
    }

    public void register (Player player, ItemStack bow, Arrow arrow) {
        if (bow.getEnchantments().isEmpty()) return;

        for (Map.Entry<Enchantment, Integer> entry : bow.getEnchantments().entrySet()) {
            Enchantment enchant = entry.getKey();
            if (enchant == Enchantment.ARROW_FIRE) {
                ArrowData data = new ArrowData(player, entry.getValue());
                dataMap.put(arrow.getUniqueId().toString(), data);
                break;
            }
        }

    }

    public void remove (Arrow arrow, boolean deleteArrow) {
        String uuid = arrow.getUniqueId().toString();
        ArrowData data = getDataFromArrow(arrow);
        if (data != null) {
            data.cleanup();
            dataMap.remove(uuid);
            if (deleteArrow) arrow.remove();
        }
    }

    public ArrowData getDataFromArrow (Arrow arrow) {
        String uuid = arrow.getUniqueId().toString();
        return dataMap.getOrDefault(uuid, null);
    }
}
