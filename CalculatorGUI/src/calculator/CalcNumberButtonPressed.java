package calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcNumberButtonPressed implements ActionListener {

    JTextArea calcDisplay;
    JButton button;

    CalcNumberButtonPressed(JTextArea calcDisplay, JButton button)
    {
        this.calcDisplay = calcDisplay;
        this.button = button;
    }

    /* Action listeners for number buttons */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (calcDisplay.getText().equals("0") || calcDisplay.getText().equals("ERROR") || calcDisplay.getText().equals("Infinity")) {
            calcDisplay.setText("");
        }

            if (e.getSource() == button) {
                calcDisplay.setText(calcDisplay.getText().concat(String.valueOf(button.getText())));
        }
    }

}
