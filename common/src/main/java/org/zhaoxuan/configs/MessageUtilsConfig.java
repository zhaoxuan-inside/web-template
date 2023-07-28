package org.zhaoxuan.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zhaoxuan.utils.MessageUtils;

@Configuration
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class MessageUtilsConfig {

    private final MessageSource messageSource;

    @Bean
    public MessageUtils messageUtils() {
        return new MessageUtils(messageSource);
    }

}
