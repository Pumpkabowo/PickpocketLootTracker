package net.runelite.client.plugins.pickpocketloot;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("pickpocketloot")
public interface PickpocketLootConfig extends Config {
    @ConfigItem(
            keyName = "showLootTable",
            name = "Show Loot Table",
            description = "Show the loot table for NPCs"
    )
    default boolean showLootTable() {
        return true;
    }
}
