package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;

public final class Task3 {
    private Task3() {
    }

    @SuppressWarnings("MultipleStringLiterals")
    public static Class<?> createClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name("Fibonacci")
            .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameters(int.class)
            .intercept(new Implementation.Simple(new FibByteCodeAppender()))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
    }

    @SuppressWarnings({"MultipleStringLiterals", "MagicNumber"})
    private static final class FibByteCodeAppender implements ByteCodeAppender {
        @Override
        public @NotNull Size apply(
            MethodVisitor mv,
            Implementation.@NotNull Context ctx,
            @NotNull MethodDescription md
        ) {
            mv.visitCode();
            Label lessOrEqualsZero = new Label();
            Label equalsOne = new Label();
            // if (n <= 0)
            mv.visitVarInsn(Opcodes.ILOAD, 0);  // Загрузка аргумента n в стек
            mv.visitJumpInsn(Opcodes.IFLE, lessOrEqualsZero);  // Если n <= 0 переход к метке lessOrEqualsZero

            // if (n == 1)
            mv.visitInsn(Opcodes.ICONST_1); // Загрузка константы 1 в стек
            mv.visitVarInsn(Opcodes.ILOAD, 0);  // Загрузка аргумента n в стек
            mv.visitJumpInsn(Opcodes.IF_ICMPEQ, equalsOne);

            // if (n > 1)
            // fib(n - 2)
            mv.visitVarInsn(Opcodes.ILOAD, 0); // Загрузка аргумента n в стек
            mv.visitInsn(Opcodes.ICONST_2);
            mv.visitInsn(Opcodes.ISUB);  // n - 2 в стек
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "Fibonacci", "fib", "(I)J", false);

            // fib(n - 1)
            mv.visitVarInsn(Opcodes.ILOAD, 0); // Загрузка аргумента n
            mv.visitInsn(Opcodes.ICONST_1);
            mv.visitInsn(Opcodes.ISUB);  // n - 1 в стек
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "Fibonacci", "fib", "(I)J", false);

            // return fib(n - 2) + fib(n - 1)
            mv.visitInsn(Opcodes.LADD);  // Сложение результатов
            mv.visitInsn(Opcodes.LRETURN);

            // Label lessOrEqualsZero
            mv.visitLabel(lessOrEqualsZero);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(Opcodes.LCONST_0);
            mv.visitInsn(Opcodes.LRETURN);

            // Label equalsOne
            mv.visitLabel(equalsOne);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(Opcodes.LCONST_1);
            mv.visitInsn(Opcodes.LRETURN);

            // End
            mv.visitMaxs(4, 1);
            mv.visitEnd();
            return new Size(4, 1);
        }
    }

}
