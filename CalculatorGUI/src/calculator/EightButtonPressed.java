package calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EightButtonPressed extends CalcNumberButtonPressed {
    EightButtonPressed(JTextArea calcDisplay, JButton button) {
        super(calcDisplay, button);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);
    }
}
