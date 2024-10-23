package net.runelite.client.plugins.pickpocketloot;


import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;
import java.util.List;

public class PickpocketLootOverlay extends Overlay {
    private final PickpocketLootPlugin plugin;
    private List<String> loot;

    @Inject
    public PickpocketLootOverlay(PickpocketLootPlugin plugin) {
        this.plugin = plugin;
        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (loot != null) {
            int y = 15;
            for (String item : loot) {
                graphics.drawString(item, 10, y);
                y += 15;
            }
        }
        return null;
    }

    public void setLoot(List<String> loot) {
        this.loot = loot;
    }

    public List<String> getLoot() {
        return loot;
    }
}
