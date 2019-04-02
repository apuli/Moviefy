package com.pvbapps.moviefy.domain.offline

import android.os.Parcelable
import io.requery.Entity
import io.requery.Key
import io.requery.Persistable
import io.requery.Table

@Table(name = "movies")
@Entity
interface MovieOffline : Parcelable, Persistable {
    @get:Key
    var id: Int
    var voteAverage: Float
    var voteCount: Int
    var video: Boolean
    var mediaType: String
    var title: String
    var popularity: Float
    var posterPath: String
    var originalLanguage: String
    var originalTitle: String
    var adult: Boolean
    var overview: String
    var backdropPath: String
    var releaseDate: String
}