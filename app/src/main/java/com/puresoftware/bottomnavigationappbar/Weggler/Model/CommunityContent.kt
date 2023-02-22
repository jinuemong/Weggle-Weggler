package com.puresoftware.bottomnavigationappbar.Weggler.Model

import java.io.Serializable

//community 데이터

class CommunityContent (
    val postId : Int,
    val category : String,
    val name : String,
    val userId : String,
    val thumbnail:String="", //자동
    var resource : String="", //자동
    var body : MultiCommunityData,
    var likeCount : Int = 0,
    val commentCount : Int = 0,
    val createTime : String,
    val updateTime : String,
):Serializable