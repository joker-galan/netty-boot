package cc.blogx.common.handler;

import cc.blogx.common.model.RequestHeaders;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 分发请求到指定服务
 *
 * @author XueYuan
 * @since 2019-03-12 21:08
 */
@ChannelHandler.Sharable
public class HttpRequestHandler extends SimpleChannelInboundHandler<RequestHeaders> {

    protected void channelRead0(ChannelHandlerContext ctx, RequestHeaders headers) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
