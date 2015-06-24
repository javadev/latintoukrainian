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
    private static final Map<String, String> CYR_TO_LAT_FIRST = new LinkedHashMap<String, String>() { {
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
    } };

    private static final Map<String, String> CYR_TO_LAT_MIDDLE = new LinkedHashMap<String, String>() { {
        put("А", "A");
        put("Б", "B");
        put("В", "V");
        put("Г", "H");
        put("Ґ", "G");
        put("Д", "D");
        put("Е", "E");
        put("Є", "Ie");
        put("Ж", "Zh");
        put("Зг", "Zgh");
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
    } };

    private static final Map<String, Map<List<String>, String>> MIDDLE_TIPS =
        new LinkedHashMap<String, Map<List<String>, String>>() { {
        put("i", new HashMap<List<String>, String>() { {
            put(Arrays.asList("к", "и"), "ї");
            put(Arrays.asList("д", "и"), "ї");
            put(Arrays.asList("л", "а"), "ї");
            put(Arrays.asList("в", "о"), "ї");
            put(Arrays.asList("р", "а"), "ї");
            put(Arrays.asList("х", "а"), "й");
            put(Arrays.asList("в", "а"), "й");
            put(Arrays.asList("к", "а"), "й");
            put(Arrays.asList("г", "а"), "й");
            put(Arrays.asList("в", "и"), "й");
            put(Arrays.asList("д", "а"), "й");
            put(Arrays.asList("л", "я"), "й");
            put(Arrays.asList("н", "а"), "й");
            put(Arrays.asList("ч", "а"), "й");
            put(Arrays.asList("р", "и"), "й");
            put(Arrays.asList("б", "а"), "й");
            put(Arrays.asList("в", "е"), "й");
            put(Arrays.asList("м", "о"), "ї");
            put(Arrays.asList("з", "і"), "й");
            put(Arrays.asList("д", "і"), "й");
        } });
        put("k", new HashMap<List<String>, String>() { {
            put(Arrays.asList("н", "с"), "ьк");
            put(Arrays.asList("р", "с"), "ьк");
        } });
        put("ts", new HashMap<List<String>, String>() { {
            put(Arrays.asList("у", "ю"), "тьс");
            put(Arrays.asList("н", "е"), "тьс");
            put(Arrays.asList("б", "є"), "тьс");
            put(Arrays.asList("в", "и"), "тьс");
            put(Arrays.asList("д", "е"), "тьс");
            put(Arrays.asList("і", "ю"), "тьс");
            put(Arrays.asList("і", "є"), "тьс");
            put(Arrays.asList("л", "ю"), "тьс");
            put(Arrays.asList("м", "е"), "тьс");
        } });
    } };

    private static final Map<String, Map<List<String>, String>> END_TIPS =
        new LinkedHashMap<String, Map<List<String>, String>>() { {
        put("i", new HashMap<List<String>, String>() { {
            put(Arrays.asList("г", "і"), "й");
            put(Arrays.asList("д", "і"), "й");
            put(Arrays.asList("к", "и"), "й");
            put(Arrays.asList("к", "і"), "й");
            put(Arrays.asList("л", "і"), "й");
            put(Arrays.asList("р", "і"), "й");
            put(Arrays.asList("р", "и"), "й");
            put(Arrays.asList("с", "і"), "й");
            put(Arrays.asList("в", "о"), "ї");
            put(Arrays.asList("б", "і"), "й");
            put(Arrays.asList("в", "и"), "й");
            put(Arrays.asList("ш", "і"), "й");
            put(Arrays.asList("ч", "и"), "й");
            put(Arrays.asList("н", "і"), "й");
            put(Arrays.asList("в", "і"), "й");
            put(Arrays.asList("ц", "и"), "й");
            put(Arrays.asList("н", "и"), "й");
            put(Arrays.asList("р", "а"), "й");
            put(Arrays.asList("д", "а"), "й");
            put(Arrays.asList("с", "е"), "й");
            put(Arrays.asList("в", "е"), "й");
            put(Arrays.asList("м", "о"), "ї");
            put(Arrays.asList("х", "а"), "й");
            put(Arrays.asList("ш", "е"), "й");
            put(Arrays.asList("в", "а"), "й");
            put(Arrays.asList("л", "и"), "й");
            put(Arrays.asList("т", "и"), "й");
            put(Arrays.asList("л", "я"), "й");
            put(Arrays.asList("ч", "е"), "й");
            put(Arrays.asList("н", "е"), "ї");
            put(Arrays.asList("т", "о"), "й");
            put(Arrays.asList("д", "е"), "й");
            put(Arrays.asList("т", "а"), "й");
            put(Arrays.asList("м", "і"), "й");
        } });
        put("l", new HashMap<List<String>, String>() { {
            put(Arrays.asList("п", "і"), "ль");
        } });
        put("n", new HashMap<List<String>, String>() { {
            put(Arrays.asList("м", "а"), "нь");
        } });
        put("ie", new HashMap<List<String>, String>() { {
            put(Arrays.asList("о", "п"), "’є");
        } });
        put("k", new HashMap<List<String>, String>() { {
            put(Arrays.asList("е", "ц"), "ьк");
        } });
    } };
    private static final Map<String, String> WORDS_WITH_COMMA = new LinkedHashMap<String, String>() { {
        put("батки", "батьки");
        put("Знамянка", "Знам’янка");
        put("Кишенки", "Кишеньки");
        put("Троц", "Троць");
        put("Донетьськ", "Донецьк");
        put("гинут", "гинуть");
        put("вороженки", "вороженьки");
        put("своїі", "своїй");
        put("козацкого", "козацького");
        put("доленка", "доленька");
        put("пісн", "піснь");
        put("відобється", "відоб'ється");
        put("згомонит", "згомонить");
        put("отоіді", "отойді");
        put("кровю", "кров'ю");
        put("семі", "сем'ї");
        put("волній", "вольній");
        put("забудте", "забудьте");
        put("помянути", "пом'янути");
        put("ділос", "ділось");
        put("взялос", "взялось");
        put("урен", "урень");
        put("завяло", "зав'яло");
        put("Попливут", "Попливуть");
        put("синому", "синьому");
        put("погулят", "погулять");
        put("сіят", "сіять");
        put("розмовлят", "розмовлять");
        put("піват", "півать");
        put("дітис", "дітись");
        put("Єст", "Єсть");
        put("Заховат", "Заховать");
        put("небудь їі", "небудь її");
        put("їі", "їй");
        put("слози", "сльози");
        put("колис", "колись");
        put("небуд", "небудь");
        put("покидат", "покидать");
        put("згадат", "згадать");
        put("рожевій квіти", "рожевії квіти");
        put("взят", "взять");
        put("діт", "діть");
        put("Летіт", "Летіть");
        put("даст", "дасть");
        put("пустят", "пустять");
        put("писменні", "письменні");
        put("навіт", "навіть");
        put("гудят", "гудять");
        put("сходит", "сходить");
        put("світит", "світить");
        put("слухат", "слухать");
        put("скажут", "скажуть");
        put("Поглузуют", "Поглузують");
        put("покепкуют", "покепкують");
        put("кинут", "кинуть");
        put("спочивают", "спочивають");
        put("батко", "батько");
        put("гетмани", "гетьмани");
        put("якогос", "якогось");
        put("гетманства", "гетьманства");
        put("Білш", "Більш");
        put("осталос", "осталось");
        put("розривают", "розривають");
        put("співают", "співають");
        put("Матрошу", "Матрьошу");
        put("радост", "радость");
        put("ілко", "ілько");
        put("кричіт", "кричіть");
        put("моіі", "моїй");
        put("розвернулас", "розвернулась");
        put("Вигравают", "Вигравають");
        put("евут", "евуть");
        put("стогнут", "стогнуть");
        put("розсердилис", "розсердились");
        put("Щос", "Щось");
        put("Виступают", "Виступають");
        put("озмовляют", "озмовляють");
        put("розказуют", "розказують");
        put("люлки", "люльки");
        put("Полщі", "Польщі");
        put("оддирают", "оддирають");
        put("Кухол", "Кухоль");
        put("ходит", "ходить");
        put("Взявшис", "Взявшись");
        put("Походжают", "Походжають");
        put("Велможна", "Вельможна");
        put("дивлюс", "дивлюсь");
        put("слозами", "сльозами");
        put("жит", "жить");
        put("Тихесенко", "Тихесенько");
        put("гомонит", "гомонить");
        put("шумит", "шумить");
        put("болит", "болить");
        put("робит", "робить");
        put("заплачут", "заплачуть");
        put("білше", "більше");
        put("слозу", "сльозу");
        put("Маріне", "Мар’їне");
        put("карій", "карії");
        put("чорній", "чорнії");
        put("сміялос", "сміялось");
        put("темній", "темнії");
        put("хотілос", "хотілось");
        put("Козацку", "Козацьку");
        put("Збират", "Збирать");
        put("козацкій", "козацькії");
        put("В Україні витают", "В Украйні витають");
        put("минулас", "минулась");
        put("родилас", "родилась");
        put("Козацкая", "Козацькая");
        put("спочит", "спочить");
        put("діялос", "діялось");
        put("плакат", "плакать");
        put("сльози за Україну", "сльози за Украйну");
        put("живут", "живуть");
        put("серденко", "серденько");
        put("побачат", "побачать");
        put("втираїте", "втирайте");
        put("поливают", "поливають");
        put("засиплют", "засиплють");
        put("ненко", "ненько");
        put("Гетте", "Гетьте");
        put("Проминут", "Проминуть");
        put("молодій", "молодії");
        put("кріз", "крізь");
        put("сміятис", "сміятись");
        put("Без надій", "Без надії");
        put("сподіватис", "сподіватись");
        put("Гет", "Геть");
        put("лит", "лить");
        put("лодовая", "льодовая");
        put("зійдут", "зійдуть");
        put("кремяную", "крем'яную");
        put("камін", "камінь");
        put("підіймат", "підіймать");
        put("хвилку", "хвильку");
        put("серденку", "серденьку");
        put("тма", "тьма");
        put("нудга", "нудьга");
        put("мерт", "мерть");
        put("важенко", "важенько");
        put("забється", "заб'ється");
        put("велми", "вельми");
        put("промін", "промінь");
    } };

    private static final Set<String> PUNCTUATIONS = new HashSet<String>(Arrays.asList(
        ",", "-", "!", "?", ":", ";", ".", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "…", "—", "“", "”",
        "«", "»", "[", "]", "–"));

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
    }
    private static final Map<String, List<ConvertCase>> LAT_TO_CYR_FIRST;
    private static final Map<String, List<ConvertCase>> LAT_TO_CYR_MIDDLE;

    static {
        LAT_TO_CYR_FIRST = new HashMap<String, List<ConvertCase>>();
        for (final Map.Entry<String, String> convert : CYR_TO_LAT_FIRST.entrySet()) {
            putMyObject(LAT_TO_CYR_FIRST, convert.getValue(), new ConvertCase(convert, false));
            putMyObject(LAT_TO_CYR_FIRST, convert.getValue().toUpperCase(), new ConvertCase(convert, false));
            putMyObject(LAT_TO_CYR_FIRST, convert.getValue().toLowerCase(), new ConvertCase(convert, true));
        }
        LAT_TO_CYR_MIDDLE = new HashMap<String, List<ConvertCase>>();
        for (final Map.Entry<String, String> convert : CYR_TO_LAT_MIDDLE.entrySet()) {
            putMyObject(LAT_TO_CYR_MIDDLE, convert.getValue(), new ConvertCase(convert, false));
            putMyObject(LAT_TO_CYR_MIDDLE, convert.getValue().toUpperCase(), new ConvertCase(convert, false));
            putMyObject(LAT_TO_CYR_MIDDLE, convert.getValue().toLowerCase(), new ConvertCase(convert, true));
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
            final String curChar3 = name.substring(index, Math.min(index + INDEX_3, name.length()));
            final String curChar4 = name.substring(index, Math.min(index + INDEX_4, name.length()));
            if (LAT_TO_CYR_FIRST.get(curChar4) == null && LAT_TO_CYR_FIRST.get(curChar3) == null
                && LAT_TO_CYR_FIRST.get(curChar2) == null && LAT_TO_CYR_FIRST.get(curChar) == null) {
                if (" ".equals(curChar)) {
                    prevConvertCase = null;
                    result.append(' ');
                } else if (curChar.matches("\\n") || PUNCTUATIONS.contains(curChar)) {
                    prevPrevConvertCase = null;
                    result.append(curChar);
                }
                continue;
            }
            List<ConvertCase> convertCase;
            if (prevConvertCase == null) {
                convertCase = LAT_TO_CYR_FIRST.get(curChar4);
                if (convertCase == null) {
                    convertCase = LAT_TO_CYR_FIRST.get(curChar3);
                    if (convertCase == null) {
                        convertCase = LAT_TO_CYR_FIRST.get(curChar2);
                        if (convertCase == null) {
                            convertCase = LAT_TO_CYR_FIRST.get(curChar);
                        } else {
                            index += INDEX_1;
                        }
                    } else {
                        index += INDEX_2;
                    }
                } else {
                    index += INDEX_3;
                }
                checkChar(result, convertCase, prevPrevConvertCase, prevConvertCase, MIDDLE_TIPS);
            } else {
                convertCase = LAT_TO_CYR_MIDDLE.get(curChar4);
                if (convertCase == null) {
                    convertCase = LAT_TO_CYR_MIDDLE.get(curChar3);
                    if (convertCase == null) {
                        convertCase = LAT_TO_CYR_MIDDLE.get(curChar2);
                        if (convertCase == null) {
                            convertCase = LAT_TO_CYR_MIDDLE.get(curChar);
                        } else {
                            index += INDEX_1;
                        }
                    } else {
                        index += INDEX_2;
                    }
                } else {
                    index += INDEX_3;
                }
                if (index >= name.length() || name.substring(index + INDEX_1, index + INDEX_2).equals(" ")
                    || name.substring(index + INDEX_1, index + INDEX_2).matches("\\n")
                    || PUNCTUATIONS.contains(name.substring(index + INDEX_1, index + INDEX_2))) {
                    checkChar(result, convertCase, prevPrevConvertCase, prevConvertCase, END_TIPS);
                    checkWordsWithComma(result, WORDS_WITH_COMMA);
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
     * @param result result buffer to store string in cyrilic
     * @param convertCase current character object
     * @param prevPrevConvertCase prev prev character object
     * @param prevConvertCase prev character object
     * @param tips convert tips
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

    /**
     * Converts character from latinic to cyrilic.
     * @param result result buffer to store string in cyrilic
     * @param wordsWithComma words to replace in buffer
     */
    private static void checkWordsWithComma(StringBuilder result, Map<String, String> wordsWithComma) {
        for (final Map.Entry<String, String> word : wordsWithComma.entrySet()) {
            final String key = word.getKey();
            if (key.length() <= result.length() && result.substring(result.length() - key.length(),
                result.length()).equalsIgnoreCase(key)) {
                result.replace(result.length() - key.length(), result.length(), word.getValue());
                break;
            }
        }
    }

    public static void main(String[] args) {
        final String message = "The utility class to convert latin words to the ukrainian characters.\n\n"
            + "For docs, license, tests, and downloads, see: https://github.com/javadev/latintoukrainian";
        System.out.println(message);
    }
}
