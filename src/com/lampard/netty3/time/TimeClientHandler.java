package com.lampard.netty3.time;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import java.util.Date;

/**
 * Created by lampard on 16/8/28.
 */
public class TimeClientHandler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

        // TODO: 16/8/28  处理tcp/ip 拆包/粘包问题
//        ChannelBuffer buf = (ChannelBuffer) e.getMessage();/**/
//        long currentTimeMillis = buf.readInt() * 1000L;
//        System.out.println(new Date(currentTimeMillis));
//        e.getChannel().close();

//        if (buf.readableBytes() >= 4) {
//            long currentTimeMillis = buf.readInt() * 1000L;
//            System.out.println(new Date(currentTimeMillis));
//            e.getChannel().close();
//        }

        // TODO: 16/8/28  传输对象
        UnixTime m = (UnixTime) e.getMessage();
        System.out.println(m);
        e.getChannel().close();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
}
