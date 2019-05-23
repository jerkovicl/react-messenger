package com.messenger.reactmessenger;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public WxMappingJackson2HttpMessageConverter() {
        List<MediaType> types = Arrays.asList(new MediaType("text", "html", DEFAULT_CHARSET),
                new MediaType("application", "json", DEFAULT_CHARSET),
                new MediaType("application", "*+json", DEFAULT_CHARSET));
        super.setSupportedMediaTypes(types);
    }
}