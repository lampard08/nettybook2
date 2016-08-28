package com.lampard.netty3.time;

import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by lampard on 16/8/28.
 */
public class TimeClient {
    public static void main(String[] args) {
        String host = "";
        Integer port = 0;

        if (StringUtils.isBlank(host)) {
            host = "lampard.com";
        }

        if (port == null || port.intValue() == 0) {
            port = 8080;
        }

        ChannelFactory factory = new NioClientSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool());

        ClientBootstrap bootstrap = new ClientBootstrap(factory);

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() {
                return Channels.pipeline(new TimeDecoder(), new TimeClientHandler());
            }
        });

        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);


        ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));
        future.awaitUninterruptibly();

        if (!future.isSuccess()) {
            future.getCause().printStackTrace();
        }

        future.getChannel().getCloseFuture().awaitUninterruptibly();
        factory.releaseExternalResources();

    }
}
