package com.webshop.controller;

import com.webshop.service.CaffFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CaffFileController {
    private final CaffFileService caffFileService;

    public CaffFileController(CaffFileService caffFileService) {
        this.caffFileService = caffFileService;
    }

    @RequestMapping("/caff-files/{caffPostId}")
    public ResponseEntity<byte[]> findCaffFileById(@PathVariable long caffPostId) {
        return new ResponseEntity<>(caffFileService.getCaffFileByteArrayByCaffPostId(caffPostId), HttpStatus.OK);
    }
}
