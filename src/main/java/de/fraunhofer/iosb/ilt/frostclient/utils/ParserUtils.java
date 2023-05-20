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

import de.fraunhofer.iosb.ilt.frostclient.model.Entity;
import de.fraunhofer.iosb.ilt.frostclient.model.EntityType;
import de.fraunhofer.iosb.ilt.frostclient.model.property.NavigationProperty;
import de.fraunhofer.iosb.ilt.frostclient.model.property.type.TypeSimple;
import java.util.UUID;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;

public class ParserUtils {

    private ParserUtils() {
        // Utility class.
    }

    public static String entityPath(EntityType entityType, Object... primaryKeyValues) {
        return String.format("%s(%s)", entityType.plural, formatKeyValuesForUrl(primaryKeyValues));
    }

    /**
     * The local path to an entity or collection. e.g.:
     * <ul>
     * <li>Things(2)/Datastreams</li>
     * <li>Datastreams(5)/Thing</li>
     * </ul>
     *
     * @param parent The entity holding the relation, can be null.
     * @param relation The relation or collection to get.
     * @return The path to the entity collection.
     */
    public static String relationPath(Entity parent, NavigationProperty relation) {
        if (parent == null) {
            throw new IllegalArgumentException("Can't generate path for null entity.");
        }
        if (!parent.getEntityType().getNavigationProperties().contains(relation)) {
            throw new IllegalArgumentException("Entity of type " + parent.getEntityType() + " has no relation of type " + relation + ".");
        }

        return String.format("%s(%s)/%s", parent.getEntityType().plural, formatKeyValuesForUrl(parent.getPrimaryKeyValues()), relation.getName());
    }

    public static String formatKeyValuesForUrl(Entity entity) {
        return formatKeyValuesForUrl(entity.getPrimaryKeyValues());
    }

    public static String formatKeyValuesForUrl(Object... pkeyValues) {
        if (pkeyValues.length == 1) {
            if (pkeyValues[0] == null) {
                throw new IllegalArgumentException("Primary key value must be non-null");
            }
            return StringHelper.quoteForUrl(pkeyValues[0]);
        } else {
            throw new NotImplementedException("Multi-valued primary keys are not supported yet.");
        }
    }

    public static Object[] tryToParse(String input) {
        if (input.startsWith("'")) {
            return new Object[]{StringUtils.replace(input.substring(1, input.length() - 1), "''", "'")};
        }
        try {
            return new Object[]{Long.valueOf(input)};
        } catch (NumberFormatException exc) {
            // not a long.
        }
        return new Object[]{input};
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
