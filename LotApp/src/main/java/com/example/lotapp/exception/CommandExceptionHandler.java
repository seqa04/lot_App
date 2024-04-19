package com.example.lotapp.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.shell.command.CommandExceptionResolver;
import org.springframework.shell.command.CommandHandlingResult;

import java.util.NoSuchElementException;

@Configuration
class CommandExceptionHandler implements CommandExceptionResolver {

    @Override
    public CommandHandlingResult resolve(Exception ex) {
        if (ex instanceof NoSuchElementException) {
            return CommandHandlingResult.of(ex.getMessage() + "\n");
        }
        if (ex instanceof IllegalArgumentException) {
            return CommandHandlingResult.of(ex.getMessage() + "\n");
        }
        if (ex instanceof RuntimeException) {
            return CommandHandlingResult.of(ex.getMessage() + "\n");
        }
        return null;
    }
}
