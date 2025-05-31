module Core {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    requires javafx.controls;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires java.net.http;

    opens dk.sdu.cbse.main to javafx.graphics, spring.core, spring.beans, spring.context;

    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
}