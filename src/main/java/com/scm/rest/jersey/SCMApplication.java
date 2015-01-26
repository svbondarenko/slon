package com.scm.rest.jersey;

import com.scm.rest.ScmPrototypeResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Created by Serhii_Bondarenko on 1/22/2015.
 */
public class SCMApplication  extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public SCMApplication(){
        register(RequestContextFilter.class);
        register(ScmPrototypeResource.class);
        register(JacksonFeature.class);
        register(MultiPartFeature.class);
    }


}