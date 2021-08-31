import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SecretMessagesGUI extends JFrame {
	private JTextField txtKey;
    private JTextArea txtIn;
    private JTextArea txtOut;
    private JSlider slider;
    private JButton btnMoveUp;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane_1;
    
    public String encode(String message, int keyVal) {
    	 String output = "";
			char key = (char) keyVal;

			for (int x = 0; x < message.length(); x++) {
				char input = message.charAt(x);
				if (input >= 'A' && input <= 'Z')
				{
					input += key;
					if (input > 'Z')
						input -= 26;
					if (input < 'A')
						input += 26;
				}

				else 
					if (input >= 'a' && input <= 'z')
				{
					input += key;
					if (input > 'z')
						input -= 26;
					if (input < 'a')
						input += 26;
				}


				else 
					if (input >= '0' && input <= '9')
				{
					input += (keyVal % 10);
					if (input > '9')
						input -= 10;
					if (input < '0')
						input += 10;
				}
				
				output += input;
			}
         return output;
    } 
    
	public SecretMessagesGUI() {
		getContentPane().setBackground(new Color(0, 139, 139));
		setMaximumSize(new Dimension(600, 400));
		setMinimumSize(new Dimension(100, 200));
	
		setTitle("Victor Secret Messages App");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 564, 140);
		getContentPane().add(scrollPane);
		
		txtIn = new JTextArea();
		scrollPane.setViewportView(txtIn);
		txtIn.setWrapStyleWord(true);
		txtIn.setLineWrap(true);
		txtIn.setFont(new Font("Lucida Console", Font.PLAIN, 18));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 210, 564, 140);
		getContentPane().add(scrollPane_1);
		
		txtOut = new JTextArea();
		scrollPane_1.setViewportView(txtOut);
		txtOut.setWrapStyleWord(true);
		txtOut.setLineWrap(true);
		txtOut.setFont(new Font("Lucida Console", Font.PLAIN, 18));
		
		txtKey = new JTextField();
		txtKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
				int key1 = Integer.parseInt(txtKey.getText()); //Получить целочисленное значение из txtKey
				slider.setValue(key1);	// Установить ползунок в значение извлеченного ключа
				}
				catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                    "Please enter a whole number value for the encryption key.");
                    txtKey.requestFocus();
                    txtKey.selectAll();
				} 
				} } );
				
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setText("3");
		txtKey.setBounds(257, 162, 62, 23);
		getContentPane().add(txtKey);
		txtKey.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Key");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(225, 162, 34, 23);
		getContentPane().add(lblNewLabel);
		
		JButton btnEncodeDecode = new JButton("Encode / Decode");
		btnEncodeDecode.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEncodeDecode.setBackground(new Color(192, 192, 192));
		btnEncodeDecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				String message = txtIn.getText();
				int key = Integer.parseInt(txtKey.getText());
				String output = encode(message, key);
				txtOut.setText(output);
			}
				catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                    "Please enter a whole number value for the encryption key.");
                    txtKey.requestFocus();
                    txtKey.selectAll();
				}
				}
		});
		btnEncodeDecode.setBounds(329, 162, 136, 23);
		getContentPane().add(btnEncodeDecode);
		
		slider = new JSlider();
		slider.setBackground(new Color(0, 139, 139));
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				txtKey.setText("" + slider.getValue());
				String message = txtIn.getText();
				int key = slider.getValue();
				String output = encode(message, key);
				txtOut.setText(output);
			}
		});
		slider.setValue(3);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(13);
		slider.setMinorTickSpacing(1);
		slider.setMinimum(-26);
		slider.setMaximum(26);
		slider.setPaintLabels(true);
		slider.setBounds(23, 162, 200, 37);
		getContentPane().add(slider);
		
		btnMoveUp = new JButton("Move Up \u2227");
		btnMoveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = txtIn.getText();
				txtIn.setText(txtOut.getText());
				slider.setValue(-slider.getValue());
			}
		});
		btnMoveUp.setBounds(471, 162, 103, 23);
		getContentPane().add(btnMoveUp);
	}

	public static void main(String[] args) {
		SecretMessagesGUI theApp = new SecretMessagesGUI();
		theApp.setSize(new java.awt.Dimension(600,400));
		theApp.setVisible(true);
		

	}
}
