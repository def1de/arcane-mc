package com.velocitypowered.arcane.collections;

import com.velocitypowered.arcane.utils.text;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;

public enum Rarity {
    COMMON,
    UNCOMMON,
    RARE,
    EPIC,
    LEGENDARY,
    MYTHIC;

    public Component getRarity(){
        switch(this){
            case COMMON:
                return text.newText("COMMON", Style.style(NamedTextColor.WHITE, TextDecoration.BOLD));
            case UNCOMMON:
                return text.newText("UNCOMMON", Style.style(NamedTextColor.GREEN, TextDecoration.BOLD));
            case RARE:
                return text.newText("RARE", Style.style(NamedTextColor.BLUE, TextDecoration.BOLD));
            case EPIC:
                return text.newText("EPIC", Style.style(NamedTextColor.DARK_PURPLE, TextDecoration.BOLD));
            case LEGENDARY:
                return text.newText("LEGENDARY", Style.style(NamedTextColor.GOLD, TextDecoration.BOLD));
            case MYTHIC:
                return text.newText("MYTHIC", Style.style(NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD));
        }
        return null;
    }
}
