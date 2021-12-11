package api;

import model.ModelCommon;

public interface VolleryResponseListener {
    void onErro(String mesage);
    void onResponse(ModelCommon response);

}
