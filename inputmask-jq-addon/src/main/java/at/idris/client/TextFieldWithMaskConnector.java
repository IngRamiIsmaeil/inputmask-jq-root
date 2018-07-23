package at.idris.client;

import at.idris.TextFieldWithMask;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

// Connector binds client-side widget class to server-side component class
// Connector lives in the client and the @Connect annotation specifies the
// corresponding server-side component
@Connect(TextFieldWithMask.class)
public class TextFieldWithMaskConnector extends AbstractComponentConnector {

    // ServerRpc is used to send events to server. Communication implementation
    // is automatically created here
    TextFieldWithMaskServerRpc serverRpc = getRpcProxy(TextFieldWithMaskServerRpc.class);

    public TextFieldWithMaskConnector() {
        
        // To receive RPC events from server, we register ClientRpc implementation 
        registerRpc(TextFieldWithMaskClientRpc.class, (TextFieldWithMaskClientRpc) scret -> {
        });

        getWidget().addOnMaskCompleteHandler(() -> serverRpc.fireMaskCompleteEvent());

        getWidget().addOnMaskInCompleteHandler(() -> serverRpc.fireMaskIncompleteEvent());
    }

    // We must implement getWidget() to cast to correct type 
    // (this will automatically create the correct widget type)
    @Override
    public TextFieldWithMaskWidget getWidget() {
        return (TextFieldWithMaskWidget) super.getWidget();
    }

    // We must implement getState() to cast to correct type
    @Override
    public TextFieldWithMaskState getState() {
        return (TextFieldWithMaskState) super.getState();
    }

    // Whenever the state changes in the server-side, this method is called
    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        // State is directly readable in the client after it is set in server
        final String mask = getState().mask;
        getWidget().setMask(mask);

        final String placeholder = getState().placeholder;
        getWidget().setPlaceholder(placeholder);

        final int maxlength = getState().maxLength;
        getWidget().getElement().setPropertyInt("maxlength", maxlength);
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(TextFieldWithMaskWidget.class);
    }
}
