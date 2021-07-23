package com.biscuits.core.framework.ioc;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PackageScannerRegister.class, FeignClientsRegister.class})
public class AutoRegisterConfiguration {
}
