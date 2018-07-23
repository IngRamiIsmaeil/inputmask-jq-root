package at.idris.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface MaskedResources extends ClientBundle{

    MaskedResources INSTANCE = GWT.create(MaskedResources.class);

    @Source("jquery-3.2.1.min.js")
    TextResource loadJQ();

    @Source("inputmask.js")
    TextResource loadImputesMask();

    @Source("jquery.inputmask.js")
    TextResource loadImputesMaskJQ();

    @Source("inputmask.binding.js")
    TextResource loadJQInputBind();

    @Source("inputmask.extensions.js")
    TextResource loadJQExt();

    @Source("inputmask.date.extensions.js")
    TextResource loadJQDExt();

    @Source("inputmask.numeric.extensions.js")
    TextResource loadJQNExt();

    @Source("inputmask.phone.extensions.js")
    TextResource loadJQPExt();

}
