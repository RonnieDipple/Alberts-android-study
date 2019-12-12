package com.stepasha.retrofitgithubquery

import retrofit2.Call
import retrofit2.http.GET

interface GithubService {

    @GET("/repositories")
    fun getRepositories(): Call<RepoResult>

    @GET("/search/repositories?q=language:kotlin&sort=stars&order=desc")
    fun searchRepo():Call<RepoResult>
}