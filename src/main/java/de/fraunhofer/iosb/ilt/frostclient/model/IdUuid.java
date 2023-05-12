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
package de.fraunhofer.iosb.ilt.frostclient.model;

import static de.fraunhofer.iosb.ilt.frostclient.utils.StringHelper.escapeForStringConstant;
import static de.fraunhofer.iosb.ilt.frostclient.utils.StringHelper.urlEncode;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * An Id implementation using UUID values.
 */
public class IdUuid implements Id<IdUuid> {

    private UUID value;

    public IdUuid() {
    }

    public IdUuid(UUID value) {
        this.value = value;
    }

    @Override
    public UUID getValue() {
        return value;
    }

    @Override
    public String getUrl() {
        return "'" + urlEncode(escapeForStringConstant(value.toString()), true) + "'";
    }

    @Override
    public String getJson() {
        return '"' + value.toString() + '"';
    }

    @Override
    public void writeTo(JsonGenerator gen) throws IOException {
        gen.writeString(value.toString());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IdUuid other = (IdUuid) obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int compareTo(IdUuid o) {
        return value.compareTo(o.value);
    }

}
