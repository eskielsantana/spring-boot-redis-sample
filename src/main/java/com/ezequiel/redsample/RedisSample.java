package com.ezequiel.redsample;

import com.ezequiel.redsample.entity.Person;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(scanBasePackages = { "com.ezequiel.redsample" })
public class RedisSample extends WebMvcConfigurerAdapter {

	final static org.slf4j.Logger logger = LoggerFactory.getLogger(RedisSample.class);

	@Bean
	JedisConnectionFactory jedisConnectionFactory(){
		return new JedisConnectionFactory();
	}

	@Bean
	@Autowired
	public RedisTemplate<String, Person> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

		if (null == redisConnectionFactory) {
			return null;
		}
		RedisTemplate<String, Person> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

	public static void main(String[] args) {
		logger.info("Application Started");
		SpringApplication.run(RedisSample.class, args);
	}

	// This function returns the data of the application in the swagger page
	@Bean
	public Docket docket()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(generateApiInfo());
	}

	// Defines which data will be displayed by swagger
	private ApiInfo generateApiInfo()
	{
		return new ApiInfo("Ezequiel's Redis Sample", "This is just a simple sample I am doing to study Redis behavior.", "Version 1.0 - Starter",
				"urn:tos", "eskiel.sj@gmail.com", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	}
}