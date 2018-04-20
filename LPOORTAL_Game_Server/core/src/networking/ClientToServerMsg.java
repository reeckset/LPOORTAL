package networking;

public class ClientToServerMsg {
    public final float dx;
    public final float dy;
    public final boolean actionBtn;
    public final String controllerState;
    public final String playerColor;
    public final String playerSkin;

    public ClientToServerMsg(float dx, float dy, boolean actionBtn, String controllerState, String playerColor, String playerSkin) {
        this.dx = dx;
        this.dy = dy;
        this.actionBtn = actionBtn;
        this.controllerState = controllerState;
        this.playerColor = playerColor;
        this.playerSkin = playerSkin;
    }

    public ClientToServerMsg(String controllerState, float dx, float dy, boolean actionBtn){
        this(dx, dy, false, controllerState, "", "");
    }

    public ClientToServerMsg(String controllerState, String playerColor, String playerSkin){
        this(0,0,false,controllerState, playerColor, playerSkin);
    }
}
