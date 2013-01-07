package com.btmatthews.testapp;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendMailServlet extends HttpServlet {

    @Override
    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            final Context ctx = new InitialContext();
            final Session session = (Session)ctx.lookup("java:comp/env/mail/Session");
            final MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("admin@btmatthews.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("brian@btmatthews.com"));
            message.setSubject("Testing");
            message.setText("One Two Three ...");
            Transport.send(message);
        } catch (final NamingException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        } catch (final AddressException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        } catch (final MessagingException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sent.jsp");
        dispatcher.forward(request, response);
    }
}