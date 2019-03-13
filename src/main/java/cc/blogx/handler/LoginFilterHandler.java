package cc.blogx.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author XueYuan.
 * @since 2019-03-13 13:30.
 */
@ChannelHandler.Sharable
public class LoginFilterHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final static String LOGIN = "/login";
    private final static String LOGOUT = "/logout";
    private final static String REGISTERED = "/registered";

    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        String url = req.uri();
//        if ()
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
