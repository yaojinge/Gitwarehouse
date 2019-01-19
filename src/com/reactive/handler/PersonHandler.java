package com.reactive.handler;
import com.pojo.TSysRole;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;
import java.util.function.Consumer;
import static  org.springframework.web.reactive.function.server.ServerResponse.ok;
public class PersonHandler {
   public static Mono<ServerResponse>list(ServerRequest requestMono){
       TSysRole tSysRole=new TSysRole();
       tSysRole.setId(10);
       tSysRole.setRoleName("admin");
       requestMono.exchange().getSession().subscribe(new Consumer<WebSession>() {
           @Override
           public void accept(WebSession webSession) {
               webSession.getAttributes().put("roles",tSysRole);
           }
       });
           return ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(tSysRole),TSysRole.class);
   }
}
