package com.adobe.aem.social.fiwt.core.servlets;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;


@Component(service = Servlet.class,
        property={"sling.servlet.paths=/bin/mail",
                "sling.servlet.extensions=html"})
public class MakaanMailService extends SlingAllMethodsServlet {


    @Reference
    MessageGatewayService messageGatewayService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        try {
            Email email = new SimpleEmail();
            email.addTo("venkatreddy7332@gmail.com");
            email.setSubject("Test Mail");
            email.setMsg("This msg coming from aem servlet");
            email.setSmtpPort(465);
            email.setSSLOnConnect(true);
            MessageGateway<Email> messageGateway = messageGatewayService.getGateway(HtmlEmail.class);
            messageGateway.send((Email) email);
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }

    }


}

