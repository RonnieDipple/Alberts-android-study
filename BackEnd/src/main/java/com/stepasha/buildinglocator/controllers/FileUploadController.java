package com.stepasha.buildinglocator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileUploadController {
public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";

}
