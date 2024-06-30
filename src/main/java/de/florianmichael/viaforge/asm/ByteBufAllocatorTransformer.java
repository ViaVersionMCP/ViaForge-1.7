/*
 * This file is part of ViaForge - https://github.com/FlorianMichael/ViaForge
 * Copyright (C) 2021-2024 FlorianMichael/EnZaXD <florian.michael07@gmail.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.florianmichael.viaforge.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;

public class ByteBufAllocatorTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if ((name == null) || (bytes == null)) {
            return bytes;
        }
        if (name.equals("io.netty.buffer.ByteBufAllocator")) {
            ClassReader cr = new ClassReader(bytes);
            ClassWriter cw = new ClassWriter(cr, 0);
            try {
                AddFieldAdapter af = new AddFieldAdapter(cw);
                cr.accept(af, 0);
            } catch (Exception e) {
                e.printStackTrace();
                return bytes;
            }
            return cw.toByteArray();
        }
        return bytes;
    }

    static class AddFieldAdapter extends ClassVisitor {
        public AddFieldAdapter(ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
        }

        @Override
        public void visitEnd() {
            FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "DEFAULT", "Lio/netty/buffer/ByteBufAllocator;", null, null);
            if (fv != null) {
                fv.visitEnd();
            }
            MethodVisitor mv = cv.visitMethod(Opcodes.ACC_STATIC, "<clinit>", "()V", null, null);
            if (mv != null) {
                mv.visitCode();
                mv.visitFieldInsn(Opcodes.GETSTATIC, "io/netty/buffer/ByteBufUtil", "DEFAULT_ALLOCATOR", "Lio/netty/buffer/ByteBufAllocator;");
                mv.visitFieldInsn(Opcodes.PUTSTATIC, "io/netty/buffer/ByteBufAllocator", "DEFAULT", "Lio/netty/buffer/ByteBufAllocator;");
                mv.visitInsn(Opcodes.RETURN);
                mv.visitMaxs(1, 0);
                mv.visitEnd();
            }
            cv.visitEnd();
        }
    }
}
