package org.apache.solr.response;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Gobble extends Writer {

    final private StringBuilder sb = new StringBuilder();
    final private List<Character> gobble = new ArrayList<Character>(1);
    final private List<Character> remove = new ArrayList<Character>(3);

    public Gobble() {
        gobble.add(' ');
        gobble.add('+');
        //remove.add('\n');
        remove.add('\r');
        remove.add('\t');
    }

    @Override
    public void write(char[] chars, int start, int to) throws IOException {
        char previous = '.';
        for (int i = start; i < to; i++) {
            if (chars[i] == previous && gobble.contains(previous)
                    || remove.contains(chars[i])) {
                // Ignore this character
            } else {
                previous = chars[i];
                sb.append(previous);
            }
        }
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
