package io.doeasy.netty.adhesive;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author <a href="mailto:kris.wang@mexc.com">kris.wang</a>
 */
public class AdhesivePackageClient {

    public static void main(String[] args){
        EventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap client = new Bootstrap();

        try{
            client.group(worker);
            client.channel(NioSocketChannel.class);
            client.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                        /**
                         * 在通道准备就绪后会触发的事件
                         */
                        public void channelActive(ChannelHandlerContext ctx) throws Exception{
                            for(int i = 0; i < 10; i++) {
                                System.out.println("正在向服务端发送第 " + (i+1) + " 次数据......");
                                ByteBuf buf = ctx.alloc().buffer(i);
                                buf.writeBytes(new byte[] {(byte)i});
                                ctx.writeAndFlush(buf);
                            }
                        }
                    });

                }
            });
            client.connect("127.0.0.1", 8081).sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
