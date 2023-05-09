package com.example.ecommerce.network

import com.example.ecommerce.model.*
import com.example.ecommerce.utils.TokenHolder
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ApiService {
    // Lấy danh sách Slider
    @GET("home")
    fun home(): Single<List<Slider>>

    // Lấy danh sách Category
    @GET("home/category")
    fun category(): Single<List<Category>>

    // Lấy danh sách Product
    @GET("home/product")
    fun product(): Single<List<Product>>

    // Lấy chi tiết sản phẩm
    @GET("home/product/{id}/")
    fun detailProduct(@Path("id") id: Int): Single<ProductDetail>

    // Lấy danh sách Property của sản phẩm
    @GET("home/property/{id}/")
    fun propertyProduct(@Path("id") id: Int): Single<List<Property>>

    // Lấy danh sách Rating của sản phẩm
    @GET("home/rating/{id}/")
    fun ratingProduct(@Path("id") id: Int): Single<List<Rating>>

    // Lấy danh sách Price của sản phẩm
    @GET("home/price/")
    fun priceProduct(@Query("id") id: Int): Single<List<Price>>

    // Lấy danh sách sản phẩm so sánh
    @GET("home/comparison/{id}/")
    fun comparisonProduct(@Path("id") id: Int): Single<List<Product>>

    // Đăng nhập
    @FormUrlEncoded
    @POST("account/dj-rest-auth/login/")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Single<Login>

    // Đăng ký
    @FormUrlEncoded
    @POST("account/dj-rest-auth/registration/")
    fun register(
        @Field("username") username: String,
        @Field("password1") password1: String,
        @Field("password2") password2: String
    ): Single<Login>

    // Thêm sản phẩm yêu thích
    @POST("account/addfavorite/{id}/")
    fun addFavorite(
        @Path("id") id: Int,
        @Header("Authorization") access_token: String
    ): Single<AddFavorite>

    // Lấy danh sách sản phẩm yêu thích
    @POST("account/listfavorite/")
    fun listFavorite(
        @Header("Authorization") access_token: String
    ): Single<List<FavoriteList>>

    // Lấy danh sách sản phẩm thuộc Category
    @GET("home/categorydetail/{id}/")
    fun categoryDetail(@Path("id") id: Int): Single<List<Product>>

    // Thêm sản phẩm vào giỏ hàng
    @POST("cart/add/{id}/{count}/{price}/")
    fun addCart(
        @Header("Authorization") access_token: String,
        @Path("id") id: Int,
        @Path("count") count: Int,
        @Path("price") price: Int,
    ): Single<List<AddCart>>
}


fun client(): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://android-api-git-crt-20110233-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}