package org.example.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Server {
    private HttpServer network;

    public Server(int port) throws IOException {
        this.network = HttpServer.create();

        this.network.bind(new InetSocketAddress(port), 0);
    }

    public void start() {
        network.start();
    }

    private static class TestHandler implements HttpHandler {

        private final Controller controller;
        private final ObjectMapper mapper = new ObjectMapper();

        private TestHandler(Controller controller) {
            this.controller = controller;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String httpMethod = exchange.getRequestMethod();
            List<Method> methodList = List.of(controller.getClass().getDeclaredMethods());


                Object result = null;

                byte[] response;

                if (result instanceof byte[]) {
                    response = (byte[]) result;
                } else {
                    response = mapper
                            .writeValueAsString(result)
                            .getBytes(StandardCharsets.UTF_8);
                }

                OutputStream os = exchange.getResponseBody();

                exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
                exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
                exchange.sendResponseHeaders(200, response.length);

                os.write(response);

                os.flush();
                os.close();
                exchange.close();



        }

        public boolean checkUrlEquals(URI reqUrl, String checkUrl) {
            List<String> pathChunks = filterArray(reqUrl.getPath().split("/"));
            List<String> checkUrlChunks = filterArray(checkUrl.split("/"));

            if (pathChunks.size() != checkUrlChunks.size()) {
                return false;
            }

            for (int i = 0; i < pathChunks.size(); i++) {
                if (!(isPath(checkUrlChunks.get(i)) ||
                        pathChunks.get(i).equals(checkUrlChunks.get(i)))) {
                    return false;
                }
            }

            return true;
        }

        private boolean isPath(String chunk) {
            return chunk.charAt(0) == '{' && chunk.charAt(chunk.length() - 1) == '}';
        }

        private List<String> filterArray(String[] arr) {
            return Arrays.stream(arr).filter(s -> s.length() > 0).toList();
        }
    }

}

