package org.zhaoxuan.common.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.zhaoxuan.common.utils.MessageUtils;

@Configuration
@ComponentScan
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class MessageUtilsConfig {

    private final MessageSource messageSource;

    @Bean
    public MessageUtils messageUtils() {
        return new MessageUtils(messageSource);
    }

}
