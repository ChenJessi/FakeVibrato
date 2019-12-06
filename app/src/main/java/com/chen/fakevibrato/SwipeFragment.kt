package com.chen.fakevibrato

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter
import kotlinx.android.synthetic.main.swipe_layout.*
import java.util.ArrayList

class SwipeFragment : Fragment(){
    private var adapter: MyPagerAdapter? = null
    private val mFragments = ArrayList<Fragment>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.swipe_layout, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFragments.add(SwipeListFragment())
        mFragments.add(SwipeListFragment())
        mFragments.add(SwipeListFragment())
        mFragments.add(SwipeListFragment())
        mFragments.add(SwipeListFragment())
        adapter = MyPagerAdapter(childFragmentManager, mFragments)
        viewPager.adapter = adapter
        viewPager.setSwipeable(true)


    }
}