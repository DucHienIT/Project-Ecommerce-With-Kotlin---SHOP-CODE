package com.example.ecommerce.di

import android.app.Application
import android.content.SharedPreferences
import com.example.ecommerce.model.*
import com.example.ecommerce.network.client
import com.example.ecommerce.repository.*
import com.example.ecommerce.repository.datasource.*
import com.example.ecommerce.repository.impl.*
import com.example.ecommerce.ui.adapter.*
import com.example.ecommerce.ui.fragment.home.ImageLoading
import com.example.ecommerce.viewmodel.*
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        val appModules = module {
            single<ImageLoading> { ImageLoadingImpl() }
            single {
                client()
            }
            factory<SliderRepository> {
                SliderRepositoryImpl(
                    RemoteSliderDataSource(get()),
                    LocalSliderDataSource()
                )
            }
            factory<CategoryRepository> {
                CategoryRepositoryImpl(
                    RemoteCategoryDataSource(get())
                )
            }
            factory<CartRepository> {
                CartImpl(
                    RemoteCartDataSource(get())
                )
            }
            factory<AddCartRepository> {
                AddCartImpl(
                    RemoteAddCartDataSource(get())
                )
            }
            factory<SubCartRepository> {
                SubCartImpl(
                    RemoteSubCartDataSource(get())
                )
            }
            factory { (category: List<Category>) -> CategoryAdapter(category, get()) }
            factory { (software: List<Software>) -> AdapterCategoryDetail(software, get()) }
            factory { (category: List<Category>) -> CategoryListAdapter(category, get()) }
            factory { (software: List<Software>) -> AmazingAdapter(software, get()) }
            factory { (property: List<Property>) -> PropertySoftwareAdapter(property) }
            factory { (rating: List<Rating>) -> AdapterRatingSoftware(rating) }
            factory { (comment: List<Comment>) -> AdapterCommentSoftware(comment) }
            factory { (cart: List<CartItem>) -> CartListAdapter(cart) }
            factory<AmazingRepository> { AmazingRepositoryImpl(RemoteAmazingDataSource(get())) }
            factory<DetailSoftwareRepository> {
                DetailSoftwareRepositorylmpl(
                    RemoteDetailDataSource(
                        get()
                    )
                )
            }
            factory<OrderDetailRepository> {
                OrderDetailRepositorylmpl(
                    RemoteOrderDetailDataSource(
                        get()
                    )
                )
            }
            factory<DetailUserRepository> {
                DetailUserImpl(
                    RemoteDetailUserDataSource(
                        get()
                    )
                )
            }


            factory<AddFavoriteRepository> { AddFavoriteImpl(RemoteAddFavoriteDataSource(get())) }
            factory { (favoriteList: List<FavoriteList>) ->
                AdapterListFavorite(
                    favoriteList
                )
            }
            factory { (listOrder: List<Order>) ->
                AdapterListOrder(
                    listOrder
                )
            }
            factory { (listDataOrder: List<Data>) ->
                AdapterDataOrder(
                    listDataOrder
                )
            }

            factory<PropertySoftwareRepository> {
                PropertySoftwareImpl(
                    RemotePropertySoftwareDataSource(
                        get()
                    )
                )
            }
            factory<FavoriteListRepository> {
                FavoriteListImpl(
                    RemoteFavoriteListDataSource(
                        get()))
            }
            factory<ListOrderRepository> {
                ListOrderImpl(
                    RemoteListOrderDataSource(
                        get()))
            }
            factory<RatingSoftwareRepository> {
                RatingSoftwareRepositoryImpl(
                    RemoteRatingSoftwareDataSource(get())
                )
            }
            factory<CommentSoftwareRepository> {
                CommentSoftwareRepositoryImpl(
                    RemoteCommentSoftwareDataSource(get())
                )
            }
            factory<PriceSoftwareRepository> {
                PriceSoftwareRepositorylmpl(
                    RemotePriceSoftwareDataSource(get())
                )
            }
            factory<ComparisonListRepository> {
                ComparisonListImpl(
                    RemoteComparisonListDataSource(get())
                )
            }
            factory { ComparisonSoftwareListAdapter(get()) }
            factory { (property: List<Property>) -> ComparisonAdapter(property) }
            factory<LoginRepository> {
                LoginRepositorylmpl(
                    RemoteLoginDataSource(get()),
                    LocalLoginDataSource(get())
                )
            }
            factory<RegisterRepository> {
                RegisterRepositorylmpl(
                    RemoteRegisterDataSource(get()),
                    LocalRegisterDataSource(get())
                )
            }

            factory<CategoryDetailRepository> {
                CategoryDetailImpl(
                    RemoteCategoryDetailDataSource(get()),
                )
            }

            single<SharedPreferences> { this@App.getSharedPreferences("user_token", MODE_PRIVATE) }
            viewModel {
                HomeViewModel(get(), get(), get())
            }
            viewModel { (id: Int) ->
                DetailSoftwareViewModel(get(), get(), get(), id)
            }
            viewModel { (id: Int) ->
                OrderDetailViewModel(get(), id)
            }
            viewModel { (id: Int) ->
                PropertySoftwareViewModel(get(), id)
            }
            viewModel { (id: Int) ->
                PriceSoftwareViewModel(get(), id)
            }
            viewModel {
                SubCartViewModel(get())
            }
            viewModel {
                AddCartViewModel(get())
            }
            viewModel {
                CartViewModel(get())
            }
            viewModel { (id: Int) ->
                ComparisonListViewModel(get(), id)
            }

            viewModel {
                LoginViewModel(get())
            }
            viewModel {
                RegisterViewModel(get())
            }
            viewModel {
                AddFavoriteViewModel(get())
            }

            viewModel {
                FavoriteListViewModel(get())
            }
            viewModel {
                ListOrderViewModel(get())
            }
            viewModel { (id: Int) ->
                CategoryDetailViewModel(get(), id)
            }
            viewModel {
                DetailUserViewModel(get())
            }


        }
        startKoin {
            androidContext(this@App)
            modules(appModules)
        }

    }
}
