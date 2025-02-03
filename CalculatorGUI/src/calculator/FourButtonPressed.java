package calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FourButtonPressed extends CalcNumberButtonPressed {
    FourButtonPressed(JTextArea calcDisplay, JButton button) {
        super(calcDisplay, button);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);
    }
}
