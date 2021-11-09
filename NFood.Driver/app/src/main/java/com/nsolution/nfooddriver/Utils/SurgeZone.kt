package com.nsolution.nfooddriver.Utils

import android.R.style
import android.graphics.Color
import androidx.annotation.NonNull
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.style.layers.FillLayer
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillColor
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillOpacity
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import java.net.URI


class SurgeZone {

  private val HEATMAP_LAYER_ID = "HEATMAP_LAYER_ID"
  private val HEATMAP_LAYER_SOURCE = "earthquakes"
  private val EARTHQUAKE_SOURCE_ID = "EARTHQUAKE_SOURCE_ID"
  private val EARTHQUAKE_SOURCE_URL = "https://www.mapbox.com/mapbox-gl-js/assets/earthquakes.geojson"
  private val CIRCLE_LAYER_ID = "earthquakes-circle"

  private var listOfHeatmapIntensityStops = arrayOf<Float>()


  fun getSurgeZone(@NonNull mapboxMap: MapboxMap?) {

    val urbanAreasSource = GeoJsonSource(
      "urban-areas",
      URI("https://d2ad6b4ur7yvpq.cloudfront.net/naturalearth-3.3.0/ne_50m_urban_areas.geojson")
    )


    mapboxMap?.getStyle {
      it.addSource(urbanAreasSource)

      val urbanArea =  FillLayer("urban-areas-fill", "urban-areas")
      urbanArea.setProperties(
        fillColor(Color.parseColor("#90fc741f")),
        fillOpacity(0.4f)
      )

      it.addLayerBelow(urbanArea, "water")
    }
  }
}