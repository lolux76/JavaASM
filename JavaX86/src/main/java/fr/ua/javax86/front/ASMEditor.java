package fr.ua.javax86.front;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ASMEditor extends JFrame {
    private JTextPane textPane;
    private Map<String, Color> colorMap = new HashMap<>();

    public ASMEditor() {
        super("ASM Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        textPane = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textPane);
        add(scrollPane, BorderLayout.CENTER);
        textPane.setBackground(Color.BLACK);
        textPane.setForeground(Color.WHITE);


        lireConfiguration();

        textPane.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                appliquerColoration();
            }
        });
    }

    private void lireConfiguration() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, List<String>> config = objectMapper.readValue(
                    new File("./coloration.json"),
                    Map.class
            );

            Map<String, List<String>> colors = (Map<String, List<String>>) config.get("colors");
            for (Map.Entry<String, List<String>> entry : colors.entrySet()) {
                Color color = getColorByName(entry.getKey());
                if (color != null) {
                    for (String word : entry.getValue()) {
                        colorMap.put(word, color);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Color getColorByName(String name) {
        switch (name) {
            case "registers":
                return Color.CYAN;
            case "numbers":
                return Color.GREEN;
            case "operation":
                return Color.ORANGE;
            default:
                return null;
        }
    }

    private AttributeSet createGrayAttributeSet() {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setForeground(attributeSet, Color.GRAY);
        return attributeSet;
    }

    private void appliquerColoration() {
        StyledDocument doc = textPane.getStyledDocument();
        doc.setCharacterAttributes(0, doc.getLength(), new SimpleAttributeSet(), true);
        String text = textPane.getText();
        int index = 0;
        for (Map.Entry<String, Color> entry : colorMap.entrySet()) {
            String word = entry.getKey();
            Color color = entry.getValue();

            while ((index = text.indexOf(word, index)) >= 0) {
                SimpleAttributeSet sas = new SimpleAttributeSet();
                StyleConstants.setForeground(sas, color);

                doc.setCharacterAttributes(index, word.length(), sas, false);
                index += word.length();
            }
            index = 0;
        }

        // Coloration des commentaires avec point virgule (en gris)
        String[] lines = text.split("\n");
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].startsWith(";")) {
                Element root = doc.getDefaultRootElement();
                Element line = root.getElement(i);
                int start = line.getStartOffset();
                int end = line.getEndOffset() - 1;

                doc.setCharacterAttributes(start, end - start, createGrayAttributeSet(), false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ASMEditor editor = new ASMEditor();
            editor.setVisible(true);
        });
    }
}

