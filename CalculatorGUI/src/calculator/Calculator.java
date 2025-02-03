package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.lang.Math;

public class Calculator {
    protected JTextArea calcDisplay;
    private JButton signBtn;
    private JButton clearBtn;
    protected JButton eightBtn;
    protected JButton fiveBtn;
    private JButton mAddBtn;
    protected JButton sevenBtn;
    protected JButton fourBtn;
    protected JButton twoBtn;
    protected JButton oneBtn;
    private JButton digitBtn;
    private JButton powerBtn;
    private JButton mSubBtn;
    protected JButton nineBtn;
    protected JButton sixBtn;
    protected JButton threeBtn;
    protected JButton zeroBtn;
    private JButton mRecallBtn;
    private JButton mClearBtn;
    private JButton squareBtn;
    private JButton sqrBtn;
    private JButton logBtn;
    private JButton percentageBtn;
    private JButton divideBtn;
    private JButton mulBtn;
    private JButton minusBtn;
    private JButton equalBtn;
    private JButton addBtn;
    private JPanel CalculatorField;
    private JPanel CalcSubfield;

    /* creating 2 arrays to e able to traverse functional buttons and number buttons */
    JButton[] functionBtns = new JButton[17];
    JButton[] numberBtns = new JButton[10];

    /* initializing different fonts for each of the buttons */
    Font myFont = new Font("Times New Roman",Font.ITALIC,12);
    Font myFont2 = new Font("Times New Roman",Font.BOLD,12);

    /* instance variables needed to perform operations */
    double num1 = 0.0, num2 = 0.0, result = 0.0;
    char operator;

    /* used to limit decimal placement */
    int digit=0;

    double memory = 0.0;


    /**
     * This is my Calculator constructor
     * This constructor utilizes 9 subclasses for each of the number buttons
     * These subclasses all extend the CalcNumberButtonPressed subclass
     */
    public Calculator() {

        calcDisplay.setEditable(false);

        /* initializing functionBtn array to each function button */
        functionBtns[0] = addBtn;
        functionBtns[1] = minusBtn;
        functionBtns[2] = mulBtn;
        functionBtns[3] = divideBtn;
        functionBtns[4] = equalBtn;
        functionBtns[5] = digitBtn;
        functionBtns[6] = signBtn;
        functionBtns[7] = clearBtn;
        functionBtns[8] = logBtn;
        functionBtns[9] = powerBtn;
        functionBtns[10] = sqrBtn;
        functionBtns[11] = squareBtn;
        functionBtns[12] = mSubBtn;
        functionBtns[13] = mAddBtn;
        functionBtns[14] = mClearBtn;
        functionBtns[15] = mRecallBtn;
        functionBtns[16] = percentageBtn;

        numberBtns[0] = zeroBtn;
        numberBtns[1] = oneBtn;
        numberBtns[2] = twoBtn;
        numberBtns[3] = threeBtn;
        numberBtns[4] = fourBtn;
        numberBtns[5] = fiveBtn;
        numberBtns[6] = sixBtn;
        numberBtns[7] = sevenBtn;
        numberBtns[8] = eightBtn;
        numberBtns[9] = nineBtn;

        /* setting fonts for function buttons */
        for (int i=0; i<17; i++)
        {
            functionBtns[i].setFont(myFont);
            functionBtns[i].setFocusable(false);
        }

        /* setting fonts and creating action listener for number buttons */
        for (int i=0; i<10; i++)
        {
            numberBtns[i].setFont(myFont2);
            numberBtns[i].setFocusable(false);
        }

        zeroBtn.addActionListener(new ZeroButtonPressed(calcDisplay,zeroBtn));
        oneBtn.addActionListener(new OneButtonPressed(calcDisplay,oneBtn));
        twoBtn.addActionListener(new TwoButtonPressed(calcDisplay,twoBtn));
        threeBtn.addActionListener(new ThreeButtonPressed(calcDisplay,threeBtn));
        fourBtn.addActionListener(new FourButtonPressed(calcDisplay,fourBtn));
        fiveBtn.addActionListener(new FiveButtonPressed(calcDisplay,fiveBtn));
        sixBtn.addActionListener(new SixButtonPressed(calcDisplay,sixBtn));
        sevenBtn.addActionListener(new SevenButtonPressed(calcDisplay,sevenBtn));
        eightBtn.addActionListener(new EightButtonPressed(calcDisplay,eightBtn));
        nineBtn.addActionListener(new NineButtonPressed(calcDisplay,nineBtn));


        /* action listeners for non-number buttons */
        signBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = Double.parseDouble(calcDisplay.getText());
                temp *= -1;
                if (temp % (int) temp != 0)
                    calcDisplay.setText(String.valueOf(temp));
                else {
                    calcDisplay.setText(String.valueOf((int) temp));
                }
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcDisplay.setText("0");
                num1 = 0;
                num2 = 0;
                result = 0;
                digit=0;
            }
        });

        digitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (digit<1) {
                    calcDisplay.setText(calcDisplay.getText().concat("."));
                    digit++;
                }
            }
        });

        powerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(calcDisplay.getText());
                operator = '^';
                calcDisplay.setText("");
                digit=0;
            }
        });

        mAddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(calcDisplay.getText());
                memory = memory + num1;
                digit=0;
            }
        });

        mSubBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(calcDisplay.getText());
                memory = memory - num1;
                digit=0;
            }
        });

        mRecallBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(calcDisplay.getText());
                result = memory;
                if (result % (int) result != 0) {
                    calcDisplay.setText(String.valueOf(result));
                }
                else {
                    calcDisplay.setText(String.valueOf((int) result));
                }
                digit=0;
            }
        });

        mClearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(calcDisplay.getText());
                memory = 0;
                digit=0;
            }
        });

        squareBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = Double.parseDouble(calcDisplay.getText());
                temp *= temp;

                if (temp % (int)temp != 0) {
                    calcDisplay.setText(String.valueOf(temp));
                }
                else {
                    calcDisplay.setText(String.valueOf((int) temp));
                }

                digit=0;
            }
        });

        sqrBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = Double.parseDouble(calcDisplay.getText());
                if (temp <0) {
                    calcDisplay.setText("ERROR");
                }
                else {
                    temp = Math.sqrt(temp);

                    if (temp % (int) temp != 0) {
                        calcDisplay.setText(String.valueOf(temp));
                    }
                    else {
                        calcDisplay.setText(String.valueOf((int) temp));
                    }
                }

                digit=0;
            }
        });

        logBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double temp = Double.parseDouble(calcDisplay.getText());

                if (temp <0) {
                    calcDisplay.setText("ERROR");
                }
                else {
                    temp = Math.log(temp);

                    if (temp % (int) temp != 0) {
                        calcDisplay.setText(String.valueOf(temp));
                    }
                    else {
                        calcDisplay.setText(String.valueOf((int) temp));
                    }
                }

                digit=0;
            }
        });

        percentageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(calcDisplay.getText());
                result = num1/100;
                calcDisplay.setText(String.valueOf(result));

                digit=0;
            }
        });

        divideBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(calcDisplay.getText());
                operator = '/';
                calcDisplay.setText("");

                digit=0;
            }
        });

        mulBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(calcDisplay.getText());
                operator = '*';
                calcDisplay.setText("");

                digit=0;
            }
        });

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(calcDisplay.getText());
                operator = '+';
                calcDisplay.setText("");

                digit=0;
            }
        });

        minusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(calcDisplay.getText());
                operator = '-';
                calcDisplay.setText("");

                digit=0;
            }
        });

/* action listener for equal button (involves all 2 number operations) */
        equalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num2 = Double.parseDouble(calcDisplay.getText());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                    case '^':
                        result = Math.pow(num1, num2);
                        break;
                }

                if (num2 == 0) {
                    calcDisplay.setText("ERROR");
                }
                else if (result % (int)result != 0)
                    calcDisplay.setText(String.valueOf(result));
                else {
                    calcDisplay.setText(String.valueOf((int) result));
                }

                num1=result;
            }
        });
    }

    /**
     * This is my main function that creates a gui and displays the frame
     */
    public static void main(String[] args) {

        JFrame frame = new JFrame("Calculator GUI");
        Calculator calculator = new Calculator();
        frame.setContentPane(calculator.CalculatorField);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,300);
        frame.setVisible(true);

    }


    public void test( String button){
        switch (button){
            case "0": zeroBtn.doClick();break;
            case "1": oneBtn.doClick();break;
            case "2": twoBtn.doClick();break;
            case "3": threeBtn.doClick();break;
            case "4": fourBtn.doClick();break;
            case "5": fiveBtn.doClick();break;
            case "6": sixBtn.doClick();break;
            case "7": sevenBtn.doClick();break;
            case "8": eightBtn.doClick();break;
            case "9": nineBtn.doClick();break;
            case "%": percentageBtn.doClick();break;
            case "-/+": signBtn.doClick();break;
            case "AC": clearBtn.doClick();break;
            case "*2": squareBtn.doClick();break;
            case "sqr": sqrBtn.doClick();break;
            case "log": logBtn.doClick();break;
            case ".": digitBtn.doClick();break;
            case "+": addBtn.doClick();break;
            case "-": minusBtn.doClick();break;
            case "*": mulBtn.doClick();break;
            case "/": divideBtn.doClick();break;
            case "**": powerBtn.doClick();break;
            case "M+": mAddBtn.doClick();break;
            case "M-": mSubBtn.doClick();break;
            case "MR": mRecallBtn.doClick();break;
            case "MC": mClearBtn.doClick();break;
            case "=": equalBtn.doClick();break;
            case "txt": System.out.println("The result is: " +
                    calcDisplay.getText());break;
            default:System.out.println("invalid input");break;
        }
    }
}