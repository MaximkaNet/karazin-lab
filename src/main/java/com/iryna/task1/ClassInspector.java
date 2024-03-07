package com.iryna.task1;

import java.lang.reflect.*;
import java.util.StringJoiner;

public class ClassInspector {
    public String inspect(String className) {
        Class<?> inspectedClass = getClassForName(className);
        // Check existing class
        if (inspectedClass == null) {
            return "Class does not exists.";
        }
        // Get class information
        return inspectedClass.getPackage().toString() + "\n" +
                // Get class definition
                getClassDefinition(inspectedClass) + " { \n" +
                // Get class fields
                getFields(inspectedClass) + "\n" +
                // Get class constructors
                getConstructors(inspectedClass) + "\n" +
                // Get class methods
                getMethods(inspectedClass) + "\n" +
                "} \n";
    }

    private String getClassDefinition(Class<?> inspectedClass) {
        StringBuilder sb = new StringBuilder();
        // Get modifiers
        sb.append(Modifier.toString(inspectedClass.getModifiers()));
        // Get name
        sb.append(" ").append(inspectedClass.getSimpleName());
        // Get extended class
        boolean hasSuperClass = inspectedClass.getSuperclass() != null;
        if (hasSuperClass && !inspectedClass.getSuperclass().isHidden()) {
            sb.append(" extends ");
            sb.append(inspectedClass.getSuperclass().getSimpleName());
        }
        // Get implemented interfaces
        if (inspectedClass.getGenericInterfaces().length != 0) {
            StringJoiner sj = new StringJoiner(", ");
            for (Class<?> inter : inspectedClass.getInterfaces()) {
                sj.add(inter.getSimpleName());
            }
            sb.append(" implements ").append(sj);
        }
        return sb.toString();
    }

    private String getFields(Class<?> inspectedClass) {
        StringBuilder sb = new StringBuilder();
        // Iterate inspected class fields
        for (Field field : inspectedClass.getDeclaredFields()) {
            // Set tabulation
            sb.append("\t")
                    // Get modifiers
                    .append(Modifier.toString(field.getModifiers()))
                    .append(" ")
                    // Get type
                    .append(field.getType().getSimpleName())
                    .append(" ")
                    // Get name
                    .append(field.getName())
                    // End
                    .append(";\n");
        }
        return sb.toString();
    }

    private String getConstructors(Class<?> inspectedClass) {
        StringBuilder sb = new StringBuilder();
        for (Constructor<?> constructor : inspectedClass.getDeclaredConstructors()) {
            sb.append("\t")
                    .append(Modifier.toString(constructor.getModifiers()))
                    .append(" ")
                    .append(inspectedClass.getSimpleName())
                    .append("(");
            StringJoiner sj = new StringJoiner(", ");
            for (Parameter param : constructor.getParameters()) {
                StringBuilder paramStr = new StringBuilder();
                if (param.getModifiers() != 0) {
                    paramStr.append(Modifier.toString(param.getModifiers()));
                    paramStr.append(" ");
                }
                paramStr.append(param.getType().getSimpleName())
                        .append(" ")
                        .append(param.getName());
                sj.add(paramStr);
            }
            sb.append(sj).append(")").append(";\n");
        }
        return sb.toString();
    }

    private String getMethods(Class<?> inspectedClass) {
        StringBuilder sb = new StringBuilder();
        for (Method method: inspectedClass.getDeclaredMethods()){
            sb.append("\t")
                    .append(Modifier.toString(method.getModifiers()))
                    .append(" ")
                    .append(method.getName())
                    .append("(");
            StringJoiner sj = new StringJoiner(", ");
            for (Parameter param : method.getParameters()) {
                StringBuilder paramStr = new StringBuilder();
                if (param.getModifiers() != 0) {
                    paramStr.append(Modifier.toString(param.getModifiers()));
                    paramStr.append(" ");
                }
                paramStr.append(param.getType().getSimpleName())
                        .append(" ")
                        .append(param.getName());
                sj.add(paramStr);
            }
            sb.append(sj).append(")").append(";\n");
        }
        return sb.toString();
    }

    public static Class<?> getClassForName(String className) {
        try {
            return Class.forName(className);
        } catch (Exception e) {
            return null;
        }
    }
}
