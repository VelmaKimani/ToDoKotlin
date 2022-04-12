package com.velma.todo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var userId: String? = null,
    var name: String? = null,
    var email: String? = null,
):Parcelable
