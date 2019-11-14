//package com.example.testapp.data.local
//
//import androidx.room.TypeConverter
//import com.example.testapp.data.Kind
//
//class KindConverter {
//
//    @TypeConverter
//    fun fromStringToKind(string: String?): Kind {
//        return when (string) {
//            "book" -> Kind.BOOK
//            "movie" -> Kind.MOVIE
//            "podcast" -> Kind.PODCAST
//            else -> Kind.BOOK
//        }
//    }
//
//    @TypeConverter
//    fun fromKindToString(kind: Kind?): String {
//        return when(kind) {
//            Kind.BOOK -> "audiobook"
//            Kind.PODCAST -> "podcast"
//            Kind.MOVIE -> "movie"
//            else -> "movie"
//        }
//    }
//}