package cc.blogx.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

/**
 * 构建响应数据
 *
 * @author XueYuan
 * @since 2019-03-22 23:49
 */
public class ResponseUtil {

    private static HttpVersion HTTP_VERSION = HttpVersion.HTTP_1_1;

    /**
     * 方法找不到
     */
    public static DefaultFullHttpResponse back404() {
        return ResponseUtil.back404("Not Found");
    }

    public static DefaultFullHttpResponse back404(String content) {
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HTTP_VERSION, HttpResponseStatus.NOT_FOUND,
                Unpooled.wrappedBuffer(content.getBytes()));
        headers(response);
        return response;
    }

    /**
     * 网络相关异常
     */
    public static DefaultFullHttpResponse back502() {
        return ResponseUtil.back404("Bad Gateway");
    }

    public static DefaultFullHttpResponse back502(String content) {
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HTTP_VERSION, HttpResponseStatus.BAD_GATEWAY,
                Unpooled.wrappedBuffer(content.getBytes()));
        headers(response);
        return response;
    }

    /**
     * 请求成功
     */
    public static DefaultFullHttpResponse back200(ResultUtil res) {
        String content = new Gson().toJson(res);
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HTTP_VERSION, HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(content.getBytes()));
        headers(response);
        return response;
    }

    private static void headers(DefaultFullHttpResponse response) {
        HttpHeaders heads = response.headers();
        heads.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON + ";charset=UTF-8");
        heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
    }


}
