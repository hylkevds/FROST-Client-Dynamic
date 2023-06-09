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

import de.fraunhofer.iosb.ilt.frostclient.model.property.EntityPropertyMain;
import de.fraunhofer.iosb.ilt.frostclient.model.property.NavigationProperty;
import de.fraunhofer.iosb.ilt.frostclient.model.property.NavigationPropertyAbstract;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The types of entities.
 */
public class EntityType implements Comparable<EntityType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityType.class.getName());

    /**
     * The entityName of this entity type as used in URLs.
     */
    public final String entityName;

    /**
     * The entityName of collections of this entity type as used in URLs.
     */
    public final String plural;

    private boolean initialised = false;

    /**
     * The Property that is the primary key of the entity.
     */
    private PrimaryKey primaryKey;

    /**
     * The Set of PROPERTIES that Entities of this type have.
     */
    private final Set<Property> properties = new LinkedHashSet<>();

    /**
     * The Set of PROPERTIES that Entities of this type have, mapped by their
     * name.
     */
    private final Map<String, Property> propertiesByName = new LinkedHashMap<>();

    /**
     * The set of Entity properties.
     */
    private final Set<EntityPropertyMain> entityProperties = new LinkedHashSet<>();

    /**
     * The set of Navigation properties.
     */
    private final Set<NavigationProperty> navigationProperties = new LinkedHashSet<>();

    /**
     * The set of Navigation properties pointing to single entities.
     */
    private final Set<NavigationProperty<Entity>> navigationEntities = new LinkedHashSet<>();

    /**
     * The set of Navigation properties pointing to entity sets.
     */
    private final Set<NavigationProperty<EntitySet>> navigationSets = new LinkedHashSet<>();

    /**
     * The ModelRegistry this EntityType is registered on.
     */
    private ModelRegistry modelRegistry;

    public EntityType(String singular, String plural) {
        this.entityName = singular;
        this.plural = plural;
    }

    public EntityType registerProperty(Property property) {
        properties.add(property);
        propertiesByName.put(property.getName(), property);
        if (property instanceof EntityPropertyMain) {
            EntityPropertyMain<?> propertyMain = (EntityPropertyMain<?>) property;
            for (String alias : propertyMain.getAliases()) {
                propertiesByName.put(alias, property);
            }
            if (primaryKey == null) {
                primaryKey = new PkSingle(propertyMain);
            }
        }
        return this;
    }

    public void init() {
        if (initialised) {
            LOGGER.error("Re-Init of EntityType!");
        }
        initialised = true;
        for (Property property : properties) {
            if (property instanceof EntityPropertyMain entityPropertyMain) {
                entityProperties.add(entityPropertyMain);
            }
            if (property instanceof NavigationPropertyAbstract np) {
                if (np.getInverse() == null) {
                    throw new IllegalStateException("NavigationProperty " + np.getName() + " has no inverse.");
                }
                if (np.getInverse().getEntityType() == null) {
                    np.getInverse().setEntityType(this);
                }

                navigationProperties.add(np);
                if (np.isEntitySet()) {
                    navigationSets.add(np);
                } else {
                    navigationEntities.add(np);
                }
            }
        }
    }

    public PrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public Property getProperty(String name) {
        return propertiesByName.get(name);
    }

    public EntityPropertyMain getEntityProperty(String name) {
        Property property = propertiesByName.get(name);
        if (property instanceof EntityPropertyMain entityPropertyMain) {
            return entityPropertyMain;
        }
        return null;
    }

    public NavigationPropertyAbstract getNavigationProperty(String name) {
        Property property = propertiesByName.get(name);
        if (property instanceof NavigationPropertyAbstract navigationPropertyMain) {
            return navigationPropertyMain;
        }
        return null;
    }

    /**
     * The Set of PROPERTIES that Entities of this type have.
     *
     * @return The Set of PROPERTIES that Entities of this type have.
     */
    public Set<Property> getPropertySet() {
        return properties;
    }

    /**
     * Get the set of Entity properties.
     *
     * @return The set of Entity properties.
     */
    public Set<EntityPropertyMain> getEntityProperties() {
        return entityProperties;
    }

    public boolean hasProperty(Property property) {
        return properties.contains(property);
    }

    /**
     * Get the set of Navigation properties.
     *
     * @return The set of Navigation properties.
     */
    public Set<NavigationProperty> getNavigationProperties() {
        return navigationProperties;
    }

    /**
     * Get the set of Navigation properties pointing to single entities.
     *
     * @return The set of Navigation properties pointing to single entities.
     */
    public Set<NavigationProperty<Entity>> getNavigationEntities() {
        return navigationEntities;
    }

    /**
     * Get the set of Navigation properties pointing to entity sets.
     *
     * @return The set of Navigation properties pointing to entity sets.
     */
    public Set<NavigationProperty<EntitySet>> getNavigationSets() {
        return navigationSets;
    }

    /**
     * The ModelRegistry this EntityType is registered on.
     *
     * @return the modelRegistry
     */
    public ModelRegistry getModelRegistry() {
        return modelRegistry;
    }

    /**
     * The ModelRegistry this EntityType is registered on.
     *
     * @param modelRegistry the modelRegistry to set
     */
    public void setModelRegistry(ModelRegistry modelRegistry) {
        if (this.modelRegistry != null && this.modelRegistry != modelRegistry) {
            throw new IllegalArgumentException("Changing the ModelRegistry on an EntityType is not allowed.");
        }
        this.modelRegistry = modelRegistry;
    }

    @Override
    public String toString() {
        return entityName;
    }

    @Override
    public int compareTo(EntityType o) {
        return entityName.compareTo(o.entityName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EntityType)) {
            return false;
        }
        EntityType other = (EntityType) obj;
        if (entityName.equals(other.entityName)) {
            LOGGER.error("Found other instance of {}", entityName);
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.entityName);
        return hash;
    }

}
