package com.omniwyse.serviceInterface;

import java.util.List;

public interface AwsLogFiles {
	
     public List<String> logFiles();
     public List<String> searchLogFiles(List<String> files, String searchkey) ;  
}
