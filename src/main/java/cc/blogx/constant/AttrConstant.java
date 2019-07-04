package cc.blogx.constant;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * channel 暂存数据的key
 *
 * @author XueYuan.
 * @since 2019-03-13 13:06.
 */
public class AttrConstant {

    public static final AttributeKey<Channel> NETTY_TOKEN_KEY = AttributeKey.valueOf("netty.token");

    public static final ConcurrentHashMap REQUEST_MAPPER_MAP = new ConcurrentHashMap();

}
