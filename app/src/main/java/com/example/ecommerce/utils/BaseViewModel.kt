package com.example.ecommerce.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerce.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * BaseViewModel là lớp abstract thực hiện các chức năng chung cho ViewModel
 * bao gồm việc quản lý các Disposable, progress bar, ...
 */
abstract class BaseViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable() // quản lý các disposable
    val progressbarLiveData = MutableLiveData<Boolean>() // LiveData để hiển thị progress bar
    override fun onCleared() {
        compositeDisposable.clear() // clear các Disposable khi ViewModel bị destroy
        super.onCleared()
    }
}

/**
 * Observer là lớp abstract thực hiện việc theo dõi Single và xử lý lỗi khi Single lỗi
 * @param compositeDisposable: quản lý các Disposable
 */
abstract class Observer<T>(private val compositeDisposable: CompositeDisposable) :
    SingleObserver<T> {
    override fun onSubscribe(d: Disposable?) {
        compositeDisposable.add(d!!)
    }

    override fun onError(e: Throwable?) {
        Log.i("LOG", e.toString()) // log lỗi khi Single lỗi
    }
}

/**
 * ViewProgress là interface chứa chung phương thức hiển thị progress bar
 */
interface ViewProgress {
    val root: CoordinatorLayout? // root view, thường là CoordinatorLayout
    val myContext: Context? // context của activity hoặc fragment
    fun progress(show: Boolean) {
        root?.let { view ->
            myContext?.let { context ->
                var progressbar = view.findViewById<View>(R.id.progress_bar)
                if (progressbar == null && show) {
                    progressbar = LayoutInflater.from(context)
                        .inflate(R.layout.progressbar, view, false)
                    view.addView(progressbar) // thêm progress bar vào view nếu progress bar chưa tồn tại
                }
                progressbar?.visibility = if (show) View.VISIBLE else View.GONE // hiển thị/ẩn progress bar
            }
        }
    }
}

/**
 * Fragment là lớp abstract thực hiện chung các chức năng của Fragment và implement ViewProgress
 */
abstract class Fragment : Fragment(), ViewProgress {
    override val root: CoordinatorLayout?
        get() = view as CoordinatorLayout // lấy root view, thường là CoordinatorLayout
    override val myContext: Context?
        get() = context // lấy context của Fragment
}

/**
 * singleHelper là extension function cho Single, thực hiện việc subscribe Single trên Schedulers.io()
 * và observe Single trên MainThread
 */
fun <T> Single<T>.singleHelper(): Single<T> {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
