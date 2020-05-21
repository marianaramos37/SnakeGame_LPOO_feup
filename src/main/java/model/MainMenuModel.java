package model;

public class MainMenuModel {
    private String title;
    private String singleTitle;
    private String multyTitle;
    private String contrucaoTitle;
    private String esqTitle;

    private char selection;

    public MainMenuModel(){
        title = "SNAKE";
        singleTitle = "PLAY SINGLEPLAYER";
        multyTitle = "PLAY MULTIPLAYER";
        contrucaoTitle= "BUILD MAPS";
        esqTitle="Press ESC to leave";
        selection = 'o';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSingleTitle() {
        return singleTitle;
    }

    public void setSingleTitle(String singleTitle) {
        this.singleTitle = singleTitle;
    }

    public String getMultyTitle() {
        return multyTitle;
    }

    public void setMultyTitle(String multyTitle) {
        this.multyTitle = multyTitle;
    }

    public String getContrucaoTitle() {
        return contrucaoTitle;
    }

    public void setContrucaoTitle(String contrucaoTitle) {
        this.contrucaoTitle = contrucaoTitle;
    }

    public char getSelection() {
        return selection;
    }

    public void setSelection(char selection) {
        this.selection = selection;
    }

    public String getEsqTitle() {
        return esqTitle;
    }

    public void setEsqTitle(String esqTitle) {
        this.esqTitle = esqTitle;
    }
}
