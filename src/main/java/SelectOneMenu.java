
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.springframework.web.context.request.RequestScope;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sabrine
 */
@ManagedBean(name = "SelectOneMenu")
@SessionScoped
public class SelectOneMenu {
    
        
    private List<SelectItem> englishItems;
    private HtmlSelectOneMenu menu = new HtmlSelectOneMenu();
 
    {
        englishItems = new ArrayList<SelectItem>();
        englishItems.add(new SelectItem("One"));
        englishItems.add(new SelectItem("Two"));
        englishItems.add(new SelectItem("Three"));
       // menu.getChildren().add((UIComponent) englishItems);
    }
 
    /** Creates a new instance of TestBean. */
    public SelectOneMenu() {
         
    }
 
    public HtmlSelectOneMenu getMenu() {
        return menu;
    }
 
    public void setMenu(HtmlSelectOneMenu menu) {
        this.menu = menu;
    }
 
    public void ActionListener(ActionEvent evt) {
        List<UIComponent> menuChildren = menu.getChildren();
 
        UISelectItems items = new UISelectItems();
        items.setValueExpression("value", FacesContext.getCurrentInstance().getApplication().getExpressionFactory().createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{testBean.englishItems}", List.class));
        menuChildren.add(items);
    }
 
    public List<SelectItem> getEnglishItems() {
        return englishItems;
    }
 
    public void setEnglishItems(List<SelectItem> englishItems) {
        this.englishItems = englishItems;
    }}
