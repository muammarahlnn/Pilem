package com.ardnn.pilem.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.ardnn.pilem.R
import com.ardnn.pilem.core.util.Helper
import com.ardnn.pilem.databinding.FragmentHomePagerBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomePagerFragment : Fragment() {

    private var _binding: FragmentHomePagerBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomePagerBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            // set action bar
            (activity as AppCompatActivity).apply {
                setSupportActionBar(binding?.toolbar?.root)
                supportActionBar?.setDisplayShowTitleEnabled(false)
            }

            // set pager
            val viewPager = binding?.viewpager as ViewPager2
            val pagerAdapter = HomePagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)
            viewPager.adapter = pagerAdapter

            // set tab layout
            val tabTitles = intArrayOf(
                R.string.now_playing,
                R.string.upcoming,
                R.string.popular,
                R.string.top_rated,
            )
            val tabLayout = binding?.tabs as TabLayout
            TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
                tab.text = getString(tabTitles[pos])
            }.attach()
            Helper.equalingEachTabWidth(tabLayout)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}