# latintoukrainian

[![Maven Central](https://img.shields.io/maven-central/v/com.github.javadev/latintoukrainian.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.github.javadev%22%20AND%20a%3A%22latintoukrainian%22)
[![Java CI](https://github.com/javadev/latintoukrainian/actions/workflows/maven.yml/badge.svg?branch=master)](https://github.com/javadev/latintoukrainian/actions/workflows/maven.yml)
[![Coverage Status](https://coveralls.io/repos/javadev/latintoukrainian/badge.svg?branch=master)](https://coveralls.io/r/javadev/latintoukrainian)

The utility class to convert latin words to ukrainian characters

| Український алфавіт | Латиниця | Позиція у слові | Приклади написання (українською мовою) | Приклади написання (латиницею) |
|----------------------|-----------|------------------|-----------------------------------------|-------------------------------|
| Аа                   | Aa        |                  | Алушта Андрій                           | Alushta Andrii                |
| Бб                   | Bb        |                  | Борщагівка Борисенко                   | Borshchahivkа Borysenko       |
| Вв                   | Vv        |                  | Вінниця Володимир                       | Vinnytsia Volodymyr           |
| Гг                   | Hh        |                  | Гадяч Богдан Згурський                 | Hadiach Bohdan Zghurskyi      |
| Ґґ                   | Gg        |                  | Ґалаґан Ґорґани                        | Galagan Gorgany               |
| Дд                   | Dd        |                  | Донецьк Дмитро                          | Donetsk Dmytro                |
| Ее                   | Ee        |                  | Рівне Олег Есман                       | Rivne Oleh Esman              |
| Єє                   | Ye/ie     | на початку слова в інших позиціях | Єнакієве Гаєвич Короп’є             | Yenakiieve Haievych Koropie   |
| Жж                   | Zh/zh     |                  | Житомир Жанна Жежелів                  | Zhytomyr Zhanna Zhezheliv     |
| Зз                   | Zz        |                  | Закарпаття Казимирчук                  | Zakarpattia Kazymyrchuk       |
| Ии                   | Yy        |                  | Медвин Михайленко                       | Medvyn Mykhailenko            |
| Іі                   | Ii        |                  | Іванків Іващенко                        | Ivankiv Ivashchenko           |
| Її                   | Yi/i      | на початку слова в інших позиціях | Їжакевич Кадиївка Мар’їне           | Yizhakevych Kadyivka Marine   |
| Йй                   | Y/i       | на початку слова в інших позиціях | Йосипівка Стрий Олексій              | Yosypivka Stryi Oleksii       |
| Кк                   | Kk        |                  | Київ Коваленко                         | Kyiv Kovalenko                |
| Лл                   | Ll        |                  | Лебедин Леонід                          | Lebedyn Leonid                |
| Мм                   | Mm        |                  | Миколаїв Маринич                        | Mykolaiv Marynych             |
| Нн                   | Nn        |                  | Ніжин Наталія                           | Nizhyn Nataliіa               |
| Оо                   | Oo        |                  | Одеса Онищенко                          | Odesa Onyshchenko            |
| Пп                   | Pp        |                  | Полтава Петро                           | Poltava Petro                 |
| Рр                   | Rr        |                  | Решетилівка Рибчинськй                 | Reshetylivka Rybchynskyi      |
| Сс                   | Ss        |                  | Суми Соломія                            | Sumy Solomiia                 |
| Тт                   | Tt        |                  | Тернопіль Троць                        | Ternopil Trots                |
| Уу                   | Uu        |                  | Ужгород Уляна                           | Uzhhorod Uliana               |
| Фф                   | Ff        |                  | Фастів Філіпчук                         | Fastiv Filipchuk              |
| Хх                   | Kh/kh     |                  | Харків Христина                         | Kharkiv Khrystyna             |
| Цц                   | Ts/ts     |                  | Біла Церква Стеценко                    | Bila Tserkva Stetsenko        |
| Чч                   | Ch/ch     |                  | Чернівці Шевченко                       | Chernivtsi Shevchenko         |
| Шш                   | Sh/sh     |                  | Шостка Кишеньки                         | Shostka Kyshenky              |
| Щщ                   | Shch/shch |                  | Щербухи Гоща Гаращенко                 | Shcherbukhy Hoshcha Harashchenko |
| Юю                   | Yu/iu     | на початку слова в інших позиціях | Юрій Корюківка                        | Yurii Koriukivka              |
| Яя                   | Ya/ia     | на початку слова в інших позиціях | Яготин Ярошенко Костянтин Знам’янка Феодосія | Yahotyn Yaroshenko Kostiantyn Znamianka Feodosiia |

[![Screen short](https://raw.github.com/javadev/latintoukrainian/master/latintoukrainian.png)](https://github.com/javadev/latintoukrainian/)
[![Screen short](https://raw.github.com/javadev/latintoukrainian/master/latintoukrainian2.png)](https://github.com/javadev/latintoukrainian/)
