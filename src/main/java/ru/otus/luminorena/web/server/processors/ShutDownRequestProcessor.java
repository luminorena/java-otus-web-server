package ru.otus.luminorena.web.server.processors;

import ru.otus.luminorena.web.server.HttpRequest;

import java.io.OutputStream;


public class ShutDownRequestProcessor implements RequestProcessor{
    @Override
    public void execute(HttpRequest httpRequest, OutputStream output)  {
        System.exit(-1);
    }
}
