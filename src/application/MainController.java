package application;

import java.util.HashMap;
import java.util.Map;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;

public class MainController {

	@FXML
	private JFXTextArea label;
	@FXML
	private JFXTextField keyField;
	@FXML
	private JFXButton ecryptBtn;
	@FXML
	private JFXButton decryptBtn;

	static String key;
	static String text;
	static String openText;
	static char[] textArr;
	static char[] keyArr;

	@FXML
	void encryptAction(ActionEvent event) {

		if (label.getText().equals("")) {
			label.setFocusColor(Color.RED);
			label.requestFocus();
		} else if (keyField.getText().equals("")) {
			keyField.setFocusColor(Color.RED);
			keyField.requestFocus();
		} else {
			String[] crypto = cryptoMethod();
			label.setText(label.getText() + "\n" + crypto[0]);
		}
	}

	@FXML
	void decryptAction(ActionEvent event) {
		if (label.getText().equals("")) {
			label.setFocusColor(Color.RED);
			label.requestFocus();
		} else if (keyField.getText().equals("")) {
			keyField.setFocusColor(Color.RED);
			keyField.requestFocus();
		} else {
			String[] crypto = cryptoMethod();
			label.setText(label.getText() + "\n" + crypto[1]);
		}
	}

	public String[] cryptoMethod() {
		String crypto[] = new String[2];

		text = label.getText();
		key = keyField.getText();

		textArr = text.toCharArray();
		keyArr = key.toCharArray();

		char[] keyArr2 = new char[text.length()];

		String[] alpha = new String[26];

		int count = 0;
		for (int i = 0; i < text.length(); i++) {

			if (count < key.length()) {
				keyArr2[i] = keyArr[count];
				count++;
			} else {
				count = 0;
				keyArr2[i] = keyArr[count];
				count++;
			}
		}
		Map<String, Integer> hm = new HashMap<String, Integer>();

		for (int i = 0; i < 26; i++) {
			alpha[i] = Character.toString((char) (65 + i));
			hm.put(Character.toString((char) (65 + i)), i);
		}

		label.setText(label.getText() + "\n");

		String encrypt = "";
		String decrypt = "";

		for (int i = 0; i < text.length(); i++) {
			encrypt += alpha[(hm.get(String.valueOf(textArr[i])) + hm.get(String.valueOf(keyArr2[i]))) % hm.size()];
			decrypt += alpha[Math.floorMod(hm.get(String.valueOf(textArr[i])) - hm.get(String.valueOf(keyArr2[i])),
					hm.size())];
		}
		crypto[0] = encrypt;
		crypto[1] = decrypt;

		return crypto;
	}

}
