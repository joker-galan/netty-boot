package cc.blogx.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取resources目录下的netty.properties配置
 *
 * @author XueYuan.
 * @since 2019-03-22 15:43.
 */
public class NettyConfig {

    private NettyConfig() {
    }

    private static final Logger logger = LoggerFactory.getLogger(NettyConfig.class);
    private static final String NETTY_CONFIG = "netty.properties";

    private static NettyConfig ourInstance = null;

    public static NettyConfig getInstance() throws IOException {
        if (null == ourInstance) {
            ourInstance = new NettyConfig();
            Properties prop = loadConfig();
            if (null != prop) {
                ourInstance.setProp(prop);
            }
        }
        return ourInstance;
    }

    private Properties prop;

    private Properties getProp() {
        return prop;
    }

    private void setProp(Properties prop) {
        this.prop = prop;
    }

    public static String getConfigByKey(String key) {
        if (null != ourInstance.getProp()) {
            return ourInstance.getProp().getProperty(key);
        }
        return "";
    }

    private static Properties loadConfig() throws IOException {
        InputStream inputStream = null;
        Properties prop = new Properties();
        try {
            logger.info("loading property file '" + NETTY_CONFIG + "'");
            inputStream = NettyConfig.class.getClassLoader().getResourceAsStream(NETTY_CONFIG);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                logger.warn("property file '" + NETTY_CONFIG + "' not found in the classpath");
            }
            return prop;
        } catch (Exception e) {
            logger.error("loading property file  '" + NETTY_CONFIG + "' find error , error message is :" + e.getMessage());
            return prop;
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
        }
    }
}
