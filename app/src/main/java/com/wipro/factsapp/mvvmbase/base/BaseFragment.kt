package com.wipro.factsapp.mvvmbase.base

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.wipro.factsapp.R
import com.wipro.factsapp.application.FactsApplication
import com.wipro.factsapp.mvvmbase.di.components.DaggerFragmentComponent
import com.wipro.factsapp.mvvmbase.di.components.FragmentComponent
import com.wipro.factsapp.mvvmbase.di.modules.FragmentModule
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    var snackbar: Snackbar? = null

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildFragmentComponent())
        super.onCreate(savedInstanceState)
        setupObservers()
        viewModel.onCreate()
    }

    private fun buildFragmentComponent() =
        DaggerFragmentComponent
            .builder()
            .applicationComponent((context!!.applicationContext as FactsApplication).applicationComponent)
            .fragmentModule(FragmentModule(this))
            .build()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(provideLayoutId(), container, false)

    protected open fun setupObservers() {
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    fun showMessage(message: String) = context?.let { Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))


    open fun showSnackBarMessage(
        view: View?,
        msg: String?,
        wantHide: Boolean
    ) {
        snackbar = Snackbar
            .make(view!!, msg!!, Snackbar.LENGTH_INDEFINITE)
        // Changing message text color
        snackbar?.setActionTextColor(ContextCompat.getColor(this!!.context!!, R.color.colorWhite))
        snackbar?.setTextColor(ContextCompat.getColor(this!!.context!!, R.color.colorWhite))
        snackbar?.show()
        if (wantHide) {
            object : CountDownTimer(2000, 1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    if (snackbar != null) snackbar?.dismiss()
                }
            }.start()
        }
    }

    open fun snackBarAlreadyVisible(): Boolean? {
        return if (snackbar != null) snackbar?.isShown else false
    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun injectDependencies(fragmentComponent: FragmentComponent)

    protected abstract fun setupView(view: View)
}