package at.idris;

import at.idris.client.TextFieldWithMaskServerRpc;
import at.idris.client.TextFieldWithMaskState;
import com.vaadin.ui.AbstractTextField;
import at.idris.client.TextFieldWithMaskClientRpc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// This is the server-side UI component that provides public API 
// for TextFieldWithMask
public class TextFieldWithMask extends AbstractTextField {

    private final TextFieldWithMaskClientRpc clientRpc;

    public interface MaskCompeleteListener {
        void onMaskCompelete();
    }

    public interface MaskIncompeleteListener {
        void onMaskInCompelete();
    }

    private final List<MaskCompeleteListener> maskCompeleteListeners;

    private final List<MaskIncompeleteListener> maskInCompeleteListeners;

    public TextFieldWithMask() {

        maskCompeleteListeners = new ArrayList();

        maskInCompeleteListeners = new ArrayList();

        clientRpc = getRpcProxy(TextFieldWithMaskClientRpc.class);

        // To receive events from the client, we register ServerRpc
        registerRpc(new TextFieldWithMaskServerRpc() {

            @Override
            public void fireMaskCompleteEvent() {
                fireMaskCompeleteEvent();
            }

            @Override
            public void fireMaskIncompleteEvent() {
                fireMaskInCompeleteEvent();
            }

            @Override
            public void updateValue(String newValue) {
                setValue(newValue);
            }
        }, TextFieldWithMaskServerRpc.class);

        setId(UUID.randomUUID().toString());
    }

    // We must override getState() to cast the state to TextFieldWithMaskState
    @Override
    protected TextFieldWithMaskState getState() {
        return (TextFieldWithMaskState) super.getState();
    }

    public void setMask(String mask){
        getState().mask = mask;
    }

    public void setPlaceholder(String placeholder){
        getState().placeholder = placeholder;
    }

    public void sayUserASecret(String secret){
        clientRpc.changeToSecret(secret);
    }

    private void fireMaskInCompeleteEvent(){
        for(MaskIncompeleteListener l : maskInCompeleteListeners)
            l.onMaskInCompelete();
    }

    private void fireMaskCompeleteEvent(){
        for(MaskCompeleteListener l : maskCompeleteListeners)
            l.onMaskCompelete();
    }

    public void addMaskCompleteListener(MaskCompeleteListener listener){
        maskCompeleteListeners.add(listener);
    }

    public void addMaskIncompleteListener(MaskIncompeleteListener listener){
        maskInCompeleteListeners.add(listener);
    }
}
