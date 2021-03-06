/*
 * Copyright (c) 2017 Lizhaotailang
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.github.tonnyl.mango.ui.user.following

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.tonnyl.mango.R
import kotlinx.android.synthetic.main.activity_common.*

/**
 * Created by lizhaotailang on 2017/8/3.
 *
 * Show the [io.github.tonnyl.mango.data.Followee]s that a [io.github.tonnyl.mango.data.User] is following.
 */
class FollowingActivity : AppCompatActivity() {

    private lateinit var mFollowingFragment: FollowingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra(FollowingPresenter.EXTRA_FOLLOWING_TITLE)

        savedInstanceState?.let {
            mFollowingFragment = supportFragmentManager.getFragment(it, FollowingFragment::class.java.simpleName) as FollowingFragment
        } ?: run {
            mFollowingFragment = FollowingFragment.newInstance()
        }

        if (!mFollowingFragment.isAdded) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, mFollowingFragment, FollowingFragment::class.java.simpleName)
                    .commit()
        }

        FollowingPresenter(mFollowingFragment, intent.getLongExtra(FollowingPresenter.EXTRA_USER_ID, 0L))
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (mFollowingFragment.isAdded) {
            supportFragmentManager.putFragment(outState, FollowingFragment::class.java.simpleName, mFollowingFragment)
        }
    }

}