package com.chen.fakevibrato.ui.home.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.BaseActivity
import com.chen.fakevibrato.tangram.*
import com.chen.fakevibrato.ui.home.presenter.MainPresenter
import com.chen.fakevibrato.widget.glide.GlideApp
import com.tmall.wireless.tangram.TangramBuilder
import com.tmall.wireless.tangram.structure.viewcreator.ViewHolderCreator
import com.tmall.wireless.tangram.support.async.CardLoadSupport
import com.tmall.wireless.tangram.util.IInnerImageSetter
import com.chen.fakevibrato.tangram.CustomViewHolder
import com.chen.fakevibrato.tangram.CustomHolderCell
import com.chen.fakevibrato.utils.AssetsUtils
import com.chen.fakevibrato.tangram.CustomClickSupport
import com.chen.fakevibrato.utils.MyLog
import com.tmall.wireless.tangram.dataparser.concrete.Card
import com.tmall.wireless.tangram.support.async.AsyncLoader
import com.tmall.wireless.tangram.support.async.AsyncPageLoader
import com.tmall.wireless.vaf.framework.VafContext
import com.bumptech.glide.Glide
import com.chen.fakevibrato.widget.glide.ImageTarget
import com.tmall.wireless.vaf.virtualview.Helper.ImageLoader
import com.tmall.wireless.vaf.virtualview.view.image.ImageBase
import com.tmall.wireless.vaf.virtualview.event.EventData
import com.tmall.wireless.vaf.virtualview.event.IEventProcessor
import com.tmall.wireless.vaf.virtualview.event.EventManager




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
        builder.registerCell("CustomCell", CustomCell::class.java, CustomCellView::class.java)

        builder.registerCell<View>("HolderCell", CustomHolderCell::class.java,
                ViewHolderCreator(R.layout.item_holder_tangram, CustomViewHolder::class.java,
                        TextView::class.java))
        builder.registerCell("NoBackground", NoBackgroundView::class.java)

        // 注册 VirtualView 版本的 Tangram 组件
        builder.registerVirtualView<View>("VVTest");
        // 生成TangramEngine实例
        val mEngine = builder.build()
        // 加载VirtualView模板数据
//        mEngine.setVirtualViewTemplate(VVTEST.BIN);
        mEngine.setVirtualViewTemplate(AssetsUtils.getAssetsFile(this, "VVTest.out"))
        // 绑定业务 support 类到 engine
        // 处理点击
        mEngine.addSimpleClickSupport(CustomClickSupport())
        // 处理曝光
        mEngine.addExposureSupport(CustomExposureSupport())

        // 异步加载数据
        val cardLoadSupport = CardLoadSupport(object : AsyncLoader {
            override fun loadData(card: Card, @NonNull callback: AsyncLoader.LoadedCallback) {
                MyLog.d( TAG + "loadData: cardType=" + card.stringType)
            }
        }, object : AsyncPageLoader {
            override fun loadData(page: Int, card: Card, callback: AsyncPageLoader.LoadedCallback) {
                MyLog.d(TAG + "loadData: page=" + page + ", cardType=" + card.stringType)
            }
        })

        CardLoadSupport.setInitialPage(1)
        mEngine.addCardLoadSupport(cardLoadSupport)
        val vafContext = mEngine.getService(VafContext::class.java)

        vafContext.setImageLoaderAdapter(object : ImageLoader.IImageLoaderAdapter {
            override fun bindImage(uri: String, imageBase: ImageBase, reqWidth: Int, reqHeight: Int) {
                if (AssetsUtils.isValidContextForGlide(this@SearchActivity)) {
                    val requestBuilder = Glide.with(this@SearchActivity).asBitmap().load(uri)
                    if (reqWidth > 0 || reqHeight > 0) {
                        requestBuilder.submit(reqWidth, reqHeight)
                    }
                    val imageTarget = ImageTarget(imageBase)
                    requestBuilder.into(imageTarget)
                }
            }

            override fun getBitmap(uri: String, reqWidth: Int, reqHeight: Int,
                                   lis: ImageLoader.Listener) {
                if (AssetsUtils.isValidContextForGlide(this@SearchActivity)) {
                    val requestBuilder = Glide.with(this@SearchActivity).asBitmap().load(uri)
                    if (reqWidth > 0 || reqHeight > 0) {
                        requestBuilder.submit(reqWidth, reqHeight)
                    }
                    val imageTarget = ImageTarget(lis)
                    requestBuilder.into(imageTarget)
                }
            }
        })


        // 注册VirtualView事件处理器
        vafContext.eventManager.register(EventManager.TYPE_Click) { data ->
            Toast.makeText(this@SearchActivity, data.mVB.action, Toast.LENGTH_SHORT).show()
            true
        }
        vafContext.eventManager.register(EventManager.TYPE_Exposure) { data ->
            MyLog.d(TAG + "Exposure process: " + data.mVB.viewCache.componentData)
            true
        }
//        mEngine.register(SimpleClickSupport::class.java,  CustomClickSupport());
//        engine.register(CardLoadSupport::class.java,  XXCardLoadSupport());
//        engine.register(ExposureSupport::class.java,  XXExposureSuport());
    }
}