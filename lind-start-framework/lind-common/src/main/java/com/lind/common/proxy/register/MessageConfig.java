package com.lind.common.proxy.register;

import com.lind.common.proxy.handler.ConsoleMessageProviderHandler;
import com.lind.common.proxy.handler.DefaultSuccessSendHandler;
import com.lind.common.proxy.handler.EmailMessageProviderHandler;
import com.lind.common.proxy.handler.MessageProviderHandler;
import com.lind.common.proxy.handler.SmsMessageProviderHandler;
import com.lind.common.proxy.handler.SuccessSendHandler;
import com.lind.common.proxy.service.DefaultMessageService;
import com.lind.common.proxy.service.MessageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {
    @Bean("messageService")
    @ConditionalOnMissingBean
    public MessageService messageService() {
        return new DefaultMessageService();
    }

    @Bean("successSendHandler")
    @ConditionalOnMissingBean
    public SuccessSendHandler successSendHandler() {
        return new DefaultSuccessSendHandler();
    }

    @Bean("ConsoleMessageProviderHandler")
     public MessageProviderHandler consoleMessageProviderHandler() {
        return new ConsoleMessageProviderHandler();
    }

    @Bean("SmsMessageProviderHandler")
     public MessageProviderHandler smsMessageProviderHandler() {
        return new SmsMessageProviderHandler();
    }

    @Bean("EmailMessageProviderHandler")
     public MessageProviderHandler emailMessageProviderHandler() {
        return new EmailMessageProviderHandler();
    }
}
