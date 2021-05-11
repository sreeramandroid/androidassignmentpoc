package com.poc.androidassignment.custombinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.poc.androidassignment.R
import com.poc.androidassignment.adapters.CountryDataDetailsAdapter
import com.poc.androidassignment.model.Row

/***
 *
 * anotation for setting set adapter and layout manager
 * ***/

@BindingAdapter("adapter", "layout_manager")
fun setConfigAdapter(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<*>,
    manager: RecyclerView.LayoutManager
) {
    recyclerView.adapter = adapter
    recyclerView.layoutManager = manager
}


/***
 *
 * anotation for setting arraylist
 * ***/


@BindingAdapter("data_list")
fun setDataList(recyclerView: RecyclerView, countryDataList: List<Row>?) {
    val countryAdapter =
        recyclerView.adapter as? CountryDataDetailsAdapter // getting null in this line.
    countryAdapter?.apply {
        if (countryDataList != null) {
            _arrayCountryData.clear()
            _arrayCountryData.addAll(countryDataList)
            notifyDataSetChanged()
        }
    }
}


@BindingAdapter("image_url")
fun glide(view: ImageView, mValurl: String?) {
    if (!mValurl.isNullOrEmpty()) {
        val url: String? = mValurl

        val glideUrl = GlideUrl(
            url,
            LazyHeaders.Builder()
                .addHeader("Accept", "image/jpg")
                .build()
        )
        Glide.with(view.context)
            .load(glideUrl)
            .into(view);
    }

    @BindingAdapter("image", "imageplaceholder")
    fun setImage(image: ImageView, url: String?, placeHolder: Drawable) {
        if (!url.isNullOrEmpty()) {
            Glide.with(image.context).load(url).centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(image)
        } else {
            image.setImageDrawable(placeHolder)
        }
    }
}