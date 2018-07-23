package at.idris.client;

import com.vaadin.shared.communication.ClientRpc;

import java.nio.charset.Charset;

// ClientRpc is used to pass events from server to client
// For sending information about the changes to component state, use State instead
public interface TextFieldWithMaskClientRpc extends ClientRpc {

    // Example API: Fire up alert box in client
     void changeToSecret(String scret);
}