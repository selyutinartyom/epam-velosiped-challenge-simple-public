package dev.abarmin.velosiped.task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class VelosipedTask2Handler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            var request = new ObjectMapper()
                    .readValue(exchange.getRequestBody(), Request.class);
            var result = new Response(request.getArg1() + request.getArg2());
            handleResponse(exchange, result);
        }
    }

    private void handleResponse(HttpExchange exchange, Response result) throws IOException {
        var os = exchange.getResponseBody();
        var mapper = new ObjectMapper();
        byte[] bytes = mapper.writeValueAsBytes(result);
        exchange.sendResponseHeaders(200, bytes.length);
        os.write(bytes);
        os.close();
    }
}
