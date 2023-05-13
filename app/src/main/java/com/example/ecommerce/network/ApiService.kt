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
    @GET("divine/categories")
    fun category(): Single<List<Category>>

    // Lấy danh sách Software
    @GET("/divine/softwares/")
    fun Software(): Single<List<Software>>

    // Lấy chi tiết sản phẩm
    @GET("/divine/software/{id}/")
    fun detailSoftware(@Path("id") id: Int): Single<SoftwareDetail>

    @GET("/divine/comments/{id}/")
    fun commentSoftware(@Path("id") id: Int): Single<List<Comment>>



    // Lấy danh sách Property của sản phẩm
    @GET("home/property/{id}/")
    fun propertySoftware(@Path("id") id: Int): Single<List<Property>>

    // Lấy danh sách Rating của sản phẩm
    @GET("home/rating/{id}/")
    fun ratingSoftware(@Path("id") id: Int): Single<List<Rating>>

    // Lấy danh sách Price của sản phẩm
    @GET("home/price/")
    fun priceSoftware(@Query("id") id: Int): Single<List<Price>>

    @GET("divine/keyCode/")
    fun keyCode(@Query("id") id: Int): Single<Keycode>
    // Lấy danh sách sản phẩm so sánh
    @GET("home/comparison/{id}/")
    fun comparisonSoftware(@Path("id") id: Int): Single<List<Software>>

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
    @GET("/divine/software/category/{id}/")
    fun categoryDetail(@Path("id") id: Int): Single<List<Software>>

    // Danh sách giỏ hàng
    @GET("/divine/cart/")
    fun cart(
        @Header("Authorization") access_token: String
    ): Single<List<CartItem>>


    // Thêm sản phẩm vào giỏ hàng( Cần bổ xung )
    @POST("/divine/cart/add/{id}/")
    fun addCart(
        @Path("id") id: Int,
        @Header("Authorization") access_token: String
    ): Single<AddCartMessage>

    // Xóa sản phẩm vào giỏ hàng( Cần bổ xung )
    @POST("/divine/cart/sub/{id}/")
    fun subCart(
        @Path("id") id: Int,
        @Header("Authorization") access_token: String
    ): Single<AddCartMessage>

    @POST("/divine/order/")
    fun order(
        @Header("Authorization") access_token: String
    ): Single<Order>

    @GET("/divine/orders/{id}/")
    fun orderDetail(
        @Path("id") id: Int,
        @Header("Authorization") access_token: String
    ): Single<OrderDetail>

    @GET("/divine/order/list/")
    fun listOrder(
        @Header("Authorization") access_token: String
    ): Single<List<Order>>

    @GET("/account/dj-rest-auth/user/")
    fun detailUser(
        @Header("Authorization") access_token: String
    ): Single<User>
}

fun client(): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://3.27.16.111/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}
