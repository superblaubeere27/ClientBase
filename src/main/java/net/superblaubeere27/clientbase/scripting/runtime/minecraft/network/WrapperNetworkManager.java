/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.scripting.runtime.minecraft.network;

import net.minecraft.network.NetworkManager;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.util.WrapperIChatComponent;

import java.net.SocketAddress;

public class WrapperNetworkManager {
    private NetworkManager real;

    public WrapperNetworkManager(NetworkManager var1) {
        this.real = var1;
    }

    public static WrapperNetworkManager provideLocalClient(SocketAddress var0) {
        return new WrapperNetworkManager(NetworkManager.provideLocalClient(var0));
    }

    public NetworkManager unwrap() {
        return this.real;
    }

    public void sendPacket(WrapperPacket var1) {
        this.real.sendPacket(var1.unwrap());
    }

    public void processReceivedPackets() {
        this.real.processReceivedPackets();
    }

    public SocketAddress getRemoteAddress() {
        return this.real.getRemoteAddress();
    }

    public void closeChannel(WrapperIChatComponent var1) {
        this.real.closeChannel(var1.unwrap());
    }

    public boolean isLocalChannel() {
        return this.real.isLocalChannel();
    }

    public boolean getIsencrypted() {
        return this.real.getIsencrypted();
    }

    public boolean isChannelOpen() {
        return this.real.isChannelOpen();
    }

    public boolean hasNoChannel() {
        return this.real.hasNoChannel();
    }

    public WrapperINetHandler getNetHandler() {
        return new WrapperINetHandler(this.real.getNetHandler());
    }

    public void setNetHandler(WrapperINetHandler var1) {
        this.real.setNetHandler(var1.unwrap());
    }

    public WrapperIChatComponent getExitMessage() {
        return new WrapperIChatComponent(this.real.getExitMessage());
    }

    public void disableAutoRead() {
        this.real.disableAutoRead();
    }

    public void setCompressionTreshold(int var1) {
        this.real.setCompressionTreshold(var1);
    }

    public void checkDisconnected() {
        this.real.checkDisconnected();
    }
}
