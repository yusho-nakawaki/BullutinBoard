package com.example.campuspost.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.campuspost.R
import com.example.campuspost.bullutin.ui.InsertPostAcitivity
import com.example.campuspost.databinding.FragmentMainBinding
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import java.nio.channels.SelectableChannel

class MainFragment : Fragment() {

    private lateinit var sectionsPageAdapter: SectionsPagerAdapter
    private lateinit var viewPager: ViewPager2

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        inflater.inflate(R.layout.fragment_main, container, false)

        //activityの場合はonCreateでbindingのインスタンス生成
        //fragmentの場合はonCreateViewで作る
        //https://developer.android.com/topic/libraries/view-binding
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        

        openInsertPostActivity()

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sectionsPageAdapter = SectionsPagerAdapter(this)
        viewPager = binding.viewPager
        viewPager.adapter = sectionsPageAdapter


        // TabLayoutをViewPager2にリンク
        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.text = "page ${(position + 1)}"
        }.attach()
    }


    private fun openInsertPostActivity() {
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            val intent = Intent(context, InsertPostAcitivity::class.java)
            startActivityForResult(intent, 9)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode != 9) { return }

        if (resultCode == Activity.RESULT_OK && data != null) {
//            val message = data.getStringExtra("message")
//            println(message)
//            sectionsPagerAdapter.placeholderFragment.posts.add(Bullutin("email", "mirrative", "2021/05/03 12:00:00", "postId", "ここに投稿したメッセージが現れるよ。", null, null, null, 20, arrayOf("0_nil"), 10, arrayOf("0_nil"), 0))
        } else if(resultCode == Activity.RESULT_CANCELED) {

        }
    }




}