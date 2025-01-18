package com.adobe.aem.social.fiwt.core.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = ServiceResourceResolver.class)
public class ServiceResourceResolver {

    @Reference
    ResourceResolverFactory resolverFactory;

    public ResourceResolver getResourceResolver(){

        ResourceResolver resolver= null;
        Map <String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, "fiwt-subService");

        try {
            resolver=resolverFactory.getServiceResourceResolver(param);
        } catch (LoginException e) {
            e.printStackTrace();
        }
        return resolver;

    }
}
