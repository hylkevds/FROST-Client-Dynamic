/*
 * Copyright (C) 2023 Fraunhofer Institut IOSB, Fraunhoferstr. 1, D 76131
 * Karlsruhe, Germany.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package de.fraunhofer.iosb.ilt.frostclient.utils;

import de.fraunhofer.iosb.ilt.frostclient.model.Id;
import de.fraunhofer.iosb.ilt.frostclient.model.IdLong;
import de.fraunhofer.iosb.ilt.frostclient.model.IdString;
import de.fraunhofer.iosb.ilt.frostclient.model.IdUuid;
import de.fraunhofer.iosb.ilt.frostclient.model.property.type.TypeSimple;
import java.util.UUID;

public class ParserUtils {

    private ParserUtils() {
        // Utility class.
    }

    public static Id idFromObject(Object input) {
        if (input instanceof Id id) {
            return id;
        }
        if (input instanceof UUID uuid) {
            return new IdUuid(uuid);
        }
        if (input instanceof Number number) {
            return new IdLong(number.longValue());
        }
        if (input instanceof CharSequence) {
            return new IdString(input.toString());
        }
        throw new IllegalArgumentException("Can not use " + ((input == null) ? "null" : input.getClass().getName()) + " (" + input + ") as an Id");
    }

    public static final TypeSimple.Parser PARSER_LONG = Long::parseLong;
    public static final TypeSimple.Parser PARSER_UUID = input -> {
        if (input.startsWith("'")) {
            return UUID.fromString(input.substring(1, input.length() - 1));
        }
        return UUID.fromString(input);
    };
    public static final TypeSimple.Parser PARSER_STRING = input -> {
        if (input.startsWith("'")) {
            String idString = input.substring(1, input.length() - 1);
            return idString.replace("''", "'");
        }
        return input;
    };

}
