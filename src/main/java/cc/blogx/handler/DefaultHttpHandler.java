package cc.blogx.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author XueYuan
 * @since 2019-03-12 21:08
 */
@ChannelHandler.Sharable
public class DefaultHttpHandler extends SimpleChannelInboundHandler {

    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {

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
