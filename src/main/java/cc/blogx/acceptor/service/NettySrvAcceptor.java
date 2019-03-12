package cc.blogx.acceptor.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.internal.PlatformDependent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;
import java.util.concurrent.ThreadFactory;

/**
 * @author XueYuan
 * @since 2019-03-04 19:44
 */
public abstract class NettySrvAcceptor implements SrvAcceptor {

    private static final Logger logger = LoggerFactory.getLogger(NettySrvAcceptor.class);

    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private ServerBootstrap bootstrap;
    private EventLoopGroup boss;
    private EventLoopGroup worker;
    private int workers;
    protected volatile ByteBufAllocator allocator;
    protected final SocketAddress localAddress;


    public NettySrvAcceptor(SocketAddress localAddress) {
        this(localAddress, AVAILABLE_PROCESSORS << 1);
    }

    public NettySrvAcceptor(SocketAddress localAddress, int workers) {
        this.localAddress = localAddress;
        this.workers = workers;
    }

    public void init() {
        ThreadFactory bossFactory = new DefaultThreadFactory("netty.acceptor.boss");
        ThreadFactory workerFactory = new DefaultThreadFactory("netty.acceptor.worker");

        boss = initEventLoopGroup(1, bossFactory);
        worker = initEventLoopGroup(workers, workerFactory);
        //使用池化的directBuffer
        /**
         * 一般高性能的场景下,使用的堆外内存，也就是直接内存，使用堆外内存的好处就是减少内存的拷贝，和上下文的切换，缺点是
         * 堆外内存处理的不好容易发生堆外内存OOM
         * 当然也要看当前的JVM是否只是使用堆外内存，换而言之就是是否能够获取到Unsafe对象#PlatformDependent.directBufferPreferred()
         */
        allocator = new PooledByteBufAllocator(PlatformDependent.directBufferPreferred());
        //create && group
        bootstrap = new ServerBootstrap().group(worker, worker);
        //ByteBufAllocator 配置
        bootstrap.childOption(ChannelOption.ALLOCATOR, allocator);
    }

    public void start() throws Exception {
        this.start(true);
    }

    public void start(boolean sync) throws Exception {
        ChannelFuture future = bind(localAddress).sync();
        logger.info("netty acceptor server start");
        if (sync) {
            future.channel().closeFuture().sync();
        }
    }

    public void shutdown() {
        boss.shutdownGracefully().awaitUninterruptibly();
        worker.shutdownGracefully().awaitUninterruptibly();
    }

    protected ServerBootstrap bootstrap() {
        return bootstrap;
    }

    protected abstract EventLoopGroup initEventLoopGroup(int threadNum, ThreadFactory factory);

    protected abstract ChannelFuture bind(SocketAddress localAddress);

    protected abstract void optionFactory();


}
