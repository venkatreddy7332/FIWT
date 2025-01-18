package com.adobe.aem.social.fiwt.core.servlets;


import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.EmptyDataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = Servlet.class, property={
        Constants.SERVICE_DESCRIPTION+"=Dropdown Values for dialog"
})
@SlingServletResourceTypes(resourceTypes = "/apps/blog/dialog/dropdown", methods = "GET")
public class SelectDropDownValuesServlet extends SlingSafeMethodsServlet {

    private static final Logger logger = LoggerFactory.getLogger(SelectDropDownValuesServlet.class);

    String[] colors={"Red","Black","White","Blue","Orange"};

    @Override
    protected void doGet( SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        try {
            Resource dialogResource=request.getResource();
            Resource dataSourceResource=dialogResource.getChild("datasource");
            List<Resource> options = new ArrayList<>();
            for (int i = 0; i < colors.length; i++) {
                ValueMap vm = new ValueMapDecorator(new HashMap<String,Object>());
                vm.put("value", colors[i].toLowerCase());
                vm.put("text", colors[i]);
                Resource optionResource = new ValueMapResource(request.getResourceResolver(), new ResourceMetadata(), "nt:unstructured", vm);
                options.add(optionResource);
            }

            // Create a DataSource for the dropdown
            DataSource dataSource = new SimpleDataSource(options.iterator());
            request.setAttribute(DataSource.class.getName(), dataSource);
        } catch (Exception e) {
            logger.error("Error generating dropdown options", e);
            request.setAttribute(DataSource.class.getName(), EmptyDataSource.instance());
        }
    }
}
