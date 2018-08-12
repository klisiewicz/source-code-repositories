package pl.karollisiewicz.common.reactive

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class ReactiveViewModel : ViewModel() {
    private val disposable = CompositeDisposable()

    protected fun Disposable.enqueueForDisposal() {
        disposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}