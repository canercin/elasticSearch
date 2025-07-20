package com.learning.elastic.controller.extended;

import com.fastsearch.fast_search.api.requests.impl.MultiMatchSearchRequest;
import com.fastsearch.fast_search.api.requests.impl.MultipleValueSearchRequest;
import com.fastsearch.fast_search.api.requests.impl.RangeSearchRequest;
import com.fastsearch.fast_search.api.requests.impl.SingleValueSearchRequest;
import com.learning.elastic.entity.PersonelEntity;
import com.learning.elastic.service.extended.PersonelExtendedService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personel")
public class PersonelExtendedController {

    private final PersonelExtendedService personelExtendedService;

    public PersonelExtendedController(PersonelExtendedService personelExtendedService) {
        this.personelExtendedService = personelExtendedService;
    }

    @GetMapping("/match-all")
    public List<PersonelEntity> matchAll() {
        return personelExtendedService.matchAll();
    }

    @PostMapping("/match")
    public List<PersonelEntity> match(@RequestBody SingleValueSearchRequest singleValueSearchRequest) {
        return personelExtendedService.match(singleValueSearchRequest);
    }

    @PostMapping("/term")
    public List<PersonelEntity> term(@RequestBody SingleValueSearchRequest singleValueSearchRequest) {
        return personelExtendedService.term(singleValueSearchRequest);
    }

    @PostMapping("/terms")
    public List<PersonelEntity> terms(@RequestBody MultipleValueSearchRequest multipleValueSearchRequest) {
        return personelExtendedService.terms(multipleValueSearchRequest);
    }

    @PostMapping("/multi-match")
    public List<PersonelEntity> multiMatch(@RequestBody MultiMatchSearchRequest multiMatchSearchRequest) {
        return personelExtendedService.multiMatch(multiMatchSearchRequest);
    }

    @PostMapping("/numeric-range")
    public List<PersonelEntity> numericRange(@RequestBody RangeSearchRequest rangeSearchRequest) {
        return personelExtendedService.numericRange(rangeSearchRequest);
    }

    @PostMapping("/date-range")
    public List<PersonelEntity> dateRange(@RequestBody RangeSearchRequest rangeSearchRequest) {
        return personelExtendedService.dateRange(rangeSearchRequest);
    }

    @GetMapping("/bool")
    public List<PersonelEntity> bool() {
        return personelExtendedService.boolQuery();
    }
}
