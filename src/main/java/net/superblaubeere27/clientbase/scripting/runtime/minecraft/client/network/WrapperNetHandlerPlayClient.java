/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.scripting.runtime.minecraft.client.network;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.network.WrapperNetworkManager;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.network.WrapperPacket;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.util.WrapperIChatComponent;

import java.util.Collection;
import java.util.UUID;

public class WrapperNetHandlerPlayClient {
    private NetHandlerPlayClient real;

    public WrapperNetHandlerPlayClient(NetHandlerPlayClient var1) {
        this.real = var1;
    }

    public NetHandlerPlayClient unwrap() {
        return this.real;
    }

    public void cleanup() {
        this.real.cleanup();
    }

    public void onDisconnect(WrapperIChatComponent var1) {
        this.real.onDisconnect(var1.unwrap());
    }

    public void addToSendQueue(WrapperPacket var1) {
        this.real.addToSendQueue(var1.unwrap());
    }

    public WrapperNetworkManager getNetworkManager() {
        return new WrapperNetworkManager(this.real.getNetworkManager());
    }

    public Collection getPlayerInfoMap() {
        return this.real.getPlayerInfoMap();
    }

    public WrapperNetworkPlayerInfo getPlayerInfo(UUID var1) {
        return new WrapperNetworkPlayerInfo(this.real.getPlayerInfo(var1));
    }

    public WrapperNetworkPlayerInfo getPlayerInfo(String var1) {
        return new WrapperNetworkPlayerInfo(this.real.getPlayerInfo(var1));
    }

    public int getCurrentServerMaxPlayers() {
        return this.real.currentServerMaxPlayers;
    }

    public void setCurrentServerMaxPlayers(int var1) {
        this.real.currentServerMaxPlayers = var1;
    }
}
