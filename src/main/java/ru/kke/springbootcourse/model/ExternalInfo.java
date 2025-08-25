package ru.kke.springbootcourse.model;

import lombok.Builder;

@Builder
public record ExternalInfo(
        String info) {
}
