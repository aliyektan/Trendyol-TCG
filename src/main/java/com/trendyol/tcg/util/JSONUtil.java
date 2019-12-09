package com.trendyol.tcg.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trendyol.tcg.constant.TCGConstants;
import com.trendyol.tcg.model.Card;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class JSONUtil {

    public static Map<String, List<Card>> readAllCards() throws URISyntaxException, IOException {

        String jsonString = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("json/cards.json").toURI())));

        Gson gson = new Gson();

        Type cardMapType = new TypeToken<Map<String, List<Card>>>() {
        }.getType();

        return gson.fromJson(jsonString, cardMapType);

    }

    public static TCGConstants readTCGConstants() throws URISyntaxException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("json/constants.json").toURI())));

        Gson gson = new Gson();

        return gson.fromJson(content, TCGConstants.class);
    }
}
