package calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SevenButtonPressed extends CalcNumberButtonPressed {
    SevenButtonPressed(JTextArea calcDisplay, JButton button) {
        super(calcDisplay, button);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);
    }
}
