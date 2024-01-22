package com.sample.LLD.Verticles;

import com.sample.LLD.Services.UserService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import static com.sample.LLD.Constants.DataConstants.*;
import static com.sample.LLD.Constants.APIEndPoints.*;

public class HttpVerticle extends AbstractVerticle {

  UserService userService = new UserService();

  public void start(Promise<Void> startPromise) {
    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());

    router.get(GET_USER).consumes(APPLICATION_JSON).produces(APPLICATION_JSON).handler(rc -> apiHandler(rc, GET_USER));

    createHttpServer(startPromise, router);
  }

  private void createHttpServer(Promise<Void> startPromise, Router router) {
    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8888, http -> {
        if (http.succeeded()) {
          System.out.println("HTTP server started on port 8888");
          startPromise.complete();
        } else {
          startPromise.fail(http.cause());
        }
    });
  }

  private void apiHandler(RoutingContext rc, String address) {

    JsonObject req = rc.body().asJsonObject();

    switch (address) {
      case GET_USER:
        userService.getUser(req);
    }
  }
}
