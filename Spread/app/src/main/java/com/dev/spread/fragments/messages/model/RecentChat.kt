package com.dev.spread.fragments.messages.model

import java.io.Serializable

data class RecentChat(
    var receiver_image: Int? = null,
    var chat_image: Int? = null,
    var chat_body: String? = null,
    var time: String? = null,
    var is_sender: Boolean? = null,
    var is_deleted: Boolean? = null,
    var is_copy: Boolean? = null,
) : Serializable
