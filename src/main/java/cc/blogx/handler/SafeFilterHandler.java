package cc.blogx.handler;

import cc.blogx.config.NettyConfig;
import cc.blogx.constant.AttrConstant;
import cc.blogx.model.RequestHeaders;
import cc.blogx.util.ResponseUtil;
import cc.blogx.util.ResultUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.internal.StringUtil;

/**
 * 安全机制
 *
 * @author XueYuan.
 * @since 2019-03-13 10:56.
 */
@ChannelHandler.Sharable
public class SafeFilterHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        RequestHeaders headers = RequestHeaders.init(req);
        checkRequestIsSafe(ctx, headers);
    }

    private void checkRequestIsSafe(ChannelHandlerContext ctx, RequestHeaders headers) {
        if (NettyConfig.getConfigByKey("netty.pass").contains(headers.getUri())) {
            ctx.writeAndFlush(headers);
        } else {
            if (!StringUtil.isNullOrEmpty(headers.getToken())
                    && ctx.channel().hasAttr(AttrConstant.NETTY_TOKEN_KEY)
                    && headers.getToken().equals(ctx.channel().attr(AttrConstant.NETTY_TOKEN_KEY).toString())) {
                ctx.writeAndFlush(headers);
            } else {
                ResultUtil res = ResultUtil.getFail();
                res.setMsg("token不匹配!");
                ctx.writeAndFlush(ResponseUtil.back200(res));
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

}
