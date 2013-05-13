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


/**
 * package org.apache.solr.response;

 import java.io.IOException;
 import java.io.Writer;
 import java.util.HashMap;

 public class Gobble extends Writer {

 final private HashMap<Character[], Character> gobble = new HashMap<Character[], Character>(6);
 final private StringBuilder stringBuilder = new StringBuilder();

 public Gobble() {
 gobble.put(new Character[]{' ', ' '}, null);
 gobble.put(new Character[]{'+', '+'}, null);
 gobble.put(new Character[]{'>', ' '}, '>');
 gobble.put(new Character[]{' ', '<'}, '<');
 gobble.put(new Character[]{'\r', null}, null);
 gobble.put(new Character[]{'\t', null}, null);
 }

 @Override
 public void write(char[] chars, int start, int to) throws IOException {
 char previous = '.';
 final Character[] compareValue = new Character[]{'.', '.'};
 final Character[] compareNull = new Character[]{'.', '.'};
 for (int i = start; i < to; i++) {
 compareValue[0] = compareNull[0] = previous;
 previous = chars[i];
 compareValue[1] = chars[i];
 if (gobble.containsKey(compareValue)) {
 _append(gobble.get(compareValue));
 } else if (gobble.containsKey(compareNull)) {
 _append(gobble.get(compareNull));
 } else {
 stringBuilder.append(chars[i]);
 flush();
 }
 }
 }

 private void _append(Character character) {
 if (character != null)
 stringBuilder.append(character);
 }

 @Override
 public void flush() throws IOException {
 }

 @Override
 public void close() throws IOException {
 }

 @Override
 public String toString() {
 return stringBuilder.toString();
 }
 }

 */
