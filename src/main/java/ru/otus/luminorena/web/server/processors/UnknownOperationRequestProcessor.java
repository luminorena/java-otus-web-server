package ru.otus.luminorena.web.server.processors;

import ru.otus.luminorena.web.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UnknownOperationRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        String response = "HTTP/1.1 404 NOT FOUND\r\nContent-Type: text/html\r\n\r\n<html><body><h1>The page is missing, 404</h1></body></html>";
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
