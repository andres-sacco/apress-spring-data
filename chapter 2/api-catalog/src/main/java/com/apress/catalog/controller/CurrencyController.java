package com.apress.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apress.catalog.dto.CurrencyDTO;
import com.apress.catalog.service.CurrrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private CurrrencyService currrencyService;

    @Autowired
    public CurrencyController(CurrrencyService currrencyService) {
        this.currrencyService = currrencyService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CurrencyDTO> getById(@PathVariable Long id) {
        CurrencyDTO response = currrencyService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<CurrencyDTO> save(@RequestBody CurrencyDTO currencyDTO) {
        CurrencyDTO response = currrencyService.save(currencyDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<CurrencyDTO> update(@RequestBody CurrencyDTO currencyDTO) {
        CurrencyDTO response = currrencyService.update(currencyDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        currrencyService.delete(id);
        return ResponseEntity.ok().build();
    }
}
