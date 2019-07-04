package cc.blogx.common.acceptor.service;

/**
 * @author XueYuan
 * @since 2019-03-04 19:36
 */
public interface SrvAcceptor {

    void start() throws Exception;

    void start(boolean sync) throws Exception;

    void shutdown();

    void httpSrvLoading();
}