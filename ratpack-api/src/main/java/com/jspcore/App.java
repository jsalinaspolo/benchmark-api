package com.jspcore;

import com.jspcore.handler.MyHandler;
import com.jspcore.service.DownstreamDependency;
import io.netty.buffer.PooledByteBufAllocator;
import ratpack.http.client.HttpClient;
import ratpack.server.RatpackServer;

public class App {

    public static void main(String... args) throws Exception {
        HttpClient httpClient = HttpClient.of(httpClientSpec -> {
            httpClientSpec.byteBufAllocator(PooledByteBufAllocator.DEFAULT);
            httpClientSpec.poolSize(100000);
        });

        final DownstreamDependency downstreamDependency = new DownstreamDependency(httpClient);

        MyHandler handler = new MyHandler(downstreamDependency);
        RatpackServer.start(server -> server
                        .serverConfig(config -> config.threads(4))
                        .handlers(chain -> chain
                                        .path("slow", handler)
                                        .get("slower", ctx -> ctx.render("Hello slower"))
                        )
        );
    }
}
