package me.friendly.exeter.events;

import me.friendly.api.event.Event;
import net.minecraft.client.gui.ScaledResolution;

public class RenderGameOverlayEvent extends Event {
    private ScaledResolution scaledResolution;

    private final Type type;
    private boolean renderPumpkin = false;
    private boolean renderItems = false;
    private boolean renderHurtcam = false;
    private boolean renderFire = false;

    public RenderGameOverlayEvent(Type type) {
        this.type = type;
    }

    public RenderGameOverlayEvent(ScaledResolution scaledResolution) {
        this.type = Type.IN_GAME;
        this.scaledResolution = scaledResolution;
    }

    public Type getType() {
        return this.type;
    }

    public ScaledResolution getScaledResolution() {
        return this.scaledResolution;
    }

    public boolean isRenderFire() {
        return this.renderFire;
    }

    public void setRenderFire(boolean renderFire) {
        this.renderFire = renderFire;
    }

    public boolean isRenderPumpkin() {
        return this.renderPumpkin;
    }

    public void setRenderPumpkin(boolean renderPumpkin) {
        this.renderPumpkin = renderPumpkin;
    }

    public boolean isRenderItems() {
        return this.renderItems;
    }

    public void setRenderItems(boolean renderItems) {
        this.renderItems = renderItems;
    }

    public boolean isRenderHurtcam() {
        return this.renderHurtcam;
    }

    public void setRenderHurtcam(boolean renderHurtcam) {
        this.renderHurtcam = renderHurtcam;
    }

    public enum Type {
        IN_GAME,
        PUMPKIN,
        ITEM,
        HURTCAM,
        FIRE
    }
}

