package com.stepasha.jsonparser

//my pojo

class Post {
    var postHeading: String? = null
    var postUrl: String? = null
    var postAuthor: String? = null
    var posts: List<String>? = null

    constructor(): super(){}
    constructor(postHeading: String, postUrl: String, postAuthor: String, posts: List<String>): super(){
        this.postHeading = postHeading
        this.postUrl = postUrl
        this.postAuthor = postAuthor
        this.posts = posts
    }
}