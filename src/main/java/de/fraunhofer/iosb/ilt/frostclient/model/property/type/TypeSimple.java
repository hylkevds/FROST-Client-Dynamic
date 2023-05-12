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

/**
 * A simple Type.
 */
public abstract class TypeSimple extends PropertyType {

    private Parser parser;
    private final TypeSimplePrimitive underlyingType;

    protected TypeSimple(String name, String description, TypeReference... typeReference) {
        this(name, description, (Parser) null, typeReference);
    }

    protected TypeSimple(String name, String description, Parser parser, TypeReference... typeReference) {
        super(name, description, typeReference);
        if (this instanceof TypeSimplePrimitive) {
            this.underlyingType = (TypeSimplePrimitive) this;
        } else {
            throw new IllegalArgumentException("This constuctor can only be used by subclass TypeSimplePrimitive or TypeSimpleSet");
        }
        this.parser = parser;
    }

    protected TypeSimple(String name, String description, TypeSimplePrimitive underlyingType, TypeReference... typeReference) {
        this(name, description, underlyingType, null, typeReference);
    }

    protected TypeSimple(String name, String description, TypeSimplePrimitive underlyingType, Parser parser, TypeReference... typeReference) {
        super(name, description, typeReference);
        this.underlyingType = underlyingType;
        this.parser = parser;
    }

    public TypeSimplePrimitive getUnderlyingType() {
        return underlyingType;
    }

    @Override
    public Object parseFromUrl(String input) {
        if (parser != null) {
            return parser.parseFromUrl(input);
        }
        if (underlyingType != this) {
            return underlyingType.parseFromUrl(input);
        }
        return super.parseFromUrl(input);
    }

    public static interface Parser {

        public Object parseFromUrl(String input);
    }
}
