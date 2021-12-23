package dev.abarmin.velosiped.task1;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class VelosipedTask1Handler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            var a = handleGetRequest(exchange, "a");
            var b = handleGetRequest(exchange, "b");
            var sum = a + b;

            handleResponse(exchange, String.valueOf(sum));
        }
    }

    private int handleGetRequest(HttpExchange exchange, String param) {
        return Integer.parseInt(
                exchange
                        .getRequestURI()
                        .getQuery()
                        .split(param + "=")[1]
                        .split("&")[0]
        );
    }

    private void handleResponse(HttpExchange exchange, String result) throws IOException {
        exchange.sendResponseHeaders(200, result.length());
        var os = exchange.getResponseBody();
        os.write(result.getBytes());
        os.close();
    }
}
