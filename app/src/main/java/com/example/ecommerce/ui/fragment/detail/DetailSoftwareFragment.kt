package com.example.ecommerce.ui.fragment.detail


import android.app.Activity
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.ui.adapter.AdapterCommentSoftware
import com.example.ecommerce.ui.adapter.AdapterRatingSoftware
import com.example.ecommerce.ui.adapter.SliderAdapterDetailSoftware
import com.example.ecommerce.utils.Fragment
import com.example.ecommerce.utils.TokenHolder
import com.example.ecommerce.viewmodel.AddCartViewModel
import com.example.ecommerce.viewmodel.AddFavoriteViewModel
import com.example.ecommerce.viewmodel.DetailSoftwareViewModel
import com.example.ecommerce.viewmodel.LoginViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_item.view.*

import kotlinx.android.synthetic.main.fragment_detail_product.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import www.sanju.motiontoast.MotionToast


class DetailSoftwareFragment : Fragment() {
    private val detailSoftwareViewModel: DetailSoftwareViewModel by viewModel { parametersOf(id) }
    private val loginViewModel: LoginViewModel by viewModel()
    private val addFavoriteViewModel: AddFavoriteViewModel by viewModel()
    private val addCartViewModel: AddCartViewModel by viewModel()
    private var args: DetailSoftwareFragmentArgs? = null
    var id: Int? = null
    val Software: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_product, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args = arguments?.let { DetailSoftwareFragmentArgs.fromBundle(it) }
        id = args?.software?.id

        println("$id")
        close_image.setOnClickListener {
            it.findNavController().popBackStack()
        }
        more_image.setOnClickListener {
            it.findNavController().navigate(
                DetailSoftwareFragmentDirections.actionDetailSoftwareFragmentToBottomSheetDialogFragment2(
                    args?.software!!
                )
            )

        }
        Picasso.get().load(args?.software?.image_url).into(image);

        favorite_image.setOnClickListener {
            if (loginViewModel.checkLoginStatus.value == false) {
                it.findNavController().navigate(R.id.action_detailSoftwareFragment_to_loginFragment2)
            } else {
                id?.let { it1 ->
                    addFavoriteViewModel.addFavorite(
                        it1,
                        "Bearer ${TokenHolder.access_token}"
                    )
                }
            }
        }
        add_cart.setOnClickListener{
            if (loginViewModel.checkLoginStatus.value == false) {
                it.findNavController().navigate(R.id.action_detailSoftwareFragment_to_loginFragment2)
            } else {
                id?.let { it1 ->
                    addCartViewModel.addCart(
                        it1,"Bearer ${TokenHolder.access_token}")
                }
                Toast.makeText(context,"Add successfully", Toast.LENGTH_SHORT).show()

            }
        }
        addFavoriteViewModel.addFavoriteLiveData.observe(viewLifecycleOwner) {
            if (it.is_favorite) {
                MotionToast.createToast(
                    requireContext() as Activity,
                    "Hurray success 😍",
                    "Add to favorite!",
                    MotionToast.TOAST_SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(requireContext() as Activity, R.font.helvetica_regular),
                )
                favorite_image.setImageResource(R.drawable.ic_round_favorite_24)
            }
            if (it.available == "Software is favorite") {
                MotionToast.createToast(
                    requireContext() as Activity,
                    "available ☹",
                    "Software is favorite !",
                    MotionToast.TOAST_WARNING,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(requireContext() as Activity, R.font.helvetica_regular),
                )
                favorite_image.setImageResource(R.drawable.ic_round_favorite_24)
            }
        }

        detailSoftwareViewModel.detailSoftwareLiveData.observe(viewLifecycleOwner) {
            (it.name).also { name -> title.text = name }
            (it.price).also { p -> price.text = p }
            (it.description).also { int -> introduction.text = int }


        }
        detailSoftwareViewModel.progressbarLiveData.observe(viewLifecycleOwner) {
            progress(it)
        }
        detailSoftwareViewModel.ratingSoftwareLiveData.observe(viewLifecycleOwner) {
            val adapterRatingSoftware: AdapterRatingSoftware by inject { parametersOf(it) }
            recyclerview_rating.adapter = adapterRatingSoftware
        }
        detailSoftwareViewModel.commentSoftwareLiveData.observe(viewLifecycleOwner) {
            val adapterCommentSoftware: AdapterCommentSoftware by inject { parametersOf(it) }
            recyclerview_comment.adapter = adapterCommentSoftware
        }
        technical_specifications.setOnClickListener {
            it.findNavController().navigate(
                DetailSoftwareFragmentDirections.actionDetailSoftwareFragmentToPropertyFragment(
                    args?.software!!
                )
            )
        }
        recyclerview_rating.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        recyclerview_comment.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }



    override fun onResume() {
        super.onResume()
        loginViewModel.checkLogin()
    }

}
