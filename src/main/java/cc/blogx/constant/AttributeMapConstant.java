package cc.blogx.constant;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * @author XueYuan.
 * @since 2019-03-13 13:06.
 */
public class AttributeMapConstant {

    public static final AttributeKey<Channel> NETTY_CHANNEL_KEY = AttributeKey.valueOf("netty.channel");

}
