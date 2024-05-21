package fr.ua.javax86.front;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Map;

public class ASMEditor extends JFrame {
    private final JTextPane textPane;
    private final DefaultTableModel tableModel;
    JTextField textFlags = new JTextField();
    private final Map<String, Color> colorMap = new HashMap<>();
    private List<String> registers;

    public ASMEditor() {
        super("ASM Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 500);

        JPanel mainPanel = new JPanel(new BorderLayout());

        textPane = new JTextPane();
        textPane.setBackground(Color.BLACK);
        textPane.setForeground(Color.WHITE);
        JScrollPane textScrollPane = new JScrollPane(textPane);

        JButton runButton = new JButton("Run");
        runButton.addActionListener(e -> updateTableWithRegisters());


        JPanel editorPanel = new JPanel(new BorderLayout());
        editorPanel.add(textScrollPane, BorderLayout.CENTER);
        editorPanel.add(runButton, BorderLayout.SOUTH);

        mainPanel.add(editorPanel, BorderLayout.CENTER);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setPreferredSize(new Dimension(1000, 500));

        tableModel = new DefaultTableModel(new Object[]{"Name", "Binary", "Hexadecimal", "Signed Decimal"}, 0);
        JTable table = new JTable(tableModel);


        textFlags.setEditable(false); // Make it non-editable
        tablePanel.add(textFlags, BorderLayout.SOUTH);

        TableColumn column;
        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 1) {
                column.setPreferredWidth(600);
            } else {
                column.setPreferredWidth(100);
            }
        }

        JScrollPane tableScrollPane = new JScrollPane(table);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        mainPanel.add(tablePanel, BorderLayout.EAST);

        add(mainPanel);

        lireColoration();

        textPane.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                appliquerColoration();
            }
        });
    }

    private void lireColoration() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, List<String>> config = objectMapper.readValue(
                    new File("./coloration.json"),
                    Map.class
            );

            Map<String, List<String>> colors = (Map<String, List<String>>) config.get("colors");
            registers = colors.get("registers");
            for (Map.Entry<String, List<String>> entry : colors.entrySet()) {
                Color color = getColorByName(entry.getKey());
                if (color != null) {
                    for (String word : entry.getValue()) {
                        colorMap.put(word, color);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur de lecture de la coloration");;
        }
    }

    private Color getColorByName(String name) {
        return switch (name) {
            case "registers" -> Color.CYAN;
            case "operation" -> Color.ORANGE;
            default -> null;
        };
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

        for (Map.Entry<String, Color> entry : colorMap.entrySet()) {
            String word = entry.getKey();
            Color color = entry.getValue();

            Pattern pattern = Pattern.compile("\\b" + Pattern.quote(word) + "\\b");
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) {
                SimpleAttributeSet sas = new SimpleAttributeSet();
                StyleConstants.setForeground(sas, color);
                doc.setCharacterAttributes(matcher.start(), word.length(), sas, false);
            }
        }
        // Coloration des nombres (positifs et négatifs)
        Pattern numberPattern = Pattern.compile("\\b-?\\d+\\b");
        Matcher numberMatcher = numberPattern.matcher(text);
        while (numberMatcher.find()) {
            SimpleAttributeSet numberSas = new SimpleAttributeSet();
            StyleConstants.setForeground(numberSas, Color.GREEN);
            doc.setCharacterAttributes(numberMatcher.start(), numberMatcher.end() - numberMatcher.start(), numberSas, false);
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


    private void updateTableWithRegisters() {

        String text = textPane.getText();
        Map<String, String> editorContent = new HashMap<>();
        editorContent.put("content", text);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", text);
        String jsonString = jsonObject.toString();

        try (FileWriter fileWriter = new FileWriter("editeur.json")) {
            fileWriter.write(jsonString);
        } catch (IOException e) {
            System.err.println("Erreur d'ecriture dans l'editeur");;
        }

        ASMEditorBack asmEditorBack = new ASMEditorBack();
        asmEditorBack.interpretEditorContent();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(new File("./resultats.json"));
            tableModel.setRowCount(0);


            Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String registerName = field.getKey();
                JsonNode registerData = field.getValue();

                if (registerData.isObject()) { // Vérifiez si le nœud est un objet
                    String binaryValue = registerData.get("binary").asText();
                    String hexValue = registerData.get("hexadecimal").asText();
                    String signedDecimalValue = registerData.get("signedDecimal").asText();
                    tableModel.addRow(new Object[]{registerName, binaryValue, hexValue, signedDecimalValue});
                } else if (registerData.isTextual() && registerName.equals("flags")) {

                    JsonNode flagsNode = rootNode.get("flags"); // Assume that flags are stored in the "flags" field
                    String flagsText = flagsNode != null ? flagsNode.asText() : "No flags";

                    textFlags.setText("Flags: "+flagsText);
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur d'écriture dans le tableau");;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ASMEditor editor = new ASMEditor();
            editor.setVisible(true);
        });
    }
}
