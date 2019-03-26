package cc.blogx;

import cc.blogx.annotation.ScanMapper;
import cc.blogx.common.acceptor.service.CommonNettySrv;

/**
 * 启动类
 *
 * @author XueYuan
 * @since 2019-03-05 19:34
 */

@ScanMapper(value = "cc.blog.service")
public class SrvStart {
    public static void main(String[] args) throws Exception {
        CommonNettySrv nettySrvAcceptor = new CommonNettySrv(SrvStart.class);
//        nettySrvAcceptor.start();
    }
}
