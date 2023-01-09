package com.algorithm.netty;

import io.netty.channel.*;

@ChannelHandler.Sharable
public class ChannelInit extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Channel client = ctx.channel();
        ChannelPipeline pipeline = client.pipeline();
        pipeline.addLast(new MyInHandler());
        ctx.pipeline().remove(this);
    }
}
