package cc.blogx.handler;

import cc.blogx.config.NettyConfig;
import cc.blogx.constant.AttributeMapConstant;
import cc.blogx.model.RequestHeaders;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author XueYuan.
 * @since 2019-03-13 10:56.
 */
@ChannelHandler.Sharable
public class SafeFilterHandler extends SimpleChannelInboundHandler<FullHttpRequest> {


    private static final Logger logger = LoggerFactory.getLogger(SafeFilterHandler.class);

    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        RequestHeaders headers = RequestHeaders.init(req);
        checkRequestIsSafe(ctx, headers);
    }

    private void checkRequestIsSafe(ChannelHandlerContext ctx, RequestHeaders headers) throws IOException {
        if (NettyConfig.getConfigByKey("netty.pass").contains(headers.getUri())) {
            ctx.writeAndFlush(headers);
        } else {
            if (!StringUtil.isNullOrEmpty(headers.getToken())
                    && ctx.channel().hasAttr(AttributeMapConstant.NETTY_TOKEN_KEY)
                    && headers.getToken().equals(ctx.channel().attr(AttributeMapConstant.NETTY_TOKEN_KEY).toString())) {
                ctx.writeAndFlush(headers);
            } else {
                //TODO token过期或者token不对
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


}
