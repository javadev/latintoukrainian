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
/**
* LatinToUkrainian utility class.
*
* @author Valentyn Kolesnikov
* @version $Revision$ $Date$
*/
var StringBuilder = (function () {
    function StringBuilder() {
        this._buffer = [];
    }
    StringBuilder.prototype.append = function (text) {
        this._buffer[this._buffer.length] = text;
        return this;
    };

    StringBuilder.prototype.insert = function (index, text) {
        this._buffer.splice(index, 0, text);
        return this;
    };

    StringBuilder.prototype.toString = function () {
        return this._buffer.join("");
    };
    return StringBuilder;
})();

var ConvertCase = (function () {
    function ConvertCase(convert, lowcase) {
        this.convert = convert;
        this.lowcase = lowcase;
    }
    return ConvertCase;
})();

var LatinToUkrainian = (function () {
    function LatinToUkrainian() {
    }
    /**
    * Generates latinic from cyrilic.
    * @param name the name
    * @return the result
    */
    LatinToUkrainian.generateUkr = function (name) {
        this.initialize();
        var result = new StringBuilder();
        var prevConvertCase = null;
        for (var index = 0; index < name.length; index += 1) {
            var curChar = name.substring(index, index + LatinToUkrainian.INDEX_1);
            var nextChar = index == name.length - 1 ? null : name.substring(index + LatinToUkrainian.INDEX_1, index + LatinToUkrainian.INDEX_2);
            if (curChar.match("[-'’,]")) {
                continue;
            }
            if (this.cyrToLat[curChar] == null) {
                if (" " == curChar) {
                    prevConvertCase = null;
                    result.append(' ');
                }
                continue;
            }
            var convertCase = this.cyrToLat[curChar];
            if (prevConvertCase == null) {
                this.checkFirstChar(result, convertCase, this.cyrToLat[nextChar] == null ? convertCase : this.cyrToLat[nextChar]);
            } else {
                this.checkMiddleChar(result, convertCase, this.cyrToLat[nextChar] == null ? convertCase : this.cyrToLat[nextChar]);
            }
            prevConvertCase = convertCase;
        }
        return result.toString();
    };
    LatinToUkrainian.checkFirstChar = function (result, convertCase, nextConvertCase) {
        var latName = convertCase.convert;
        switch (latName.length) {
            case LatinToUkrainian.LENGTH_2:
                result.append(convertCase.lowcase ? latName.substring(LatinToUkrainian.INDEX_0, LatinToUkrainian.INDEX_1).toLowerCase() : nextConvertCase.lowcase ? latName.substring(LatinToUkrainian.INDEX_0, LatinToUkrainian.INDEX_1) : latName.substring(LatinToUkrainian.INDEX_0, LatinToUkrainian.INDEX_1).toUpperCase());
                if (convertCase.convert == "ZZ" && nextConvertCase.convert == "HH") {
                    result.append(nextConvertCase.lowcase ? "g" : "G");
                }
                break;
            case LatinToUkrainian.LENGTH_3:
            case LatinToUkrainian.LENGTH_4:
                result.append(convertCase.lowcase ? latName.substring(LatinToUkrainian.INDEX_0, LatinToUkrainian.INDEX_2).toLowerCase() : nextConvertCase.lowcase ? latName.substring(LatinToUkrainian.INDEX_0, LatinToUkrainian.INDEX_2) : latName.substring(LatinToUkrainian.INDEX_0, LatinToUkrainian.INDEX_2).toUpperCase());
                break;
            case LatinToUkrainian.LENGTH_8:
                result.append(convertCase.lowcase ? latName.substring(LatinToUkrainian.INDEX_0, LatinToUkrainian.INDEX_4).toLowerCase() : nextConvertCase.lowcase ? latName.substring(LatinToUkrainian.INDEX_0, LatinToUkrainian.INDEX_4) : latName.substring(LatinToUkrainian.INDEX_0, LatinToUkrainian.INDEX_4).toUpperCase());
                break;
            default:
                break;
        }
    };

    /**
    * @param result the output string
    * @param convertCase the current char
    * @param nextConvertCase the next char
    */
    LatinToUkrainian.checkMiddleChar = function (result, convertCase, nextConvertCase) {
        var latName = convertCase.convert;
        switch (latName.length) {
            case LatinToUkrainian.LENGTH_2:
                result.append(convertCase.lowcase ? latName.substring(LatinToUkrainian.INDEX_1, LatinToUkrainian.INDEX_2).toLowerCase() : nextConvertCase.lowcase ? latName.substring(LatinToUkrainian.INDEX_1, LatinToUkrainian.INDEX_2) : latName.substring(LatinToUkrainian.INDEX_1, LatinToUkrainian.INDEX_2).toUpperCase());
                if (convertCase.convert == "ZZ" && nextConvertCase.convert == "HH") {
                    result.append(nextConvertCase.lowcase ? "g" : "G");
                }
                break;
            case LatinToUkrainian.LENGTH_3:
                result.append(convertCase.lowcase ? latName.substring(LatinToUkrainian.INDEX_2, LatinToUkrainian.INDEX_3).toLowerCase() : nextConvertCase.lowcase ? latName.substring(LatinToUkrainian.INDEX_2, LatinToUkrainian.INDEX_3) : latName.substring(LatinToUkrainian.INDEX_2, LatinToUkrainian.INDEX_3).toUpperCase());
                break;
            case LatinToUkrainian.LENGTH_4:
                result.append(convertCase.lowcase ? latName.substring(LatinToUkrainian.INDEX_2, LatinToUkrainian.INDEX_4).toLowerCase() : nextConvertCase.lowcase ? latName.substring(LatinToUkrainian.INDEX_2, LatinToUkrainian.INDEX_4) : latName.substring(LatinToUkrainian.INDEX_2, LatinToUkrainian.INDEX_4).toUpperCase());
                break;
            case LatinToUkrainian.LENGTH_8:
                result.append(convertCase.lowcase ? latName.substring(LatinToUkrainian.INDEX_4, LatinToUkrainian.INDEX_8).toLowerCase() : nextConvertCase.lowcase ? latName.substring(LatinToUkrainian.INDEX_4, LatinToUkrainian.INDEX_8) : latName.substring(LatinToUkrainian.INDEX_4, LatinToUkrainian.INDEX_8).toUpperCase());
                break;
            default:
                break;
        }
    };
    LatinToUkrainian.initialize = function () {
        this.cyrToLat = {};
        for (var convert in LatinToUkrainian.Convert) {
            this.cyrToLat[LatinToUkrainian.Convert[convert].substring(LatinToUkrainian.INDEX_0, LatinToUkrainian.INDEX_1)] = new ConvertCase(convert, false);
            this.cyrToLat[LatinToUkrainian.Convert[convert].substring(LatinToUkrainian.INDEX_1, LatinToUkrainian.INDEX_2)] = new ConvertCase(convert, true);
            if (convert == LatinToUkrainian.Convert.EE) {
                this.cyrToLat["Ё"] = new ConvertCase(convert, false);
                this.cyrToLat["ё"] = new ConvertCase(convert, true);
            }
        }
    };
    LatinToUkrainian.INDEX_0 = 0;
    LatinToUkrainian.INDEX_1 = 1;
    LatinToUkrainian.INDEX_2 = 2;
    LatinToUkrainian.INDEX_3 = 3;
    LatinToUkrainian.INDEX_4 = 4;
    LatinToUkrainian.INDEX_8 = 8;
    LatinToUkrainian.LENGTH_2 = 2;
    LatinToUkrainian.LENGTH_3 = 3;
    LatinToUkrainian.LENGTH_4 = 4;
    LatinToUkrainian.LENGTH_8 = 8;
    LatinToUkrainian.cyrToLat = {};

    LatinToUkrainian.CYR_TO_LAT_FIRST = {
        "А": "A",
        "Б": "B",
        "В": "V",
        "Г": "H",
        "Ґ": "G",
        "Д": "D",
        "Е": "E",
        "Є": "Ye",
        "Ж": "Zh",
        "Зг": "Zgh",
        "З": "Z",
        "Й": "Y",
        "И": "Y",
        "І": "I",
        "Ї": "Yi",
        "К": "K",
        "Л": "L",
        "М": "M",
        "Н": "N",
        "О": "O",
        "П": "P",
        "Р": "R",
        "С": "S",
        "Т": "T",
        "У": "U",
        "Ф": "F",
        "Х": "Kh",
        "Ц": "Ts",
        "Ч": "Ch",
        "Ш": "Sh",
        "Щ": "Shch",
        "Ю": "Yu",
        "Я": "Ya"
    };

    LatinToUkrainian.CYR_TO_LAT_MIDDLE = {
        "А": "A",
        "Б": "B",
        "В": "V",
        "Г": "H",
        "Ґ": "G",
        "Д": "D",
        "Е": "E",
        "Є": "Ie",
        "Ж": "Zh",
        "Зг": "Zgh",
        "З": "Z",
        "И": "Y",
        "І": "I",
        "Ї": "I",
        "Й": "I",
        "К": "K",
        "Л": "L",
        "М": "M",
        "Н": "N",
        "О": "O",
        "П": "P",
        "Р": "R",
        "С": "S",
        "Т": "T",
        "У": "U",
        "Ф": "F",
        "Х": "Kh",
        "Ц": "Ts",
        "Ч": "Ch",
        "Ш": "Sh",
        "Щ": "Shch",
        "Ю": "Iu",
        "Я": "Ia"
    };

    LatinToUkrainian.PUNCTUATIONS = [
        ",", "-", "!", "?", ":", ";", ".", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "…", "—", "“", "”",
        "«", "»", "[", "]"];

    return LatinToUkrainian;
})();
