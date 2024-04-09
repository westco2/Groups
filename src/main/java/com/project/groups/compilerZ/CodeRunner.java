package com.project.groups.compilerZ;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class CodeRunner {
    private static final long TIMEOUT_MS = 2000; // 입력 대기 시간 (밀리초)


    public static String CodeRunnerTest(String code, String input, String answer) {
         // 입력 뒤에 개행 추가
        Object result = runStudentCode(code, input, TIMEOUT_MS);

        System.out.println("result: " + result);
        System.out.println("answer: " + answer);


        if (result instanceof String && ((String) result).startsWith("컴파일 오류")) {
            return "컴파일 오류: " + ((String) result).substring(7); // 컴파일 오류 메시지 반환
        } else if (result instanceof String && ((String) result).startsWith("입력 값 처리 중 오류가 발생했습니다:")) {
            return "입력 값 처리 오류: " + ((String) result).substring(17); // 입력 값 처리 오류 메시지 반환
        } else if (((String)result).trim().equals(answer)) {
            return "통과";
        } else {
            return "실패";
        }
    }

    public static Object runStudentCode(String code, String input,long timeoutMillis) {
        Object result = null;
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            JavaFileObject studentFileObject = new JavaSourceFromString("Solution", code);

            Iterable<? extends JavaFileObject> compilationUnits = Collections.singletonList(studentFileObject);

            JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);
            boolean success = task.call();

            if (success) {
                URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File(".").toURI().toURL()});
                Class<?> solutionClass = classLoader.loadClass("Solution");

                // 코드 실행 제한 시간 설정
                FutureTask<Object> taskWithTimeout = new FutureTask<>(() -> {
                    InputStream originalIn = System.in;
                    PrintStream originalOut = System.out;

                    try {
                        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                        System.setIn(in);

                        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
                        System.setOut(new PrintStream(outContent));

                        solutionClass.getMethod("main", String[].class).invoke(null, (Object) null);

                        String output = outContent.toString();
                        return output; // 결과값 반환
                    } finally {
                        System.setIn(originalIn);
                        System.setOut(originalOut);
                    }
                });

                executor.execute(taskWithTimeout); // FutureTask 실행

                try {
                    result = taskWithTimeout.get(timeoutMillis, TimeUnit.MILLISECONDS); // 시간 초과 예외 발생
                } catch (TimeoutException e) {
                    result = "시간 초과: 코드 실행이 " + timeoutMillis + "밀리초를 초과했습니다.";
                    System.out.println(result);
                } finally {
                    // 코드 실행 종료
                    taskWithTimeout.cancel(true);
                }

                executor.shutdownNow(); // 실행 완료 후 리소스 정리
            } else {
                result = "컴파일 실패";
                // 컴파일 에러를 출력
                for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                    System.out.println(diagnostic.getMessage(null));
                }
            }
        } catch (Exception e) {
            result = "오류: " + e.getMessage(); // 오류 메시지 반환
            e.printStackTrace();
        }

        return result;
    }

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
        Scanner scanner = new Scanner(output);
        int lastValue = 0;
        while (scanner.hasNextInt()) {
            lastValue = scanner.nextInt();
        }
        scanner.close();
        return lastValue;
    }
}

