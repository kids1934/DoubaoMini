package com.example.doubaomini.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.doubaomini.bean.ChatMessage;

import java.util.List;

public class BaseHomeVM extends ViewModel {

    public MutableLiveData<List<ChatMessage>> message = new MutableLiveData<>();


}
