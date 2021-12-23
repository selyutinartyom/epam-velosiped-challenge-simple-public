package dev.abarmin.velosiped.task2;

import com.sun.net.httpserver.HttpServer;
import dev.abarmin.velosiped.task1.VelosipedTask1Handler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class VelosipedTask2Impl implements VelosipedTask2 {

    private HttpServer server;
    private final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    @Override
    public void startServer(int port) {
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", new VelosipedTask2Handler());
            server.setExecutor(executor);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stopServer() {
        if (server == null) {
            return;
        }
        server.stop(1);
    }
}
