package com.rajivcoomar.ivrtest.readthemanual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajivcoomar.ivrtest.readthemanual.service.ExcelService;

@RestController
//@RequestMapping("/excel")
public class SanityCheck{
	

    @Autowired
    ExcelService  excelService;
    

	@GetMapping("/DB")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		 return excelService.readExcelData().toString();
	}

}
