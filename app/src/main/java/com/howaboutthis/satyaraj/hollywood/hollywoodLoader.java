package com.howaboutthis.satyaraj.hollywood;

import java.util.List;
import android.content.AsyncTaskLoader;
import android.content.Context;

public class hollywoodLoader extends AsyncTaskLoader<List<ItemEvent>> {

    private String mUrl;
    private String mShow;

    hollywoodLoader(Context context, String url, String show) {
        super(context);
        mUrl = url;
        mShow = show;
    }

    @Override
    protected void onStartLoading(){
        forceLoad();
    }

    @Override
    public List<ItemEvent> loadInBackground() {
        if(mUrl==null)
            return null;

        return Utils.fetchNewsData(mUrl,mShow);
    }
}
