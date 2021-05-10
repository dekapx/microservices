package com.dekapx.apps.contact.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditModel {
    @Schema(description = "Name of the Audit property.")
    private String propertyName;

    @Schema(description = "Audit property value before modification.")
    private String oldValue;

    @Schema(description = "Audit property value after modification.")
    private String newValue;

    @Schema(description = " Audit property modified by.")
    private String modifiedBy;

    @Schema(description = "Date Audit property modified.")
    private String modifiedDate;
}
