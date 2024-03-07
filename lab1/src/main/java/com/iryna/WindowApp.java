package com.iryna;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowApp extends JFrame {
    private final ClassInspector classInspector = new ClassInspector();
    private JLabel label;
    private JTextField textField;
    private JTextArea textArea;
    private JButton analyzeButton;
    private JButton clearButton;
    private JButton exitButton;

    public WindowApp() {
        init();
    }

    public static void main(String[] args) {
        WindowApp app = new WindowApp();
    }

    private void init() {
        setTitle("Class analyzer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(400, 300);

        setResizable(false);

        // First row: Label та TextInput
        JPanel inputPanel = new JPanel();
        label = new JLabel("Enter class to analyze:");
        textField = new JTextField(20);
        inputPanel.add(label);
        inputPanel.add(textField);

        // The second row: area for output data
        JPanel outputPanel = new JPanel();
        textArea = new JTextArea(20, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        outputPanel.add(scrollPane);

        // The third row: 3 buttons (Analyze, Clear, Exit)
        JPanel controlsPanel = new JPanel();
        analyzeButton = new JButton("Analyze");
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");

        // Set buttons commands
        analyzeButton.setActionCommand("analyze");
        clearButton.setActionCommand("clear");
        exitButton.setActionCommand("exit");

        controlsPanel.add(analyzeButton);
        controlsPanel.add(clearButton);
        controlsPanel.add(exitButton);

        // Add frames to frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(outputPanel, BorderLayout.CENTER);
        getContentPane().add(controlsPanel, BorderLayout.SOUTH);

        // Event listeners
        analyzeButton.addActionListener(new ButtonActionHandler());
        clearButton.addActionListener(new ButtonActionHandler());
        exitButton.addActionListener(new ButtonActionHandler());

        pack();
        setVisible(true);
    }

    private class ButtonActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if(command.equals("analyze")) {
                String subjectClass = textField.getText();
                textArea.append(classInspector.inspect(subjectClass));
            } else if (command.equals("clear")) {
                textArea.setText("");
                textField.setText("");
            } else {
                System.exit(0);
            }
        }
    }
}