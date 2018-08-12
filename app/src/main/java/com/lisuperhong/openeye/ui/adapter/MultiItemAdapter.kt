package com.lisuperhong.openeye.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.google.gson.Gson
import com.lisuperhong.openeye.mvp.model.bean.*
import com.lisuperhong.openeye.utils.Constant
import com.orhanobut.logger.Logger
import org.json.JSONException
import org.json.JSONObject

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/5 15:10
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class MultiItemAdapter(context: Context, datas: ArrayList<BaseBean.Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var context: Context? = null
    private var arrayList: ArrayList<BaseBean.Item>

    init {
        this.context = context
        this.arrayList = datas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return createMultiViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val gson = Gson()
        val data = arrayList[position]
        val dataMap = data.data as Map<*, *>
        var dataJson: JSONObject? = null
        try {
            dataJson = JSONObject(dataMap)
        } catch (e: JSONException) {
            Logger.d(e.printStackTrace())
        }
        when (holder) {
            is SquareCardItemHolder -> {
                Logger.d("SquareCardItemHolder called, position = $position")
                val itemList = dataMap["itemList"] as List<Map<*, *>>
                if (itemList[0]["type"] == "followCard") {
                    val squareCardCollection = gson.fromJson(dataJson.toString(), SquareCardCollection::class.java)
                    bindSquareCardItemHolder(context!!, squareCardCollection, holder)
                } else {
                    val squareCardCollection2 = gson.fromJson(dataJson.toString(), SquareCardCollection2::class.java)
                    bindSquareCardItemHolder(context!!, squareCardCollection2, holder)
                }
            }
            is TextCardItemHolder -> {
                Logger.d("TextCardItemHolder called, position = $position")
                val textCard = gson.fromJson(dataJson.toString(), TextCard::class.java)
                bindTextCardItemHolder(context!!, textCard, holder)
            }
            is FollowCardItemHolder -> {
                Logger.d("FollowCardItemHolder called, position = $position")
                val followCard = gson.fromJson(dataJson.toString(), FollowCard::class.java)
                if (position + 1 < itemCount) {
                    if (arrayList[position + 1].type == "followCard") {
                        bindFollowCardItemHolder(context!!, followCard, holder, true)
                    } else {
                        bindFollowCardItemHolder(context!!, followCard, holder, false)
                    }
                }
            }
            is VideoSmallCardItemHolder -> {
                Logger.d("VideoSmallCardItemHolder called, position = $position")
                val videoSmallCard = gson.fromJson(dataJson.toString(), VideoSmallCard::class.java)
                if (position + 1 < itemCount) {
                    if (arrayList[position + 1].type == "videoSmallCard") {
                        bindVideoSmallCardItemHolder(context!!, videoSmallCard, holder, true)
                    } else {
                        bindVideoSmallCardItemHolder(context!!, videoSmallCard, holder, false)
                    }
                }
            }
            is PictureFollowCardItemHolder -> {
                Logger.d("PictureFollowCardItemHolder called, position = $position")
                val pictureFollowCard = gson.fromJson(dataJson.toString(), PictureFollowCard::class.java)
                bindPictureFollowCardItemHolder(context!!, pictureFollowCard, holder)
            }
            is AutoPlayFollowCardItemHolder -> {
                Logger.d("AutoPlayFollowCardItemHolder called, position = $position")
                val autoPlayFollowCard = gson.fromJson(dataJson.toString(), AutoPlayFollowCard::class.java)
                bindAutoPlayFollowCardItemHolder(context!!, autoPlayFollowCard, holder)
            }
            is BannerItemHolder -> {
                Logger.d("BannerItemHolder called, position = $position")
                val banner = gson.fromJson(dataJson.toString(), Banner::class.java)
                bindBannerItemHolder(context!!, banner, holder)
            }
            is HorizontalScrollCardItemHolder -> {
                val horizontalScrollCard = gson.fromJson(dataJson.toString(), HorizontalScrollCard::class.java)
                bindHorizontalScrollCardItemHolder(context!!, horizontalScrollCard, holder)
            }
            is BriefCardItemHolder -> {
                val briefCard = gson.fromJson(dataJson.toString(), BriefCard::class.java)
                bindBriefCardItemHolder(context!!, briefCard, holder)
            }
            is VideoCollectionWithBriefItemHolder -> {
                val videoCollectionWithBrief = gson.fromJson(dataJson.toString(), VideoCollectionWithBrief::class.java)
                bindVideoCollectionWithBriefItemHolder(context!!, videoCollectionWithBrief, holder)
            }

            else -> {
                Logger.d("default TextCardItemHolder called, position = $position")
                val textCard = gson.fromJson(dataJson.toString(), TextCard::class.java)
                bindTextCardItemHolder(context!!, textCard, holder)
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item: BaseBean.Item = arrayList[position]
        return when (item.type) {
            "squareCardCollection" -> Constant.ITEM_TYPE_SQUARECARD

            "textCard" -> Constant.ITEM_TYPE_TEXTCARD

            "followCard" -> Constant.ITEM_TYPE_FLLOWCARD

            "videoSmallCard" -> Constant.ITEM_TYPE_VIDEOSMALLCARD

            "autoPlayFollowCard" -> Constant.ITEM_TYPE_AUTOPLAYFOLLOWCARD

            "banner" -> Constant.ITEM_TYPE_BANNER

            "banner2" -> Constant.ITEM_TYPE_BANNER

            "pictureFollowCard" -> Constant.ITEM_TYPE_PICTUREFOLLOWCARD

            "horizontalScrollCard" -> Constant.ITEM_TYPE_HORIZONTALSCROLLCARD

            "briefCard" -> Constant.ITEM_TYPE_BRIEFCARD

            "videoCollectionWithBrief" -> Constant.ITEM_TYPE_VIDEOCOLLECTIONWITHBRIEF

            else -> Constant.ITEM_TYPE_TEXTCARD
        }
    }

    private fun clearAll() = arrayList.clear()

    /**
     * 初始化或刷新数据
     */
    fun setRefreshData(datas: ArrayList<BaseBean.Item>) {
        notifyItemRangeRemoved(0, itemCount)
        clearAll()
        arrayList.addAll(datas)
        notifyItemRangeInserted(0, datas.size)
    }

    /**
     * 加载更多数据
     */
    fun setLoadMore(datas: ArrayList<BaseBean.Item>) {
        arrayList.addAll(datas)
        notifyItemRangeInserted(itemCount, datas.size)
    }
}