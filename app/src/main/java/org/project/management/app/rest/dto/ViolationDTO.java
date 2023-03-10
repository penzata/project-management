package org.project.management.app.rest.dto;

import lombok.Builder;

@Builder
public record ViolationDTO(String fieldName,
                           String message) {}