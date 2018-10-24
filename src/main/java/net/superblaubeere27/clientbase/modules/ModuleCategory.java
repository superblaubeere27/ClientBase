package net.superblaubeere27.clientbase.modules;

public enum ModuleCategory {
    RENDER("Render"), MOVEMENT("Movement"), COMBAT("Combat"), MISC("Misc"), PLAYER("Player"), WORLD("World"), EXPLOIT("Exploit");

    private String name;

    ModuleCategory(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
