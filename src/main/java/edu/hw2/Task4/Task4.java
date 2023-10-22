package edu.hw2.Task4;

public final class Task4 {
    private Task4() {
    }

    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement callingMethod = stackTrace[2];

        String className = callingMethod.getClassName();
        String methodName = callingMethod.getMethodName();

        return new CallingInfo(className, methodName);
    }
}
