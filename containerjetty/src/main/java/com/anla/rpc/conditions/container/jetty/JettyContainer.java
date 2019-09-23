package com.anla.rpc.conditions.container.jetty;

import org.apache.dubbo.container.Container;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author anLA7856
 * @date 19-7-28 下午8:32
 * @description
 */
public class JettyContainer implements Container {

    private static final Logger logger = LoggerFactory.getLogger(JettyContainer.class);

    private static Server server = new Server(8080);

    public void setServerHandler(Handler handler){
        server.setHandler(handler);
    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to start jetty" + e.getMessage(), e);
        }
    }

    @Override
    public void stop() {
        if (server != null) {
            try {
                server.stop();
            } catch (Exception e) {
                logger.warn(e.getMessage(), e);
            }
        }
    }
}
