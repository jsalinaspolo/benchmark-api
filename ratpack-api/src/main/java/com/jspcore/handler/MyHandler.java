package com.jspcore.handler;

import com.jspcore.service.DownstreamDependency;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.client.ReceivedResponse;

public class MyHandler implements Handler {

    private DownstreamDependency downstreamDependency;

    public MyHandler(DownstreamDependency downstreamDependency) {
        this.downstreamDependency = downstreamDependency;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        downstreamDependency.retrieve().then(r -> ctx.render(r.getBody().getText()));
    }
}
