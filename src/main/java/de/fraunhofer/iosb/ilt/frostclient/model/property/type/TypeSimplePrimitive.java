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
package de.fraunhofer.iosb.ilt.frostclient.model.property.type;

import static de.fraunhofer.iosb.ilt.frostclient.utils.ParserUtils.PARSER_LONG;
import static de.fraunhofer.iosb.ilt.frostclient.utils.ParserUtils.PARSER_STRING;
import static de.fraunhofer.iosb.ilt.frostclient.utils.ParserUtils.PARSER_UUID;
import static de.fraunhofer.iosb.ilt.frostclient.utils.TypeReferencesHelper.TYPE_REFERENCE_BIGDECIMAL;
import static de.fraunhofer.iosb.ilt.frostclient.utils.TypeReferencesHelper.TYPE_REFERENCE_BOOLEAN;
import static de.fraunhofer.iosb.ilt.frostclient.utils.TypeReferencesHelper.TYPE_REFERENCE_DATE;
import static de.fraunhofer.iosb.ilt.frostclient.utils.TypeReferencesHelper.TYPE_REFERENCE_DURATION;
import static de.fraunhofer.iosb.ilt.frostclient.utils.TypeReferencesHelper.TYPE_REFERENCE_GEOJSONOBJECT;
import static de.fraunhofer.iosb.ilt.frostclient.utils.TypeReferencesHelper.TYPE_REFERENCE_INTEGER;
import static de.fraunhofer.iosb.ilt.frostclient.utils.TypeReferencesHelper.TYPE_REFERENCE_LONG;
import static de.fraunhofer.iosb.ilt.frostclient.utils.TypeReferencesHelper.TYPE_REFERENCE_OBJECT;
import static de.fraunhofer.iosb.ilt.frostclient.utils.TypeReferencesHelper.TYPE_REFERENCE_STRING;
import static de.fraunhofer.iosb.ilt.frostclient.utils.TypeReferencesHelper.TYPE_REFERENCE_TIMEINSTANT;
import static de.fraunhofer.iosb.ilt.frostclient.utils.TypeReferencesHelper.TYPE_REFERENCE_UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import de.fraunhofer.iosb.ilt.frostclient.utils.Constants;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The various Primitive types defined by OData.
 */
public class TypeSimplePrimitive extends TypeSimple {

    public static final String EDM_BINARY_NAME = "Edm.Binary";

    public static final String EDM_BOOLEAN_NAME = "Edm.Boolean";
    public static final String EDM_BYTE_NAME = "Edm.Byte";
    public static final String EDM_DATE_NAME = "Edm.Date";
    public static final String EDM_DATETIMEOFFSET_NAME = "Edm.DateTimeOffset";
    public static final String EDM_DECIMAL_NAME = "Edm.Decimal";
    public static final String EDM_DOUBLE_NAME = "Edm.Double";
    public static final String EDM_DURATION_NAME = "Edm.Duration";
    public static final String EDM_GUID_NAME = "Edm.Guid";
    public static final String EDM_INT16_NAME = "Edm.Int16";
    public static final String EDM_INT32_NAME = "Edm.Int32";
    public static final String EDM_INT64_NAME = "Edm.Int64";
    public static final String EDM_SBYTE_NAME = "Edm.SByte";
    public static final String EDM_SINGLE_NAME = "Edm.Single";
    public static final String EDM_STREAM_NAME = "Edm.Stream";
    public static final String EDM_STRING_NAME = "Edm.String";
    public static final String EDM_TIMEOFDAY_NAME = "Edm.TimeOfDay";
    public static final String EDM_GEOGRAPHY_NAME = "Edm.Geography";
    public static final String EDM_GEOGRAPHYPOINT_NAME = "Edm.GeographyPoint";
    public static final String EDM_GEOGRAPHYLINESTRING_NAME = "Edm.GeographyLineString";
    public static final String EDM_GEOGRAPHYPOLYGON_NAME = "Edm.GeographyPolygon";
    public static final String EDM_GEOGRAPHYMULTIPOINT_NAME = "Edm.GeographyMultiPoint";
    public static final String EDM_GEOGRAPHYMULTILINESTRING_NAME = "Edm.GeographyMultiLineString";
    public static final String EDM_GEOGRAPHYMULTIPOLYGON_NAME = "Edm.GeographyMultiPolygon";
    public static final String EDM_GEOGRAPHYCOLLECTION_NAME = "Edm.GeographyCollection";
    public static final String EDM_GEOMETRY_NAME = "Edm.Geometry";
    public static final String EDM_GEOMETRYPOINT_NAME = "Edm.GeometryPoint";
    public static final String EDM_GEOMETRYLINESTRING_NAME = "Edm.GeometryLineString";
    public static final String EDM_GEOMETRYPOLYGON_NAME = "Edm.GeometryPolygon";
    public static final String EDM_GEOMETRYMULTIPOINT_NAME = "Edm.GeometryMultiPoint";
    public static final String EDM_GEOMETRYMULTILINESTRING_NAME = "Edm.GeometryMultiLineString";
    public static final String EDM_GEOMETRYMULTIPOLYGON_NAME = "Edm.GeometryMultiPolygon";
    public static final String EDM_GEOMETRYCOLLECTION_NAME = "Edm.GeometryCollection";
    public static final String EDM_UNTYPED_NAME = "Edm.Untyped";

    public static final TypeSimplePrimitive EDM_BINARY = new TypeSimplePrimitive(EDM_BINARY_NAME, "Binary data", TYPE_REFERENCE_STRING);
    public static final TypeSimplePrimitive EDM_BOOLEAN = new TypeSimplePrimitive(EDM_BOOLEAN_NAME, "Binary-valued logic", TYPE_REFERENCE_BOOLEAN);
    public static final TypeSimplePrimitive EDM_BYTE = new TypeSimplePrimitive(EDM_BYTE_NAME, "Unsigned 8-bit integer", TYPE_REFERENCE_INTEGER);
    public static final TypeSimplePrimitive EDM_DATE = new TypeSimplePrimitive(EDM_DATE_NAME, "Date without a time-zone offset", TYPE_REFERENCE_DATE);
    public static final TypeSimplePrimitive EDM_DATETIMEOFFSET = new TypeSimplePrimitive(EDM_DATETIMEOFFSET_NAME, "Date and time with a time-zone offset, no leap seconds", TYPE_REFERENCE_TIMEINSTANT);
    public static final TypeSimplePrimitive EDM_DECIMAL = new TypeSimplePrimitive(EDM_DECIMAL_NAME, "Numeric values with decimal representation", TYPE_REFERENCE_BIGDECIMAL);
    public static final TypeSimplePrimitive EDM_DOUBLE = new TypeSimplePrimitive(EDM_DOUBLE_NAME, "IEEE 754 binary64 floating-point number (15-17 decimal digits)", TYPE_REFERENCE_BIGDECIMAL);
    public static final TypeSimplePrimitive EDM_DURATION = new TypeSimplePrimitive(EDM_DURATION_NAME, "Signed duration in days, hours, minutes, and (sub)seconds", TYPE_REFERENCE_DURATION);
    public static final TypeSimplePrimitive EDM_GUID = new TypeSimplePrimitive(EDM_GUID_NAME, "16-byte (128-bit) unique identifier", PARSER_UUID, TYPE_REFERENCE_UUID);
    public static final TypeSimplePrimitive EDM_INT16 = new TypeSimplePrimitive(EDM_INT16_NAME, "Signed 16-bit integer", PARSER_LONG, TYPE_REFERENCE_INTEGER);
    public static final TypeSimplePrimitive EDM_INT32 = new TypeSimplePrimitive(EDM_INT32_NAME, "Signed 32-bit integer", PARSER_LONG, TYPE_REFERENCE_INTEGER);
    public static final TypeSimplePrimitive EDM_INT64 = new TypeSimplePrimitive(EDM_INT64_NAME, "Signed 64-bit integer", PARSER_LONG, TYPE_REFERENCE_LONG);
    public static final TypeSimplePrimitive EDM_SBYTE = new TypeSimplePrimitive(EDM_SBYTE_NAME, "Signed 8-bit integer", PARSER_LONG, TYPE_REFERENCE_INTEGER);
    public static final TypeSimplePrimitive EDM_SINGLE = new TypeSimplePrimitive(EDM_SINGLE_NAME, "IEEE 754 binary32 floating-point number (6-9 decimal digits)", TYPE_REFERENCE_BIGDECIMAL);
    public static final TypeSimplePrimitive EDM_STREAM = new TypeSimplePrimitive(EDM_STREAM_NAME, "Binary data stream", TYPE_REFERENCE_STRING);
    public static final TypeSimplePrimitive EDM_STRING = new TypeSimplePrimitive(EDM_STRING_NAME, "Sequence of characters", PARSER_STRING, TYPE_REFERENCE_STRING);
    public static final TypeSimplePrimitive EDM_TIMEOFDAY = new TypeSimplePrimitive(EDM_TIMEOFDAY_NAME, "Clock time 00:00-23:59:59.999999999999", TYPE_REFERENCE_DATE);
    public static final TypeSimplePrimitive EDM_GEOGRAPHY = new TypeSimplePrimitive(EDM_GEOGRAPHY_NAME, "Abstract base type for all Geography types", TYPE_REFERENCE_OBJECT);
    public static final TypeSimplePrimitive EDM_GEOGRAPHYPOINT = new TypeSimplePrimitive(EDM_GEOGRAPHYPOINT_NAME, "A point in a round-earth coordinate system", TYPE_REFERENCE_OBJECT);
    public static final TypeSimplePrimitive EDM_GEOGRAPHYLINESTRING = new TypeSimplePrimitive(EDM_GEOGRAPHYLINESTRING_NAME, "Line string in a round-earth coordinate system", TYPE_REFERENCE_OBJECT);
    public static final TypeSimplePrimitive EDM_GEOGRAPHYPOLYGON = new TypeSimplePrimitive(EDM_GEOGRAPHYPOLYGON_NAME, "Polygon in a round-earth coordinate system", TYPE_REFERENCE_OBJECT);
    public static final TypeSimplePrimitive EDM_GEOGRAPHYMULTIPOINT = new TypeSimplePrimitive(EDM_GEOGRAPHYMULTIPOINT_NAME, "Collection of points in a round-earth coordinate system", TYPE_REFERENCE_OBJECT);
    public static final TypeSimplePrimitive EDM_GEOGRAPHYMULTILINESTRING = new TypeSimplePrimitive(EDM_GEOGRAPHYMULTILINESTRING_NAME, "Collection of line strings in a round-earth coordinate system", TYPE_REFERENCE_OBJECT);
    public static final TypeSimplePrimitive EDM_GEOGRAPHYMULTIPOLYGON = new TypeSimplePrimitive(EDM_GEOGRAPHYMULTIPOLYGON_NAME, "Collection of polygons in a round-earth coordinate system", TYPE_REFERENCE_OBJECT);
    public static final TypeSimplePrimitive EDM_GEOGRAPHYCOLLECTION = new TypeSimplePrimitive(EDM_GEOGRAPHYCOLLECTION_NAME, "Collection of arbitrary Geography values", TYPE_REFERENCE_OBJECT);
    public static final TypeSimplePrimitive EDM_GEOMETRY = new TypeSimplePrimitive(EDM_GEOMETRY_NAME, "Abstract base type for all Geometry types", TYPE_REFERENCE_GEOJSONOBJECT, TYPE_REFERENCE_STRING);
    public static final TypeSimplePrimitive EDM_GEOMETRYPOINT = new TypeSimplePrimitive(EDM_GEOMETRYPOINT_NAME, "Point in a flat-earth coordinate system", TYPE_REFERENCE_OBJECT);
    public static final TypeSimplePrimitive EDM_GEOMETRYLINESTRING = new TypeSimplePrimitive(EDM_GEOMETRYLINESTRING_NAME, "Line string in a flat-earth coordinate system", TYPE_REFERENCE_OBJECT);
    public static final TypeSimplePrimitive EDM_GEOMETRYPOLYGON = new TypeSimplePrimitive(EDM_GEOMETRYPOLYGON_NAME, "Polygon in a flat-earth coordinate system", TYPE_REFERENCE_OBJECT);
    public static final TypeSimplePrimitive EDM_GEOMETRYMULTIPOINT = new TypeSimplePrimitive(EDM_GEOMETRYMULTIPOINT_NAME, "Collection of points in a flat-earth coordinate system", TYPE_REFERENCE_OBJECT);
    public static final TypeSimplePrimitive EDM_GEOMETRYMULTILINESTRING = new TypeSimplePrimitive(EDM_GEOMETRYMULTILINESTRING_NAME, "Collection of line strings in a flat-earth coordinate system", TYPE_REFERENCE_OBJECT);
    public static final TypeSimplePrimitive EDM_GEOMETRYMULTIPOLYGON = new TypeSimplePrimitive(EDM_GEOMETRYMULTIPOLYGON_NAME, "Collection of polygons in a flat-earth coordinate system", TYPE_REFERENCE_OBJECT);
    public static final TypeSimplePrimitive EDM_GEOMETRYCOLLECTION = new TypeSimplePrimitive(EDM_GEOMETRYCOLLECTION_NAME, "Collection of arbitrary Geometry values", TYPE_REFERENCE_OBJECT);
    public static final TypeSimplePrimitive EDM_UNTYPED = new TypeSimplePrimitive(EDM_UNTYPED_NAME, "Can be any valid JSON.", TYPE_REFERENCE_OBJECT);

    public static final TypeSimplePrimitive STA_ID_LONG = EDM_INT64;
    public static final TypeSimplePrimitive STA_ID_STRING = EDM_STRING;
    public static final TypeSimplePrimitive STA_ID_UUID = EDM_GUID;

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeSimplePrimitive.class.getName());
    private static final Map<String, TypeSimplePrimitive> TYPES = new HashMap<>();

    static {
        TYPES.put(Constants.VALUE_ID_TYPE_LONG, STA_ID_LONG);
        TYPES.put(Constants.VALUE_ID_TYPE_STRING, STA_ID_STRING);
        TYPES.put(Constants.VALUE_ID_TYPE_UUID, STA_ID_UUID);
        TYPES.put("TimeInstant", EDM_DATETIMEOFFSET);
        for (Field field : FieldUtils.getAllFields(TypeSimplePrimitive.class)) {
            if (!Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            try {
                final TypeSimplePrimitive primitive = (TypeSimplePrimitive) FieldUtils.readStaticField(field, false);
                final String name = primitive.getName();
                TYPES.put(name, primitive);
                LOGGER.debug("Registered type: {}", name);
            } catch (IllegalArgumentException ex) {
                LOGGER.error("Failed to initialise: {}", field, ex);
            } catch (IllegalAccessException ex) {
                LOGGER.trace("Failed to initialise: {}", field, ex);
            } catch (ClassCastException ex) {
                // It's not a TypeSimplePrimitive
            }
        }
    }

    public static TypeSimplePrimitive getType(String name) {
        return TYPES.get(name);
    }

    private TypeSimplePrimitive(String name, String description, TypeReference... typeReference) {
        super(name, description, typeReference);
    }

    private TypeSimplePrimitive(String name, String description, Parser parser, TypeReference... typeReference) {
        super(name, description, parser, typeReference);
    }

}
