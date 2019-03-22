package cc.blogx;

import cc.blogx.acceptor.service.CommonNettySrv;

/**
 * @author XueYuan
 * @since 2019-03-05 19:34
 */
public class SrvStart {

    public static void main(String[] args) throws Exception {

        CommonNettySrv nettySrvAcceptor = new CommonNettySrv();
        nettySrvAcceptor.start();
    }
}
