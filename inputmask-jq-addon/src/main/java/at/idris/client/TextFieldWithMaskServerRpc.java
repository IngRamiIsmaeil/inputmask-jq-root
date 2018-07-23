package at.idris.client;


import com.vaadin.shared.communication.ServerRpc;

// ServerRpc is used to pass events from client to server
public interface TextFieldWithMaskServerRpc extends ServerRpc {
    void fireMaskCompleteEvent();

    void fireMaskIncompleteEvent();

    void updateValue(String newValue);
}
