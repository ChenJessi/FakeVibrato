package com.chen.fakevibrato.ui.home.view

import android.widget.ImageView
import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.BaseActivity
import com.chen.fakevibrato.tangram.CustomAnnotationView
import com.chen.fakevibrato.tangram.CustomClickSupport
import com.chen.fakevibrato.tangram.CustomInterfaceView
import com.chen.fakevibrato.ui.home.presenter.MainPresenter
import com.chen.fakevibrato.widget.glide.GlideApp
import com.tmall.wireless.tangram.TangramBuilder
import com.tmall.wireless.tangram.support.ExposureSupport
import com.tmall.wireless.tangram.support.SimpleClickSupport
import com.tmall.wireless.tangram.support.async.CardLoadSupport
import com.tmall.wireless.tangram.util.IInnerImageSetter

/**
 * @author Created by CHEN on 2019/9/14
 * @email 188669@163.com
 * 搜索
 */
class SearchActivity : BaseActivity<MainPresenter>(){
    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initView() {

        initTangram()
    }

    override fun initListener() {

    }

    override fun initData() {
       
    }

    private fun initTangram(){

        TangramBuilder.init(this@SearchActivity, object : IInnerImageSetter {
            override fun <IMAGE : ImageView> doLoadImageUrl(view: IMAGE,
                                                            url: String?) {
                //加载图片
                GlideApp.with(this@SearchActivity).load(url).into(view)
            }
        }, ImageView::class.java)

        val builder = TangramBuilder.newInnerBuilder(this@SearchActivity)
        builder.registerCell("type", ImageView::class.java)

        builder.registerCell("InterfaceCell", CustomInterfaceView::class.java)
        builder.registerCell("AnnotationCell", CustomAnnotationView::class.java)
//        builder.registerCell("CustomCell", CustomCell::class.java, CustomCellView::class.java)
//        builder.registerCell("HolderCell", CustomHolderCell::class.java,
//                ViewHolderCreator(R.layout.item_holder, CustomViewHolder::class.java,
//                        TextView::class.java))
//        builder.registerCell("NoBackground", NoBackgroundView::class.java)





        val engine = builder.build()
        engine.register(SimpleClickSupport::class.java,  CustomClickSupport());
//        engine.register(CardLoadSupport::class.java,  XXCardLoadSupport());
//        engine.register(ExposureSupport::class.java,  XXExposureSuport());
    }
}