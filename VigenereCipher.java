

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;
public class VigenereCipher extends JFrame implements  ActionListener{
	private JTextArea tfEncriptar, tfDesencriptar;
	private JTextField tfNumber;
    private JButton bEncriptar, bDesencriptar;
    private JPanel panel1, panel2, panel3;

	public VigenereCipher(){
		 super("Vigenere Cipher");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 1. Crear objetos
        tfEncriptar = new JTextArea();
        bEncriptar  = new JButton("Cipher");
        bDesencriptar = new JButton("Decipher");

        tfDesencriptar   = new JTextArea();

        tfNumber = new JTextField();
        // Adicionar deteccion de eventos a los botones
        bEncriptar.addActionListener(this);
        bDesencriptar.addActionListener(this);


        //taDatos    = new JTextArea(10,30);
        panel1     = new JPanel();
        panel2     = new JPanel();
        panel3		= new JPanel();
        // 2. Adicionar los objetos al panel1
        panel1.setLayout(new GridLayout(3,2,20,20));
        panel2.setLayout(new FlowLayout());
        panel3.setLayout(new GridLayout(2,1));

        panel1.add(new JLabel("Secret Code Word:"));
        panel1.add(tfNumber);

        panel1.add(new JLabel("Message to cipher/decipher: "));
        panel1.add(tfEncriptar);

        //panel1.add(new JLabel("      "));
        panel1.add(new JLabel("Result: "));
        //panel1.add(new JLabel("      "));
        panel1.add(tfDesencriptar);

        panel2.add(bEncriptar);
        panel2.add(bDesencriptar);

        panel3.add(panel1);
        //panel2.add(new JScrollPane(taDatos));

        // 3. Adicionar panel2 al JFrame
        panel3.add(panel2);
        add(panel3);
        setSize(250,250);
        setVisible(true);



	}
	public void actionPerformed(ActionEvent e)
    {

        if(e.getSource()== bEncriptar)
        {
          String result = vCipher(getCode(),tfEncriptar.getText());
          tfEncriptar.setText(tfEncriptar.getText().toUpperCase());
          tfDesencriptar.setText(result);
        }

        if(e.getSource()== bDesencriptar)
        {
        	String result = vDecipher(getCode(),tfEncriptar.getText());
        	tfEncriptar.setText(tfEncriptar.getText().toUpperCase());
          	tfDesencriptar.setText(result);
        }

    }

	public static void main(String args[]){;
		new VigenereCipher();
	}

	public String getCode(){
		String offset = tfNumber.getText().toUpperCase();
		tfNumber.setText(offset);
		if(offset.equals("")){
			tfNumber.setText("A");
			return "A";
		}
		return offset;
	}

	public String vCipher(String code, String text){
		int codeLength = code.length();
		int textLength = text.length();
		String cipher = "";
		for(int i = 0 ;  i < textLength ; i++){
			cipher = cipher + cipher(code.charAt(i%codeLength)-65,""+text.charAt(i));
		}
		return cipher;
	}

	public String vDecipher(String code, String text){
		int codeLength = code.length();
		int textLength = text.length();
		String cipher = "";
		for(int i = 0 ;  i < textLength ; i++){
			cipher = cipher + decipher(code.charAt(i%codeLength)-65,""+text.charAt(i));
		}
		return cipher;
	}


	public String cipher(int offset,String text){
		String cipheredText = "";
		int length = text.length();
		char letter;
		text = text.toUpperCase();
		// ((letter - 65 + offset)%52)+65
		for(int i = 0; i<length;i++){
			letter = text.charAt(i);
			letter = (char)(((letter + offset - 65)%26) + 65 );
			cipheredText += letter;
		}
		return cipheredText;
	}

	public String decipher(int offset,String text){
		String decipheredText = "";
		int length = text.length();
		char letter;
		text = text.toUpperCase();
	    // ((letter - 65 - offset)%52)+65
		int dis = 0;
		for(int i = 0; i<length;i++){
			letter = text.charAt(i);
			dis = letter - offset - 65;
			if(dis<0){
				letter = (char)(dis + 91);
			}
			else{
				letter = (char)(dis + 65);
			}
			decipheredText += letter;
		}
		return decipheredText;
	}

}