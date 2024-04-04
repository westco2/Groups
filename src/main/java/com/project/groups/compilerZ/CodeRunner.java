package com.project.groups.compilerZ;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.*;

    public class CodeRunner {
        public static String CodeRunnerTest(String code) {

            String answer = "-2";
            // 학생이 제출한 코드
            String studentCode = code;
            System.out.println("studentCode = " + studentCode);
//                    "import java.util.Scanner;\n" +
//                    "public class Solution {\n" +
//                    "    public static void main(String[] args) {\n" +
//                    "        Scanner scanner = new Scanner(System.in);\n" +
//                    "        int a = scanner.nextInt();\n" +
//                    "        int b = scanner.nextInt();\n" +
//                    "        System.out.println(a - b);\n" +
//                    "    }\n" +
//                    "}";


            // 학생이 제출한 코드 실행 및 결과 출력

            Object result = runStudentCode(studentCode, "3 5");
            if (result != null) {
                System.out.println("Execution result:");
                System.out.println("------------------");
                System.out.println("Result: " + result.toString());
                System.out.println("정답: " + answer);
                System.out.println("------------------");
            }
            if(result.toString().equals(answer)) return "통과";
            else return "실패";
        }

        public static Object runStudentCode(String code, String input) {
            // 컴파일할 파일 준비
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            JavaFileObject studentFileObject = new JavaSourceFromString("Solution", code);

            // 컴파일 수행
            Iterable<? extends JavaFileObject> compilationUnits = Collections.singletonList(studentFileObject);
            JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);
            boolean success = task.call();

            Object result = null;
            System.out.println("success = " + success);

            // 컴파일 결과 출력
            if (success) {
                System.out.println("Compilation successful");

                // 입력 값을 설정하여 실행
                try {
                    URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File(".").toURI().toURL()});
                    Class<?> solutionClass = classLoader.loadClass("Solution");

                    // 입력 값을 자동으로 설정하여 main 메서드 호출
                    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes()); // 입력값 설정
                    System.setIn(in);

                    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
                    PrintStream originalOut = System.out;
                    System.setOut(new PrintStream(outContent));

                    Method mainMethod = solutionClass.getMethod("main", String[].class);
                    mainMethod.invoke(null, (Object) null);

                    // 실행 결과를 얻기 위해 콘솔 출력을 읽어옴
                    String output = outContent.toString();
                    result = parseResult(output);

                    // 콘솔 출력을 원래대로 복원
                    System.setOut(originalOut);

                } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                         InvocationTargetException | MalformedURLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Compilation failed");
                for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                    System.out.println(diagnostic.getMessage(null));
                }
            }

            return result;
        }

        // 소스 코드를 컴파일할 때 사용할 JavaFileObject 구현 클래스
        static class JavaSourceFromString extends SimpleJavaFileObject {
            final String code;

            JavaSourceFromString(String name, String code) {
                super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
                this.code = code;
            }

            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                return code;
            }
        }

        private static int parseResult(String output) {
            // 콘솔 출력에서 결과값을 파싱하여 반환하는 로직을 구현
            // 이 예제에서는 간단히 마지막 줄의 정수값을 추출하는 것으로 가정
            String[] lines = output.trim().split("\n");
            String lastLine = lines[lines.length - 1];
            return Integer.parseInt(lastLine.trim());
        }
    }

