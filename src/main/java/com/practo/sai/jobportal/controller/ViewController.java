package com.practo.sai.jobportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public String admin() {
    return "index.html";
  }

  @RequestMapping(value = "/employee", method = RequestMethod.GET)
  public String employee() {
    return "index.html";
  }

}
