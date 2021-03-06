package com.dubllik.mystlviewer.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by elenaozerova on 25/12/2017.
 */

public class IOUtils {
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }

    private static long copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        long count = 0;
        int n = 0;
        while ((n = input.read(buffer)) != -1) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    public static long copy(InputStream input, OutputStream output, int length) throws IOException {
        byte[] buffer = new byte[length];
        int count = 0;
        int n = 0;
        int max = length;
        while ((n = input.read(buffer, 0, max)) != -1) {
            output.write(buffer, 0, n);
            count += n;
            if (count > length) {
                break;
            }

            max -= n;
            if (max <= 0) {
                break;
            }
        }
        return count;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }

        try {
            closeable.close();
        } catch (Throwable e) {
            // do nothing
        }
    }
}
