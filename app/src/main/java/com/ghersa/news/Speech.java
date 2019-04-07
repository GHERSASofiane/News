package com.ghersa.news;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.Locale;

public class Speech {

    private TextToSpeech mTTS;
    private Context context;
    private String pref_Langue;

    public Speech(Context c){
        this.context = c;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        pref_Langue = sharedPreferences.getString("pref_Langue", "fr");

        mTTS = new TextToSpeech(this.context, new TextToSpeech.OnInitListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = mTTS.setLanguage(Locale.forLanguageTag(pref_Langue));
                    if(result == TextToSpeech.LANG_MISSING_DATA
                        || result == TextToSpeech.LANG_NOT_SUPPORTED ){
                        Log.i("Speech","Langage Not Supperted");
                    }
                }else {
                    Log.i("Speech","Status ERRUR");
                }
            }
        });
    }

    public void speak(String str){
        mTTS.speak(str, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void Destroy(){
        if(mTTS != null){
            mTTS.stop();
            mTTS.shutdown();
        }
    }

}
