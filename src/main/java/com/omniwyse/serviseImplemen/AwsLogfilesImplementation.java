package com.omniwyse.serviseImplemen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.omniwyse.serviceInterface.AwsLogFiles;

@Service
public class AwsLogfilesImplementation implements AwsLogFiles {
    
	@Autowired
	private AmazonS3 amazonS3;
	
	@Value("${jsa.s3.bucket}")
	private String bucketname;
	
	@Value("${jsa.s3.prefix}")
	private String prefix;
	
	@Value("${jsa.s3.searchKey}")
	private String searchKey;
	
	@Value("${jsa.s3.searchValue}")
	private String searchValue;
	
	@Override
	public List<String> logFiles() {
		List<String> list = new ArrayList<String>();
		String key = "<ns2:" + searchKey +">" + searchValue + "</ns2:" + searchKey + ">";
		ObjectListing objectListing = amazonS3.listObjects(bucketname, prefix + "/");
		for(S3ObjectSummary os : objectListing.getObjectSummaries())
		{
			list.add(os.getKey());
		}
		
		return searchLogFiles(list, key);
	}

	@Override
	public List<String> searchLogFiles(List<String> files, String searchkey) {
		List<String> listfiles = new ArrayList<String>();
		for(String keyvalue : files)
		{
			S3Object s3object = amazonS3.getObject(bucketname, keyvalue );
			BufferedReader br = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));
            try {
				String readline = br.readLine();
				while (readline != null)
				{
					if(readline.contains(searchkey))
					{
						listfiles.add(searchkey);
						System.out.println(listfiles);
					}
					
					readline = br.readLine();
				}
			} 
            catch (IOException e) 
            {
				e.printStackTrace();
			}
		}
		return listfiles;
	}

}
