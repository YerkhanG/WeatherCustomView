package com.example.weatherview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.weatherview.databinding.ViewWeatherBinding

class WeatherView @JvmOverloads constructor (
    context : Context,
    attrs : AttributeSet? =  null,
    defStyleAttr: Int = 0

) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding : ViewWeatherBinding =
        ViewWeatherBinding.inflate(LayoutInflater.from(context),this)
    init{
        setAttrs(attrs ,R.styleable.WeatherView){
            binding.city.text = it.getString(R.styleable.WeatherView_weather_city)
            binding.country.text = it.getString(R.styleable.WeatherView_weather_country)
            binding.temperature.text = it.getString(R.styleable.WeatherView_weather_temp)
            //Не знаю почему , но тут он отказывается работать потому что "a out of bounds" хотя я все норм проставил
            var a = it.getInt(R.styleable.WeatherView_weather_time,0)
            binding.timeOfTheDay.text = time.values()[a].name
            var b = it.getInt(R.styleable.WeatherView_weather_type,0)
            binding.state.text = typeOfWeather.values()[a].name
            if(b == 0 && a == 0){
                binding.typeOfWeather.setImageResource(R.drawable.moon_cloud_rain)
            }
            if(b == 0 && a == 1){
                binding.typeOfWeather.setImageResource(R.drawable.sun_cloud_rain)
            }
            if(b == 1 && a == 0){
                binding.typeOfWeather.setImageResource(R.drawable.moon_cloud_rain)
            }
            if(b == 1 && a == 1){
                binding.typeOfWeather.setImageResource(R.drawable.sun_cloud_rain)
            }
            if(it.getBoolean(R.styleable.WeatherView_weather_wind,true)){
                binding.wind.text = "wind"
            }
            else{
                binding.wind.text = "no wind"
            }
        }
    }
//
//    var temperature: String
//        get() = binding.temperature.text.toString()
//        set(value) {
//            binding.temperature.text = value
//        }
//    var country: String
//        get() = binding.country.text.toString()
//        set(value) {
//            binding.country.text = value
//        }
//    var city: String
//        get() = binding.city.text.toString()
//        set(value) {
//            binding.city.text = value
//        }
//    var wind: String
//        get() = binding.wind.text.toString()
//        set(value) {
//            binding.city.text = value
//        }
    enum class time{
        NIGHT, DAY
    }
    enum class typeOfWeather{
        WEAK_RAIN,STRONG_RAIN,WEAK_SNOWFALL,STRONG_SNOWFALL,
    }
    inline fun View.setAttrs(
        attrs: AttributeSet?,
        styleable: IntArray,
        crossinline body: (TypedArray) -> Unit
    ) {
        context.theme.obtainStyledAttributes(attrs, styleable, 0, 0)
            .apply {
                try {
                    body.invoke(this)
                } finally {
                    recycle()
                }
            }
    }
}

