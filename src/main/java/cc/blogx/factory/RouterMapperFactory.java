package cc.blogx.factory;

import cc.blogx.annotation.Mapping;
import cc.blogx.annotation.Router;
import cc.blogx.common.model.RouterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class RouterMapperFactory {

    private static final Logger logger = LoggerFactory.getLogger(RouterMapperFactory.class);

    private static RouterMapperFactory factory = null;

    private RouterMapperFactory() {
    }

    public static RouterMapperFactory build(Set<String> clazz) throws Exception {
        if (null == factory) {
            factory = new RouterMapperFactory();
            Map<String, RouterMapper> router = init(clazz);
            factory.setRouter(router);
        }
        return factory;
    }

    private static Map<String, RouterMapper> init(Set<String> sets) throws Exception {
        logger.info("loading router mapping");
        Map<String, RouterMapper> result = new HashMap<>();
        if (sets.size() > 0) {
            boolean isRouter = false;
            String prefix = "";

            for (String className : sets) {
                Class clazz = Class.forName(className);
                Annotation[] annotations = clazz.getAnnotations();

                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().getName().equals(Router.class.getName())) {
                        isRouter = true;
                        continue;
                    }
                    if (annotation.annotationType().getName().equals(Mapping.class.getName())) {
                        Mapping mapping = (Mapping) annotation;
                        prefix = "/" + mapping.url().replace("/", "");
                    }
                }

                if (isRouter) {
                    Method[] methods = clazz.getMethods();
                    if (methods.length > 0) {
                        for (Method method : methods) {
                            Mapping mapping = method.getAnnotation(Mapping.class);
                            if (null != mapping) {
                                RouterMapper mapper = new RouterMapper();
                                mapper.setMethod(mapping.method().name());
//                                if()
                                mapper.setUrl(prefix + "/" + mapper.getUrl().replace("/", ""));
                                if (result.containsKey(mapper.getUrl())) {
                                    throw new Exception(method.getName() + "is already exit");
                                } else {
                                    result.put(mapper.getUrl(), mapper);
                                }
                            }
                        }
                    }
                }

                isRouter = false;
                prefix = "";
            }
        }
        return result;
    }

    private Map<String, RouterMapper> router;

    private void setRouter(Map<String, RouterMapper> router) {
        this.router = router;
    }

    public RouterMapper getRouter(String key) {
        return router.get(key);
    }
}
