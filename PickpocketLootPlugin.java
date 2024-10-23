package net.runelite.client.plugins.pickpocketloot;

import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

@PluginDescriptor(
        name = "Pickpocket Loot",
        description = "Shows potential loot from pickpocketing NPCs",
        tags = {"pickpocket", "loot", "thieving"}
)
public class PickpocketLootPlugin extends Plugin {
    @Inject
    private OverlayManager overlayManager;

    @Inject
    private PickpocketLootOverlay lootOverlay;

    private static final Map<Integer, List<String>> LOOT_TABLES = new HashMap<>();

    @Override
    protected void startUp() {
        overlayManager.add(lootOverlay);
        LootTableInitializer.initialize(LOOT_TABLES);
    }

    @Override
    protected void shutDown() {
        overlayManager.remove(lootOverlay);
    }

    @Subscribe
    public void onMenuOptionClicked(MenuOptionClicked event) {
        if ("Pickpocket".equals(event.getMenuOption())) {
            showLootTable(event.getId());
        }
    }

    private void showLootTable(int npcId) {
        List<String> loot = LOOT_TABLES.get(npcId);
        if (loot != null) {
            lootOverlay.setLoot(loot);
        }
    }

    public List<String> getCurrentLoot() {
        return lootOverlay.getLoot();
    }

    static class LootTableInitializer {
        static void initialize(Map<Integer, List<String>> lootTables) {
            lootTables.put(1, Arrays.asList("Coins", "Lockpick", "Rogue equipment"));
            lootTables.put(2, Arrays.asList("Coins", "Gem", "Clue scroll"));
            // Add more NPC loot tables here
        }
    }
}
