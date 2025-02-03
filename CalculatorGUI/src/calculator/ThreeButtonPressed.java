package calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ThreeButtonPressed extends CalcNumberButtonPressed {
    ThreeButtonPressed(JTextArea calcDisplay, JButton button) {
        super(calcDisplay, button);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);
    }
}
