package com.nsolution.nfooddriver.Adapter.RecyclerView.Adapter

import android.content.Intent
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mapbox.api.staticmap.v1.MapboxStaticMap
import com.mapbox.api.staticmap.v1.StaticMapCriteria
import com.mapbox.api.staticmap.v1.StaticMapCriteria.STREET_STYLE
import com.mapbox.api.staticmap.v1.models.StaticMarkerAnnotation
import com.mapbox.api.staticmap.v1.models.StaticPolylineAnnotation
import com.mapbox.geojson.Point
import com.nsolution.nfooddriver.Adapter.RecyclerView.ViewHolder.TripHistoryHolder
import com.nsolution.nfooddriver.Models.ItemTripHistoryDto
import com.nsolution.nfooddriver.R
import com.nsolution.nfooddriver.Ui.History.DetailTripHistory
import com.nsolution.nfooddriver.Utils.FormatCurrency


class TripHistoryAdapter(val listTripHistory: ArrayList<ItemTripHistoryDto>?) :
  BaseAdapter<TripHistoryHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripHistoryHolder {
    val view = getView(parent, viewType, R.layout.item_trip_history)
    return TripHistoryHolder(view)
  }

  override fun onBindViewHolder(holder: TripHistoryHolder, position: Int) {
    val itemHistoryTrip = listTripHistory?.get(position)

    holder.tripDateTime.text = itemHistoryTrip?.tripDateTime
    holder.tripVehical.text = itemHistoryTrip?.tripVehical
    holder.tripEarnings.text = FormatCurrency.FormatCurrencyVietNam(itemHistoryTrip?.tripEarnings)
    holder.containerLayout.setOnClickListener {
      val intent = Intent(context, DetailTripHistory::class.java)
      context.startActivity(intent)
    }

    getStaticMap(holder.tripMapImage)
  }

  override fun getItemCount(): Int {
    return getSizeList(listTripHistory)
  }

  private fun getStaticMap(imageView: ImageView) {
    val tokenMapbox = context.resources.getString(R.string.mapbox_access_token)
//      .cameraPoint(Point.fromLngLat(108.4395, 11.9525))
//      .cameraPoint(Point.fromLngLat( 108.4050, 11.9491))
    val markers = arrayListOf<StaticMarkerAnnotation>()
    val polylines = arrayListOf<StaticPolylineAnnotation>()

    markers.add(
      StaticMarkerAnnotation.builder().name(StaticMapCriteria.LARGE_PIN)
        .lnglat(Point.fromLngLat(108.4395, 11.9525))
        .build()
    )

    markers.add(
      StaticMarkerAnnotation.builder().name(StaticMapCriteria.LARGE_PIN)
        .lnglat(Point.fromLngLat(108.4050, 11.9491))
        .build()
    )

    polylines.add(StaticPolylineAnnotation.builder().polyline("dassss").build())

    val staticImage = MapboxStaticMap.builder()
      .accessToken(tokenMapbox)
      .styleId(STREET_STYLE)
      .staticPolylineAnnotations(polylines)
      .staticMarkerAnnotations(markers)
      .cameraPoint(Point.fromLngLat(108.4050, 11.9491))
      .cameraZoom(12.0)
      .width(640)
      .height(320)
      .retina(true)
      .build()

    val imageUrl = staticImage.url().toString()
    Glide.with(context)
      .load("https://api.mapbox.com/styles/v1/mapbox/streets-v11/static/pin-s-a+7ac142(-122.46589,37.77343),pin-s-b+fff(-122.42816,37.75965),path-5+7ac142(%7DrpeFxbnjVsFwdAvr@cHgFor@jEmAlFmEMwM_FuItCkOi@wc@bg@wBSgM)/auto/1000x500?access_token=pk.eyJ1IjoibmdxdW9jaHV5ZGwyMyIsImEiOiJja2pwdTVtMmUxc3Z2MnNtam13Y2V0NWc1In0.r2dN07ZpCoDTxZwziNm1cQ")
      .into(imageView)
  }
}