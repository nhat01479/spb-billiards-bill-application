package com.cg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageableReqDTO {
    private String keySearch;
    private Integer page;
    private String sortBy;
    private String sort;
    private Integer limit;
    private String dimension;
}
