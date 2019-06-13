package com.anla.rpc.hellodubbo.api.controller;

import com.anla.rpc.hellodubbo.stub.provider.IndexFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/5/20 15:15
 **/
@Slf4j
@RestController
@RequestMapping("/api/index")
public class IndexController {

    @Autowired
    private IndexFacade indexFacade;

    @GetMapping("/echo")
    public String echoBack(String msg){
        return indexFacade.index(msg).toString();
    }
}
