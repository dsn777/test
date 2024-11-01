//package com.demo.taxi;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class MessageController {
//    private final ProducerService producerService;
//
//    public MessageController(ProducerService producerService) {
//        this.producerService = producerService;
//    }
//
//    @GetMapping("/send")
//    public void sendMessage(@RequestParam String message) {
//        producerService.sendMessage(message);
//    }
//}
