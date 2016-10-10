/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.lavanya.myapplication.backend;

import com.example.JokeClass;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
		name = "myApi",
		version = "v1",
		namespace = @ApiNamespace(
				ownerDomain = "backend.myapplication.lavanya.example.com",
				ownerName = "backend.myapplication.lavanya.example.com",
				packagePath = ""
		)
)
public class MyEndpoint {

	/**
	 * A simple endpoint method that takes a name and says Hi back
	 */
	@ApiMethod(name = "telljoke")
	public MyBean telljoke() {
		MyBean response = new MyBean();
		JokeClass jokeClass = new JokeClass();
		response.setData(jokeClass.getJokes());

		return response;
	}

}
