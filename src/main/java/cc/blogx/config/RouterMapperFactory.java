package cc.blogx.config;

import cc.blogx.annotation.Router;
import cc.blogx.common.acceptor.service.NettySrvAcceptor;
import cc.blogx.common.model.RouterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RouterMapperFactory {

    private static final Logger logger = LoggerFactory.getLogger(RouterMapperFactory.class);

    private static RouterMapperFactory factory = null;

    private RouterMapperFactory() {
    }

    public static RouterMapperFactory build(Set<String> clazz) throws ClassNotFoundException {
        if (null == factory) {
            factory = new RouterMapperFactory();
            Map<String, RouterMapper> router = init(clazz);
            factory.setRouter(router);
        }
        return factory;
    }

    private static Map<String, RouterMapper> init(Set<String> clazzs) throws ClassNotFoundException {
        logger.info("loading router mapping");
        Map<String, RouterMapper> router = new HashMap<>();
        if (clazzs.size() > 0) {
            for (String className : clazzs) {
                Class clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Router.class)) {
                    if(clazz.isAnnotationPresent())
                    System.out.println(123);
                }
            }
        }
        return router;
    }

    private Map<String, RouterMapper> router;

    private void setRouter(Map<String, RouterMapper> router) {
        this.router = router;
    }

    public RouterMapper getRouter(String key) {
        return router.get(key);
    }
}
