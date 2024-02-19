package io.doeasy.netty.adhesive;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * 粘包服务端
 * @author <a href="mailto:kris.wang@mexc.com">kris.wang</a>
 */
public class AdhesivePackageServer {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();
        server.group(group);
        server.channel(NioServerSocketChannel.class);
        server.childHandler(new ServerInitializer());
        server.bind("127.0.0.1", 8081);
    }

}
