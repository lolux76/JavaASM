package fr.ua.javax86.front;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ASMEditor extends JFrame {
    private JTextPane textPane;
    private JTable table;
    private Map<String, Color> colorMap = new HashMap<>();

    public ASMEditor() {
        super("ASM Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);

        JPanel mainPanel = new JPanel(new BorderLayout());

        textPane = new JTextPane();
        textPane.setBackground(Color.BLACK);
        textPane.setForeground(Color.WHITE);
        JScrollPane textScrollPane = new JScrollPane(textPane);
        mainPanel.add(textScrollPane, BorderLayout.CENTER);

        JPanel tablePanel = new JPanel(new BorderLayout());
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Name", "Binary", "Hexadecimal", "Signed Decimal"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        mainPanel.add(tablePanel, BorderLayout.EAST);

        add(mainPanel);

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

