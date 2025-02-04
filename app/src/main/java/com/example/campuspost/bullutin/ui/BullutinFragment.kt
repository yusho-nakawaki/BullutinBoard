package com.example.campuspost.bullutin.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campuspost.R
import com.example.campuspost.bullutin.ui.adapter.BullutinAdapter
import com.example.campuspost.bullutin.utility.BullutinInjectorUtils
import com.example.campuspost.databinding.FragmentBullutinBinding


class BullutinFragment: Fragment() {

    private lateinit var bullutinViewModel: BullutinViewModel
    private lateinit var binding: FragmentBullutinBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var bullutinAdapter: BullutinAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewBinding()

        val layoutManager = LinearLayoutManager(view?.context,
                LinearLayoutManager.VERTICAL, false)
        binding.recycleviewBullutin.layoutManager = layoutManager
        binding.recycleviewBullutin.setHasFixedSize(true)

        val faculty = BullutinInjectorUtils.provideBullutinViewModelFaculty()
        bullutinViewModel = ViewModelProvider(this, faculty).get(BullutinViewModel::class.java)


        bullutinViewModel.getPosts()

    }

    fun initViewBinding() {
        binding = FragmentBullutinBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
    }


//    fun addPost(post: Bullutin) {
//        val faculty = BullutinInjectorUtils.provideBullutinViewModelFaculty()
//        val viewModel = ViewModelProvider(this, faculty)
//            .get(BullutinViewModel::class.java)
//        viewModel.addPost(post)
//    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bullutin, container, false)

        handlePosts()
        openItemBullutin()

        return view
    }



    private fun handlePosts() {
        // fragmentではobserve(this)ではなくviewLifecycleOwner
        bullutinViewModel.run {
            posts.observe(viewLifecycleOwner, {
                val bullutinRecyclerView =
                        view?.findViewById<RecyclerView>(R.id.recycleview_bullutin)!!
                bullutinAdapter = BullutinAdapter(view?.context!!, it, bullutinViewModel)
                viewManager = LinearLayoutManager(view?.context,
                        LinearLayoutManager.VERTICAL, false)
                recyclerView = bullutinRecyclerView.apply {
                    layoutManager = viewManager
                    adapter = bullutinAdapter
                }
            })
        }
    }


    // 画面遷移
    private fun openItemBullutin() {
        bullutinViewModel.openItemBullutin.observe(viewLifecycleOwner) {
            val intent = Intent(context, ItemBullutinActivity::class.java)
            intent.putExtra("item-post", it.message)
            startActivity(intent)
        }
    }



    // SectionsPageAdapterでインスタンス
    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): BullutinFragment {
            return BullutinFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}


