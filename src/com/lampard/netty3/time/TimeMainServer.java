package com.lampard.netty3.time;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by lampard on 16/8/28.
 */
public class TimeMainServer {
    private static final ChannelGroup allChannels = new DefaultChannelGroup("time-server");

    public static void main(String[] args) {

        NioServerSocketChannelFactory socketChannelFactory = new NioServerSocketChannelFactory(
                Executors.newFixedThreadPool(10), Executors.newFixedThreadPool(10));

        ServerBootstrap serverBootstrap = new ServerBootstrap(socketChannelFactory);
        serverBootstrap.setOption("child.tcpNoDelay", true);


        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new TimeEncoder(), new TimeServerHandler());
            }
        });
        serverBootstrap.setOption("child.keepAlive", true);


        Channel channel = serverBootstrap.bind(new InetSocketAddress(8080));


        allChannels.add(channel);
//        waitForShutdownCommand();

        ChannelGroupFuture future = allChannels.close();
        future.awaitUninterruptibly();
        socketChannelFactory.releaseExternalResources();
    }
}
