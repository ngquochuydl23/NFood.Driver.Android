package com.nsolution.nfooddriver.Models

import android.location.Location
import android.os.Build
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LocationDto {
  @SerializedName("ElapsedRealtimeNanos")
  @Expose
  var elapsedRealtimeNanos: Long = 0

  @SerializedName("ElapsedRealtimeUncertaintyNanos")
  @Expose
  var elapsedRealtimeUncertaintyNanos = 0.0

  @SerializedName("Latitude")
  @Expose
  var latitude: Double = 0.0

  @SerializedName("Longitude")
  @Expose
  var longitude: Double = 0.0

  @SerializedName("Altitude")
  @Expose
  var altitude: Double = 0.0

  @SerializedName("Speed")
  @Expose
  var speed = 0.0f

  @SerializedName("Bearing")
  @Expose
  var bearing = 0.0f

  @SerializedName("HorizontalAccuracyMeters")
  @Expose
  var horizontalAccuracyMeters = 0.0f

  @SerializedName("VerticalAccuracyMeters")
  @Expose
  var verticalAccuracyMeters = 0.0f

  @SerializedName("SpeedAccuracyMetersPerSecond")
  @Expose
  var speedAccuracyMetersPerSecond = 0.0f

  @SerializedName("BearingAccuracyDegrees")
  @Expose
  var bearingAccuracyDegrees = 0.0f



  companion object {

    fun convertToLocationDto(@NonNull location : Location) : LocationDto{
      return LocationDto().apply {
        elapsedRealtimeNanos = location.elapsedRealtimeNanos
       //  elapsedRealtimeUncertaintyNanos = location.elapsedRealtimeUncertaintyNanos
        latitude = location.latitude
        longitude = location.longitude
        altitude = location.altitude
        speed = location.speed
        bearing = location.bearing
        verticalAccuracyMeters =  location.verticalAccuracyMeters
        speedAccuracyMetersPerSecond = location.speedAccuracyMetersPerSecond
        bearingAccuracyDegrees = location.bearingAccuracyDegrees
      }
    }
  }
}