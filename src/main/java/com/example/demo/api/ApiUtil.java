package com.example.demo.api;

import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiUtil {
    public static void setExampleResponse(NativeWebRequest req, String contentType, String example) {
        try {
            HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Content-Type", contentType);
            res.getWriter().print(example);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
