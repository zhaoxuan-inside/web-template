package com.ebupt.ysx.middle.auth.common.config;

import com.ebupt.ysx.middle.common.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class MessageUtilsConfig {

    private final MessageSource messageSource;

    /**
     * redisson bean
     */
    @Bean
    public MessageUtils messageUtils() {
        return new MessageUtils(messageSource);
    }

}
