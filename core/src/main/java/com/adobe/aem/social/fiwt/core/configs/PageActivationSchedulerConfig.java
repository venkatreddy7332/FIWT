package com.adobe.aem.social.fiwt.core.configs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Page Activation Scheduler Configuration")
public @interface PageActivationSchedulerConfig {

    @AttributeDefinition
    public String schedulerName();

    @AttributeDefinition
    public boolean enable() default false;

    @AttributeDefinition
    public String cronExpression();

    @AttributeDefinition
    public String pagePath();
}

