package ru.otus.luminorena.web.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.luminorena.web.server.application.Storage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private int port;
    private Dispatcher dispatcher;
    private static final Logger logger = LogManager.getLogger(HttpServer.class.getName());

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.debug("Сервер запущен на порту:" + port);
            this.dispatcher = new ru.otus.luminorena.web.server.Dispatcher();
            logger.debug("Диспетчер проинициализирован");
            Storage.init();
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    byte[] buffer = new byte[8192];
                    int n = socket.getInputStream().read(buffer);
                    if (n > 0) {
                        String rawRequest = new String(buffer, 0, n);
                        HttpRequest request = new HttpRequest(rawRequest);
                        request.info(true);
                        dispatcher.execute(request, socket.getOutputStream());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
