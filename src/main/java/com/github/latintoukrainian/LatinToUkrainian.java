/*
 * $Id$
 *
 * Copyright 2015 Valentyn Kolesnikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.latintoukrainian;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LatinToUkrainian utility class.
 *
 * @author Valentyn Kolesnikov
 * @version $Revision$ $Date$
 */
public class LatinToUkrainian {
    private static final int INDEX_1 = 1;
    private static final int INDEX_2 = 2;
    private static final int INDEX_3 = 3;
    private static final int INDEX_4 = 4;
    private static final int INDEX_5 = 5;
    private static final Map<String, String> CYR_TO_LAT_FIRST = new LinkedHashMap<String, String>() {{
        put("А", "A");
        put("Б", "B");
        put("В", "V");
        put("Г", "H");
        put("Ґ", "G");
        put("Д", "D");
        put("Е", "E");
        put("Є", "Ye");
        put("Ж", "Zh");
        put("Зг", "Zgh");
        put("З", "Z");
        put("Й", "Y");
        put("И", "Y");
        put("І", "I");
        put("Ї", "Yi");
        put("К", "K");
        put("Л", "L");
        put("М", "M");
        put("Н", "N");
        put("О", "O");
        put("П", "P");
        put("Р", "R");
        put("С", "S");
        put("Т", "T");
        put("У", "U");
        put("Ф", "F");
        put("Х", "Kh");
        put("Ц", "Ts");
        put("Ч", "Ch");
        put("Ш", "Sh");
        put("Щ", "Shch");
        put("Ю", "Yu");
        put("Я", "Ya");
    }};

    private static final Map<String, String> CYR_TO_LAT_MIDDLE = new LinkedHashMap<String, String>() {{
        put("А", "A");
        put("Б", "B");
        put("В", "V");
        put("Г", "H");
        put("Ґ", "G");
        put("Д", "D");
        put("Е", "E");
        put("Є", "Ie");
        put("Ж", "Zh");
        put("З", "Z");
        put("И", "Y");
        put("І", "I");
        put("Ї", "I");
        put("Й", "I");
        put("К", "K");
        put("Л", "L");
        put("М", "M");
        put("Н", "N");
        put("О", "O");
        put("П", "P");
        put("Р", "R");
        put("С", "S");
        put("Т", "T");
        put("У", "U");
        put("Ф", "F");
        put("Х", "Kh");
        put("Ц", "Ts");
        put("Ч", "Ch");
        put("Ш", "Sh");
        put("Щ", "Shch");
        put("Ю", "Iu");
        put("Я", "Ia");
    }};

    private static final Map<String, Map<List<String>, String>> MIDDLE_TIPS = new LinkedHashMap<String, Map<List<String>, String>>() {{
        put("i", new HashMap<List<String>, String>() {{
            put(Arrays.asList("к", "и"), "ї");
            put(Arrays.asList("д", "и"), "ї");
            put(Arrays.asList("л", "а"), "ї");
            put(Arrays.asList("а", "р"), "’ї");
            put(Arrays.asList("х", "а"), "й");
        }});
        put("k", new HashMap<List<String>, String>() {{
            put(Arrays.asList("н", "с"), "ьк");
            put(Arrays.asList("р", "с"), "ьк");
        }});
    }};

    private static final Map<String, Map<List<String>, String>> END_TIPS = new LinkedHashMap<String, Map<List<String>, String>>() {{
        put("i", new HashMap<List<String>, String>() {{
            put(Arrays.asList("д", "і"), "й");
            put(Arrays.asList("к", "и"), "й");
            put(Arrays.asList("л", "і"), "й");
            put(Arrays.asList("р", "і"), "й");
            put(Arrays.asList("р", "и"), "й");
            put(Arrays.asList("с", "і"), "й");
        }});
        put("l", new HashMap<List<String>, String>() {{
            put(Arrays.asList("п", "і"), "ль");
        }});
        put("n", new HashMap<List<String>, String>() {{
            put(Arrays.asList("м", "а"), "нь");
        }});
        put("ie", new HashMap<List<String>, String>() {{
            put(Arrays.asList("о", "п"), "’є");
        }});
        put("k", new HashMap<List<String>, String>() {{
            put(Arrays.asList("е", "ц"), "ьк");
        }});
    }};

    private static final Set<String> PUNCTUATIONS = new HashSet<String>(Arrays.asList(",", "-", "!", "?", ":", ";", "."));

    private static class ConvertCase {
        private final Map.Entry<String, String> convert;
        private final boolean lowcase;
        public ConvertCase(Map.Entry<String, String> convert, boolean lowcase) {
            this.convert = convert;
            this.lowcase = lowcase;
        }
        public Map.Entry<String, String> getConvert() {
            return convert;
        }
        public boolean isLowcase() {
            return lowcase;
        }
        @Override
        public String toString() {
            return convert + (lowcase ? " lower" : " upper");
        }
    }
    private static final Map<String, List<ConvertCase>> latToCyrFirst;
    private static final Map<String, List<ConvertCase>> latToCyrMiddle;

    static {
        latToCyrFirst = new HashMap<String, List<ConvertCase>>();
        for (final Map.Entry<String, String> convert : CYR_TO_LAT_FIRST.entrySet()) {
            putMyObject(latToCyrFirst, convert.getValue(), new ConvertCase(convert, false));
            putMyObject(latToCyrFirst, convert.getValue().toLowerCase(), new ConvertCase(convert, true));
        }
        latToCyrMiddle = new HashMap<String, List<ConvertCase>>();
        for (final Map.Entry<String, String> convert : CYR_TO_LAT_MIDDLE.entrySet()) {
            putMyObject(latToCyrMiddle, convert.getValue(), new ConvertCase(convert, false));
            putMyObject(latToCyrMiddle, convert.getValue().toLowerCase(), new ConvertCase(convert, true));
        }
    }

    private static <T> void putMyObject(Map<String, List<T>> map, String key, T value) {
        final List<T> myList;
        if (map.get(key) == null) {
            myList = new ArrayList<T>();
            map.put(key, myList);
        } else {
            myList = map.get(key);
        }
        myList.add(value);
    }

    /**
     * Generates cyrilic from latinic.
     * @param name the name
     * @return the result
     */
    public static String generateUkr(String name) {
        final StringBuilder result = new StringBuilder();
        List<ConvertCase> prevPrevConvertCase = null;
        List<ConvertCase> prevConvertCase = null;
        for (int index = 0; index < name.length(); index += 1) {
            final String curChar = name.substring(index, index + INDEX_1);
            final String curChar2 = name.substring(index, Math.min(index + INDEX_2, name.length()));
            final String curChar4 = name.substring(index, Math.min(index + INDEX_4, name.length()));
//System.out.println("curChar - " + curChar + ", curChar2 - " + curChar2);
            if (latToCyrFirst.get(curChar4) == null && latToCyrFirst.get(curChar2) == null
                    && latToCyrFirst.get(curChar) == null) {
                if (" ".equals(curChar)) {
                    prevConvertCase = null;
                    result.append(' ');
                } else if (curChar.matches("\\n") || PUNCTUATIONS.contains(curChar)) {
                    result.append(curChar);
                }
                continue;
            }
            List<ConvertCase> convertCase;
            if (prevConvertCase == null) {
                convertCase = latToCyrFirst.get(curChar4);
                if (convertCase == null) {
                    convertCase = latToCyrFirst.get(curChar2);
                    if (convertCase == null) {
                        convertCase = latToCyrFirst.get(curChar);
                    } else {
                        index += 1;
                    }
                } else {
                    index += 3;
                }
                checkChar(result, convertCase, prevPrevConvertCase, prevConvertCase, MIDDLE_TIPS);
            } else {
                convertCase = latToCyrMiddle.get(curChar4);
                if (convertCase == null) {
                    convertCase = latToCyrMiddle.get(curChar2);
                    if (convertCase == null) {
                        convertCase = latToCyrMiddle.get(curChar);
                    } else {
                        index += 1;
                    }
                } else {
                    index += 3;
                }
                if (index >= name.length() || name.substring(index + INDEX_1, index + INDEX_2).equals(" ")) {
                    checkChar(result, convertCase, prevPrevConvertCase, prevConvertCase, END_TIPS);
                } else {
                    checkChar(result, convertCase, prevPrevConvertCase, prevConvertCase, MIDDLE_TIPS);
                }
            }
            prevPrevConvertCase = prevConvertCase;
            prevConvertCase = convertCase;
       } 
       return result.toString();
    }
    
    /**
     * Converts character from latinic to cyrilic.
     * @param result resut buffer to store string in latin
     * @param convertCase current character object
     * @param prevPrevConvertCase prev prev character object
     * @param prevConvertCase prev character object
     */
    private static void checkChar(StringBuilder result, List<ConvertCase> convertCase,
        List<ConvertCase> prevPrevConvertCase, List<ConvertCase> prevConvertCase,
        Map<String, Map<List<String>, String>> tips) {
        String cyrName = convertCase.get(0).getConvert().getKey();
        if (tips.get(convertCase.get(0).getConvert().getValue().toLowerCase()) != null
           && prevPrevConvertCase != null && prevConvertCase != null) {
            final Map<List<String>, String> tipMap = tips.get(convertCase.get(0).getConvert().getValue().toLowerCase());
            for (final Map.Entry<List<String>, String> tip : tipMap.entrySet()) {
                if (tip.getKey().get(0).equalsIgnoreCase(prevPrevConvertCase.get(0).getConvert().getKey())
                    && tip.getKey().get(1).equalsIgnoreCase(prevConvertCase.get(0).getConvert().getKey())) {
                    cyrName = tip.getValue();
                    break;
                }
            }
        }
        result.append(convertCase.get(0).isLowcase() ? cyrName.toLowerCase() : cyrName);
    }

    public static void main(String[] args) {
        final String message = "The utility class to convert latin words to the ukrainian characters.\n\n" +
            "For docs, license, tests, and downloads, see: https://github.com/javadev/latintoukrainian";
        System.out.println(message);
    }
}
