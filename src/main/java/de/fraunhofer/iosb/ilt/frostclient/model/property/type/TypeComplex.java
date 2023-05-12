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

import com.fasterxml.jackson.core.type.TypeReference;
import de.fraunhofer.iosb.ilt.frostclient.model.PropertyType;
import de.fraunhofer.iosb.ilt.frostclient.utils.TypeReferencesHelper;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The PropertyType of Complex Properties.
 */
public class TypeComplex extends PropertyType {

    public static final String STA_MAP_NAME = "Object";
    public static final String STA_OBJECT_NAME = "ANY";
    public static final String STA_TIMEINTERVAL_NAME = "TimeInterval";
    public static final String STA_TIMEVALUE_NAME = "TimeValue";
    public static final String KEY_INTERVAL_START = "start";
    public static final String KEY_INTERVAL_END = "end";

    public static final TypeComplex STA_MAP = new TypeComplex(STA_MAP_NAME, "A free object that can contain anything", TypeReferencesHelper.TYPE_REFERENCE_MAP, true);
    public static final TypeComplex STA_OBJECT = new TypeComplex(STA_OBJECT_NAME, "A free type, can be anything", TypeReferencesHelper.TYPE_REFERENCE_OBJECT, true);
    public static final TypeComplex STA_OBJECT_UNTYPED = new TypeComplex(STA_OBJECT_NAME, "A free type, can be anything", null, true);

    public static final TypeComplex STA_TIMEINTERVAL = new TypeComplex(STA_TIMEINTERVAL_NAME, "An ISO time interval.", TypeReferencesHelper.TYPE_REFERENCE_TIMEINTERVAL)
            .addProperty(KEY_INTERVAL_START, TypeSimplePrimitive.EDM_DATETIMEOFFSET, true)
            .addProperty(KEY_INTERVAL_END, TypeSimplePrimitive.EDM_DATETIMEOFFSET, true);
    public static final TypeComplex STA_TIMEVALUE = new TypeComplex(STA_TIMEVALUE_NAME, "An ISO time instant or time interval.", TypeReferencesHelper.TYPE_REFERENCE_TIMEVALUE)
            .addProperty(KEY_INTERVAL_START, TypeSimplePrimitive.EDM_DATETIMEOFFSET, true)
            .addProperty(KEY_INTERVAL_END, TypeSimplePrimitive.EDM_DATETIMEOFFSET, false);

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeComplex.class.getName());
    private static final Map<String, TypeComplex> TYPES = new HashMap<>();

    static {
        for (Field field : FieldUtils.getAllFields(TypeComplex.class)) {
            if (!Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            try {
                final TypeComplex type = (TypeComplex) FieldUtils.readStaticField(field, false);
                final String name = type.getName();
                TYPES.put(name, type);
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

    public static TypeComplex getType(String name) {
        return TYPES.get(name);
    }

    private final boolean openType;
    private final Map<String, PropertyType> properties = new LinkedHashMap<>();
    private final Map<String, Boolean> propertiesRequired = new LinkedHashMap<>();

    public TypeComplex(String name, String description, TypeReference typeReference) {
        this(name, description, typeReference, false);
    }

    public TypeComplex(String name, String description, TypeReference typeReference, boolean openType) {
        super(name, description, typeReference);
        this.openType = openType;
    }

    public boolean isOpenType() {
        return openType;
    }

    public Map<String, PropertyType> getProperties() {
        return properties;
    }

    public boolean isRequired(String property) {
        return propertiesRequired.getOrDefault(property, false);
    }

    public TypeComplex addProperty(String name, PropertyType property, boolean required) {
        properties.put(name, property);
        if (required) {
            propertiesRequired.put(name, required);
        }
        return this;
    }

}
