package com.terraformersmc.modmenu.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.FormattedCharSequence;

public class DrawingUtil {
    public static void drawBadge(GuiGraphicsExtractor context, int x, int y, int width, FormattedCharSequence text, int outlineColor, int fillColor, int textColor) {
        context.fill(x, y, x + width, y + 9, fillColor);
        context.fill(x, y, x + width, y + 1, outlineColor);
        context.fill(x, y + 8, x + width, y + 9, outlineColor);
        context.fill(x, y, x + 1, y + 9, outlineColor);
        context.fill(x + width - 1, y, x + width, y + 9, outlineColor);
        context.text(Minecraft.getInstance().font, text, x + 3, y + 1, textColor, false);
    }
}
