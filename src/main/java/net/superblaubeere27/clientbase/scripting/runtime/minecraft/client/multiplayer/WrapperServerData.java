/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.scripting.runtime.minecraft.client.multiplayer;

import net.minecraft.client.multiplayer.ServerData;

public class WrapperServerData {
    private ServerData real;

    public WrapperServerData(ServerData var1) {
        this.real = var1;
    }

    public ServerData unwrap() {
        return this.real;
    }

    public String getBase64EncodedIconData() {
        return this.real.getBase64EncodedIconData();
    }

    public void setBase64EncodedIconData(String var1) {
        this.real.setBase64EncodedIconData(var1);
    }


    public void copyFrom(WrapperServerData var1) {
        this.real.copyFrom(var1.unwrap());
    }

    public String getServerName() {
        return this.real.serverName;
    }

    public void setServerName(String var1) {
        this.real.serverName = var1;
    }

    public String getServerIP() {
        return this.real.serverIP;
    }

    public void setServerIP(String var1) {
        this.real.serverIP = var1;
    }

    public String getPopulationInfo() {
        return this.real.populationInfo;
    }

    public void setPopulationInfo(String var1) {
        this.real.populationInfo = var1;
    }

    public String getServerMOTD() {
        return this.real.serverMOTD;
    }

    public void setServerMOTD(String var1) {
        this.real.serverMOTD = var1;
    }

    public long getPingToServer() {
        return this.real.pingToServer;
    }

    public void setPingToServer(long var1) {
        this.real.pingToServer = var1;
    }

    public int getVersion() {
        return this.real.version;
    }

    public void setVersion(int var1) {
        this.real.version = var1;
    }

    public String getGameVersion() {
        return this.real.gameVersion;
    }

    public void setGameVersion(String var1) {
        this.real.gameVersion = var1;
    }

    public boolean isField_78841_f() {
        return this.real.field_78841_f;
    }

    public void setField_78841_f(boolean var1) {
        this.real.field_78841_f = var1;
    }

    public String getPlayerList() {
        return this.real.playerList;
    }

    public void setPlayerList(String var1) {
        this.real.playerList = var1;
    }
}
