package org.project.management.app.rest.dto;

import lombok.Builder;

@Builder
public record ErrorDTO(String fieldName,
                       String message) {
}
