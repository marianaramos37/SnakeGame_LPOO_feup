package model;

public class MainMenuModel {
    private String title;
    private String singleTitle;
    private String multyTitle;
    private String esqTitle;

    private char selection;

    public MainMenuModel(){
        title = "SNAKE";
        singleTitle = "PLAY SINGLEPLAYER";
        multyTitle = "PLAY MULTIPLAYER";
        esqTitle="Press ESC to leave";
        selection = 'o';
    }

    public String getTitle() {
        return title;
    }



    public String getSingleTitle() {
        return singleTitle;
    }


    public String getMultyTitle() {
        return multyTitle;
    }



    public String getEsqTitle() {
        return esqTitle;
    }


}
