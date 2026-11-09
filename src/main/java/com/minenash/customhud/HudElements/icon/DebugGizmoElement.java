package com.minenash.customhud.HudElements.icon;

import com.minenash.customhud.data.Flags;
import com.minenash.customhud.mixin.accessors.DebugHudAccessor;
import com.minenash.customhud.render.RenderPiece;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import net.minecraft.client.Camera;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.Mth;

import static com.minenash.customhud.CustomHud.CLIENT;

// No longer works
public class DebugGizmoElement extends IconElement {

    private final float size;

    public DebugGizmoElement(Flags flags) {
        super(flags, 10);
        this.size = (int)(10*scale) / 2F;
    }

    @Override
    public void render(GuiGraphicsExtractor context, RenderPiece piece) {
        Camera camera = CLIENT.gameRenderer.mainCamera();
        float yaw = (float) Math.toRadians(camera.yRot());
        float pitch = (float) Math.toRadians(camera.xRot());
        int cx = (int) (piece.x + shiftX + size);
        int cy = (int) (piece.y + shiftY + size);
        int len = (int) size;
        int xX = (int) (cx + len * Math.cos(yaw));
        int xY = (int) (cy + len * Math.sin(pitch) * Math.sin(yaw));
        int yX = cx;
        int yY = (int) (cy - len * Math.cos(pitch));
        int zX = (int) (cx - len * Math.sin(yaw));
        int zY = (int) (cy + len * Math.sin(pitch) * Math.cos(yaw));

        context.fill(cx - 1, cy - 1, cx + 1, cy + 1, 0xFFFFFFFF);
        context.fill(Math.min(cx, xX), Math.min(cy, xY), Math.max(cx, xX) + 1, Math.max(cy, xY) + 1, 0xFFFF0000);
        context.fill(Math.min(cx, yX), Math.min(cy, yY), Math.max(cx, yX) + 1, Math.max(cy, yY) + 1, 0xFF00FF00);
        context.fill(Math.min(cx, zX), Math.min(cy, zY), Math.max(cx, zX) + 1, Math.max(cy, zY) + 1, 0xFF0000FF);
    }



}
