package model;

import com.google.gson.annotations.SerializedName;

public class Today {
    @SerializedName("internal")
    private Info infoInternal;

    public Info getInfoInternal() {
        return infoInternal;
    }

    public void setInfoInternal(Info infoInternal) {
        this.infoInternal = infoInternal;
    }
    @SerializedName("world")
    private Info infoWorld;
    public Info getInfoWorld(){
        return infoWorld;
    }
    public void setInfoWorld(Info infoWorld){
        this.infoWorld = infoWorld;
    }
}
