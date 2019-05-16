package com.jazzchris.musicchallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ChallengeApplication {
		
	@Autowired
	private Environment env;
	
	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}
	
	@Bean
	@Lazy
	public DbxClientV2 dbxClientV2() {
		DbxRequestConfig requestConfig = new DbxRequestConfig(env.getProperty("dropbox.client_id"));
		DbxClientV2 dbxClientV2 = new DbxClientV2(requestConfig, env.getProperty("dropbox.access_token"));
		return dbxClientV2;
		
	}

}
