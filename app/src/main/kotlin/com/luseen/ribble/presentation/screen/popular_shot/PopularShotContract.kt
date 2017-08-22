package com.luseen.ribble.presentation.screen.popular_shot

import com.luseen.ribble.domain.entity.Shot
import com.luseen.ribble.presentation.base_mvp.base.BaseContract

/**
 * Created by Chatikyan on 01.08.2017.
 */
interface PopularShotContract {

    interface View : BaseContract.View {

        fun showLoading()

        fun hideLoading()

        fun showError()

        fun onShotListReceive(shotList: List<Shot>)
    }

    interface Presenter : BaseContract.Presenter<View>{

    }

}