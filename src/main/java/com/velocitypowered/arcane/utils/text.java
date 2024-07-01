package com.velocitypowered.arcane.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;

public class text {

    public static Component newText(String text) {
        return Component.text(text).decoration(TextDecoration.ITALIC, false);
    }
    public static Component newText(String text, Style style) {
        return Component.text(text, style).decoration(TextDecoration.ITALIC, false);
    }
}
