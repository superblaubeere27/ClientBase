package net.superblaubeere27.clientbase.gui.clickgui;

import net.superblaubeere27.clientbase.utils.GLUtil;
import net.superblaubeere27.clientbase.utils.fontRenderer.GlyphPageFontRenderer;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL41.glClearDepthf;

public class ClientBaseRendererImpl implements IRenderer {
    private GlyphPageFontRenderer renderer;

    public ClientBaseRendererImpl(GlyphPageFontRenderer renderer) {
        this.renderer = renderer;
    }


    @Override
    public void drawRect(double x, double y, double w, double h, Color c) {
        GLUtil.drawRect(GL11.GL_QUADS, x / 2.0, y / 2.0, x / 2.0 + w / 2.0, y / 2.0 + h / 2.0, GLUtil.toRGBA(c));
    }

    @Override
    public void drawOutline(double x, double y, double w, double h, float lineWidth, Color c) {
        GL11.glLineWidth(lineWidth);
        GLUtil.drawRect(GL11.GL_LINE_LOOP, x / 2.0, y / 2.0, x / 2.0 + w / 2.0, y / 2.0 + h / 2.0, GLUtil.toRGBA(c));
    }

    @Override
    public void setColor(Color c) {
        GLUtil.setColor(c);
    }

    @Override
    public void drawString(int x, int y, String text, Color color) {
        renderer.drawString(text, x / 2f, y / 2f, GLUtil.toRGBA(color), false);
    }

    @Override
    public int getStringWidth(String str) {
        return renderer.getStringWidth(str) * 2;
    }

    @Override
    public int getStringHeight(String str) {
        return renderer.getFontHeight() * 2;
    }

    @Override
    public void drawTriangle(double x1, double y1, double x2, double y2, double x3, double y3, Color color) {
//        Tessellator tessellator = Tessellator.getInstance();
//        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
//        GlStateManager.enableBlend();
//        GlStateManager.disableTexture2D();
//        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
//
//        setColor(color);
//
//        worldrenderer.begin(GL_TRIANGLES, DefaultVertexFormats.POSITION);
//        worldrenderer.pos(x1, y1, 0.0D).endVertex();
//        worldrenderer.pos(x2, y2, 0.0D).endVertex();
//        worldrenderer.pos(x3, y3, 0.0D).endVertex();
//        tessellator.draw();
//        GlStateManager.enableTexture2D();
//        GlStateManager.disableBlend();
    }

    @Override
    public void initMask() {
        glClearDepthf(1.0f);
        glClear(GL_DEPTH_BUFFER_BIT);
        glColorMask(false, false, false, false);
        glDepthFunc(GL_LESS);
        glEnable(GL_DEPTH_TEST);
        glDepthMask(true);
    }

    @Override
    public void useMask() {
        glColorMask(true, true, true, true);
        glDepthMask(true);
        glDepthFunc(GL_EQUAL);
    }

    @Override
    public void disableMask() {
        glClearDepthf(1.0f);
        glClear(GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
        glDisable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
        glDepthMask(false);
    }
}
