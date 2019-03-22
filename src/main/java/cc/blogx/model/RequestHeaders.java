package cc.blogx.model;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.StringUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author XueYuan.
 * @since 2019-03-22 14:11.
 */
public class RequestHeaders implements Serializable {

    private final static String TOKEN = "token";

    private String method;
    private String uri;
    private String token;
    private String body;
    private Map<String, Object> attr;


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, Object> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, Object> attr) {
        this.attr = attr;
    }


    public static RequestHeaders init(FullHttpRequest req) {
        RequestHeaders headers = new RequestHeaders();
        headers.setMethod(req.method().toString());
        headers.setUri(req.uri());
        //TODO 目前只支持 content-type 为 application/json 的，其他的后期添上
        headers.setBody(req.content().toString(CharsetUtil.UTF_8));
        if (!StringUtil.isNullOrEmpty(req.headers().get(TOKEN))) {
            headers.setToken(req.headers().get(TOKEN));
        }
        Iterator iterator = req.headers().iteratorAsString();
        Map<String, Object> attr = new HashMap<String, Object>(req.headers().size());
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            attr.put(entry.getKey().toString(), entry.getValue());
        }
        headers.setAttr(attr);
        return headers;
    }

}
