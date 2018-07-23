package at.idris.client;

import com.google.gwt.user.client.ui.TextBox;
import com.vaadin.client.ui.Field;

import java.util.ArrayList;
import java.util.List;

// Extend any GWT Widget

public class TextFieldWithMaskWidget extends TextBox implements Field{

    protected interface MaskEventListener {
        void handleMaskEvent();
    }

    public static final String CLASSNAME = "ad-masked-input-field";

    private String mask;

    private String placeholder;

    private List<MaskEventListener> maskCompleteHandlers;

    private List<MaskEventListener> maskInCompeleteHandlers;

    public TextFieldWithMaskWidget() {

        // CSS class-name should not be v- prefixed
        setStyleName(CLASSNAME);

        // State is set to widget in TextFieldWithMaskConnector

        maskCompleteHandlers = new ArrayList();

        maskInCompeleteHandlers = new ArrayList();

        MaskedInputFieldScriptLoader.INSTANCE.injectResources();

        /*getElement().setAttribute("maxlength", "100");*/
    }

    public void setMask(String mask) {
        if(null != mask && !mask.isEmpty() && !mask.equals(this.mask)){
            this.mask = mask;
            setFieldProperties(CLASSNAME,this.mask, this.placeholder);
        }
       
    }

    public void setPlaceholder(String placeholder) {
        if(null != placeholder && !placeholder.isEmpty() && !placeholder.equals(this.placeholder)){
            this.placeholder = placeholder;
            setFieldProperties(CLASSNAME, this.mask, this.placeholder);
        }
    }
    
    private native void setFieldProperties(String clzName, String mask, String placeholder)/*-{
        var thisWidget = this;
        var props = {
            oncomplete : function () {
                thisWidget.@at.idris.client.TextFieldWithMaskWidget::onMaskComplete()()
            },
            onincomplete: function () {
                thisWidget.@at.idris.client.TextFieldWithMaskWidget::onMaskIncomplete()()
            }
        };
        if(placeholder !== ""){
            props.placeholder = placeholder;
        }
        $wnd.console.log('Calling JQ to setting mask to ' + mask);
        $wnd.jQuery('.'+clzName).inputmask(mask,props);
    }-*/;

    public void addOnMaskCompleteHandler(MaskEventListener handler){
        maskCompleteHandlers.add(handler);
    }

    public void addOnMaskInCompleteHandler(MaskEventListener handler){
        maskInCompeleteHandlers.add(handler);
    }

    private void onMaskComplete(){
        for(MaskEventListener h : maskCompleteHandlers){
            h.handleMaskEvent();
        }
    }

    private void onMaskIncomplete() {
        for(MaskEventListener h : maskInCompeleteHandlers)
            h.handleMaskEvent();
    }
}