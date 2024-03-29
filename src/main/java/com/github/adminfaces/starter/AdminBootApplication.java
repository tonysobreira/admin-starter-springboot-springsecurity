/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.adminfaces.starter;

import com.github.adminfaces.starter.facade.Facade;
import com.github.adminfaces.starter.model.Authority;
import com.github.adminfaces.starter.model.Car;
import com.github.adminfaces.starter.util.Utils;
import java.util.List;
import javax.inject.Inject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author rmpestano
 */
@SpringBootApplication
public class AdminBootApplication {
	@Inject
	private Utils utils;

    @Bean
    public List<Car> getCars() {
        return utils.getCars();
    }
    
    @Inject
    private Facade facade;
    
    @Bean
    public List<Authority> initAuthorities() {
    	List<Authority> authList = facade.findAllAuthority();
		Authority auth;

		if (authList.isEmpty()) {
			auth = new Authority();
			auth.setAuthority("ROLE_ADMIN");

			facade.save(auth);

			auth = new Authority();
			auth.setAuthority("ROLE_USER");

			facade.save(auth);
		}
		
		return authList;
    }
	
}
