package com.adobe.aem.social.fiwt.core.listner;

import com.day.cq.mailer.MessageGatewayService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
        service = EventHandler.class,
        immediate = true,
        property = {
                EventConstants.EVENT_TOPIC+"=org/apache/sling/api/resource/Resource/*",
                EventConstants.EVENT_FILTER+"=(path=/content/*)"
        }
)
public class PageModifiedEventHandler implements EventHandler {

    private static final Logger log= LoggerFactory.getLogger(PageModifiedEventHandler.class);


    @Override
    public void handleEvent(Event event) {
       String path= event.getProperty("path").toString();
       String resourceType= event.getProperty("resourceType").toString();
       log.info("Path="+path+"{---} resourceType="+resourceType);
    }
}
