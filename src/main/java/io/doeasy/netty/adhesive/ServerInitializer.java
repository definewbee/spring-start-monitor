package io.doeasy.netty.adhesive;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author <a href="mailto:kris.wang@mexc.com">kris.wang</a>
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
        socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
            /**
             * 数据就绪事件：当收到客户端数据时会读取通道内的数据
             */
            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                System.out.println(ctx.channel());
                super.channelReadComplete(ctx);
            }
        });
    }
}
