package com.infobip.urlshortener.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <h2>
 *     Help Controller
 * </h2>
 * <p>
 *     This controller serves help page
 * </p>
 */
@Controller
public class HelpController {

    @GetMapping("/help")
    public String getHelpPage() {
        return "index";
    }

    @GetMapping("/")
    public String redirectToHelp() {
        return "redirect:help";
    }
}
