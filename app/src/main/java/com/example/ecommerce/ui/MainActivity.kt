package com.example.ecommerce.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.ecommerce.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.ecommerce.utils.setupWithNavController


class MainActivity : AppCompatActivity() {
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Nếu không có trạng thái được lưu trữ, thiết lập thanh điều hướng dưới cùng
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // Thiết lập lại thanh điều hướng dưới cùng nếu có trạng thái được lưu trữ
        setupBottomNavigationBar()
    }

    /**
     * Thiết lập thanh điều hướng dưới cùng và các Fragment tương ứng
     */
    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Danh sách ID của các đồ thị điều hướng của các Fragment
        val navGraphIds = listOf(
            R.navigation.account,
            R.navigation.cart,
            R.navigation.category,
            R.navigation.home
        )

        // Thiết lập thanh điều hướng dưới cùng và Fragment tương ứng cho từng mục
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        // Lưu trữ NavController hiện tại để có thể điều hướng trở lại
        currentNavController = controller
    }

    /**
     * Xử lý sự kiện khi nhấn nút Back trên thiết bị
     */
    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}
