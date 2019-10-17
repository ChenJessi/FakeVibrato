package com.chen.fakevibrato

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import com.chen.fakevibrato.base.BaseFragment
import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter
import com.chen.fakevibrato.ui.home.presenter.MainPresenter
import com.qmuiteam.qmui.widget.QMUIViewPager
import kotlinx.android.synthetic.main.swipe_layout.*
import org.greenrobot.eventbus.EventBus
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