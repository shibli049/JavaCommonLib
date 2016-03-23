/*
 * Copyright (C) 2016 shibli
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.shibli049.soap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author shibli
 */
public class SoapLoggingHandler implements SOAPHandler<SOAPMessageContext> {

    public boolean handleMessage(SOAPMessageContext c) {
        SOAPMessage msg = c.getMessage();

        boolean request = ((Boolean) c.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY)).booleanValue();

        try {
            if (request) { // This is a request message.
                // Write the message to the output stream 
                msg.writeTo(System.out);
            } else { // This is the response message 
                msg.writeTo(System.out);
            }
        } catch (Exception ex) {
        }
        return true;
    }

    public boolean handleFault(SOAPMessageContext c) {
        SOAPMessage msg = c.getMessage();
        try {
            msg.writeTo(System.out);
        } catch (Exception e) {
        }
        return true;
    }

    public void close(MessageContext c) {
    }

    public Set getHeaders() {
        return null;
    }

    /**
     * Pass your web service proxy port here.
     * This method will add SoapLoggingHandler class 
     * as a handler chain. 
     * @param <WebServiceProxyPort>
     * @param wspp
     * 
     */
    public static <WebServiceProxyPort> void setHandler(WebServiceProxyPort wspp) {
        BindingProvider bp = (BindingProvider) wspp;
        Binding binding = bp.getBinding();
        List handlerList = binding.getHandlerChain();
        if (handlerList == null) {
            handlerList = new ArrayList();
        }
        SoapLoggingHandler loggingHandler = new SoapLoggingHandler();
        handlerList.add(loggingHandler);
        binding.setHandlerChain(handlerList);
    }
}

