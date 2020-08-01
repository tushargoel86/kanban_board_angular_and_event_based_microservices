package com.tushar.eventstores.example.kanban.task.config;

import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

@Configuration
class DateTimeConfig {
 
    @Bean
    public FormattingConversionService conversionService() {
        DefaultFormattingConversionService conversionService = 
          new DefaultFormattingConversionService(false);
 
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateFormatter(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
      //  registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("dd.MM.yyy"));
        registrar.registerFormatters(conversionService);
 
        // other desired formatters
 
        return conversionService;
    }
}