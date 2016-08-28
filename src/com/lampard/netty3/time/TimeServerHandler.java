package com.lampard.netty3.time;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

/**
 * Created by lampard on 16/8/28.
 */
public class TimeServerHandler extends SimpleChannelHandler {

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {

        Channel channel = e.getChannel();

//        ChannelBuffer timeChannelBuffer = ChannelBuffers.buffer(4);
//        timeChannelBuffer.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
//
//        ChannelFuture channelFuture = channel.write(timeChannelBuffer);
//
//        channelFuture.addListener(new ChannelFutureListener() {
//            public void operationComplete(ChannelFuture future) {
//                Channel ch = future.getChannel();
//                ch.close();
//            }
//        });

        UnixTime time = new UnixTime((int) (System.currentTimeMillis() / 1000));
        ChannelFuture f = e.getChannel().write(time);
        f.addListener(ChannelFutureListener.CLOSE);


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }

}
