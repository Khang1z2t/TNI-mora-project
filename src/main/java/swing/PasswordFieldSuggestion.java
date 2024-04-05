package swing;

import javax.swing.JPasswordField;

public class PasswordFieldSuggestion extends JPasswordField {

    private PasswordFieldSuggestionUI passUI;

    public PasswordFieldSuggestion() {
        passUI = new PasswordFieldSuggestionUI(this);
        setUI(passUI);
    }

    public void addItemSuggestion(String text) {
        passUI.getItems().add(text);
    }

    public void removeItemSuggestion(String text) {
        passUI.getItems().remove(text);
    }

    public void clearItemSuggestion() {
        passUI.getItems().clear();
    }

    public void setRound(int round) {
        passUI.setRound(round);
    }

    public int getRound() {
        return passUI.getRound();
    }

}
