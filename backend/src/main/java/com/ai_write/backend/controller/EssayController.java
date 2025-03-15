package com.ai_write.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai_write.backend.payload.RequestDTO;
import com.ai_write.backend.payload.ResponseDTO;
import com.ai_write.backend.service.EssayService;


@RestController
@RequestMapping("/api")
public class EssayController {

    @Autowired
    private EssayService essayService;

    @CrossOrigin(origins = "http://127.0.0.1:3000")
    @PostMapping("/generate")
    public ResponseEntity<ResponseDTO> generate(@RequestBody RequestDTO body) {
        ResponseDTO responseDTO = essayService.genrateEssay(body);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
