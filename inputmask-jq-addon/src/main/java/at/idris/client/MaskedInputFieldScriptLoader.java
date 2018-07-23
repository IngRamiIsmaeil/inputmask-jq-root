package at.idris.client;

import com.google.gwt.core.client.GWT;

public class MaskedInputFieldScriptLoader {

    public static final MaskedInputFieldScriptLoader INSTANCE = GWT.create(MaskedInputFieldScriptLoader.class);
    private static boolean injected = false;

    public static void checkIfResourcesInjected(){
        if(!injected){
            INSTANCE.injectResources();
            injected = true;
        }
    }

    protected void injectResources() {
        if(!isJQueryLoaded()){
            inject(MaskedResources.INSTANCE.loadJQ().getText());
        }
        inject(MaskedResources.INSTANCE.loadImputesMask().getText());
        inject(MaskedResources.INSTANCE.loadImputesMaskJQ().getText());
        inject(MaskedResources.INSTANCE.loadJQInputBind().getText());
        inject(MaskedResources.INSTANCE.loadJQExt().getText());
        inject(MaskedResources.INSTANCE.loadJQDExt().getText());
        inject(MaskedResources.INSTANCE.loadJQNExt().getText());
        inject(MaskedResources.INSTANCE.loadJQPExt().getText());
    }

    protected static native boolean isJQueryLoaded()/*-{
        if($wnd.jQuery){
            return true;
        }
        return false;
    }-*/;

    protected static native void inject(String script)/*-{
        $wnd.eval(script);
    }-*/;
}
