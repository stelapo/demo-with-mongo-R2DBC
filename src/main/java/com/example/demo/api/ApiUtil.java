package com.example.demo.api;

import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;

public class ApiUtil {

    public static boolean applicationJsonHeaderExists(String header) {
        return MediaType.parseMediaTypes(header)
                .stream()
                .filter(mediaType -> mediaType.isCompatibleWith(MediaType.valueOf("application/json")))
                .count() > 0;
    }

    public static boolean applicationJsonHeaderExists(NativeWebRequest request) {
        return applicationJsonHeaderExists(request.getHeader("Accept"));
    }
}
