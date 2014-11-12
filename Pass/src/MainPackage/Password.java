package MainPackage;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;


public class Password extends JPanel implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String OK = "ok";
    private static String HELP = "help";

    private JFrame controlFrame; 
    private JPasswordField pwdField;

    public Password(JFrame f) {
        
        controlFrame = f;

        
        pwdField = new JPasswordField(10);
        pwdField.setActionCommand(OK);
        pwdField.addActionListener(this);

        JLabel label = new JLabel("Enter the password: ");
        label.setLabelFor(pwdField);

        JComponent buttonPane = createButtonPanel();

        
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPane.add(label);
        textPane.add(pwdField);

        add(textPane);
        add(buttonPane);
        JButton helpButton = new JButton("Help");
        add(helpButton);
        helpButton.setActionCommand(HELP);
        helpButton.addActionListener(this);
    }

    protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(0,1));
        JButton okButton = new JButton("OK");

        okButton.setActionCommand(OK);
        okButton.addActionListener(this);

        p.add(okButton);

        return p;
    }

    public void actionPerformed(ActionEvent e) {
        String comd = e.getActionCommand();

        if (OK.equals(comd)) {
            char[] input = pwdField.getPassword();
            if (isPasswordCorrect(input)) {
                JOptionPane.showMessageDialog(controlFrame,
                    "Congratulations! You typed the right password.");
            } else {
                JOptionPane.showMessageDialog(controlFrame,
                    "Incorrect password. Try again.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            }

            
            Arrays.fill(input, '0');

            pwdField.selectAll();
            resetFocus();
        } else { 
            JOptionPane.showMessageDialog(controlFrame,
                "You can get the password by searching this example's\n"
              + "source code for the string \"correctPassword\".\n"
              + "Or look at the section How to Use Password Fields in\n"
              + "the components section of The Java Tutorial.");
        }
    }


    private static boolean isPasswordCorrect(char[] input) {
        boolean isCorrect = true;
        char[] correctPassword = { 'b', 'u', 'g', 'a', 'b', 'o', 'o' };

        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals (input, correctPassword);
        }

        
        Arrays.fill(correctPassword,'0');

        return isCorrect;
    }

    
    protected void resetFocus() {
        pwdField.requestFocusInWindow();
    }

   
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("PasswordDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     
        final Password newContentPane = new Password(frame);
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);

       
        frame.addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                newContentPane.resetFocus();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
  
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
      
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		createAndShowGUI();
            }
        });
    }
}

