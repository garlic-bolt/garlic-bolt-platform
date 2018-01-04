package com.chanjetpay.garlic.config;

import com.chanjetpay.garlic.api.BlockService;
import com.chanjetpay.garlic.api.MerchantService;
import feign.*;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by libaoa on 2017/11/13.
 */
@Configuration
@PropertySource({"classpath:feign.properties"})
public class FeignConfig {

	private static final Logger log = LoggerFactory.getLogger(FeignConfig.class);

	@Value("${feign.url}")
	private String feignUrl;

	@Value("${feign.token}")
	private String feignToken;

	private RequestInterceptor requestInterceptor = new RequestInterceptor() {
		@Override
		public void apply(RequestTemplate requestTemplate) {
			requestTemplate.header("access-token",feignToken);
		}
	};

	@Bean(name="blockService")
	public BlockService blockService(){
		log.info("blockService started feignUrl: " + feignUrl);
		return Feign.builder()
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.options(new Request.Options(1000, 3500))
				.retryer(new Retryer.Default(5000, 5000, 3))
				.target(BlockService.class, feignUrl);
	}

	@Bean(name="merchantService")
	public MerchantService merchantService(){
		log.info("merchantService started feignUrl: " + feignUrl);
		return Feign.builder()
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.options(new Request.Options(1000, 3500))
				.retryer(new Retryer.Default(5000, 5000, 3))
				.requestInterceptor(requestInterceptor)
				.target(MerchantService.class, feignUrl);
	}
}
