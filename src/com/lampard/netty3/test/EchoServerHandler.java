package com.lampard.netty3.test;

import org.jboss.netty.buffer.BigEndianHeapChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

import java.util.Date;


/**
 * Created by lampard on 16/8/28.
 */
public class EchoServerHandler extends SimpleChannelUpstreamHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);


        ChannelBuffer buf = (ChannelBuffer) e.getMessage();
        while (buf.readable()) {
            System.out.println((char) buf.readByte());
            System.out.flush();
        }

        // Send back the received message to the remote peer.
        String message = "receiv message:" + new String(((BigEndianHeapChannelBuffer) e.getMessage()).array(), "UTF-8");
        message = message + ";time:" + new Date();

        BigEndianHeapChannelBuffer backMessage = new BigEndianHeapChannelBuffer(message.getBytes("UTF-8"));
        e.getChannel().write(backMessage);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);
        e.getCause().printStackTrace();
        Channel ch = e.getChannel();
        ch.close();
    }
}
