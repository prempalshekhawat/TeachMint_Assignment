package com.prempal.teachmintassignment.data.remote.response

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Keep
data class MainResponse(
    @SerializedName("items")
    var items: List<Item>? = null
) {
    @Keep
    @Parcelize
    @Entity(tableName = "main_table")
    data class Item(
        @SerializedName("contributors_url")
        var contributorsURL: String? = null,
        @SerializedName("description")
        var desc: String? = null,
        @SerializedName("id")
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        @SerializedName("language")
        var language: String? = null,
        @SerializedName("name")
        var githubRepoName: String? = null,
        @SerializedName("svn_url")
        var githubRepoURL: String? = null,
    ) : Parcelable
}