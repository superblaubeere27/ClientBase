package net.superblaubeere27.clientbase.notifications;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.LinkedBlockingQueue;

public class NotificationManager {
    @NotNull
    private static LinkedBlockingQueue<Notification> pendingNotifications = new LinkedBlockingQueue<>();
    @Nullable
    private static Notification currentNotification = null;

    public static void show(Notification notification) {
//        if (Minecraft.getMinecraft().currentScreen != null)
        pendingNotifications.add(notification);
    }

    public static void update() {
        if (currentNotification != null && !currentNotification.isShown()) {
            currentNotification = null;
        }

        if (currentNotification == null && !pendingNotifications.isEmpty()) {
            currentNotification = pendingNotifications.poll();
            currentNotification.show();
        }

    }

    public static void render() {
        update();

        if (currentNotification != null)
            currentNotification.render();
    }
}
