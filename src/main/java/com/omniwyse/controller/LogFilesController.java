package com.omniwyse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omniwyse.serviceInterface.AwsLogFiles;

@RestController
public class LogFilesController {
 
	@Autowired
	AwsLogFiles awslogfiles;
	
	@RequestMapping(value = "/search", method= RequestMethod.GET)
	public List<String> search()
	{
		return awslogfiles.logFiles();
	}
}
