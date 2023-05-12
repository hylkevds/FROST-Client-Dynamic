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
package de.fraunhofer.iosb.ilt.frostclient.models;

import static de.fraunhofer.iosb.ilt.frostclient.model.property.type.TypeSimplePrimitive.EDM_DATETIMEOFFSET;
import static de.fraunhofer.iosb.ilt.frostclient.model.property.type.TypeSimplePrimitive.EDM_STRING;
import static de.fraunhofer.iosb.ilt.frostclient.model.property.type.TypeSimplePrimitive.EDM_UNTYPED;
import static de.fraunhofer.iosb.ilt.frostclient.utils.Constants.CONTENT_TYPE_APPLICATION_GEOJSON;
import static de.fraunhofer.iosb.ilt.frostclient.utils.SpecialNames.AT_IOT_ID;
import static de.fraunhofer.iosb.ilt.frostclient.utils.TypeReferencesHelper.TYPE_REFERENCE_UOM;

import de.fraunhofer.iosb.ilt.frostclient.model.Entity;
import de.fraunhofer.iosb.ilt.frostclient.model.EntityType;
import de.fraunhofer.iosb.ilt.frostclient.model.Id;
import de.fraunhofer.iosb.ilt.frostclient.model.ModelRegistry;
import de.fraunhofer.iosb.ilt.frostclient.model.ext.TimeInstant;
import de.fraunhofer.iosb.ilt.frostclient.model.ext.TimeInterval;
import de.fraunhofer.iosb.ilt.frostclient.model.ext.TimeValue;
import de.fraunhofer.iosb.ilt.frostclient.model.ext.UnitOfMeasurement;
import de.fraunhofer.iosb.ilt.frostclient.model.property.EntityPropertyMain;
import de.fraunhofer.iosb.ilt.frostclient.model.property.NavigationPropertyEntity;
import de.fraunhofer.iosb.ilt.frostclient.model.property.NavigationPropertyEntitySet;
import de.fraunhofer.iosb.ilt.frostclient.model.property.type.TypeComplex;
import de.fraunhofer.iosb.ilt.frostclient.model.property.type.TypeSimplePrimitive;
import de.fraunhofer.iosb.ilt.frostclient.utils.Constants;
import java.time.ZonedDateTime;
import java.util.Map;
import org.geojson.GeoJsonObject;

/**
 *
 */
public class SensorThingsSensingV11 {

    public static final String NAME_DATASTREAM = "Datastream";
    public static final String NAME_DATASTREAMS = "Datastreams";
    public static final String NAME_FEATUREOFINTEREST = "FeatureOfInterest";
    public static final String NAME_FEATURESOFINTEREST = "FeaturesOfInterest";
    public static final String NAME_HISTORICALLOCATION = "HistoricalLocation";
    public static final String NAME_HISTORICALLOCATIONS = "HistoricalLocations";
    public static final String NAME_LOCATION = "Location";
    public static final String NAME_LOCATIONS = "Locations";
    public static final String NAME_OBSERVATION = "Observation";
    public static final String NAME_OBSERVATIONS = "Observations";
    public static final String NAME_OBSERVEDPROPERTY = "ObservedProperty";
    public static final String NAME_OBSERVEDPROPERTIES = "ObservedProperties";
    public static final String NAME_SENSOR = "Sensor";
    public static final String NAME_SENSORS = "Sensors";
    public static final String NAME_THING = "Thing";
    public static final String NAME_THINGS = "Things";

    public static final String NAME_DEFINITION = "definition";
    public static final String NAME_NAME = "name";
    public static final String NAME_SYMBOL = "symbol";

    public static final String NAME_EP_CREATIONTIME = "creationTime";
    public static final String NAME_EP_DESCRIPTION = "description";
    public static final String NAME_EP_DEFINITION = NAME_DEFINITION;
    public static final String NAME_EP_FEATURE = "feature";
    public static final String NAME_EP_ENCODINGTYPE = "encodingType";
    public static final String NAME_EP_LOCATION = "location";
    public static final String NAME_EP_METADATA = "metadata";
    public static final String NAME_EP_NAME = "name";
    public static final String NAME_EP_OBSERVATIONTYPE = "observationType";
    public static final String NAME_EP_OBSERVEDAREA = "observedArea";
    public static final String NAME_EP_PARAMETERS = "parameters";
    public static final String NAME_EP_PHENOMENONTIME = "phenomenonTime";
    public static final String NAME_EP_PROPERTIES = "properties";
    public static final String NAME_EP_RESULT = "result";
    public static final String NAME_EP_RESULTTIME = "resultTime";
    public static final String NAME_EP_RESULTQUALITY = "resultQuality";
    public static final String NAME_EP_TIME = "time";
    public static final String NAME_EP_UNITOFMEASUREMENT = "unitOfMeasurement";
    public static final String NAME_EP_VALIDTIME = "validTime";

    public static final TypeComplex ept_Uom = new TypeComplex("UnitOfMeasurement", "The Unit Of Measurement Type", TYPE_REFERENCE_UOM)
            .addProperty(NAME_NAME, EDM_STRING, false)
            .addProperty(NAME_SYMBOL, EDM_STRING, false)
            .addProperty(NAME_DEFINITION, EDM_STRING, false);

    public static final EntityPropertyMain<TimeInstant> EP_CREATIONTIME = new EntityPropertyMain<>(NAME_EP_CREATIONTIME, EDM_DATETIMEOFFSET);
    public static final EntityPropertyMain<String> EP_DESCRIPTION = new EntityPropertyMain<>(NAME_EP_DESCRIPTION, EDM_STRING);
    public static final EntityPropertyMain<String> EP_DEFINITION = new EntityPropertyMain<>(NAME_EP_DEFINITION, EDM_STRING);
    public static final EntityPropertyMain<Object> EP_FEATURE = new EntityPropertyMain<>(NAME_EP_FEATURE, TypeSimplePrimitive.EDM_GEOMETRY, false);
    public static final EntityPropertyMain<Object> EP_LOCATION = new EntityPropertyMain<>(NAME_EP_LOCATION, TypeSimplePrimitive.EDM_GEOMETRY, false);
    public static final EntityPropertyMain<String> EP_METADATA = new EntityPropertyMain<>(NAME_EP_METADATA, EDM_STRING);
    public static final EntityPropertyMain<String> EP_NAME = new EntityPropertyMain<>(NAME_EP_NAME, EDM_STRING);
    public static final EntityPropertyMain<String> EP_OBSERVATIONTYPE = new EntityPropertyMain<>(NAME_EP_OBSERVATIONTYPE, EDM_STRING);
    public static final EntityPropertyMain<GeoJsonObject> EP_OBSERVEDAREA = new EntityPropertyMain<>(NAME_EP_OBSERVEDAREA, TypeSimplePrimitive.EDM_GEOMETRY);
    public static final EntityPropertyMain<TimeValue> EP_PHENOMENONTIME = new EntityPropertyMain<>(NAME_EP_PHENOMENONTIME, TypeComplex.STA_TIMEVALUE, false);
    public static final EntityPropertyMain<TimeInterval> EP_PHENOMENONTIMEDS = new EntityPropertyMain<>(NAME_EP_PHENOMENONTIME, TypeComplex.STA_TIMEINTERVAL, false);
    public static final EntityPropertyMain<Map<String, Object>> EP_PARAMETERS = new EntityPropertyMain<>(NAME_EP_PARAMETERS, TypeComplex.STA_MAP, false);
    public static final EntityPropertyMain<Object> EP_RESULT = new EntityPropertyMain<>(NAME_EP_RESULT, TypeSimplePrimitive.EDM_UNTYPED, true);
    public static final EntityPropertyMain<TimeInstant> EP_RESULTTIME = new EntityPropertyMain<>(NAME_EP_RESULTTIME, EDM_DATETIMEOFFSET, false);
    public static final EntityPropertyMain<TimeInterval> EP_RESULTTIMEDS = new EntityPropertyMain<>(NAME_EP_RESULTTIME, TypeComplex.STA_TIMEINTERVAL, false);
    public static final EntityPropertyMain<Object> EP_RESULTQUALITY = new EntityPropertyMain<>(NAME_EP_RESULTQUALITY, TypeComplex.STA_OBJECT, false);
    public static final EntityPropertyMain<TimeInstant> EP_TIME = new EntityPropertyMain<>(NAME_EP_TIME, EDM_DATETIMEOFFSET);
    public static final EntityPropertyMain<UnitOfMeasurement> EP_UNITOFMEASUREMENT = new EntityPropertyMain<>(NAME_EP_UNITOFMEASUREMENT, ept_Uom);
    public static final EntityPropertyMain<TimeInterval> EP_VALIDTIME = new EntityPropertyMain<>(NAME_EP_VALIDTIME, TypeComplex.STA_TIMEINTERVAL);
    public static final EntityPropertyMain<Map<String, Object>> EP_PROPERTIES = new EntityPropertyMain<>(NAME_EP_PROPERTIES, TypeComplex.STA_MAP, false);
    public static final EntityPropertyMain<String> EP_ENCODINGTYPE = new EntityPropertyMain<>(NAME_EP_ENCODINGTYPE, EDM_STRING);

    public static final EntityPropertyMain<?> EP_ID = new EntityPropertyMain<>(AT_IOT_ID, EDM_UNTYPED);

    public final NavigationPropertyEntity npObservationDatastream = new NavigationPropertyEntity(NAME_DATASTREAM);
    public final NavigationPropertyEntity npObservationFeatureofinterest = new NavigationPropertyEntity(NAME_FEATUREOFINTEREST);

    public final NavigationPropertyEntitySet npSensorDatastreams = new NavigationPropertyEntitySet(NAME_DATASTREAMS);

    public final NavigationPropertyEntitySet npObspropDatastreams = new NavigationPropertyEntitySet(NAME_DATASTREAMS);

    public final NavigationPropertyEntitySet npThingHistoricallocations = new NavigationPropertyEntitySet(NAME_HISTORICALLOCATIONS);
    public final NavigationPropertyEntitySet npThingDatastreams = new NavigationPropertyEntitySet(NAME_DATASTREAMS);
    public final NavigationPropertyEntitySet npThingLocations = new NavigationPropertyEntitySet(NAME_LOCATIONS);

    public final NavigationPropertyEntitySet npDatastreamObservations = new NavigationPropertyEntitySet(NAME_OBSERVATIONS, npObservationDatastream);
    public final NavigationPropertyEntity npDatastreamObservedproperty = new NavigationPropertyEntity(NAME_OBSERVEDPROPERTY, npObspropDatastreams);
    public final NavigationPropertyEntity npDatastreamSensor = new NavigationPropertyEntity(NAME_SENSOR, npSensorDatastreams);
    public final NavigationPropertyEntity npDatastreamThing = new NavigationPropertyEntity(NAME_THING, npThingDatastreams);

    public final NavigationPropertyEntitySet npFeatureObservations = new NavigationPropertyEntitySet(NAME_OBSERVATIONS, npObservationFeatureofinterest);

    public final NavigationPropertyEntitySet npLocationHistoricallocations = new NavigationPropertyEntitySet(NAME_HISTORICALLOCATIONS);
    public final NavigationPropertyEntitySet npLocationThings = new NavigationPropertyEntitySet(NAME_THINGS, npThingLocations);

    public final NavigationPropertyEntitySet npHistlocLocations = new NavigationPropertyEntitySet(NAME_LOCATIONS, npLocationHistoricallocations);
    public final NavigationPropertyEntity npHistlocThing = new NavigationPropertyEntity(NAME_THING, npThingHistoricallocations);

    public final EntityType etThing;
    public final EntityType etSensor;
    public final EntityType etObservedProperty;
    public final EntityType etObservation;
    public final EntityType etLocation;
    public final EntityType etHistoricalLocation;
    public final EntityType etFeatureOfInterest;
    public final EntityType etDatastream;

    public final ModelRegistry mr;

    public SensorThingsSensingV11() {
        mr = new ModelRegistry();

        etThing = new EntityType(NAME_THING, NAME_THINGS);
        etSensor = new EntityType(NAME_SENSOR, NAME_SENSORS);
        etObservedProperty = new EntityType(NAME_OBSERVEDPROPERTY, NAME_OBSERVEDPROPERTIES);
        etObservation = new EntityType(NAME_OBSERVATION, NAME_OBSERVATIONS);
        etLocation = new EntityType(NAME_LOCATION, NAME_LOCATIONS);
        etHistoricalLocation = new EntityType(NAME_HISTORICALLOCATION, NAME_HISTORICALLOCATIONS);
        etFeatureOfInterest = new EntityType(NAME_FEATUREOFINTEREST, NAME_FEATURESOFINTEREST);
        etDatastream = new EntityType(NAME_DATASTREAM, NAME_DATASTREAMS);

        mr.registerPropertyType(ept_Uom)
                .registerPropertyType(TypeComplex.STA_OBJECT)
                .registerPropertyType(TypeComplex.STA_MAP)
                .registerPropertyType(TypeComplex.STA_TIMEINTERVAL)
                .registerPropertyType(TypeComplex.STA_TIMEVALUE)
                .registerEntityType(etDatastream)
                .registerEntityType(etFeatureOfInterest)
                .registerEntityType(etHistoricalLocation)
                .registerEntityType(etLocation)
                .registerEntityType(etObservation)
                .registerEntityType(etObservedProperty)
                .registerEntityType(etSensor)
                .registerEntityType(etThing);
        etDatastream
                .registerProperty(EP_ID)
                .registerProperty(EP_NAME)
                .registerProperty(EP_DESCRIPTION)
                .registerProperty(EP_OBSERVATIONTYPE)
                .registerProperty(EP_UNITOFMEASUREMENT)
                .registerProperty(EP_OBSERVEDAREA)
                .registerProperty(EP_PHENOMENONTIMEDS)
                .registerProperty(EP_PROPERTIES)
                .registerProperty(EP_RESULTTIMEDS)
                .registerProperty(npDatastreamObservedproperty)
                .registerProperty(npDatastreamSensor)
                .registerProperty(npDatastreamThing)
                .registerProperty(npDatastreamObservations);
        etFeatureOfInterest
                .registerProperty(EP_ID)
                .registerProperty(EP_NAME)
                .registerProperty(EP_DESCRIPTION)
                .registerProperty(EP_ENCODINGTYPE)
                .registerProperty(EP_FEATURE)
                .registerProperty(EP_PROPERTIES)
                .registerProperty(npFeatureObservations);
        etHistoricalLocation
                .registerProperty(EP_ID)
                .registerProperty(EP_TIME)
                .registerProperty(npHistlocThing)
                .registerProperty(npHistlocLocations);
        etLocation
                .registerProperty(EP_ID)
                .registerProperty(EP_NAME)
                .registerProperty(EP_DESCRIPTION)
                .registerProperty(EP_ENCODINGTYPE)
                .registerProperty(EP_LOCATION)
                .registerProperty(EP_PROPERTIES)
                .registerProperty(npLocationHistoricallocations)
                .registerProperty(npLocationThings);
        etObservation
                .registerProperty(EP_ID)
                .registerProperty(EP_PHENOMENONTIME)
                .registerProperty(EP_RESULTTIME)
                .registerProperty(EP_RESULT)
                .registerProperty(EP_RESULTQUALITY)
                .registerProperty(EP_VALIDTIME)
                .registerProperty(EP_PARAMETERS)
                .registerProperty(npObservationDatastream)
                .registerProperty(npObservationFeatureofinterest);
        etObservedProperty
                .registerProperty(EP_ID)
                .registerProperty(EP_NAME)
                .registerProperty(EP_DEFINITION)
                .registerProperty(EP_DESCRIPTION)
                .registerProperty(EP_PROPERTIES)
                .registerProperty(npObspropDatastreams);
        etSensor
                .registerProperty(EP_ID)
                .registerProperty(EP_NAME)
                .registerProperty(EP_DESCRIPTION)
                .registerProperty(EP_ENCODINGTYPE)
                .registerProperty(EP_METADATA)
                .registerProperty(EP_PROPERTIES)
                .registerProperty(npSensorDatastreams);
        etThing
                .registerProperty(EP_ID)
                .registerProperty(EP_NAME)
                .registerProperty(EP_DESCRIPTION)
                .registerProperty(EP_PROPERTIES)
                .registerProperty(npThingLocations)
                .registerProperty(npThingHistoricallocations)
                .registerProperty(npThingDatastreams);
    }

    public ModelRegistry getModelRegistry() {
        return mr;
    }

    public Entity newThing() {
        return new Entity(etThing);
    }

    public Entity newThing(Id id) {
        return new Entity(etThing, id);
    }

    public Entity newThing(String name, String description) {
        return newThing()
                .setProperty(EP_NAME, name)
                .setProperty(EP_DESCRIPTION, description);
    }

    public Entity newThing(String name, String description, Map<String, Object> properties) {
        return newThing(name, description)
                .setProperty(EP_PROPERTIES, properties);
    }

    public Entity newLocation() {
        return new Entity(etLocation);
    }

    public Entity newLocation(Id id) {
        return new Entity(etLocation, id);
    }

    public Entity newLocation(String name, String description, GeoJsonObject location) {
        return newLocation(name, description, CONTENT_TYPE_APPLICATION_GEOJSON, location);
    }

    public Entity newLocation(String name, String description, String encodingType, Object location) {
        return new Entity(etLocation)
                .setProperty(EP_NAME, name)
                .setProperty(EP_DESCRIPTION, description)
                .setProperty(EP_ENCODINGTYPE, encodingType)
                .setProperty(EP_LOCATION, location);
    }

    public Entity newDatastream() {
        return new Entity(etDatastream);
    }

    public Entity newDatastream(Id id) {
        return new Entity(etDatastream, id);
    }

    public Entity newDatastream(String name, String description, UnitOfMeasurement uom) {
        return newDatastream()
                .setProperty(EP_NAME, name)
                .setProperty(EP_DESCRIPTION, description)
                .setProperty(EP_OBSERVATIONTYPE, Constants.OM_MEASUREMENT)
                .setProperty(EP_UNITOFMEASUREMENT, uom);
    }

    public Entity newSensor() {
        return new Entity(etSensor);
    }

    public Entity newSensor(Id id) {
        return new Entity(etSensor, id);
    }

    public Entity newSensor(String name, String description, String encodingType, String metaData) {
        return newSensor()
                .setProperty(EP_NAME, name)
                .setProperty(EP_DESCRIPTION, description)
                .setProperty(EP_ENCODINGTYPE, encodingType)
                .setProperty(EP_METADATA, metaData);
    }

    public Entity newObservedProperty() {
        return new Entity(etObservedProperty);
    }

    public Entity newObservedProperty(Id id) {
        return new Entity(etObservedProperty, id);
    }

    public Entity newObservedProperty(String name, String definition, String desription) {
        return newObservedProperty()
                .setProperty(EP_NAME, name)
                .setProperty(EP_DEFINITION, definition)
                .setProperty(EP_DESCRIPTION, desription);
    }

    public Entity newObservation() {
        return new Entity(etObservation);
    }

    public Entity newObservation(Id id) {
        return new Entity(etObservation, id);
    }

    public Entity newObservation(Object result) {
        return newObservation()
                .setProperty(EP_RESULT, result);
    }

    public Entity newObservation(Object result, ZonedDateTime phenomenonTime) {
        return newObservation(result)
                .setProperty(EP_PHENOMENONTIME, TimeValue.create(phenomenonTime));
    }

    public Entity newObservation(Object result, TimeInterval phenomenonTime) {
        return newObservation(result)
                .setProperty(EP_PHENOMENONTIME, new TimeValue(phenomenonTime));
    }

    public Entity newFeatureOfInterest() {
        return new Entity(etFeatureOfInterest);
    }

    public Entity newFeatureOfInterest(Id id) {
        return new Entity(etFeatureOfInterest, id);
    }
}
