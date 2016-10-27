package com.practo.sai.jobportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller that resolves views.
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
@Controller
public class ViewController {

  /**
   * Controller Method that redirects to index page.
   * 
   * @return index.html
   */
  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public String admin() {
    return "index.html";
  }

  /**
   * Controller Method that redirects to index page.
   * 
   * @return index.html
   */
  @RequestMapping(value = "/employee", method = RequestMethod.GET)
  public String employee() {
    return "index.html";
  }

}
