package com.qust.zq.images;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingButtonsFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Login Example");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);
		frame.setVisible(true);
	}

	private static void placeComponents(JPanel panel) {
		panel.setLayout(null);
		newButton(panel, "SystemUI", 10, 10).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				//runCmd("apt-get update");
				//testExecuteCommand();
			}
		});
	}

	private static JButton newButton(JPanel panel, String btnName, int x, int y) {
		JButton button = new JButton(btnName);
		int width = 120;
		int height = 25;
		button.setBounds(x, y, width, height);
		panel.add(button);
		return button;
	}

	private static void runCmd(String cmd) {
		Process process = null;
		List<String> processList = new ArrayList<String>();
		try {
			process = Runtime.getRuntime().exec(cmd);
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				processList.add(line);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String line : processList) {
			System.out.println(line);
		}
	}

	private static void runCmds(String[] cmds) throws IOException, InterruptedException {
		for (String cmd : cmds) {
			System.out.print(cmd);
			System.out.print(' ');
		}
		Process process = Runtime.getRuntime().exec(cmds);
		InputStreamReader ir = new InputStreamReader(process.getInputStream());
		LineNumberReader input = new LineNumberReader(ir);
		String line;
		while ((line = input.readLine()) != null) {
			System.out.println(line);
		}
	}

	protected static String sudoCmd = "echo \"Abcd123456\" | sudo -S ";

	private static void testExecuteCommand() throws IOException, InterruptedException {
		String cmds[] = { "/bin/bash", "-c", sudoCmd + "ls -l /home/kingfox" };
		SwingButtonsFrame.runCmds(cmds);
	}
}

class MyWindow {

}