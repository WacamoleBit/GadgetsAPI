package com.manu.GadgetAPI;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class ServiceConfiguration extends WsConfigurerAdapter {
    @Bean
    public XsdSchema gadgetSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schema.xsd"));
    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/api/*");
    }

    @Bean(name = "gadgets")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema gadgetSchema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("gadgetsPort");
        wsdl.setLocationUri("/api");
        wsdl.setTargetNamespace("https://apis.uv.mx/gadgets");
        wsdl.setSchema(gadgetSchema);
        return wsdl;
    }
}
