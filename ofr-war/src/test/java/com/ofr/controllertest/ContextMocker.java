package com.ofr.controllertest;

import org.easymock.EasyMock;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 3/6/13
 * Time: 1:58 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ContextMocker extends FacesContext {

    protected ContextMocker() {
    }

    public static FacesContext mockFacesContext() {
        FacesContext context = EasyMock.createMock(FacesContext.class);
        setCurrentInstance(context);
        return context;
    }

    @Override
    public ExternalContext getExternalContext() {
        ExternalContext context = EasyMock.createMock(ExternalContext.class);
        return context;
    }
}
