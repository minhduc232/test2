package com.example.baitest2.config;

import com.example.baitest2.component.DateUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.internal.Errors;
import org.modelmapper.spi.ValueReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Configuration
public class MapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        return mapper;
    }

    @Bean
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();
        mapper.addConverter(new AbstractConverter<String, Date>() {
            @Override
            protected Date convert(String source) {
                try {
                    return DateUtils.convertToDate(source);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        mapper.addConverter(new AbstractConverter<UUID, String>() {
            @Override
            protected String convert(UUID uuid) {
                return uuid.toString();
            }
        });
        mapper.addConverter(new AbstractConverter<String, UUID>() {
            @Override
            protected UUID convert(String source) {
                return UUID.fromString(source);
            }
        });
        mapper.addConverter(new AbstractConverter<ZonedDateTime, Date>() {
            @Override
            protected Date convert(ZonedDateTime source) {
                return Date.from(source.toInstant());
            }
        });
        mapper.getConfiguration()
                .setFieldAccessLevel(AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .addValueReader(new RecordValueReader());
        return mapper;
    }

    private static class RecordValueReader implements ValueReader<Record> {

        @Override
        public Object get(Record record, String memberName) {
            var field = matchField(record, memberName);
            if (field != null) {
                try {
                    return field.get(record);
                } catch (IllegalAccessException e) {
                    throw new Errors().addMessage(e, "Cannot get the member").toMappingException();
                }
            }
            return null;
        }

        @Override
        public Member<Record> getMember(Record record, String memberName) {
            var field = matchField(record, memberName);
            var type = field != null ? field.getType() : null;
            return new Member<>(type) {
                @Override
                public Object get(Record record, String memberName) {
                    return RecordValueReader.this.get(record, memberName);
                }
            };
        }

        @Override
        public Collection<String> memberNames(Record record) {
            return Arrays.stream(record.getClass().getDeclaredFields())
                    .peek(field -> field.setAccessible(true))
                    .map(Field::getName)
                    .toList();
        }

        private Field matchField(Record source, String memberName) {
            return Arrays.stream(source.getClass().getDeclaredFields())
                    .peek(field -> field.setAccessible(true))
                    .filter(field -> memberName.equalsIgnoreCase(field.getName()))
                    .findFirst().orElse(null);
        }
    }

}
