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

public class UpdateProductDataProcessor implements RequestProcessor{
    private static final Logger logger = LogManager.getLogger(UpdateProductDataProcessor.class.getName());
    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        Gson gson = new Gson();
        Item item = gson.fromJson(httpRequest.getBody(), Item.class);
        String jsonOutItem = gson.toJson(item);
        logger.debug(jsonOutItem);
        String errorMsg = "Id " + item.getId()  + " is not found";
        String response;
        try {
            Storage.update(item);
            response = "HTTP/1.1 201 Created\r\nContent-Type: application/json\r\n\r\n" + jsonOutItem;
            logger.info(response);
        } catch (RuntimeException e) {
            response = "HTTP/1.1 404 Not Found\r\nContent-Type: application/json\r\n\r\n" + errorMsg;
            logger.error(response);
        }
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }

}
