package com.adobe.aem.social.fiwt.core.configs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "Genreal API Configs", description = "This is Configuration for General APIs")
public @interface GeneralAPIConfig {

    @AttributeDefinition(
            name="api 1",
            description = "this field for api 1",
            type = AttributeType.STRING,
            defaultValue = "https://www.google.co.in"
    )
    public String api1();

    @AttributeDefinition(
            name = "Gender",
            description = "This filed for description",
            type = AttributeType.STRING,
            options = {
                    @Option(
                            label = "Male",
                            value = "male"
                    ),
                    @Option(
                            label="Female",
                            value = "female"
                    ),
                    @Option(
                            label = "Others",
                            value = "others"
                    )
            }
    )
    public String gender();
}
