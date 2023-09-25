package com.camel.soap.routes;

import com.camel.soap.processors.CreateGenre;
import com.camel.soap.service.GenreService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.jaxws.CxfEndpoint;
import org.example.testwsdlfile.GetGenre;
import org.example.testwsdlfile.TestWSDLFile;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SoapRoute extends RouteBuilder {
    @Bean("game")
    public CxfEndpoint endpoint() {
        CxfEndpoint cxfEndpoint = new CxfEndpoint();
        cxfEndpoint.setAddress("/game");
        cxfEndpoint.setServiceClass(TestWSDLFile.class);
        return cxfEndpoint;
    }

    @Override
    public void configure() throws Exception {
        from("cxf:bean:game")
                .log("Initial Call")
                .recipientList(simple("direct:${header.operationName}"));

        from("direct:GetGame").log("Estoy aqui");

        from("direct:CreateGenre")
                .log("Body: ${body}")
                .doTry()
                    .bean(GenreService.class,"createData")
                .doCatch(Error.class)
                    .log("An error occur whe the route were trying to create a genre");

        from("direct:GetGenre")
                .id("get_genre")
                .log("Get Genre")
                .process(exchange -> {
                    exchange.getMessage().setBody(exchange.getIn().getBody(String.class));
                })
                .bean(GenreService.class,"getOne")
                .log("Data -> ${body.id}");
    }
}
