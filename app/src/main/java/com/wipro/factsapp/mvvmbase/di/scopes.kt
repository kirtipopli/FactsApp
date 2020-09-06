package com.wipro.factsapp.mvvmbase.di

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityScope


@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class FragmentScope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class BottomSheetFragmentScope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class DialogFragmentScope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class ViewModelScope