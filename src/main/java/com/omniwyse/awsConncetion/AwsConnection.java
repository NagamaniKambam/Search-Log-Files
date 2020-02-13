package com.omniwyse.awsConncetion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsConnection {

	@Value("${jsa.aws.access_key_id}")
	private String accessid;

	@Value("${jsa.aws.secret_access_key}")
	private String accesskey;

	@Value("${jsa.s3.region}")
	private String region;

	@Bean
	
	public AmazonS3 s3client() {
		AWSCredentials credentials = new BasicAWSCredentials(accessid, accesskey);

		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.fromName(region))
				.build();

		return s3client;

	}

}
