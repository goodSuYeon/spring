package kr.or.ddit.ioc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"kr.or.ddit"})
public class ComponentScanJavaConfig {

	/*
	   <context:component-scan base-package="kr.or.ddit">
	   </context:component-scan>
	*/
	

}
