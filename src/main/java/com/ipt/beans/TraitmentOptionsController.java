package com.ipt.beans;

import com.ipt.beans.util.JsfUtil;
import com.ipt.beans.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("traitmentOptionsController")
@SessionScoped
public class TraitmentOptionsController implements Serializable {

    @EJB
    private com.ipt.beans.TraitmentOptionsFacade ejbFacade;
    private List<TraitmentOptions> items = null;
    private TraitmentOptions selected;
   

    public TraitmentOptionsController() {
        
    }

    public TraitmentOptions getSelected() {
        return selected;
    }

    public void setSelected(TraitmentOptions selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TraitmentOptionsFacade getFacade() {
        return ejbFacade;
    }

    public TraitmentOptions prepareCreate() {
        selected = new TraitmentOptions();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TraitmentOptionsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TraitmentOptionsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TraitmentOptionsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TraitmentOptions> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage(); 
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public TraitmentOptions getTraitmentOptions(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TraitmentOptions> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TraitmentOptions> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TraitmentOptions.class)
    public static class TraitmentOptionsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TraitmentOptionsController controller = (TraitmentOptionsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "traitmentOptionsController");
            return controller.getTraitmentOptions(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TraitmentOptions) {
                TraitmentOptions o = (TraitmentOptions) object;
               return getStringKey(o.getIdTraitementOptions());
                
               
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TraitmentOptions.class.getName()});
                return null;
            }
        }
        
        

    }
    
    
}
