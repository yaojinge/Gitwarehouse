package com.reactive;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;

@EnableWebFlux
@Configuration
@ComponentScan
public class webFluxConfig implements WebFluxConfigurer,ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Bean
    public SpringResourceTemplateResolver templateResolver(){
   SpringResourceTemplateResolver templateResolver=new SpringResourceTemplateResolver();
   templateResolver.setApplicationContext(applicationContext);
   templateResolver.setCharacterEncoding("UTF-8");
   templateResolver.setSuffix(".html");
   templateResolver.setPrefix("classpath:/templates/");
   templateResolver.setCacheable(false);
   return templateResolver;
    }
    @Bean
     public SpringWebFluxTemplateEngine templateEngine(){
        SpringWebFluxTemplateEngine templateEngine=new SpringWebFluxTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
   }
    @Bean
   public ThymeleafReactiveViewResolver viewResolver(){
        ThymeleafReactiveViewResolver  thymeleafReactiveViewResolver=new ThymeleafReactiveViewResolver();
        thymeleafReactiveViewResolver.setTemplateEngine(templateEngine());
         return thymeleafReactiveViewResolver;
   }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(viewResolver());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         this.applicationContext=applicationContext;
    }
}
