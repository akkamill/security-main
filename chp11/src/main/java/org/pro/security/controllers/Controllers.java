package org.pro.security.controllers;

import lombok.extern.slf4j.Slf4j;
import org.pro.security.entity.Otp;
import org.pro.security.entity.Sms;
import org.pro.security.entity.User;
import org.pro.security.service.SmsService;
import org.pro.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class Controllers {

    private final UserService userService;

    @Autowired
    private SmsService smsService;

    public Controllers(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/add")
    public void userAdd(@RequestBody User user) {
        userService.addUser(user);
    }


    @PostMapping("/user/auth")
    public void auth(@RequestBody User user) {
        userService.auth(user);
    }

    @PostMapping("/otp/check")
    public void check(@RequestBody Otp otp, HttpServletResponse response) {
        if (userService.check(otp)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    //    @PostMapping("/otp/check")
//    @ResponseStatus(HttpStatus.OK)
//    public void check(@RequestBody Otp otp, HttpServletResponse response) {
//        if (!userService.check(otp)) {
////            response.setStatus(HttpServletResponse.SC_OK);
////        } else {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
////        }
//    }

//    @PostMapping("/otp/check")
//    public ResponseEntity<Void> check(@RequestBody Otp otp) {
//        if (userService.check(otp)) {
//            return ResponseEntity.ok().build();  // OK status
//        } else {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();  // Forbidden status
//        }
//    }


    @GetMapping("/test")
    public String test() {
        return "Test";
    }

    @PostMapping("/sms/request")
    public String smsProcess(@RequestBody Sms sms) {

        log.info("process SMS " + sms.toString());
        return smsService.sendSMS(sms.getSmsNumber(), sms.getSmsMessage());
    }
}