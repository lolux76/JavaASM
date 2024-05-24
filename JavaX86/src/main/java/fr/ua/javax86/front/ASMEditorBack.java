package fr.ua.javax86.front;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.ua.javax86.asm.ASM;
import fr.ua.javax86.exceptions.EmptyStackException;
import fr.ua.javax86.exceptions.FullStackException;
import fr.ua.javax86.model.Flags;
import fr.ua.javax86.model.Register;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ASMEditorBack {
    ASM asm = new ASM();
    Set<String> usedRegisters = new HashSet<>();

    public void interpretEditorContent() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(new File("./editeur.json"));
            String editorContent = rootNode.path("content").asText();
            String[] lines = editorContent.split("\n");
            for (String line : lines) {
                interpretLine(line.trim());
            }
            saveResultsToJson();
        } catch (IOException e) {
            System.err.println("Impossible d'interpréter l'éditeur");
        }
    }

    private void interpretLine(String line)  {
        if (line.isEmpty() || line.startsWith(";")) {
            return;
        }

        String[] tokens = line.split("\\s+");
        if (tokens.length < 2) {
            System.err.println("Invalid instruction: " + line);
            return;
        }

        String instruction = tokens[0];
        String[] args = line.substring(instruction.length()).split(",");

        switch (instruction.toLowerCase()) {
            case "mov":
                if (args.length == 2) {
                    String reg = args[0].trim();
                    asm.mov(reg, Integer.parseInt(args[1].trim()));
                    usedRegisters.add(reg);
                } else {
                    System.err.println("Invalid mov instruction: " + line);
                }
                break;
            case "shl":
                if (args.length == 2) {
                    String reg = args[0].trim();
                    asm.shl(reg, Integer.parseInt(args[1].trim()));
                    usedRegisters.add(reg);
                } else {
                    System.err.println("Invalid shl instruction: " + line);
                }
                break;
            case "add":
                if (args.length == 2) {
                    String reg1 = args[0].trim();
                    String reg2 = args[1].trim();
                    asm.add(reg1, reg2);
                    usedRegisters.add(reg1);
                    usedRegisters.add(reg2);
                } else {
                    System.err.println("Invalid add instruction: " + line);
                }
                break;
            case "sub":
                if (args.length == 2) {
                    String reg1 = args[0].trim();
                    String reg2 = args[1].trim();
                    asm.sub(reg1, reg2);
                    usedRegisters.add(reg1);
                    usedRegisters.add(reg2);
                } else {
                    System.err.println("Invalid sub instruction: " + line);
                }
                break;
            case "mul":
                if (args.length == 1) {
                    String reg = args[0].trim();
                    asm.mul(reg);
                    usedRegisters.add(reg);
                    usedRegisters.add("edx");

                } else {
                    System.err.println("Invalid mul instruction: " + line);
                }
                break;
            case "div":
                if (args.length == 1) {
                    String reg = args[0].trim();
                    asm.div(reg);
                    usedRegisters.add(reg);
                    usedRegisters.add("edx");
                } else {
                    System.err.println("Invalid div instruction: " + line);
                }
                break;
            case "and":
                if (args.length == 2) {
                    String reg1 = args[0].trim();
                    String reg2 = args[1].trim();
                    asm.and(reg1, reg2);
                    usedRegisters.add(reg1);
                    usedRegisters.add(reg2);
                } else {
                    System.err.println("Invalid and instruction: " + line);
                }
                break;
            case "or":
                if (args.length == 2) {
                    String reg1 = args[0].trim();
                    String reg2 = args[1].trim();
                    asm.or(reg1, reg2);
                    usedRegisters.add(reg1);
                    usedRegisters.add(reg2);
                } else {
                    System.err.println("Invalid or instruction: " + line);
                }
                break;
            case "xor":
                if (args.length == 2) {
                    String reg1 = args[0].trim();
                    String reg2 = args[1].trim();
                    asm.xor(reg1, reg2);
                    usedRegisters.add(reg1);
                    usedRegisters.add(reg2);
                } else {
                    System.err.println("Invalid xor instruction: " + line);
                }
                break;
            case "not":
                if (args.length == 1) {
                    String reg = args[0].trim();
                    asm.not(reg);
                    usedRegisters.add(reg);
                } else {
                    System.err.println("Invalid not instruction: " + line);
                }
                break;
            case "pop":
                if(args.length == 1){
                    String reg = args[0].trim();
                    try {
                        asm.pop(); // a changer (y'a pas de registre ???)
                    } catch (EmptyStackException e) {
                        throw new RuntimeException(e);
                    }
                    usedRegisters.add(reg);
                }
                else {
                    System.err.println("Invalid pop instruction: " + line);
                }
                break;
            case "push":
                if(args.length == 1){
                    String reg = args[0].trim();
                    try {
                        asm.push(reg);
                    } catch (FullStackException e) {
                        throw new RuntimeException(e);
                    }
                    usedRegisters.add(reg);
                }
                else {
                    System.err.println("Invalid push instruction: " + line);
                }
                break;
            default:
                System.err.println("Unknown instruction: " + instruction);
                break;
        }
    }

    private void saveResultsToJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ObjectNode results = objectMapper.createObjectNode();
            for (String reg : usedRegisters) {
                Register register = asm.parsing(reg);
                ObjectNode registerNode = results.putObject(reg);
                registerNode.put("binary", register.toString());
                registerNode.put("hexadecimal", register.toHex());
                registerNode.put("signedDecimal", register.toSigned());
            }

            results.put("flags",Flags.getFlags().toString());

            objectMapper.writeValue(new File("./resultats.json"), results);
        } catch (IOException e) {
            System.err.println("Impossible de lire les résultats ");;
        }
    }
}
