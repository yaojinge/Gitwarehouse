package com.main;
import com.app.AppConfig;
import com.reactive.webFluxConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.netty.http.server.HttpServer;
import java.io.IOException;


public class webFluxApplication {
    public static void main(String[] args) throws IOException {
       AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class,webFluxConfig.class);
        //创建HttpHandle对象：WebHttpdlerBuilder.applicationContex方法创建
        HttpHandler httpHandler=WebHttpHandlerBuilder.applicationContext(context).build();
        //创建ReactorHttpHandlerAdapter对象
        ReactorHttpHandlerAdapter reactorHttpHandlerAdapter=new ReactorHttpHandlerAdapter(httpHandler);
       // 使用HttpServer创建对应的服务
      HttpServer.create().port(8090).handle(reactorHttpHandlerAdapter).bind().block();
        System.in.read();
    }
}
