package net.superblaubeere27.clientbase.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.superblaubeere27.clientbase.ClientBase;

/**
 * Project: LiquidBase
 * -----------------------------------------------------------
 * Copyright © 2017 | CCBlueX | All rights reserved.
 */
@SideOnly(Side.CLIENT)
public class ChatUtils {
    public static final String PRIMARY_COLOR = "§7";
    public static final String SECONDARY_COLOR = "§1";
    private static final String PREFIX = PRIMARY_COLOR + "[" + SECONDARY_COLOR + ClientBase.CLIENT_NAME + PRIMARY_COLOR + "] ";

    public static void send(final String s) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(IChatComponent.Serializer.jsonToComponent("{text:\"" + s + "\"}"));
    }

    public static void success(String s) {
        info(s);
    }

    public static void info(String s) {
        send(PREFIX + s);
    }
}
