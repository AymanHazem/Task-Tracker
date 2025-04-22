package com.ayman.taskracker.domain.dto;

public record ErrorResponse(int status , String message , String details) {
}
