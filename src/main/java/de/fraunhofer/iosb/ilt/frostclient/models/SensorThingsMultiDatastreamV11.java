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

import static de.fraunhofer.iosb.ilt.frostclient.model.property.type.TypeSimplePrimitive.EDM_STRING;
import static de.fraunhofer.iosb.ilt.frostclient.models.SensorThingsSensingV11.EP_DESCRIPTION;
import static de.fraunhofer.iosb.ilt.frostclient.models.SensorThingsSensingV11.EP_NAME;
import static de.fraunhofer.iosb.ilt.frostclient.models.SensorThingsSensingV11.EP_OBSERVATIONTYPE;
import static de.fraunhofer.iosb.ilt.frostclient.models.SensorThingsSensingV11.NAME_OBSERVATIONS;
import static de.fraunhofer.iosb.ilt.frostclient.models.SensorThingsSensingV11.NAME_OBSERVEDPROPERTIES;
import static de.fraunhofer.iosb.ilt.frostclient.models.SensorThingsSensingV11.NAME_SENSOR;
import static de.fraunhofer.iosb.ilt.frostclient.models.SensorThingsSensingV11.NAME_THING;
import static de.fraunhofer.iosb.ilt.frostclient.utils.TypeReferencesHelper.TYPE_REFERENCE_LIST_STRING;
import static de.fraunhofer.iosb.ilt.frostclient.utils.TypeReferencesHelper.TYPE_REFERENCE_LIST_UOM;

import de.fraunhofer.iosb.ilt.frostclient.model.Entity;
import de.fraunhofer.iosb.ilt.frostclient.model.EntityType;
import de.fraunhofer.iosb.ilt.frostclient.model.Id;
import de.fraunhofer.iosb.ilt.frostclient.model.ModelRegistry;
import de.fraunhofer.iosb.ilt.frostclient.model.ext.UnitOfMeasurement;
import de.fraunhofer.iosb.ilt.frostclient.model.property.EntityPropertyMain;
import de.fraunhofer.iosb.ilt.frostclient.model.property.NavigationPropertyEntity;
import de.fraunhofer.iosb.ilt.frostclient.model.property.NavigationPropertyEntitySet;
import de.fraunhofer.iosb.ilt.frostclient.model.property.type.TypeSimpleSet;
import de.fraunhofer.iosb.ilt.frostclient.utils.Constants;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class SensorThingsMultiDatastreamV11 {

    private static final String NAME_MULTI_DATASTREAM = "MultiDatastream";
    private static final String NAME_MULTI_DATASTREAMS = "MultiDatastreams";
    private static final String NAME_MULTIOBSERVATIONDATATYPES = "multiObservationDataTypes";

    public static final EntityPropertyMain<List<String>> EP_MULTIOBSERVATIONDATATYPES = new EntityPropertyMain<>(NAME_MULTIOBSERVATIONDATATYPES, new TypeSimpleSet(EDM_STRING, TYPE_REFERENCE_LIST_STRING));
    public static final EntityPropertyMain<List<UnitOfMeasurement>> EP_UNITOFMEASUREMENTS = new EntityPropertyMain<>("unitOfMeasurements", new TypeSimpleSet(SensorThingsSensingV11.ept_Uom, TYPE_REFERENCE_LIST_UOM));

    public static final NavigationPropertyEntity NP_OBSERVATION_MULTIDATASTREAM = new NavigationPropertyEntity(NAME_MULTI_DATASTREAM);
    public static final NavigationPropertyEntitySet NP_MULTIDATASTREAM_OBSERVATIONS = new NavigationPropertyEntitySet(NAME_OBSERVATIONS, NP_OBSERVATION_MULTIDATASTREAM);

    public static final NavigationPropertyEntitySet NP_OBSPROP_MULTIDATASTREAMS = new NavigationPropertyEntitySet(NAME_MULTI_DATASTREAMS);
    public static final NavigationPropertyEntitySet NP_MULTIDATASTREAM_OBSERVEDPROPERTIES = new NavigationPropertyEntitySet(NAME_OBSERVEDPROPERTIES, NP_OBSPROP_MULTIDATASTREAMS);

    public static final NavigationPropertyEntitySet NP_THING_MULTIDATASTREAMS = new NavigationPropertyEntitySet(NAME_MULTI_DATASTREAMS);
    public static final NavigationPropertyEntity NP_MULTIDATASTREAM_THING = new NavigationPropertyEntity(NAME_THING, NP_THING_MULTIDATASTREAMS);

    public static final NavigationPropertyEntitySet NP_SENSOR_MULTIDATASTREAMS = new NavigationPropertyEntitySet(NAME_MULTI_DATASTREAMS);
    public static final NavigationPropertyEntity NP_MULTIDATASTREAM_SENSOR = new NavigationPropertyEntity(NAME_SENSOR, NP_SENSOR_MULTIDATASTREAMS);

    public final EntityType etMultiDatastream = new EntityType(NAME_MULTI_DATASTREAM, NAME_MULTI_DATASTREAMS);

    public final ModelRegistry mr;

    public SensorThingsMultiDatastreamV11(SensorThingsSensingV11 modelSensing) {
        this(modelSensing.getModelRegistry());
    }

    public SensorThingsMultiDatastreamV11(ModelRegistry mrSensing) {
        this.mr = mrSensing;
        mr.registerEntityType(etMultiDatastream);

        etMultiDatastream
                .registerProperty(SensorThingsSensingV11.EP_ID)
                .registerProperty(ModelRegistry.EP_SELFLINK)
                .registerProperty(SensorThingsSensingV11.EP_NAME)
                .registerProperty(SensorThingsSensingV11.EP_DESCRIPTION)
                .registerProperty(SensorThingsSensingV11.EP_OBSERVATIONTYPE)
                .registerProperty(EP_MULTIOBSERVATIONDATATYPES)
                .registerProperty(EP_UNITOFMEASUREMENTS)
                .registerProperty(SensorThingsSensingV11.EP_OBSERVEDAREA)
                .registerProperty(SensorThingsSensingV11.EP_PHENOMENONTIMEDS)
                .registerProperty(SensorThingsSensingV11.EP_PROPERTIES)
                .registerProperty(SensorThingsSensingV11.EP_RESULTTIMEDS)
                .registerProperty(NP_MULTIDATASTREAM_OBSERVEDPROPERTIES)
                .registerProperty(NP_MULTIDATASTREAM_SENSOR)
                .registerProperty(NP_MULTIDATASTREAM_THING)
                .registerProperty(NP_MULTIDATASTREAM_OBSERVATIONS);
        // Register multiDatastream on existing entities.
        mr.getEntityTypeForName(SensorThingsSensingV11.NAME_THING)
                .registerProperty(NP_THING_MULTIDATASTREAMS);
        mr.getEntityTypeForName(SensorThingsSensingV11.NAME_OBSERVEDPROPERTY)
                .registerProperty(NP_OBSPROP_MULTIDATASTREAMS);
        mr.getEntityTypeForName(SensorThingsSensingV11.NAME_SENSOR)
                .registerProperty(NP_SENSOR_MULTIDATASTREAMS);
        mr.getEntityTypeForName(SensorThingsSensingV11.NAME_OBSERVATION)
                .registerProperty(NP_OBSERVATION_MULTIDATASTREAM);
    }

    public ModelRegistry getModelRegistry() {
        return mr;
    }

    public Entity newMultiDatastream() {
        return new Entity(etMultiDatastream);
    }

    public Entity newMultiDatastream(Id id) {
        return new Entity(etMultiDatastream, id);
    }

    public Entity newMultiDatastream(String name, String description, UnitOfMeasurement... uoms) {
        return newMultiDatastream()
                .setProperty(EP_NAME, name)
                .setProperty(EP_DESCRIPTION, description)
                .setProperty(EP_OBSERVATIONTYPE, Constants.OM_MEASUREMENT)
                .setProperty(EP_UNITOFMEASUREMENTS, Arrays.asList(uoms));
    }

}
