package com.ishitwa.url_shortner.util;

import java.math.BigInteger;
import java.util.UUID;

public class UtilFunctions {

    public static UUID getUUID(String id){
        UUID uuid = new UUID(
                new BigInteger(id.substring(0, 16), 16).longValue(),
                new BigInteger(id.substring(16), 16).longValue());
        return uuid;
    }

    public static UUID getUUID2(String id){
        return UUID.fromString(id);
    }
}
