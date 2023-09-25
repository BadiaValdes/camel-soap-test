package com.camel.soap.processors;

import com.camel.soap.service.GenreService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.example.testwsdlfile.GenreType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateGenre implements Processor {

    @Autowired
    private GenreService genreService;

    @Override
    public void process(Exchange exchange) throws Exception {
        GenreType genreType = exchange.getIn().getBody(GenreType.class);
        try {
            genreService.createData(genreType);
        }
        catch (Error e){
            System.out.println(e);
        }
        exchange.getMessage().setBody("<trama>All is well</trama>");



    }
}
