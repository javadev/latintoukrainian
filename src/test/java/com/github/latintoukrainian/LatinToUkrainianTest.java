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

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * LatinToUkrainianTest unit test.
 *
 * @author Valentyn Kolesnikov
 * @version $Revision$ $Date$
 */
public class LatinToUkrainianTest {
    /**
     * Checks string converter.
     */
    @Test public void generateUkr() {
        assertEquals("", LatinToUkrainian.generateUkr(""));
        new LatinToUkrainian();
    }

    private void equal(String actual, String expected) {
        assertEquals(expected, LatinToUkrainian.generateUkr(actual));
    }

    /**.*/
    @Test public void latinToUkrainian() {
//Аа Aа
        equal("Alushta Andrii", "Алушта Андрій");
//Бб Bb
        equal("Borshchahivka Borysenko", "Борщагівка Борисенко");
//Вв Vv
        equal("Vinnytsia Volodymyr", "Вінниця Володимир");
//Гг Hh
        equal("Hadiach Bohdan Zghurskyi", "Гадяч Богдан Згурський");
//Ґґ Gg
        equal("Galagan Gorgany", "Ґалаґан Ґорґани");
//Дд Dd
        equal("Donetsk Dmytro", "Донецьк Дмитро");
//Ее Eе
        equal("Rivne Oleh Esman", "Рівне Олег Есмань");
//Єє Ye ie на початку слова в інших позиціях
        equal("Yenakiieve Haievych Koropie", "Єнакієве Гаєвич Короп’є");
//Жж Zh zh
        equal("Zhytomyr Zhanna Zhezheliv", "Житомир Жанна Жежелів");
//Зз Zz
        equal("Zakarpattia Kazymyrchuk", "Закарпаття Казимирчук");
//Ии Yy
        equal("Medvyn Mykhailenko", "Медвин Михайленко");
//Іі Ii
        equal("Ivankiv Ivashchenko", "Іванків Іващенко");
//Її Yi i на початку слова в інших позиціях
        equal("Yizhakevych Kadyivka Marine", "Їжакевич Кадиївка Мар’їне");
//Йй Y i на початку слова в інших позиціях
        equal("Yosypivka Stryi Oleksii", "Йосипівка Стрий Олексій");
//Кк Kk
        equal("Kyiv Kovalenko", "Київ Коваленко");
//Лл Ll
        equal("Lebedyn Leonid", "Лебедин Леонід");
//Мм Mm
        equal("Mykolaiv Marynych", "Миколаїв Маринич");
//Нн Nn
        equal("Nizhyn Nataliia", "Ніжин Наталія");
//Оо Oo
        equal("Odesa Onyshchenko", "Одеса Онищенко");
//Пп Pp
        equal("Poltava Petro", "Полтава Петро");
//Рр Rr
        equal("Reshetylivka Rybchynskyi", "Решетилівка Рибчинський");
//Сс Ss
        equal("Sumy Solomiia", "Суми Соломія");
//Тт Tt
        equal("Ternopil Trots", "Тернопіль Троць");
//Уу Uu
        equal("Uzhhorod Uliana", "Ужгород Уляна");
//Фф Ff
        equal("Fastiv Filipchuk", "Фастів Філіпчук");
//Хх Kh kh
        equal("Kharkiv Khrystyna", "Харків Христина");
//Цц Ts ts
        equal("Bila Tserkva Stetsenko", "Біла Церква Стеценко");
//Чч Ch ch
        equal("Chernivtsi Shevchenko", "Чернівці Шевченко");
//Шш Sh sh
        equal("Shostka Kyshenky", "Шостка Кишеньки");
//Щщ Shch shch
        equal("Shcherbukhy Hoshcha Harashchenko", "Щербухи Гоща Гаращенко");
//Юю Yu іu на початку слова в інших позиціях
        equal("Yurii Koriukivka", "Юрій Корюківка");
//Яя Ya ia на початку слова в інших позиціях
        equal("Yahotyn Yaroshenko Kostiantyn Znamianka Feodosiia", "Яготин Ярошенко Костянтин Знам’янка Феодосія");
    }

    @Test
    public void punctuations() {
        equal("I - pokazhem, shcho my, brattia, kozatskoho rodu!\n", "І - покажем, що ми, браття, козацького роду!\n");
    }

    @Test
    public void words_with_iy_in_the_middle() {
        equal("spilkuiutsia", "спілкуються");
        equal("tvoikh", "твоїх");
    }

    @Test
    public void words_with_soft_sign() {
        equal("batky", "батьки");
        equal("yakii spilkuiutsia tvoi batky", "якій спілкуються твої батьки");
        equal("za nei; spetsialnymy zakhodamy", "за неї; спеціальними заходами");
    }

    @Test
    public void words_with_iy_at_the_end() {
        equal("Anatolii", "Анатолій");
        equal("Hennadii", "Геннадій");
        equal("Serhii", "Сергій");
        equal("yakii", "якій");
        equal("tvoi", "твої");
    }

    @Test
    public void words_with_numbers() {
        equal("25 dekabria 1845,", "25 декабря 1845,");
    }

    @Test
    public void words_with_capital_letters() {
        equal("YA", "Я");
        equal("NIANIA", "НЯНЯ");
        equal("niania", "няня");
        equal("Ya", "Я");
        equal("ya", "я");
    }

    @Test
    public void words_with_first_i() {
        equal("I vid sliz tykh hariachykh roztane", "І від сліз тих гарячих розтане");
    }

    @Test
    public void testMain() {
        LatinToUkrainian.main(new String[] {});
    }

    @Test
    public void text01() throws Exception {
        String text = readFile("text01.txt");
        String latinic = UkrainianToLatin.generateLat(text);
        String result = LatinToUkrainian.generateUkr(latinic);
        assertEquals(result, text);
    }

    @Test
    public void text02() throws Exception {
        String text = readFile("text02.txt");
        String latinic = UkrainianToLatin.generateLat(text);
        String result = LatinToUkrainian.generateUkr(latinic);
        assertEquals(result, text);
    }

    @Test
    public void text03() throws Exception {
        String text = readFile("text03.txt");
        String latinic = UkrainianToLatin.generateLat(text);
        String result = LatinToUkrainian.generateUkr(latinic);
        assertEquals(result, text);
    }

    @Test
    public void text04() throws Exception {
        String text = readFile("text04.txt");
        String latinic = UkrainianToLatin.generateLat(text);
        String result = LatinToUkrainian.generateUkr(latinic);
        assertEquals(result, text);
    }

    @Test
    public void text05() throws Exception {
        String text = readFile("text05.txt");
        String latinic = UkrainianToLatin.generateLat(text);
        String result = LatinToUkrainian.generateUkr(latinic);
        assertEquals(result, text);
    }

    @Test
    public void text06() throws Exception {
        String text = readFile("text06.txt");
        String latinic = UkrainianToLatin.generateLat(text);
        String result = LatinToUkrainian.generateUkr(latinic);
        assertEquals(result, text);
    }

    @Test
    public void text07() throws Exception {
        String text = readFile("text07.txt");
        String latinic = UkrainianToLatin.generateLat(text);
        String result = LatinToUkrainian.generateUkr(latinic);
        assertEquals(result, text);
    }

    @Test
    @Ignore
    public void text08() throws Exception {
        String text = readFile("text08.txt");
        String latinic = UkrainianToLatin.generateLat(text);
        String result = LatinToUkrainian.generateUkr(latinic);
        assertEquals(result, text);
    }

    private String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/com/github/latintoukrainian/" + fileName)),
                StandardCharsets.UTF_8);
    }
}
