package networking;

public class ServerToClientMsg {
    public final String controllerState;

    ServerToClientMsg(String controllerState){
        this.controllerState = controllerState;
    }
}
