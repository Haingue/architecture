package com.imt.intes.partservice.dto;

import java.util.List;

public record PageDto<T> (
        List<T> content,
        int page,
        int pageNumber,
        int totalElement
){}
