package net.superblaubeere27.clientbase.gui.clickgui;

import net.minecraft.client.gui.GuiScreen;
import net.superblaubeere27.clientbase.ClientBase;
import net.superblaubeere27.clientbase.gui.clickgui.components.Button;
import net.superblaubeere27.clientbase.gui.clickgui.components.Label;
import net.superblaubeere27.clientbase.gui.clickgui.components.ScrollPane;
import net.superblaubeere27.clientbase.gui.clickgui.components.*;
import net.superblaubeere27.clientbase.gui.clickgui.layout.FlowLayout;
import net.superblaubeere27.clientbase.gui.clickgui.layout.GridLayout;
import net.superblaubeere27.clientbase.modules.Module;
import net.superblaubeere27.clientbase.modules.ModuleCategory;
import net.superblaubeere27.clientbase.utils.Function;
import net.superblaubeere27.clientbase.utils.fontRenderer.GlyphPageFontRenderer;
import net.superblaubeere27.clientbase.valuesystem.BooleanValue;
import net.superblaubeere27.clientbase.valuesystem.ModeValue;
import net.superblaubeere27.clientbase.valuesystem.NumberValue;
import net.superblaubeere27.clientbase.valuesystem.Value;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.superblaubeere27.clientbase.utils.Utils.getPoint;

public class ClickGUI extends GuiScreen {
    private final GlyphPageFontRenderer consolas;
    private final Pane spoilerPane;
    private final HashMap<ModuleCategory, Pane> categoryPaneMap;
    private Window window;
    private IRenderer renderer;
    private List<ActionEventListener> onRenderListeners = new ArrayList<ActionEventListener>();

    public ClickGUI() {
        consolas = GlyphPageFontRenderer.create("Consolas", 15, false, false, false);
        renderer = new ClientBaseRendererImpl(consolas);

        window = new Window(ClientBase.CLIENT_NAME, 50, 50, 900, 400);

        Pane conentPane = new ScrollPane(renderer, new GridLayout(1));

        Pane buttonPane = new Pane(renderer, new FlowLayout());

        HashMap<ModuleCategory, List<Module>> moduleCategoryMap = new HashMap<ModuleCategory, List<Module>>();
        categoryPaneMap = new HashMap<ModuleCategory, Pane>();

        for (Module module : ClientBase.INSTANCE.moduleManager.getModules()) {
            if (!moduleCategoryMap.containsKey(module.getCategory())) {
                moduleCategoryMap.put(module.getCategory(), new ArrayList<Module>());
            }

            moduleCategoryMap.get(module.getCategory()).add(module);
        }

        HashMap<ModuleCategory, Pane> paneMap = new HashMap<ModuleCategory, Pane>();

        List<Spoiler> spoilers = new ArrayList<Spoiler>();
        List<Pane> paneList = new ArrayList<Pane>();

        for (Map.Entry<ModuleCategory, List<Module>> moduleCategoryListEntry : moduleCategoryMap.entrySet()) {
            Pane spoilerPane = new Pane(renderer, new GridLayout(1));


            for (final Module module : moduleCategoryListEntry.getValue()) {
                Pane settingPane = new Pane(renderer, new GridLayout(4));

                {
                    settingPane.addComponent(new Label(renderer, "State"));
                    final CheckBox cb;
                    settingPane.addComponent(cb = new CheckBox(renderer, "Enabled"));
                    onRenderListeners.add(new ActionEventListener() {
                        @Override
                        public void onActionEvent() {
                            cb.setSelected(module.getState());
                        }
                    });
                    cb.setListener(new ValueChangeListener<Boolean>() {
                        @Override
                        public boolean onValueChange(Boolean val) {
                            module.setState(val);
                            return true;
                        }
                    });
                }
                {
                    settingPane.addComponent(new Label(renderer, "Keybind"));
                    final KeybindButton kb;
                    settingPane.addComponent(kb = new KeybindButton(renderer, new Function<Integer, String>() {
                        @Override
                        public String apply(Integer key) {
                            return Keyboard.getKeyName(key);
                        }
                    }));
                    onRenderListeners.add(new ActionEventListener() {
                        @Override
                        public void onActionEvent() {
                            kb.setValue(module.getKeybind());
                        }
                    });

                    kb.setListener(new ValueChangeListener<Integer>() {
                        @Override
                        public boolean onValueChange(Integer val) {
                            module.setKeybind(val);
                            System.out.println();
                            return true;
                        }
                    });
                }

                List<Value> values = ClientBase.INSTANCE.valueManager.getAllValuesFrom(module.getName());

                if (values != null) {
                    for (final Value value : values) {
                        if (value instanceof BooleanValue) {
                            settingPane.addComponent(new Label(renderer, value.getName()));

                            final CheckBox cb;

                            settingPane.addComponent(cb = new CheckBox(renderer, "Enabled"));
                            cb.setListener(new ValueChangeListener<Boolean>() {
                                @Override
                                public boolean onValueChange(Boolean object) {
                                    return value.setObject(object);
                                }
                            });
                            onRenderListeners.add(new ActionEventListener() {
                                @Override
                                public void onActionEvent() {
                                    cb.setSelected(((BooleanValue) value).getObject());
                                }
                            });
                        }
                        if (value instanceof ModeValue) {
                            settingPane.addComponent(new Label(renderer, value.getName()));

                            final ComboBox cb;

                            settingPane.addComponent(cb = new ComboBox(renderer, ((ModeValue) value).getModes(), ((ModeValue) value).getObject()));
                            cb.setListener(new ValueChangeListener<Integer>() {
                                @Override
                                public boolean onValueChange(Integer object) {
                                    value.setObject(object);

                                    System.out.println("lol");
                                    return true;
                                }
                            });
                            onRenderListeners.add(new ActionEventListener() {
                                @Override
                                public void onActionEvent() {
                                    cb.setSelectedIndex(((ModeValue) value).getObject());
                                }
                            });
                        }
                        if (value instanceof NumberValue) {
                            settingPane.addComponent(new Label(renderer, value.getName()));

                            final Slider cb;

                            Slider.NumberType type = Slider.NumberType.DECIMAL;

                            if (value.getObject() instanceof Integer) {
                                type = Slider.NumberType.INTEGER;
                            } else if (value.getObject() instanceof Long) {
                                type = Slider.NumberType.TIME;
                            } else if (value.getObject() instanceof Float && ((NumberValue) value).getMin().intValue() == 0 && ((NumberValue) value).getMax().intValue() == 100) {
                                type = Slider.NumberType.PERCENT;
                            }

                            settingPane.addComponent(cb = new Slider(renderer, ((Number) value.getObject()).doubleValue(), ((NumberValue) value).getMin().doubleValue(), ((NumberValue) value).getMax().doubleValue(), type));
                            cb.setListener(new ValueChangeListener<Number>() {
                                @Override
                                public boolean onValueChange(Number val) {
                                    if (value.getObject() instanceof Integer) {
                                        value.setObject(val.intValue());
                                    }
                                    if (value.getObject() instanceof Float) {
                                        value.setObject(val.floatValue());
                                    }
                                    if (value.getObject() instanceof Long) {
                                        value.setObject(val.longValue());
                                    }
                                    if (value.getObject() instanceof Double) {
                                        value.setObject(val.doubleValue());
                                    }

                                    return true;
                                }
                            });

                            onRenderListeners.add(new ActionEventListener() {
                                @Override
                                public void onActionEvent() {
                                    cb.setValue(((Number) value.getObject()).doubleValue());
                                }
                            });
                        }
                    }
                }
                Spoiler spoiler = new Spoiler(renderer, module.getName(), width, settingPane);

                paneList.add(settingPane);
                spoilers.add(spoiler);

                spoilerPane.addComponent(spoiler);

                paneMap.put(moduleCategoryListEntry.getKey(), spoilerPane);
            }

            categoryPaneMap.put(moduleCategoryListEntry.getKey(), spoilerPane);


        }


        spoilerPane = new Pane(renderer, new GridLayout(1));


        for (final ModuleCategory moduleCategory : categoryPaneMap.keySet()) {
            Button button;
            buttonPane.addComponent(button = new Button(renderer, moduleCategory.toString()));
            button.setOnClickListener(new ActionEventListener() {
                @Override
                public void onActionEvent() {
                    ClickGUI.this.setCurrentCategory(moduleCategory);
                }
            });
        }

        conentPane.addComponent(buttonPane);

        int maxWidth = Integer.MIN_VALUE;

        for (Pane pane : paneList) {
            maxWidth = Math.max(maxWidth, pane.getWidth());
        }

        window.setWidth(28 + maxWidth);

        for (Spoiler spoiler : spoilers) {
            spoiler.preferredWidth = maxWidth;
            spoiler.setWidth(maxWidth);
        }

        spoilerPane.setWidth(maxWidth);
        buttonPane.setWidth(maxWidth);

        conentPane.addComponent(spoilerPane);

        conentPane.updateLayout();

        window.setContentPane(conentPane);


        if (categoryPaneMap.keySet().size() > 0)
            setCurrentCategory(categoryPaneMap.keySet().iterator().next());
    }

    private void setCurrentCategory(ModuleCategory category) {
        spoilerPane.clearComponents();
        spoilerPane.addComponent(categoryPaneMap.get(category));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for (ActionEventListener onRenderListener : onRenderListeners) {
            onRenderListener.onActionEvent();
        }

        Point point = getPoint();
        window.mouseMoved(point.x * 2, point.y * 2);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glLineWidth(1.0f);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        window.render(renderer);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        window.mouseMoved(mouseX * 2, mouseY * 2);
        window.mousePressed(mouseButton, mouseX * 2, mouseY * 2);

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        window.mouseMoved(mouseX * 2, mouseY * 2);
        window.mouseReleased(state, mouseX * 2, mouseY * 2);

        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        window.mouseMoved(mouseX * 2, mouseY * 2);
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();

        int eventDWheel = Mouse.getEventDWheel();

        window.mouseWheel(eventDWheel);

    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        window.keyPressed(keyCode, typedChar);
        super.keyTyped(typedChar, keyCode);
    }
}