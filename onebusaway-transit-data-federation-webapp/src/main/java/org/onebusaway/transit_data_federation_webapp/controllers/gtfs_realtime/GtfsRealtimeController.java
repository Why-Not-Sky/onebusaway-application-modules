/**
 * Copyright (C) 2011 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.transit_data_federation_webapp.controllers.gtfs_realtime;

import java.io.IOException;
import java.io.OutputStream;

import org.onebusaway.transit_data_federation.impl.realtime.gtfs_realtime.GtfsRealtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.transit.realtime.GtfsRealtime.FeedMessage;

@Controller
public class GtfsRealtimeController {

  private GtfsRealtimeService _gtfsRealtimeService;

  @Autowired
  public void setGtfsRealtimeService(GtfsRealtimeService gtfsRealtimeService) {
    _gtfsRealtimeService = gtfsRealtimeService;
  }

  @RequestMapping(value = "/gtfs-realtime/trip-updates.action")
  public void tripUpdates(OutputStream out) throws IOException {
    FeedMessage tripUpdates = _gtfsRealtimeService.getTripUpdates();
    tripUpdates.writeTo(out);
  }

  @RequestMapping(value = "/gtfs-realtime/vehicle-positions.action")
  public void vehiclePositions(OutputStream out) throws IOException {
    FeedMessage vehiclePositions = _gtfsRealtimeService.getVehiclePositions();
    vehiclePositions.writeTo(out);
  }

  @RequestMapping(value = "/gtfs-realtime/alerts.action")
  public void alerts(OutputStream out) throws IOException {
    FeedMessage alerts = _gtfsRealtimeService.getAlerts();
    alerts.writeTo(out);
  }
}