package ru.otus.luminorena.web.server.application.processors;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.luminorena.web.server.HttpRequest;
import ru.otus.luminorena.web.server.application.Item;
import ru.otus.luminorena.web.server.application.Storage;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CreateNewProductProcessor implements RequestProcessor {
    private static final Logger logger = LogManager.getLogger(CreateNewProductProcessor.class.getName());
    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        Gson gson = new Gson();
        Item item = gson.fromJson(httpRequest.getBody(), Item.class);
        Storage.save(item);
        logger.debug("item has been saved");
        String jsonOutItem = gson.toJson(item);
        logger.debug(jsonOutItem);
        String response = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n" + jsonOutItem;
        logger.info(response);
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
